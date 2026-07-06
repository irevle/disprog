/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package com.foodreservation.service;
import com.foodreservation.model.MenuItem;
import com.foodreservation.model.OrderItem;
import com.foodreservation.model.Reservation;
import com.foodreservation.model.User;
import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;


/**
 *
 * @author elve
 */
@WebService(serviceName = "FoodReservationWS")
public class FoodReservationWS {

    User userModel;
    MenuItem menuModel;
    Reservation reservationModel;
    OrderItem orderModel;

    public FoodReservationWS() {
        userModel = new User();
        menuModel = new MenuItem();
        reservationModel = new Reservation();
        orderModel = new OrderItem();
    }

    @WebMethod(operationName = "checkLogin")
    public boolean checkLogin(@WebParam(name = "username") String username,
                              @WebParam(name = "password") String password) {
        return userModel.checkLogin(username, password);
    }

    @WebMethod(operationName = "register")
    public boolean register(@WebParam(name = "username") String username,
                            @WebParam(name = "password") String password,
                            @WebParam(name = "fullName") String fullName,
                            @WebParam(name = "email") String email,
                            @WebParam(name = "phone") String phone) {
        return userModel.register(username, password, fullName, email, phone);
    }

    @WebMethod(operationName = "viewMenuItems")
    public ArrayList<String> viewMenuItems() {
        return menuModel.viewListDataString();
    }

    @WebMethod(operationName = "searchMenu")
    public ArrayList<String> searchMenu(@WebParam(name = "keyword") String keyword) {
        return menuModel.searchByNameOrCategory(keyword);
    }

    @WebMethod(operationName = "createReservation")
    public int createReservation(@WebParam(name = "userId") int userId,
                                  @WebParam(name = "date") String date,
                                  @WebParam(name = "time") String time,
                                  @WebParam(name = "endTime") String endTime,
                                  @WebParam(name = "guests") int guests,
                                  @WebParam(name = "notes") String notes) {
        return reservationModel.createReservation(userId, date, time, endTime, guests, notes);
    }

    @WebMethod(operationName = "cancelReservation")
    public boolean cancelReservation(@WebParam(name = "reservationId") int reservationId) {
        return reservationModel.cancelReservation(reservationId);
    }

    @WebMethod(operationName = "viewReservations")
    public ArrayList<String> viewReservations(@WebParam(name = "userId") int userId) {
        return reservationModel.viewReservationsByUser(userId);
    }

    @WebMethod(operationName = "viewAllReservations")
    public ArrayList<String> viewAllReservations() {
        return reservationModel.viewAllReservations();
    }

    @WebMethod(operationName = "createOrderItem")
    public boolean createOrderItem(@WebParam(name = "reservationId") int reservationId,
                                    @WebParam(name = "menuItemId") int menuItemId,
                                    @WebParam(name = "quantity") int quantity,
                                    @WebParam(name = "subtotal") double subtotal) {
        return orderModel.createOrderItem(reservationId, menuItemId, quantity, subtotal);
    }

    @WebMethod(operationName = "updateOrderStatus")
    public boolean updateOrderStatus(@WebParam(name = "orderItemId") int orderItemId,
                                      @WebParam(name = "status") String status) {
        return orderModel.updateStatus(orderItemId, status);
    }

    @WebMethod(operationName = "viewOrderItems")
    public ArrayList<String> viewOrderItems(@WebParam(name = "reservationId") int reservationId) {
        return orderModel.viewOrderItems(reservationId);
    }
}