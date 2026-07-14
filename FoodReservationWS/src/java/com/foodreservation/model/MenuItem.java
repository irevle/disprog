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

public class MenuItem extends MyModel {
    private int id;
    private String name;
    private String category;
    private double price;
    private String description;
    private boolean available;

    public MenuItem() {
        this._getConnection();
    }

    public ArrayList<String> viewListDataString() {
        ArrayList<String> collections = new ArrayList<>();
        try {
            this.statement = (Statement) MyModel.conn.createStatement();
            this.result = this.statement.executeQuery("SELECT * FROM menu_items");
            while (this.result.next()) {
                collections.add(
                    this.result.getInt("id") + ";"
                  + this.result.getString("name") + ";"
                  + this.result.getString("category") + ";"
                  + this.result.getDouble("price") + ";"
                  + this.result.getString("description") + ";"
                  + this.result.getBoolean("available")
                );
            }
        } catch (Exception e) {
            System.out.println("Error viewListDataString " + e);
        }
        return collections;
    }

    public ArrayList<String> searchByNameOrCategory(String keyword) {
        ArrayList<String> collections = new ArrayList<>();
        try {
            if (!MyModel.conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement(
                    "SELECT * FROM menu_items WHERE name LIKE ? OR category LIKE ?"
                );
                sql.setString(1, "%" + keyword + "%");
                sql.setString(2, "%" + keyword + "%");
                this.result = sql.executeQuery();
                while (this.result.next()) {
                    collections.add(
                        this.result.getInt("id") + ";"
                      + this.result.getString("name") + ";"
                      + this.result.getString("category") + ";"
                      + this.result.getDouble("price") + ";"
                      + this.result.getString("description") + ";"
                      + this.result.getBoolean("available")
                    );
                }
                sql.close();
            }
        } catch (Exception e) {
            System.out.println("Error searchByNameOrCategory " + e);
        }
        return collections;
    }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setCategory(String category) { this.category = category; }
    public void setPrice(double price) { this.price = price; }
    public void setDescription(String description) { this.description = description; }
    public void setAvailable(boolean available) { this.available = available; }

    @Override
    public ArrayList<Object> viewListData() {
        ArrayList<Object> collections = new ArrayList<>();
        try {
            this.statement = (Statement) MyModel.conn.createStatement();
            this.result = this.statement.executeQuery("SELECT * FROM menu_items");
            while (this.result.next()) {
                MenuItem m = new MenuItem();
                m.id = this.result.getInt("id");
                m.name = this.result.getString("name");
                m.category = this.result.getString("category");
                m.price = this.result.getDouble("price");
                m.description = this.result.getString("description");
                m.available = this.result.getBoolean("available");
                collections.add(m);
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
                    "INSERT INTO menu_items (name, category, price, description, available) "
                  + "VALUES (?, ?, ?, ?, ?)"
                );
                sql.setString(1, this.name);
                sql.setString(2, this.category);
                sql.setDouble(3, this.price);
                sql.setString(4, this.description);
                sql.setBoolean(5, this.available);
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
                    "UPDATE menu_items SET name = ?, category = ?, price = ?, "
                  + "description = ?, available = ? WHERE id = ?"
                );
                sql.setString(1, this.name);
                sql.setString(2, this.category);
                sql.setDouble(3, this.price);
                sql.setString(4, this.description);
                sql.setBoolean(5, this.available);
                sql.setInt(6, this.id);
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
                    "DELETE FROM menu_items WHERE id = ?"
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