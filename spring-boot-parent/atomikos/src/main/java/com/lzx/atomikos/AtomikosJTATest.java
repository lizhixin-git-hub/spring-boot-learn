package com.lzx.atomikos;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.jdbc.AtomikosDataSourceBean;
import org.apache.commons.lang3.math.NumberUtils;

import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.Properties;

/**
 * Atomikos两阶段提交,保证两个数据库事务
 */
public class AtomikosJTATest {

    private static AtomikosDataSourceBean createAtomikosDataSourceBean(String dbName) {
        //连接池属性
        Properties properties = new Properties();
        properties.setProperty("url", "jdbc:mysql://106.53.217.86:3306/" + dbName + "?allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=GMT%2B8");
        properties.setProperty("user", "root");
        properties.setProperty("password", "Lzx1922026241@");

        //使用AtomikosDataSourceBean封装com.mysql.jdbc.jdbc2.optional.MysqlXADataSource
        AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
        //Atomikos要求为每一个AtomikosDataSourceBean名称，为了方便记忆，这里设置为和dbName相同
        atomikosDataSourceBean.setUniqueResourceName(dbName);
        atomikosDataSourceBean.setXaDataSourceClassName("com.mysql.cj.jdbc.MysqlXADataSource");
        atomikosDataSourceBean.setXaProperties(properties);
        return atomikosDataSourceBean;
    }

    public static void main(String[] args) throws SystemException {
        AtomikosDataSourceBean atomikosDataSourceBean1 = createAtomikosDataSourceBean("edu-db-1");
        AtomikosDataSourceBean atomikosDataSourceBean2 = createAtomikosDataSourceBean("lzx");

        Connection connection1 = null;
        Connection connection2 = null;

        PreparedStatement preparedStatement1 = null;
        PreparedStatement preparedStatement2 = null;

        UserTransaction userTransaction = new UserTransactionImp();

        try {
            //开启事务
            userTransaction.begin();

            //执行db1上的sql
            connection1 = atomikosDataSourceBean1.getConnection();
            String sql1 = "INSERT INTO t_udict (dictid, ustatus, uvalue) VALUES (?, ?, ?)";
            preparedStatement1 = connection1.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
            preparedStatement1.setLong(NumberUtils.INTEGER_ONE, 1200L);
            preparedStatement1.setString(NumberUtils.INTEGER_TWO, "Normal");
            preparedStatement1.setString((NumberUtils.INTEGER_ONE + NumberUtils.INTEGER_TWO) , "500");
            preparedStatement1.executeUpdate();

            //模拟异常，直接进入catch代码块，2个都不会提交
            //int i = 1/0;

            //执行db2上的sql
            connection2 = atomikosDataSourceBean2.getConnection();
            String sql2 = "INSERT INTO good (stock) VALUES (?)";
            preparedStatement2 = connection2.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);
            preparedStatement2.setInt(NumberUtils.INTEGER_ONE, 100);
            preparedStatement2.executeUpdate();

            //跨系统调用接口，两阶段提交
            userTransaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            userTransaction.rollback();
        } finally {
            try {
                if (Objects.nonNull(preparedStatement1)) {
                    preparedStatement1.close();
                }

                if (Objects.nonNull(preparedStatement2)) {
                    preparedStatement2.close();
                }

                if (Objects.nonNull(connection1)) {
                    connection1.close();
                }

                if (Objects.nonNull(connection2)) {
                    connection2.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
