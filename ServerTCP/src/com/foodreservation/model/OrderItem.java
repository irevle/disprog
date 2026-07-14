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

public class OrderItem extends MyModel {
    private int id;
    private int reservationId;
    private int menuItemId;
    private int quantity;
    private double subtotal;
    private String status;

    public OrderItem() {
        this._getConnection();
    }

    public boolean createOrderItem(int reservationId, int menuItemId,
                                    int quantity, double subtotal) {
        boolean success = false;
        try {
            if (!MyModel.conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement(
                    "INSERT INTO order_items (reservation_id, menu_item_id, quantity, subtotal) "
                  + "VALUES (?, ?, ?, ?)"
                );
                sql.setInt(1, reservationId);
                sql.setInt(2, menuItemId);
                sql.setInt(3, quantity);
                sql.setDouble(4, subtotal);
                success = sql.executeUpdate() > 0;
                sql.close();
            }
        } catch (Exception e) {
            System.out.println("Error createOrderItem " + e);
        }
        return success;
    }

    public boolean updateStatus(int orderItemId, String status) {
        boolean success = false;
        try {
            if (!MyModel.conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement(
                    "UPDATE order_items SET status = ? WHERE id = ?"
                );
                sql.setString(1, status);
                sql.setInt(2, orderItemId);
                success = sql.executeUpdate() > 0;
                sql.close();
            }
        } catch (Exception e) {
            System.out.println("Error updateStatus " + e);
        }
        return success;
    }

    public ArrayList<String> viewOrderItems(int reservationId) {
        ArrayList<String> collections = new ArrayList<>();
        try {
            if (!MyModel.conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement(
                    "SELECT oi.*, m.name, m.category, m.price FROM order_items oi "
                  + "JOIN menu_items m ON oi.menu_item_id = m.id "
                  + "WHERE oi.reservation_id = ?"
                );
                sql.setInt(1, reservationId);
                this.result = sql.executeQuery();
                while (this.result.next()) {
                    collections.add(
                        this.result.getInt("id") + ";"
                      + this.result.getInt("reservation_id") + ";"
                      + this.result.getString("name") + ";"
                      + this.result.getString("category") + ";"
                      + this.result.getDouble("price") + ";"
                      + this.result.getInt("quantity") + ";"
                      + this.result.getDouble("subtotal") + ";"
                      + this.result.getString("status")
                    );
                }
                sql.close();
            }
        } catch (Exception e) {
            System.out.println("Error viewOrderItems " + e);
        }
        return collections;
    }

    public ArrayList<String> viewAllOrderItems() {
        ArrayList<String> collections = new ArrayList<>();
        try {
            if (!MyModel.conn.isClosed()) {
                this.statement = (Statement) MyModel.conn.createStatement();
                this.result = this.statement.executeQuery(
                    "SELECT oi.*, m.name, m.category, m.price FROM order_items oi "
                  + "JOIN menu_items m ON oi.menu_item_id = m.id"
                );
                while (this.result.next()) {
                    collections.add(
                        this.result.getInt("id") + ";"
                      + this.result.getInt("reservation_id") + ";"
                      + this.result.getString("name") + ";"
                      + this.result.getString("category") + ";"
                      + this.result.getDouble("price") + ";"
                      + this.result.getInt("quantity") + ";"
                      + this.result.getDouble("subtotal") + ";"
                      + this.result.getString("status")
                    );
                }
            }
        } catch (Exception e) {
            System.out.println("Error viewAllOrderItems " + e);
        }
        return collections;
    }

    @Override
    public ArrayList<Object> viewListData() {
        ArrayList<Object> collections = new ArrayList<>();
        try {
            this.statement = (Statement) MyModel.conn.createStatement();
            this.result = this.statement.executeQuery("SELECT * FROM order_items");
            while (this.result.next()) {
                OrderItem o = new OrderItem();
                o.id = this.result.getInt("id");
                collections.add(o);
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
                    "INSERT INTO order_items (reservation_id, menu_item_id, quantity, subtotal) "
                  + "VALUES (?, ?, ?, ?)"
                );
                sql.setInt(1, this.reservationId);
                sql.setInt(2, this.menuItemId);
                sql.setInt(3, this.quantity);
                sql.setDouble(4, this.subtotal);
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
                    "UPDATE order_items SET quantity = ?, subtotal = ?, status = ? WHERE id = ?"
                );
                sql.setInt(1, this.quantity);
                sql.setDouble(2, this.subtotal);
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
                    "DELETE FROM order_items WHERE id = ?"
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