package com.infore.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HConnectionManager;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;

/**
 * Created by xuyao on 2017/8/30.
 */
public class HBaseTest {
  private static final String TABLE_NAME = "students";
//  private static final String TABLE_NAME = "test";
  private static final String FAMILY_COL_SCORE = "score";
  private static final String FAMILY_COL_INFO = "info";

  private Configuration conf;

  public static void main(String[] args) {

    HBaseTest test = new HBaseTest();
    test.init();
    test.createTable();
//    test.insertData();
//    test.scanTable();
//    test.queryByRowKey();
//        test.deleteRow();
//        test.deleteFamily();
//        test.deleteTable();
  }

  /**
   * 初始化hbase的配置信息,也可以将hbase的配置文件hbase-site.xml引入项目，则不需要在代码中填写配置信息
   */
  public void init() {

    conf = HBaseConfiguration.create();
    conf.set("hbase.zookeeper.quorum", "192.168.31.91");
    conf.set("hbase.zookeeper.property.clientPort", "2181");
  }

  /**
   * 创建表
   */
  public void createTable() {

    Connection conn = null;

    try {
      conn = ConnectionFactory.createConnection(conf);
      HBaseAdmin admin = (HBaseAdmin) conn.getAdmin();
      System.out.println("here");
      HTableDescriptor desc = new HTableDescriptor(TableName.valueOf(TABLE_NAME));
      System.out.println("here1");
      desc.addFamily(new HColumnDescriptor(FAMILY_COL_SCORE));
      desc.addFamily(new HColumnDescriptor(FAMILY_COL_INFO));
      System.out.println("here2!");
      if (admin.tableExists(TABLE_NAME)) {
        System.out.println("table " + TABLE_NAME + " is exists !");
        System.exit(0);
      } else{
        admin.createTable(desc);
        System.out.println("table " + TABLE_NAME + " created successfully.");
      }
    } catch (IOException e) {
      System.out.println("here3");
      e.printStackTrace();
    } finally {
      if ( conn != null) {
        try {
          conn.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  /**
   * 在指定表中插入数据
   */
  public void insertData() {

    Connection conn = null;
    HTable table = null;


    try {

      conn = ConnectionFactory.createConnection(conf);
      table = (HTable) conn.getTable(TableName.valueOf(TABLE_NAME));
      List<Put> puts = new ArrayList<Put>();
      // 添加数据，一个Put代表一行，构造函数传入的是RowKey
      Put put1 = new Put(Bytes.toBytes("001:carl"));
      put1.addColumn(Bytes.toBytes(FAMILY_COL_SCORE), Bytes.toBytes("english"), Bytes.toBytes("97"));
      put1.addColumn(Bytes.toBytes(FAMILY_COL_SCORE), Bytes.toBytes("math"), Bytes.toBytes("99"));
      put1.addColumn(Bytes.toBytes(FAMILY_COL_INFO), Bytes.toBytes("weight"), Bytes.toBytes("130"));
      puts.add(put1);
      Put put2 = new Put(Bytes.toBytes("002:sophie"));
      put2.addColumn(Bytes.toBytes(FAMILY_COL_SCORE), Bytes.toBytes("english"), Bytes.toBytes("100"));
      put2.addColumn(Bytes.toBytes(FAMILY_COL_SCORE), Bytes.toBytes("math"), Bytes.toBytes("92"));
      put2.addColumn(Bytes.toBytes(FAMILY_COL_INFO), Bytes.toBytes("weight"), Bytes.toBytes("102"));
      puts.add(put2);
      // 将数据加入表
      table.put(puts);

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (table != null) {
        try {
          table.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      if (conn != null) {
        try {
          conn.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  /**
   * 在指定表中查询所有数据(全表扫描)
   */
  public void scanTable() {

    Connection conn = null;
    HTable table = null;
    ResultScanner scann = null;

    try {

      conn = ConnectionFactory.createConnection(conf);
      table = (HTable) conn.getTable(TableName.valueOf(TABLE_NAME));
      scann = table.getScanner(new Scan());
      for (Result rs : scann) {
        System.out.println("该行数据的RowKey为："+new String(rs.getRow()));
        for (Cell cell : rs.rawCells()) {
          System.out.println("列族:" + new String(CellUtil.cloneFamily(cell)) + "\t" +
              "列修饰符：" + new String(CellUtil.cloneQualifier(cell)) + "\t" +
              "值:" + new String(CellUtil.cloneValue(cell)) + "\t" +
              "时间戳:" + cell.getTimestamp());
        }
        System.out.println("-----------------------------------------------");
      }

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (table != null) {
        try {
          table.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      if (conn != null) {
        try {
          conn.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  /**
   * 通过rowkey在指定表中查询一行数据
   */
  public void queryByRowKey() {

    Connection conn = null;
    HTable table = null;

    try {
      conn = ConnectionFactory.createConnection(conf);
      table = (HTable) conn.getTable(TableName.valueOf(TABLE_NAME));
      Get get = new Get("001:carl".getBytes());
//            get.setMaxVersions(2);
//            get.addColumn(FAMILY_COL_SCORE.getBytes(), "english".getBytes());
      Result rs = table.get(get);
      System.out.println("表" + TABLE_NAME + "中RowKey为001:carl的行数据如下");

      for (Cell cell : rs.rawCells()) {
        System.out.println("列族:" + new String(CellUtil.cloneFamily(cell)) + "\t" +
            "列修饰符：" + new String(CellUtil.cloneQualifier(cell)) + "\t" +
            "值:" + new String(CellUtil.cloneValue(cell)) + "\t" +
            "时间戳:" + cell.getTimestamp());
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (table != null) {
        try {
          table.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      if (conn != null) {
        try {
          conn.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  /**
   * 删除表中指定RowKey的行
   */
  public void deleteRow() {

    Connection conn = null;
    HTable table = null;
    try {
      conn = ConnectionFactory.createConnection(conf);
      table = (HTable) conn.getTable(TableName.valueOf(TABLE_NAME));
      table.delete(new Delete("001：carl".getBytes()));
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        if (table != null) {
          table.close();
        }
        if (conn != null) {
          conn.close();
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

  }

  /**
   * 删除指定名称的列族
   */
  public void deleteFamily() {

    Connection conn = null;
    HBaseAdmin admin = null;
    try {
      conn = ConnectionFactory.createConnection(conf);
      admin = (HBaseAdmin) conn.getAdmin();
      admin.deleteColumn(TABLE_NAME.getBytes(), FAMILY_COL_INFO);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        if (null != conn) {
          conn.close();
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * 删除指定表
   */
  public void deleteTable() {

    Connection conn = null;
    HBaseAdmin admin = null;
    try {
      conn = ConnectionFactory.createConnection(conf);
      admin = (HBaseAdmin) conn.getAdmin();
      // 在删除一张表前，要先使其失效
      admin.disableTable(TABLE_NAME);
      admin.deleteTable(TABLE_NAME);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        if (conn != null) {
          conn.close();
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

  }
}
