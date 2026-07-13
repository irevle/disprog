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

public class Payment extends MyModel {
    private int id;
    private int reservationId;
    private double amount;
    private String paymentMethod;
    private String paymentStatus;
    private String paidAt;

    public Payment() {
        this._getConnection();
    }

    public int createPayment(int reservationId, double amount, String method) {
        try {
            if (!MyModel.conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement(
                    "INSERT INTO payments (reservation_id, amount, payment_method, payment_status, paid_at) "
                  + "VALUES (?, ?, ?, 'PAID', NOW())",
                    Statement.RETURN_GENERATED_KEYS
                );
                sql.setInt(1, reservationId);
                sql.setDouble(2, amount);
                sql.setString(3, method);
                sql.executeUpdate();

                this.result = sql.getGeneratedKeys();
                if (this.result.next()) {
                    int newId = this.result.getInt(1);
                    sql.close();
                    return newId;
                }
                sql.close();
            }
        } catch (Exception e) {
            System.out.println("Error createPayment " + e);
        }
        return -1;
    }

    public ArrayList<String> viewByReservation(int reservationId) {
        ArrayList<String> collections = new ArrayList<>();
        try {
            if (!MyModel.conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement(
                    "SELECT * FROM payments WHERE reservation_id = ?"
                );
                sql.setInt(1, reservationId);
                this.result = sql.executeQuery();
                while (this.result.next()) {
                    collections.add(
                        this.result.getInt("id") + ";"
                      + this.result.getInt("reservation_id") + ";"
                      + this.result.getDouble("amount") + ";"
                      + this.result.getString("payment_method") + ";"
                      + this.result.getString("payment_status")
                    );
                }
                sql.close();
            }
        } catch (Exception e) {
            System.out.println("Error viewByReservation " + e);
        }
        return collections;
    }

    @Override
    public ArrayList<Object> viewListData() {
        ArrayList<Object> collections = new ArrayList<>();
        try {
            this.statement = (Statement) MyModel.conn.createStatement();
            this.result = this.statement.executeQuery("SELECT * FROM payments");
            while (this.result.next()) {
                Payment p = new Payment();
                p.id = this.result.getInt("id");
                collections.add(p);
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
                    "INSERT INTO payments (reservation_id, amount, payment_method, payment_status) "
                  + "VALUES (?, ?, ?, ?)"
                );
                sql.setInt(1, this.reservationId);
                sql.setDouble(2, this.amount);
                sql.setString(3, this.paymentMethod);
                sql.setString(4, this.paymentStatus);
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
                    "UPDATE payments SET payment_status = ?, paid_at = NOW() WHERE id = ?"
                );
                sql.setString(1, this.paymentStatus);
                sql.setInt(2, this.id);
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
                    "DELETE FROM payments WHERE id = ?"
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
