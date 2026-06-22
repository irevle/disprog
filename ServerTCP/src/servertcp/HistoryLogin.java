package servertcp;

import java.sql.PreparedStatement;
import java.sql.Statement;

public class HistoryLogin extends MyConnection {

    private String username;
    private String time_login;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTime_login() {
        return time_login;
    }

    public void setTime_login(String time_login) {
        this.time_login = time_login;
    }

    public HistoryLogin(String username, String time_login) {
        this.username = username;
        this.time_login = time_login;
        getConnection();
    }

    public void insert() {
        try {
            if (!connect.isClosed()) {
                stat = (Statement) connect.createStatement();
                PreparedStatement sql = (PreparedStatement) 
                    connect.prepareStatement("insert into history_login(username, time_login) values (?,?) ");
                sql.setString(1, getUsername());
                sql.setString(2, getTime_login());
                sql.executeUpdate();
            } else {
                System.out.println("Koneksi Hilang");
            }
        } catch (Exception ex) {
            System.out.println("Error di insert : "+ex);
        }
    }

}
