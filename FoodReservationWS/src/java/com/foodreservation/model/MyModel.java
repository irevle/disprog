/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.foodreservation.model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
/**
 *
 * @author elve
 */

public abstract class MyModel {
    protected static Connection conn;
    protected Statement statement;
    protected ResultSet result;

    public MyModel() {
        this.conn = this._getConnection();
        this.statement = null;
        this.result = null;
    }

    public Connection _getConnection() {
        if (MyModel.conn == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
                return DriverManager.getConnection(
                    "jdbc:mysql://localhost/food_reservation",
                    "root",
                    ""
                );
            } catch (Exception e) {
                System.out.println("Error di getConnection " + e);
            }
        }
        return MyModel.conn;
    }

    public void closeConnection() {
        try {
            if (MyModel.conn != null && !MyModel.conn.isClosed()) {
                MyModel.conn.close();
                MyModel.conn = null;
                System.out.println("Koneksi ditutup.");
            }
        } catch (Exception e) {
            System.out.println("Error closeConnection: " + e.getMessage());
        }
    }

    public abstract void insertData();
    public abstract void updateData();
    public abstract void deleteData();
    public abstract ArrayList<Object> viewListData();
}