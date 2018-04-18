package com.infore.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;

/**
 * Created by xuyao on 2017/8/30.
 */
public class HbaseClient {
  private static HBaseAdmin admin = null;
  public static Configuration configuration;
  public HbaseClient() throws MasterNotRunningException, ZooKeeperConnectionException, IOException {
    configuration = HBaseConfiguration.create();
    configuration.set("hbase.zookeeper.quorum", "192.168.31.253:2181");
//    configuration.set("hbase.zookeeper.property.clientPort", "2181");
//    configuration.set("zookeeper.znode.parent", "/hbase");
//    configuration.set("hbase.master", "192.168.31.91:60010");
    System.out.println(configuration);
  }

  public static void main(String[] args) throws Exception{
    HbaseClient app = new HbaseClient();
    app.getAllTables();
//    app.scanTable();

  }
  public List getAllTables() {
    List<String> tables = null;
    Connection conn = null;
      try {
        System.out.println("here");
        conn = ConnectionFactory.createConnection(configuration);
        System.out.println("here2");
        admin = (HBaseAdmin) conn.getAdmin();
        System.out.println("here3");
        HTableDescriptor[] allTable = admin.listTables();
        System.out.println(allTable.length);
        if (allTable.length > 0)
          tables = new ArrayList<String>();
        for (HTableDescriptor hTableDescriptor : allTable) {
          tables.add(hTableDescriptor.getNameAsString());
          System.out.println(hTableDescriptor.getNameAsString());
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    return tables;
  }

  /**
   * 在指定表中查询所有数据(全表扫描)
   */
  public void scanTable() {

    Connection conn = null;
    HTable table = null;
    ResultScanner scann = null;

    try {
      System.out.println("here xx");
      conn = ConnectionFactory.createConnection(configuration);
      System.out.println("here1");
      table = (HTable) conn.getTable(TableName.valueOf("test"));
      System.out.println("here2");
      scann = table.getScanner(new Scan());
      System.out.println("here3");
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
}
