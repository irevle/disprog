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

public class Reservation extends MyModel {
    private int id;
    private int userId;
    private int tableId;
    private String reservationDate;
    private String reservationTime;
    private String endTime;
    private int guestCount;
    private String status;
    private String notes;
    private String createdAt;

    public Reservation() {
        this._getConnection();
    }

    public int createReservation(int userId, String date, String time,
                                  String endTime, int guests, String notes) {
        try {
            if (!MyModel.conn.isClosed()) {
                // cari meja terkecil yang pas berdasarkan jumlah customer
                PreparedStatement findTable = (PreparedStatement) MyModel.conn.prepareStatement(
                    "SELECT t.id FROM tables t WHERE t.capacity >= ? AND t.status = 'AVAILABLE' "
                  + "AND t.id NOT IN ("
                  + "    SELECT r.table_id FROM reservations r "
                  + "    WHERE r.reservation_date = ? AND r.reservation_time = ? "
                  + "    AND r.status != 'CANCELLED'"
                  + ") ORDER BY t.capacity ASC LIMIT 1"
                );
                findTable.setInt(1, guests);
                findTable.setString(2, date);
                findTable.setString(3, time);
                this.result = findTable.executeQuery();

                if (this.result.next()) {
                    int tableId = this.result.getInt("id");
                    findTable.close();

                    PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement(
                        "INSERT INTO reservations (user_id, table_id, reservation_date, "
                      + "reservation_time, end_time, guest_count, notes) "
                      + "VALUES (?, ?, ?, ?, ?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS
                    );
                    sql.setInt(1, userId);
                    sql.setInt(2, tableId);
                    sql.setString(3, date);
                    sql.setString(4, time);
                    sql.setString(5, endTime);
                    sql.setInt(6, guests);
                    sql.setString(7, notes);
                    sql.executeUpdate();

                    this.result = sql.getGeneratedKeys();
                    if (this.result.next()) {
                        int newId = this.result.getInt(1);
                        sql.close();
                        return newId;
                    }
                    sql.close();
                } else {
                    findTable.close();
                }
            }
        } catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().contains("Duplicate")) {
                System.out.println("Double-booking prevented");
            } else {
                System.out.println("Error createReservation " + e);
            }
        }
        return -1;
    }

    public boolean updateReservation(int id, String tanggal, String jam,
                                      String tableNumber, int tamu, String status) {
        try {
            if (!MyModel.conn.isClosed()) {
                PreparedStatement findTable = (PreparedStatement) MyModel.conn.prepareStatement(
                    "SELECT id FROM tables WHERE table_number = ?"
                );
                findTable.setString(1, tableNumber);
                this.result = findTable.executeQuery();
                int tableId = this.result.next() ? this.result.getInt("id") : -1;
                findTable.close();

                PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement(
                    "UPDATE reservations SET reservation_date = ?, reservation_time = ?, "
                  + "table_id = ?, guest_count = ?, status = ? WHERE id = ?"
                );
                sql.setString(1, tanggal);
                sql.setString(2, jam);
                sql.setInt(3, tableId);
                sql.setInt(4, tamu);
                sql.setString(5, status);
                sql.setInt(6, id);
                boolean ok = sql.executeUpdate() > 0;
                sql.close();
                return ok;
            }
        } catch (Exception e) {
            System.out.println("Error updateReservation " + e);
        }
        return false;
    }

    public boolean cancelReservation(int reservationId) {
        boolean success = false;
        try {
            if (!MyModel.conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement(
                    "UPDATE reservations SET status = 'CANCELLED' WHERE id = ? AND status != 'CANCELLED'"
                );
                sql.setInt(1, reservationId);
                success = sql.executeUpdate() > 0;
                sql.close();
            }
        } catch (Exception e) {
            System.out.println("Error cancelReservation " + e);
        }
        return success;
    }

    public ArrayList<String> viewReservationsByUser(int userId) {
        ArrayList<String> collections = new ArrayList<>();
        try {
            if (!MyModel.conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement(
                    "SELECT r.*, t.table_number FROM reservations r "
                  + "JOIN tables t ON r.table_id = t.id WHERE r.user_id = ? "
                  + "ORDER BY r.reservation_date DESC, r.reservation_time DESC"
                );
                sql.setInt(1, userId);
                this.result = sql.executeQuery();
                while (this.result.next()) {
                    collections.add(
                        this.result.getInt("id") + ";"
                      + this.result.getInt("user_id") + ";"
                      + this.result.getString("table_number") + ";"
                      + this.result.getString("reservation_date") + ";"
                      + this.result.getString("reservation_time") + ";"
                      + this.result.getString("end_time") + ";"
                      + this.result.getInt("guest_count") + ";"
                      + this.result.getString("status") + ";"
                      + (this.result.getString("notes") != null ? this.result.getString("notes") : "")
                    );
                }
                sql.close();
            }
        } catch (Exception e) {
            System.out.println("Error viewReservationsByUser " + e);
        }
        return collections;
    }

    public ArrayList<String> viewAllReservations() {
        ArrayList<String> collections = new ArrayList<>();
        try {
            if (!MyModel.conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement(
                    "SELECT r.*, t.table_number, u.full_name FROM reservations r "
                  + "JOIN tables t ON r.table_id = t.id "
                  + "JOIN users u ON r.user_id = u.id "
                  + "ORDER BY r.reservation_date DESC, r.reservation_time DESC"
                );
                this.result = sql.executeQuery();
                while (this.result.next()) {
                    collections.add(
                        this.result.getInt("id") + ";"
                      + this.result.getString("full_name") + ";"
                      + this.result.getString("table_number") + ";"
                      + this.result.getString("reservation_date") + ";"
                      + this.result.getString("reservation_time") + ";"
                      + this.result.getString("end_time") + ";"
                      + this.result.getInt("guest_count") + ";"
                      + this.result.getString("status") + ";"
                      + (this.result.getString("notes") != null ? this.result.getString("notes") : "")
                    );
                }
                sql.close();
            }
        } catch (Exception e) {
            System.out.println("Error viewAllReservations " + e);
        }
        return collections;
    }

    @Override
    public ArrayList<Object> viewListData() {
        ArrayList<Object> collections = new ArrayList<>();
        try {
            this.statement = (Statement) MyModel.conn.createStatement();
            this.result = this.statement.executeQuery("SELECT * FROM reservations");
            while (this.result.next()) {
                Reservation r = new Reservation();
                r.id = this.result.getInt("id");
                collections.add(r);
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
                    "INSERT INTO reservations (user_id, table_id, reservation_date, "
                  + "reservation_time, end_time, guest_count, notes) "
                  + "VALUES (?, ?, ?, ?, ?, ?, ?)"
                );
                sql.setInt(1, this.userId);
                sql.setInt(2, this.tableId);
                sql.setString(3, this.reservationDate);
                sql.setString(4, this.reservationTime);
                sql.setString(5, this.endTime);
                sql.setInt(6, this.guestCount);
                sql.setString(7, this.notes);
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
                    "UPDATE reservations SET status = ?, notes = ? WHERE id = ?"
                );
                sql.setString(1, this.status);
                sql.setString(2, this.notes);
                sql.setInt(3, this.id);
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
                    "DELETE FROM reservations WHERE id = ?"
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