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
 * @author elve
 */

public class User extends MyModel {
    private int id;
    private String username;
    private String password;
    private String fullName;
    private String email;
    private String phone;
    private String role;
    private String createdAt;

    public User() {
        this.username = "";
        this.password = "";
        this._getConnection();
    }

    public boolean checkLogin(String username, String password) {
        boolean found = false;
        try {
            if (!MyModel.conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement(
                    "SELECT id FROM users WHERE username = ? AND password = SHA2(?, 256)"
                );
                sql.setString(1, username);
                sql.setString(2, password);
                this.result = sql.executeQuery();
                if (this.result.next()) {
                    found = true;
                }
                sql.close();
            }
        } catch (Exception e) {
            System.out.println("Error checkLogin " + e);
        }
        return found;
    }

    public boolean register(String username, String password, String fullName,
                            String email, String phone) {
        boolean success = false;
        try {
            if (!MyModel.conn.isClosed()) {
                PreparedStatement check = (PreparedStatement) MyModel.conn.prepareStatement(
                    "SELECT id FROM users WHERE username = ?"
                );
                check.setString(1, username);
                this.result = check.executeQuery();
                boolean exists = this.result.next();
                check.close();

                if (!exists) {
                    PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement(
                        "INSERT INTO users (username, password, full_name, email, phone, role) "
                      + "VALUES (?, SHA2(?, 256), ?, ?, ?, 'Customer')"
                    );
                    sql.setString(1, username);
                    sql.setString(2, password);
                    sql.setString(3, fullName);
                    sql.setString(4, email);
                    sql.setString(5, phone);
                    sql.executeUpdate();
                    sql.close();
                    success = true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error register " + e);
        }
        return success;
    }
    
    public String getUserRole(String username) {
        String role = "Customer";
        try {
            if (!MyModel.conn.isClosed()) {
                PreparedStatement sql = MyModel.conn.prepareStatement(
                        "SELECT role FROM users WHERE username = ?"
                );
                sql.setString(1, username);
                this.result = sql.executeQuery();
                if (this.result.next()) {
                    role = this.result.getString("role");
                }
                sql.close();
            }
        } catch (Exception e) {
            System.out.println("Error getUserRole " + e);
        }
        return role;
    }
    
    public int getUserId(String username) {
        int id = -1;
        try {
            if (!MyModel.conn.isClosed()) {
                PreparedStatement sql = MyModel.conn.prepareStatement(
                        "SELECT id FROM users WHERE username = ?"
                );
                sql.setString(1, username);
                this.result = sql.executeQuery();
                if (this.result.next()) {
                    id = this.result.getInt("id");
                }
                sql.close();
            }
        } catch (Exception e) {
            System.out.println("Error getUserId " + e);
        }
        return id;
    }

    @Override
    public ArrayList<Object> viewListData() {
        ArrayList<Object> collections = new ArrayList<>();
        try {
            this.statement = (Statement) MyModel.conn.createStatement();
            this.result = this.statement.executeQuery("SELECT * FROM users");
            while (this.result.next()) {
                User u = new User();
                u.id = this.result.getInt("id");
                u.username = this.result.getString("username");
                u.fullName = this.result.getString("full_name");
                u.email = this.result.getString("email");
                u.phone = this.result.getString("phone");
                u.role = this.result.getString("role");
                collections.add(u);
            }
        } catch (Exception e) {
            System.out.println("Error viewListData " + e);
        }
        return collections;
    }

    public ArrayList<String> viewListDataString() {
        ArrayList<String> collections = new ArrayList<>();
        try {
            this.statement = (Statement) MyModel.conn.createStatement();
            this.result = this.statement.executeQuery("SELECT * FROM users");
            while (this.result.next()) {
                collections.add(
                    this.result.getInt("id") + ";"
                  + this.result.getString("username") + ";"
                  + this.result.getString("full_name") + ";"
                  + this.result.getString("email") + ";"
                  + this.result.getString("phone") + ";"
                  + this.result.getString("role")
                );
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
                    "INSERT INTO users (username, password, full_name, email, phone, role) "
                  + "VALUES (?, SHA2(?, 256), ?, ?, ?, ?)"
                );
                sql.setString(1, this.username);
                sql.setString(2, this.password);
                sql.setString(3, this.fullName);
                sql.setString(4, this.email);
                sql.setString(5, this.phone);
                sql.setString(6, this.role);
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
                    "UPDATE users SET full_name = ?, email = ?, phone = ? WHERE id = ?"
                );
                sql.setString(1, this.fullName);
                sql.setString(2, this.email);
                sql.setString(3, this.phone);
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
                    "DELETE FROM users WHERE id = ?"
                );
                sql.setInt(1, this.id);
                sql.executeUpdate();
                sql.close();
            }
        } catch (Exception e) {
            System.out.println("Error deleteData " + e);
        }
    }
}