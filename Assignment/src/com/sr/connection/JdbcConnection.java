
package com.sr.connection;

import java.sql.DriverManager;
import java.sql.Connection;

public class JdbcConnection
{
    public static Connection getCon() {
        Connection con = null;
         String driver = "oracle.jdbc.driver.OracleDriver";
         String url = "jdbc:oracle:thin:@localhost:1539:orcl";
         String user = "college";
         String password = "Admin1";
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, password);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}
