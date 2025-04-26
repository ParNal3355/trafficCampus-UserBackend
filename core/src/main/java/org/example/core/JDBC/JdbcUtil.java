package org.example.core.JDBC;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

//JDBC的Util类
@Component
public class JdbcUtil {

    private static DataSource dataSource;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        JdbcUtil.dataSource = dataSource;
    }

    // 获取连接的静态方法
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
