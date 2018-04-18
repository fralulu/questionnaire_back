package com.infore.common.hbase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.BufferedMutator;
import org.apache.hadoop.hbase.client.BufferedMutatorParams;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.RetriesExhaustedWithDetailsException;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.PageFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hbase ddl dml 操作类 Created by xuyao on 2017/9/6.
 */
public class HbaseOpt {

  private static Logger log = LoggerFactory.getLogger(HbaseOpt.class);

  public static class putobj extends Put {

    public putobj(String row) {
      super(Bytes.toBytes(row));
    }

    //进行数据插入
    public void addColumn(String family, String column, String value) {
      super.addColumn(Bytes.toBytes(family), Bytes.toBytes(column), Bytes.toBytes(value));
    }
  }

  /**
   * 创建表
   *
   * @param columnFamilies 列族
   * @return 返回执行时间 xx/毫秒
   */
  public static long createTable(Connection connection, String table,
      String... columnFamilies)
      throws Exception {
    long currentTime = System.currentTimeMillis();
    Admin admin = null;
    try {
      admin = connection.getAdmin();
      TableName tableName = TableName.valueOf(table);
      if (admin.tableExists(tableName)) {
        log.warn("table:{} exists!", new String(tableName.getName()));
      } else {
        HTableDescriptor tableDescriptor = new HTableDescriptor(tableName);
        for (String columnFamily : columnFamilies) {
          tableDescriptor.addFamily(new HColumnDescriptor(columnFamily));
        }
        admin.createTable(tableDescriptor);
        log.info("create table:{} success!", new String(tableName.getName()));
      }
    } finally {
      closeAdmin(admin);
    }
    return System.currentTimeMillis() - currentTime;
  }

  /**
   * 给table,新增列族
   *
   * @param connection
   * @param table
   * @param columnFamilies
   * @return 返回执行时间 xx/毫秒
   * @throws Exception
   */
  public static long addTableColumn(Connection connection, String table, String... columnFamilies)
      throws Exception {
    long currentTime = System.currentTimeMillis();
    Admin admin = null;
    try {
      admin = connection.getAdmin();
      TableName tableName = TableName.valueOf(table);
      if (!admin.tableExists(tableName)) {
        log.warn("table:{} not exists!", new String(tableName.getName()));
      } else {
        for (String columnFamily : columnFamilies) {
          admin.addColumn(tableName,new HColumnDescriptor(columnFamily));
          log.info("add columnFamily:{} success!", columnFamily);
        }
      }
    } finally {
      closeAdmin(admin);
    }
    return System.currentTimeMillis() - currentTime;
  }

  /**
   * 删除表中的列族数据
   *
   * @param connection
   * @param table
   * @param columnFamilies
   * @return 返回执行时间 xx/毫秒
   * @throws Exception
   */
  public static long delTableColumn(Connection connection, String table, String... columnFamilies)
      throws Exception {
    long currentTime = System.currentTimeMillis();
    Admin admin = null;
    try {
      admin = connection.getAdmin();
      TableName tableName = TableName.valueOf(table);
      if (!admin.tableExists(tableName)) {
        log.warn("table:{} not exists!", new String(tableName.getName()));
      } else {
        for (String columnFamily : columnFamilies) {
          admin.deleteColumn(tableName,Bytes.toBytes(columnFamily));
          log.info("delete columnFamily:{} success!", columnFamily);
        }
      }
    } finally {
      closeAdmin(admin);
    }
    return System.currentTimeMillis() - currentTime;
  }

    /**
     * 单条数据插入，默认 latest_timestamp
     *
     * eg. 列名是由它的列族前缀和修饰符(qualifier)连接而成, contents:html 由列族contents加冒号(:)加 修饰符(qualifier) html组成的
     *
     * @param row 每条记录的“主键”，方便快速查找
     * @param columnFamily 列族
     * @param column 属于某一个columnfamily
     * @param value 存储单元格Cell对应的实际的值Value
     * @return 返回执行时间 xx/毫秒
     */
  public static long put(Connection connection, String tableName,
      String row, String columnFamily, String column, String value) throws Exception {
    long currentTime = System.currentTimeMillis();
    Table table = null;
    try {
      table = getTable(connection, tableName);
      Put put = new Put(Bytes.toBytes(row));
      put.addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(column), Bytes.toBytes(value));
      table.put(put);
    } finally {
      closeTable(table);
    }
    return System.currentTimeMillis() - currentTime;
  }

  /**
   * 异步往指定表添加数据
   *
   * @param tablename 表名
   * @param puts 需要添加的数据
   * @return long                返回执行时间
   */
  public static long put(Connection connection, String tablename, List<Put> puts) throws Exception {
    long currentTime = System.currentTimeMillis();
    final BufferedMutator.ExceptionListener listener = new BufferedMutator.ExceptionListener() {
      @Override
      public void onException(RetriesExhaustedWithDetailsException e, BufferedMutator mutator) {
        for (int i = 0; i < e.getNumExceptions(); i++) {
//          System.out.println("Failed to sent put " + e.getRow(i) + ".");
          log.error("Failed to sent put " + e.getRow(i) + ".");
        }
      }
    };
    BufferedMutatorParams params = new BufferedMutatorParams(TableName.valueOf(tablename))
        .listener(listener);
    params.writeBufferSize(5 * 1024 * 1024);

    final BufferedMutator mutator = connection.getBufferedMutator(params);
    try {
      mutator.mutate(puts);
      mutator.flush();
    } finally {
      mutator.close();
    }
    return System.currentTimeMillis() - currentTime;
  }

  /**
   * 异步往指定表添加数据
   *
   * @param tablename 表名
   * @param put 需要添加的数据
   * @return long                返回执行时间
   */
  public static long put(Connection connection, String tablename, Put put) throws Exception {
    return put(connection, tablename, Arrays.asList(put));
  }

  /**
   * 批量，往指定表添加数据.同步操作
   *
   * @param tablename 表名
   * @param puts 需要添加的数据
   * @return long                返回执行时间
   */
  public static long putByHTable(Connection connection, String tablename, List<Put> puts)
      throws Exception {
    long currentTime = System.currentTimeMillis();
    HTable htable = (HTable) getTable(connection, tablename);
    htable.setAutoFlushTo(false);//关闭自动提交
    htable.setWriteBufferSize(5 * 1024 * 1024);//writeBuffer设置为5M
    try {
      htable.put((List<Put>) puts);
      htable.flushCommits();
    } finally {
      closehHTable(htable);
    }
    return System.currentTimeMillis() - currentTime;
  }

  /**
   * 在指定表中查询所有数据(全表扫描)
   *
   * @return ResultScanner
   */
  public static ResultScanner scan(Connection connection, String tableName) throws Exception {
    Table table = null;
    try {
      table = getTable(connection, tableName);
      ResultScanner rs = null;
      //Scan scan = new Scan(Bytes.toBytes("u120000"), Bytes.toBytes("u200000"));
      rs = table.getScanner(new Scan());

      /*
      // 两种操作方式之：1
      for (Result ret : rs) {
        System.out.println("该行数据的RowKey为：" + new String(ret.getRow()));
        for (Cell cell : ret.rawCells()) {
          System.out.println("列族:" + new String(CellUtil.cloneFamily(cell)) + "\t" +
              "列修饰符：" + new String(CellUtil.cloneQualifier(cell)) + "\t" +
              "值:" + new String(CellUtil.cloneValue(cell)) + "\t" +
              "时间戳:" + cell.getTimestamp());
        }
        // 两种操作方式之：2
        for (Result r : rs) {
          NavigableMap<byte[], NavigableMap<byte[], NavigableMap<Long, byte[]>>> navigableMap = r
              .getMap();
          for (Map.Entry<byte[], NavigableMap<byte[], NavigableMap<Long, byte[]>>> entry : navigableMap
              .entrySet()) {
            log.info("row:{} key:{}", Bytes.toString(r.getRow()), Bytes.toString(entry.getKey()));
            NavigableMap<byte[], NavigableMap<Long, byte[]>> map = entry.getValue();
            for (Map.Entry<byte[], NavigableMap<Long, byte[]>> en : map.entrySet()) {
              System.out.print(Bytes.toString(en.getKey()) + "##");
              NavigableMap<Long, byte[]> ma = en.getValue();
              for (Map.Entry<Long, byte[]> e : ma.entrySet()) {
                System.out.print(e.getKey() + "###");
                System.out.println(Bytes.toString(e.getValue()));
              }
            }
          }
        }
        */
      return rs;
    } finally {
      closeTable(table);
    }
  }


  /**
   * 分页检索表数据。<br> （如果在创建表时为此表指定了非默认的命名空间，则需拼写上命名空间名称，格式为【namespace:tablename】）。
   *
   * @param tableName 表名称(*)。
   * @param startRowKey 起始行键(可以为空，如果为空，则从表中第一行开始检索)。
   * @param endRowKey 结束行键(可以为空)。
   * @param filterList 检索条件过滤器集合(不包含分页过滤器；可以为空)。
   * @param maxVersions 指定最大版本数【如果为最大整数值，则检索所有版本；如果为最小整数值，则检索最新版本；否则只检索指定的版本数】。
   * @param pageModel 分页模型(*)。
   * @return 返回HBasePageModel分页对象。
   */
  public static HBasePageModel scanResultByPageFilter(Connection connection, String tableName,
      String startRowKey,
      String endRowKey, FilterList filterList, int maxVersions, HBasePageModel pageModel)
      throws Exception {
    if (pageModel == null) {
      pageModel = new HBasePageModel(10);
    }
    if (maxVersions <= 0) {
      //默认只检索数据的最新版本
      maxVersions = Integer.MIN_VALUE;
    }
    pageModel.initStartTime();
    pageModel.initEndTime();

    byte[] startRow = null;
    byte[] endRow = null;

    if (StringUtils.isNotBlank(startRowKey)) {
      startRow =Bytes.toBytes(startRowKey);
    }
    if (StringUtils.isNotBlank(endRowKey)) {
      endRow =Bytes.toBytes(endRowKey);
    }

    if (StringUtils.isBlank(tableName)) {
      return pageModel;
    }
    Table table = null;

    try {
      table = getTable(connection, tableName);
      int tempPageSize = pageModel.getPageSize();
      boolean isEmptyStartRowKey = false;
      if (startRow == null) {
        //则读取表的第一行记录
        Result firstResult = selectFirstResultRow(connection, tableName, filterList);
        if (firstResult.isEmpty()) {
          return pageModel;
        }
        startRow = firstResult.getRow();
      }
      if (pageModel.getPageStartRowKey() == null) {
        isEmptyStartRowKey = true;
        pageModel.setPageStartRowKey(startRow);
      } else {
        if (pageModel.getPageEndRowKey() != null) {
          pageModel.setPageStartRowKey(pageModel.getPageEndRowKey());
        }
        //从第二页开始，每次都多取一条记录，因为第一条记录是要删除的。
        tempPageSize += 1;
      }

      Scan scan = new Scan();
      scan.setStartRow(pageModel.getPageStartRowKey());
      if (endRow != null) {
        scan.setStopRow(endRow);
      }
      PageFilter pageFilter = new PageFilter(pageModel.getPageSize() + 1);
      if (filterList != null) {
        filterList.addFilter(pageFilter);
        scan.setFilter(filterList);
      } else {
        scan.setFilter(pageFilter);
      }
      if (maxVersions == Integer.MAX_VALUE) {
        scan.setMaxVersions();
      } else if (maxVersions == Integer.MIN_VALUE) {

      } else {
        scan.setMaxVersions(maxVersions);
      }
      ResultScanner scanner = table.getScanner(scan);
//      showScan(scanner);

      List<Result> resultList = new ArrayList<Result>();
      int index = 0;
      for (Result rs : scanner.next(tempPageSize)) {
        if (isEmptyStartRowKey == false && index == 0) {
          index += 1;
          continue;
        }
        if (!rs.isEmpty()) {
          resultList.add(rs);
        }
        index += 1;
      }
      scanner.close();
      pageModel.setResultList(resultList);
    } finally {
      closeTable(table);
    }

    //pagemodel opt[
    int pageIndex = pageModel.getPageIndex() + 1;
    pageModel.setPageIndex(pageIndex);
    if (pageModel.getResultList().size() > 0) {
      //获取本次分页数据首行和末行的行键信息
      byte[] pageStartRowKey = pageModel.getResultList().get(0).getRow();
      byte[] pageEndRowKey = pageModel.getResultList().get(pageModel.getResultList().size() - 1)
          .getRow();
      pageModel.setPageStartRowKey(pageStartRowKey);
      pageModel.setPageEndRowKey(pageEndRowKey);
    }
    int queryTotalCount = pageModel.getQueryTotalCount() + pageModel.getResultList().size();
    pageModel.setQueryTotalCount(queryTotalCount);
    pageModel.initEndTime();
    pageModel.printTimeInfo();
    return pageModel;
  }

  private static void showScan(ResultScanner rs) {
    for (Result ret : rs) {
      System.out.println("该行数据的RowKey为：" + new String(ret.getRow()));
      for (Cell cell : ret.rawCells()) {
        System.out.println("列族:" + new String(CellUtil.cloneFamily(cell)) + "\t" +
            "列修饰符：" + new String(CellUtil.cloneQualifier(cell)) + "\t" +
            "值:" + new String(CellUtil.cloneValue(cell)) + "\t" +
            "时间戳:" + cell.getTimestamp());
      }
    }
  }

  /**
   * 检索指定表的第一行记录。<br> （如果在创建表时为此表指定了非默认的命名空间，则需拼写上命名空间名称，格式为【namespace:tablename】）。
   *
   * @param tableName 表名称(*)。
   * @param filterList 过滤器集合，可以为null。
   */
  public static Result selectFirstResultRow(Connection connection, String tableName,
      FilterList filterList) throws Exception {
    if (StringUtils.isBlank(tableName)) {
      return null;
    }
    Table table = null;
    try {
      table = getTable(connection, tableName);
      Scan scan = new Scan();
      if (filterList != null) {
        scan.setFilter(filterList);
      }
      ResultScanner scanner = table.getScanner(scan);
      Iterator<Result> iterator = scanner.iterator();
      int index = 0;
      while (iterator.hasNext()) {
        Result rs = iterator.next();
        if (index == 0) {
          scanner.close();
          return rs;
        }
      }
    } finally {
      closeTable(table);
    }
    return null;
  }

  /**
   * 获取单条数据
   *
   * @param row 行主键
   */
  public static Result getRow(Connection connection, String tablename, String row)
      throws Exception {
    Table table = getTable(connection, tablename);
    Result rs = null;
    if (table != null) {
      try {
        Get g = new Get(Bytes.toBytes(row));
        rs = table.get(g);
      } finally {
        closeTable(table);
      }
    }
    return rs;
  }

  /**
   * 获取多行数据
   */
  public static <T> Result[] getRows(Connection connection, String tablename, List<T> rows)
      throws Exception {
    List<Get> gets = null;
    Result[] results = null;
    if (CollectionUtils.isEmpty(rows)) {
      return results;
    }
    Table table = getTable(connection, tablename);

    try {
      if (table != null) {
        gets = new ArrayList<Get>();
        for (T row : rows) {
          gets.add(new Get(Bytes.toBytes(String.valueOf(row))));
        }
      }
      if (gets.size() > 0) {
        results = table.get(gets);
      }
    } finally {
      closeTable(table);
    }
    return results;
  }


  /**
   * 删除表中所有数据
   *
   * @return 返回执行时间
   */
  public static long deleteTable(Connection connection, String tableName) throws Exception {
    long currentTime = System.currentTimeMillis();
    Admin admin = null;
    try {
      admin = connection.getAdmin();
      TableName table = TableName.valueOf(tableName);
      if (admin.tableExists(table)) {
        //必须先disable, 再delete
        admin.disableTable(table);
        admin.deleteTable(table);
      }
    } finally {
      closeAdmin(admin);
    }
    return System.currentTimeMillis() - currentTime;
  }

  /**
   * 删除rowkey数据
   *
   * @return 返回执行时间
   */
  public static long deleteRow(Connection connection, String tablename, String row)
      throws Exception {
    long currentTime = System.currentTimeMillis();
    Table table = getTable(connection, tablename);
    if (table != null) {
      try {
        Delete d = new Delete(row.getBytes());
        table.delete(d);
      } finally {
        closeTable(table);
      }
    }
    return System.currentTimeMillis() - currentTime;
  }

  /**
   * 删除rowkey中指定列族下的column
   *
   * @param connection
   * @param tablename
   * @param rowKey
   * @param colunmFamily
   * @param qualifier
   * @return 返回执行时间
   * @throws Exception
   */
  public static long delColumnualifier(Connection connection,String tablename,String rowKey,String colunmFamily,String qualifier)
      throws Exception {
    long currentTime = System.currentTimeMillis();
    Table table = getTable(connection, tablename);
    if (table != null) {
      try {
      Delete delete = new Delete(Bytes.toBytes(rowKey));
      delete.addColumns(Bytes.toBytes(colunmFamily), Bytes.toBytes(qualifier));
      table.delete(delete);
      log.info("delColumnualifier rowKey:{},colunmFamily:{},qualifier:{} success",rowKey,colunmFamily,qualifier);
      } finally {
        closeTable(table);
      }
    }
    return System.currentTimeMillis() - currentTime;
  }

  /**
   * 删除多行数据
   *
   * @return 返回执行时间
   */
  public static long deleteRows(Connection connection, String tablename, String[] rows)
      throws Exception {
    long currentTime = System.currentTimeMillis();
    Table table = getTable(connection, tablename);
    if (table != null) {
      try {
        List<Delete> list = new ArrayList<Delete>();
        for (String row : rows) {
          Delete d = new Delete(row.getBytes());
          list.add(d);
        }
        if (list.size() > 0) {
          table.delete(list);
        }
      } finally {
        closeTable(table);
      }
    }
    return System.currentTimeMillis() - currentTime;
  }

  /**
   * 获取  Table
   *
   * @param tableName 表名
   */
  public static Table getTable(Connection connection, String tableName) throws Exception {
    return connection.getTable(TableName.valueOf(tableName));
  }


  /**
   * 全局hbase连接 释放
   * @param conn
   */
  public static void releaseConnect(Connection conn) {
    if (null != conn) {
      try {
        conn.close();
      } catch (Exception e) {
        log.error("close Connect failed:{}", e);
      }
    }
  }

  /**
   * 关闭table
   */
  public static void closeTable(Table table) {
    if (table != null) {
      try {
        table.close();
      } catch (IOException e) {
        log.info("close table failed:{}", e);
      }

    }
  }

  /**
   * 关闭htable
   */
  public static void closehHTable(HTable htable) {
    if (htable != null) {
      try {
        htable.close();
      } catch (IOException e) {
        log.info("close htable failed:{}", e);
      }

    }
  }

  /**
   *
   */
  public static void closeAdmin(Admin admin) {
    if (admin != null) {
      try {
        admin.close();
      } catch (IOException e) {
        log.info("close admin failed:{}", e);
      }
    }
  }

  /**
   */
  public static void closeResultScanner(ResultScanner rs) {
    if (rs != null) {
      rs.close();
    }
  }
}
