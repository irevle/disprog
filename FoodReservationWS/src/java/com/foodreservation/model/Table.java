/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.foodreservation.model;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
/**
 *
 * @author alievar
 */

public class Table extends MyModel {
    private int id;
    private String tableNumber;
    private int capacity;
    private String status;

    public Table() {
        this._getConnection();
    }

    public ArrayList<String> viewListDataString() {
        ArrayList<String> collections = new ArrayList<>();
        try {
            this.statement = (Statement) MyModel.conn.createStatement();
            this.result = this.statement.executeQuery("SELECT * FROM tables ORDER BY table_number");
            while (this.result.next()) {
                collections.add(
                    this.result.getInt("id") + ";"
                  + this.result.getString("table_number") + ";"
                  + this.result.getInt("capacity") + ";"
                  + this.result.getString("status")
                );
            }
        } catch (Exception e) {
            System.out.println("Error viewListDataString " + e);
        }
        return collections;
    }

    public boolean updateStatus(int tableId, String status) {
        boolean success = false;
        try {
            if (!MyModel.conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement(
                    "UPDATE tables SET status = ? WHERE id = ?"
                );
                sql.setString(1, status);
                sql.setInt(2, tableId);
                success = sql.executeUpdate() > 0;
                sql.close();
            }
        } catch (Exception e) {
            System.out.println("Error updateStatus " + e);
        }
        return success;
    }

    @Override
    public ArrayList<Object> viewListData() {
        ArrayList<Object> collections = new ArrayList<>();
        try {
            this.statement = (Statement) MyModel.conn.createStatement();
            this.result = this.statement.executeQuery("SELECT * FROM tables");
            while (this.result.next()) {
                Table t = new Table();
                t.id = this.result.getInt("id");
                t.tableNumber = this.result.getString("table_number");
                t.capacity = this.result.getInt("capacity");
                t.status = this.result.getString("status");
                collections.add(t);
            }
        } catch (Exception e) {
            System.out.println("Error viewListData " + e);
        }
        return collections;
    }

    @Override
    public void insertData() {
        try {
            if (!MyModel.conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement(
                    "INSERT INTO tables (table_number, capacity, status) VALUES (?, ?, ?)"
                );
                sql.setString(1, this.tableNumber);
                sql.setInt(2, this.capacity);
                sql.setString(3, this.status);
                sql.executeUpdate();
                sql.close();
            }
        } catch (Exception e) {
            System.out.println("Error insertData " + e);
        }
    }

    @Override
    public void updateData() {
        try {
            if (!MyModel.conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement(
                    "UPDATE tables SET table_number = ?, capacity = ?, status = ? WHERE id = ?"
                );
                sql.setString(1, this.tableNumber);
                sql.setInt(2, this.capacity);
                sql.setString(3, this.status);
                sql.setInt(4, this.id);
                sql.executeUpdate();
                sql.close();
            }
        } catch (Exception e) {
            System.out.println("Error updateData " + e);
        }
    }

    @Override
    public void deleteData() {
        try {
            if (!MyModel.conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement(
                    "DELETE FROM tables WHERE id = ?"
                );
                sql.setInt(1, this.id);
                sql.executeUpdate();
                sql.close();
            }
        } catch (Exception e) {
            System.out.println("Error deleteData " + e);
        }
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setTableNumber(String tableNumber) {
        this.tableNumber = tableNumber;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getTableNumber() {
        return tableNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getStatus() {
        return status;
    }
}
