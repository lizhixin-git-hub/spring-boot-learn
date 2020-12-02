package com.lzx.atomikos;

import com.mysql.cj.jdbc.JdbcConnection;
import com.mysql.cj.jdbc.MysqlXAConnection;
import com.mysql.cj.jdbc.MysqlXid;
import org.apache.commons.lang3.math.NumberUtils;

import javax.sql.XAConnection;
import javax.transaction.xa.XAException;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;
import java.sql.*;

/**
 * 两阶段提交协议
 */
public class XATest {

    public static void main(String[] args) throws SQLException, XAException {
        String user = "root";
        String password = "Lzx1922026241@";

        //获得资源管理器操作接口实例 RM1
        String url1 = "jdbc:mysql://106.53.217.86:3306/edu-db-1?allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=GMT%2B8";
        Connection connection1 = DriverManager.getConnection(url1, user, password);
        //true标示打印XA语句，用于调试
        XAConnection xaConnection1 = new MysqlXAConnection((JdbcConnection) connection1, Boolean.TRUE);
        XAResource xaResource1 = xaConnection1.getXAResource();

        //获得资源管理器操作接口实例
        String url12 = "jdbc:mysql://106.53.217.86:3306/lzx?allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=GMT%2B8";
        Connection connection2 = DriverManager.getConnection(url12, user, password);
        //true标示打印XA语句，用于调试
        XAConnection xaConnection2 = new MysqlXAConnection((JdbcConnection) connection2, Boolean.TRUE);
        XAResource xaResource2 = xaConnection2.getXAResource();

        //AP请求TM执行一个分布式事务，TM生成全局事务ID
        byte[] gTrId = "g12345".getBytes();
        int formatId = 1;

        //==============分别执行xaResource1、xaResource2上的事务分支
        //TM生成xaResource1上的事务分支id
        byte[] bqual1 = "b00001".getBytes();
        Xid xid1 = new MysqlXid(gTrId, bqual1, formatId);
        //执行xaResource1上的事务分支
        xaResource1.start(xid1, XAResource.TMNOFLAGS);
        String sql1 = "INSERT INTO t_udict (dictid, ustatus, uvalue) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement1 = connection1.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
        preparedStatement1.setLong(NumberUtils.INTEGER_ONE, 1200L);
        preparedStatement1.setString(NumberUtils.INTEGER_TWO, "Normal");
        preparedStatement1.setString((NumberUtils.INTEGER_ONE + NumberUtils.INTEGER_TWO), "500");
        preparedStatement1.execute();
        xaResource1.end(xid1, XAResource.TMSUCCESS);

        //TM生成xaResource2上的事务分支id
        byte[] bqual2 = "b00002".getBytes();
        Xid xid2 = new MysqlXid(gTrId, bqual2, formatId);
        //执行xaResource2上的事务分支
        xaResource2.start(xid2, XAResource.TMNOFLAGS);
        String sql2 = "INSERT INTO good (stock) VALUES (?)";
        PreparedStatement preparedStatement2 = connection2.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);
        preparedStatement2.setInt(NumberUtils.INTEGER_ONE, 100);
        preparedStatement2.execute();
        xaResource2.end(xid2, XAResource.TMSUCCESS);

        //================两阶段提交=============
        //阶段一：prepare1
        int xaResource1_prepare = xaResource1.prepare(xid1);
        int xaResource2_prepare = xaResource2.prepare(xid2);

        //阶段二：prepare2提交所有事务分支
        //TM判断有两个事务分支，所以不能优化为一阶段提交
        //所有事务分支都prepare成功，提交事务
        if (xaResource1_prepare == XAResource.XA_OK && xaResource2_prepare == XAResource.XA_OK) {
            xaResource1.commit(xid1, Boolean.FALSE);
            xaResource2.commit(xid2, Boolean.FALSE);
        } else {
            //如果有事务分支没有成功，则回滚
            xaResource1.rollback(xid1);
            xaResource2.rollback(xid2);
        }
    }

}
