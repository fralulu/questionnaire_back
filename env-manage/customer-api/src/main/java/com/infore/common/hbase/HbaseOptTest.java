package com.infore.common.hbase;

import com.infore.common.hbase.HbaseOpt.putobj;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.filter.BinaryComparator;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.QualifierFilter;
import org.apache.hadoop.hbase.filter.RegexStringComparator;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.filter.ValueFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.hbase.util.CollectionUtils;
import sun.rmi.runtime.Log;

/**
 * Created by xuyao on 2017/9/6.
 */
public class HbaseOptTest {
  private static Configuration conf;
  private static Connection connection = null;

  private static String tableName = "demo";
  private static String row = "u12000";

  public static void main(String[] args) {
    HbaseOptTest test = new HbaseOptTest();
    test.init();
    test.curd();
  }

  /**
   * 初始化hbase的配置信息,也可以将hbase的配置文件hbase-site.xml引入项目，则不需要在代码中填写配置信息
   */
  public static void init() {
    conf = HBaseConfiguration.create();
    conf.set("hbase.zookeeper.quorum", "192.168.31.91");
    conf.set("hbase.zookeeper.property.clientPort", "2181");

    try {
      connection=ConnectionFactory.createConnection(conf);
    } catch (IOException e) {
      System.out.println("error"+e);
      HbaseOpt.releaseConnect(connection);
    }
  }

  public static void curd(){
    try {
//      create();
//      put();
//      deleteTable();
//      delete();
//      scan();
//      addColunm();
//      delColunm();
//      delColumnualifier();
      scanByFilter();

    } catch (IOException e) {
      e.printStackTrace();
      System.out.println("========");
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("===1=====");
    }
  }

  private static void create() throws Exception{
    long time = HbaseOpt.createTable(connection, tableName, "cf1", "cf2");
    System.out.println("-----time:"+(time/1000));
  }

  private static void put() throws Exception{

    long time1 = HbaseOpt.put(connection, tableName, row, "cf3", "name", "ricky");
    System.out.println("-----time1:"+(time1/1000));

    HbaseOpt.putobj put1 = new putobj(row);
    put1.addColumn("cf1", "age", "28");
//    long time2 = HbaseOpt.put(connection, tableName, put1);
//    System.out.println("-----time2:"+time2);

    Put put=new Put(Bytes.toBytes(row));
    put.addColumn(Bytes.toBytes("cf1"),Bytes.toBytes("password"),Bytes.toBytes("root"));
//    long time3 = HbaseOpt.put(connection, tableName, put);
//    System.out.println("-----time3:"+time3);



    HbaseOpt.putobj put4 = new putobj(row);
    put4.addColumn("cf1", "sex", "男");

    HbaseOpt.putobj put5 = new putobj(row);
    put5.addColumn("cf1", "content", "我是大红烧豆腐接口加看工单复健科高大光辉看。梵蒂冈的风格的风格，gfgf");

    List<Put> puts = new ArrayList<>();
    puts.add(put1);
    puts.add(put);
    puts.add(put4);
    puts.add(put5);

//    long time4 = HbaseOpt.putByHTable(connection, tableName, puts);
//    System.out.println("-----time4:"+time4);

  }

  private static void deleteTable() throws Exception{
    HbaseOpt.deleteTable(connection,tableName);
  }

  private static void delete() throws Exception{
    long time = HbaseOpt.deleteRow(connection, tableName, row);
    System.out.println("------time:"+time);
  }

  private static void scan() throws Exception {
    ResultScanner rs = HbaseOpt.scan(connection, tableName);
    showScan(rs);
    HbaseOpt.closeResultScanner(rs);
  }

  private static void addColunm() throws Exception {
    long time = HbaseOpt.addTableColumn(connection, tableName, "cf4","cf5");
    System.out.println("----time:"+time);
  }

  private static void delColunm() throws Exception {
    long time = HbaseOpt.delTableColumn(connection, tableName, "cf5");
    System.out.println("----time:"+time);
  }

  private static void delColumnualifier() throws Exception {
    long time = HbaseOpt.delColumnualifier(connection, tableName,row , "cf3", "name");
    System.out.println("----time:"+time);
  }

  private static void scanByFilter() throws Exception {
    FilterList filterList = new FilterList();
    HBasePageModel pageModel = new HBasePageModel(1);
    pageModel.setPageIndex(2);
    String endRowKey = null;
//    endRowKey = "u1234";
//    Filter filter1 = new RowFilter(CompareOp.EQUAL,new BinaryComparator(Bytes.toBytes(row)));
//    Filter filter2 = new ValueFilter(CompareOp.EQUAL,new RegexStringComparator("ri*"));
//    Filter filter3 = new QualifierFilter(CompareOp.EQUAL, new BinaryComparator(Bytes.toBytes("cf1")));
    Filter filter4 = new ValueFilter(CompareOp.EQUAL, new BinaryComparator(Bytes.toBytes("root")));

//    filterList.addFilter(filter1);
//    filterList.addFilter(filter2);
//    filterList.addFilter(filter3);

    pageModel=HbaseOpt.scanResultByPageFilter(connection,tableName,row,endRowKey,filterList,Integer.MAX_VALUE,pageModel);
    System.out.println("----time:"+pageModel.toString());
    if (org.apache.commons.collections.CollectionUtils.isNotEmpty(pageModel.getResultList())) {
      showResultScan(pageModel.getResultList());
//      showScanCell(pageModel.getResultList().get(0).rawCells());
    }
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

  private static void showResultScan(List<Result> rs) {
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

  private static void showScanCell(Cell[] cells) {
    for (Cell cell : cells) {
      System.out.println("列族:" + new String(CellUtil.cloneFamily(cell)) + "\t" +
          "列修饰符：" + new String(CellUtil.cloneQualifier(cell)) + "\t" +
          "值:" + new String(CellUtil.cloneValue(cell)) + "\t" +
          "时间戳:" + cell.getTimestamp());
    }
  }

}
