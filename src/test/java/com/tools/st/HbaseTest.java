package com.tools.st;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.shaded.protobuf.generated.CellProtos;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hbase.thirdparty.com.google.protobuf.Message;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class HbaseTest {

    @Test
    public void test() throws IOException {
        Configuration config = HBaseConfiguration.create();
        Connection connection = ConnectionFactory.createConnection(config);
        Table table = connection.getTable(TableName.valueOf("test"));
        try {
//            Get get = Gets
//            Put put = new Put(Bytes.toBytes("row5"));
//            put.getRow()
//            put.addColumn(Bytes.toBytes("cf"), Bytes.toBytes("a"), Bytes.toBytes("eeeeee"));
        } finally {
          table.close();
          connection.close();
        }
    }
}
