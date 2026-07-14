/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clienttcp;

import java.io.*;
import java.net.*;


/**
 *
 * @author elve
 */
public class SocketClient {
    private Socket socket;
    private BufferedReader reader;
    private DataOutputStream writer;

    public SocketClient() throws IOException {
        socket = new Socket("localhost", 6000);
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new DataOutputStream(socket.getOutputStream());
        reader.readLine(); // consume welcome "OK|Connected..."
    }

    private String sendCommand(String cmd) throws IOException {
        while (reader.ready()) {
            reader.readLine();
        }
        writer.writeBytes(cmd + "\n");
        return reader.readLine();
    }

    // Returns [role, userId] on success, null on failure
    public String[] login(String username, String password) throws IOException {
        String res = sendCommand("LOGIN|" + username + "|" + password);
        if (res.startsWith("OK|")) {
            String[] parts = res.split("\\|");
            return new String[]{parts[1], parts[2]};  // [role, userId]
        }
        return null;
    }

    public int reserve(int userId, String date, String time,
                       String endTime, int guests, String notes) throws IOException {
        String res = sendCommand("RESERVE|" + userId + "|" + date
            + "|" + time + "|" + endTime + "|" + guests + "|" + notes);
        if (res.startsWith("OK|")) return Integer.parseInt(res.split("\\|")[1]);
        return -1;
    }

    public boolean cancelReservation(int reservationId) throws IOException {
        return sendCommand("CANCEL_RESERVATION|" + reservationId).startsWith("OK");
    }

    public String[] getMenu() throws IOException {
        String res = sendCommand("GET_MENU");
        if (res.startsWith("OK|"))
            return res.substring(3).split("~");
        return new String[0];
    }

    public boolean addOrderItem(int reservationId, int menuItemId,
                                 int quantity, double subtotal) throws IOException {
        return sendCommand("ADD_ORDER_ITEM|" + reservationId + "|"
            + menuItemId + "|" + quantity + "|" + subtotal).startsWith("OK");
    }

    public String[] getAllReservations() throws IOException {
        String res = sendCommand("GET_ALL_RESERVATIONS");
        if (res.startsWith("OK|"))
            return res.substring(3).split("~");
        return new String[0];
    }

    public String[] getOrderItems(int reservationId) throws IOException {
        String res = sendCommand("GET_ORDER_ITEMS|" + reservationId);
        if (res.startsWith("OK|"))
            return res.substring(3).split("~");
        return new String[0];
    }

    public String[] getAllOrderItems() throws IOException {
        String res = sendCommand("GET_ALL_ORDER_ITEMS");
        if (res.startsWith("OK|"))
            return res.substring(3).split("~");
        return new String[0];
    }

    public boolean register(String username, String password,
                             String fullName, String email, String phone) throws IOException {
        return sendCommand("REGISTER|" + username + "|" + password
            + "|" + fullName + "|" + email + "|" + phone).startsWith("OK");
    }

    public String[] getHistory(int userId) throws IOException {
        String res = sendCommand("GET_HISTORY|" + userId);
        if (res.startsWith("OK|"))
            return res.substring(3).split("~");
        return new String[0];
    }

    public int pay(int reservationId, double amount, String method) throws IOException {
        String res = sendCommand("PAY|" + reservationId + "|" + amount + "|" + method);
        if (res.startsWith("OK|")) return Integer.parseInt(res.split("\\|")[1]);
        return -1;
    }

    public boolean updateOrderStatus(int orderItemId, String status) throws IOException {
        return sendCommand("UPDATE_ORDER_STATUS|" + orderItemId + "|" + status).startsWith("OK");
    }

    public boolean updateReservation(int id, String tanggal, String jam,
                                      String meja, int tamu, String status) throws IOException {
        return sendCommand("UPDATE_RESERVATION|" + id + "|"
            + tanggal + "|" + jam + "|" + meja + "|" + tamu + "|" + status).startsWith("OK");
    }

    public String[] getTables() throws IOException {
        String res = sendCommand("GET_TABLES");
        if (res.startsWith("OK|"))
            return res.substring(3).split("~");
        return new String[0];
    }

    public boolean updateTableStatus(int tableId, String status) throws IOException {
        return sendCommand("UPDATE_TABLE_STATUS|" + tableId + "|" + status).startsWith("OK");
    }

    public void close() throws IOException {
        socket.close();
    }
}
