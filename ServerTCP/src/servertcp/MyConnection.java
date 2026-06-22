package servertcp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Miftah
 */
public class MyConnection {

    Connection connect;
    Statement stat;
    ResultSet result;

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connect = DriverManager.getConnection("jdbc:mysql://localhost/week13_nrp", "root", "");
        } catch (Exception ex) {
            System.out.println("Error Get Connection : "+ex);
        }

        return connect;
    }

}
