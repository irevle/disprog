/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package servertcp;

import com.foodreservation.model.MenuItem;
import com.foodreservation.model.OrderItem;
import com.foodreservation.model.Payment;
import com.foodreservation.model.Reservation;
import com.foodreservation.model.Table;
import com.foodreservation.model.User;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * TCP Server for the Food Reservation and Ordering System.
 * Listens for client connections, and for each one spins up a HandleSocket
 * thread. Commands coming from clients are pipe-delimited strings, e.g.
 *   LOGIN|username|password
 *   RESERVE|userId|date|time|endTime|guests|notes
 * Responses sent back follow the same style:
 *   OK|...data...
 *   ERR|message
 * Rows inside a response are separated by "~", fields inside a row by ";"
 * (this matches the format Person 3 already used in FoodReservationWS).
 *
 * @author alievar
 */
public class FormServer extends javax.swing.JFrame implements Runnable {

    Socket incoming;
    ServerSocket s;
    Thread t;
    ArrayList<HandleSocket> clients = new ArrayList<HandleSocket>();

    // model instances - reused across all commands (matches Person 3's WS pattern)
    User userModel;
    Table tableModel;
    MenuItem menuModel;
    Reservation reservationModel;
    OrderItem orderModel;
    Payment paymentModel;

    public FormServer() throws IOException {
        initComponents();

        userModel = new User();
        tableModel = new Table();
        menuModel = new MenuItem();
        reservationModel = new Reservation();
        orderModel = new OrderItem();
        paymentModel = new Payment();

        try {
            s = new ServerSocket(6000);
            if (t == null) {
                t = new Thread(this, "Food Reservation Server");
                t.start();
            }
        } catch (Exception Ex) {
            System.out.println(Ex);
        }
    }

    // prints a line to the server's log window
    public void log(String msg) {
        txtChat.append(msg + "\n");
    }

    public void removeClient(HandleSocket hs) {
        clients.remove(hs);
        log("-- a client disconnected --");
    }

    // send a line to every connected client (pass null for sender to broadcast to all)
    public void broadCast(String tmp, HandleSocket sender) {
        for (HandleSocket c : clients) {
            if (c != sender) {
                c.sendMsg(tmp);
            }
        }
    }

    /**
     * Main dispatch: parses "COMMAND|arg1|arg2|..." and routes to the
     * matching business method. Reply goes back to the requesting client only;
     * broadCast() is used separately for real-time updates to everyone else.
     */
    public void handleCommand(HandleSocket client, String commandLine) {
        try {
            String[] parts = commandLine.split("\\|", -1);
            String cmd = parts[0];

            switch (cmd) {

                case "LOGIN": {
                    String username = parts[1];
                    String password = parts[2];
                    boolean ok = userModel.checkLogin(username, password);
                    if (ok) {
                        String role = userModel.getUserRole(username);
                        int userId = userModel.getUserId(username);  // need to add this method
                        client.sendMsg("OK|" + role + "|" + userId);
                        log(username + " logged in");
                    } else {
                        client.sendMsg("ERR|Invalid username or password");
                    }
                    break;
                }

                case "REGISTER": {
                    String username = parts[1];
                    String password = parts[2];
                    String fullName = parts[3];
                    String email = parts[4];
                    String phone = parts[5];
                    boolean ok = userModel.register(username, password, fullName, email, phone);
                    if (ok) {
                        client.sendMsg("OK|Registration success");
                        log("New user registered: " + username);
                    } else {
                        client.sendMsg("ERR|Username already taken");
                    }
                    break;
                }

                case "GET_TABLES": {
                    ArrayList<String> rows = tableModel.viewListDataString();
                    client.sendMsg("OK|" + String.join("~", rows));
                    break;
                }

                case "GET_MENU": {
                    ArrayList<String> rows = menuModel.viewListDataString();
                    client.sendMsg("OK|" + String.join("~", rows));
                    break;
                }

                case "RESERVE": {
                    int userId = Integer.parseInt(parts[1]);
                    String date = parts[2];
                    String time = parts[3];
                    String endTime = parts[4];
                    int guests = Integer.parseInt(parts[5]);
                    String notes = parts.length > 6 ? parts[6] : "";

                    int reservationId = reservationModel.createReservation(
                        userId, date, time, endTime, guests, notes
                    );
                    if (reservationId != -1) {
                        client.sendMsg("OK|" + reservationId);
                        log("Reservation #" + reservationId + " created for user " + userId);
                        broadCast("RESERVATION_UPDATE|" + reservationId + "|CONFIRMED", client);
                    } else {
                        client.sendMsg("ERR|No table available for that date/time/guest count");
                    }
                    break;
                }

                case "CANCEL_RESERVATION": {
                    int reservationId = Integer.parseInt(parts[1]);
                    boolean ok = reservationModel.cancelReservation(reservationId);
                    if (ok) {
                        client.sendMsg("OK|Reservation cancelled");
                        broadCast("RESERVATION_UPDATE|" + reservationId + "|CANCELLED", client);
                    } else {
                        client.sendMsg("ERR|Could not cancel reservation");
                    }
                    break;
                }

                case "UPDATE_RESERVATION": {
                    int id = Integer.parseInt(parts[1]);
                    String tanggal = parts[2];
                    String jam = parts[3];
                    String meja = parts[4];
                    int tamu = Integer.parseInt(parts[5]);
                    String status = parts[6];
                    boolean ok = reservationModel.updateReservation(id, tanggal, jam, meja, tamu, status);
                    if (ok) {
                        client.sendMsg("OK|Reservation updated");
                        broadCast("RESERVATION_UPDATE|" + id + "|" + status, client);
                    } else {
                        client.sendMsg("ERR|Could not update reservation");
                    }
                    break;
                }

                case "GET_HISTORY": {
                    int userId = Integer.parseInt(parts[1]);
                    ArrayList<String> rows = reservationModel.viewReservationsByUser(userId);
                    client.sendMsg("OK|" + String.join("~", rows));
                    break;
                }

                case "GET_ALL_RESERVATIONS": {
                    ArrayList<String> rows = reservationModel.viewAllReservations();
                    client.sendMsg("OK|" + String.join("~", rows));
                    break;
                }

                case "ADD_ORDER_ITEM": {
                    int reservationId = Integer.parseInt(parts[1]);
                    int menuItemId = Integer.parseInt(parts[2]);
                    int quantity = Integer.parseInt(parts[3]);
                    double subtotal = Double.parseDouble(parts[4]);
                    boolean ok = orderModel.createOrderItem(reservationId, menuItemId, quantity, subtotal);
                    if (ok) {
                        client.sendMsg("OK|Order item added");
                        broadCast("ORDER_UPDATE|" + reservationId + "|PENDING", client);
                    } else {
                        client.sendMsg("ERR|Could not add order item");
                    }
                    break;
                }

                case "UPDATE_ORDER_STATUS": {
                    int orderItemId = Integer.parseInt(parts[1]);
                    String status = parts[2];
                    boolean ok = orderModel.updateStatus(orderItemId, status);
                    if (ok) {
                        client.sendMsg("OK|Order status updated");
                        broadCast("ORDER_UPDATE|" + orderItemId + "|" + status, client);
                    } else {
                        client.sendMsg("ERR|Could not update order status");
                    }
                    break;
                }

                case "GET_ORDER_ITEMS": {
                    int reservationId = Integer.parseInt(parts[1]);
                    ArrayList<String> rows = orderModel.viewOrderItems(reservationId);
                    client.sendMsg("OK|" + String.join("~", rows));
                    break;
                }

                case "GET_ALL_ORDER_ITEMS": {
                    ArrayList<String> rows = orderModel.viewAllOrderItems();
                    client.sendMsg("OK|" + String.join("~", rows));
                    break;
                }

                case "PAY": {
                    int reservationId = Integer.parseInt(parts[1]);
                    double amount = Double.parseDouble(parts[2]);
                    String method = parts[3];
                    int paymentId = paymentModel.createPayment(reservationId, amount, method);
                    if (paymentId != -1) {
                        client.sendMsg("OK|" + paymentId);
                        log("Payment #" + paymentId + " received for reservation " + reservationId);
                    } else {
                        client.sendMsg("ERR|Payment failed");
                    }
                    break;
                }

                case "UPDATE_TABLE_STATUS": {
                    int tableId = Integer.parseInt(parts[1]);
                    String status = parts[2];
                    boolean ok = tableModel.updateStatus(tableId, status);
                    if (ok) {
                        client.sendMsg("OK|Table status updated");
                        broadCast("TABLE_UPDATE|" + tableId + "|" + status, client);
                    } else {
                        client.sendMsg("ERR|Could not update table status");
                    }
                    break;
                }

                default:
                    client.sendMsg("ERR|Unknown command: " + cmd);
            }
        } catch (Exception ex) {
            System.out.println("Error handleCommand: " + ex);
            client.sendMsg("ERR|Server error processing command");
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                incoming = s.accept();
                HandleSocket hs = new HandleSocket(this, incoming);
                hs.start();
                clients.add(hs);
                log("-- a client connected --");
            }
        } catch (Exception Ex) {
            System.out.println(Ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtChat = new javax.swing.JTextArea();
        txtPesan = new javax.swing.JTextField();
        btnSend = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("FOOD RESERVATION SERVER");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setText("Broadcast");

        txtChat.setColumns(20);
        txtChat.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        txtChat.setRows(5);
        jScrollPane1.setViewportView(txtChat);

        txtPesan.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        txtPesan.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        btnSend.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnSend.setText("SEND");
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 634, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(txtPesan, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(btnSend))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(274, 274, 274)
                        .addComponent(jLabel1)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtPesan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSend))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // lets the admin sitting at the server broadcast a plain announcement to everyone
    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendActionPerformed
        String msg = txtPesan.getText();
        log("Server (broadcast): " + msg);
        broadCast("ANNOUNCE|" + msg, null);
        txtPesan.setText("");
    }//GEN-LAST:event_btnSendActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new FormServer().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(FormServer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSend;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtChat;
    private javax.swing.JTextField txtPesan;
    // End of variables declaration//GEN-END:variables
}
