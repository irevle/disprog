
package com.foodreservation.service;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.foodreservation.service package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _AddTable_QNAME = new QName("http://service.foodreservation.com/", "addTable");
    private final static QName _AddTableResponse_QNAME = new QName("http://service.foodreservation.com/", "addTableResponse");
    private final static QName _CancelReservation_QNAME = new QName("http://service.foodreservation.com/", "cancelReservation");
    private final static QName _CancelReservationResponse_QNAME = new QName("http://service.foodreservation.com/", "cancelReservationResponse");
    private final static QName _CheckLogin_QNAME = new QName("http://service.foodreservation.com/", "checkLogin");
    private final static QName _CheckLoginResponse_QNAME = new QName("http://service.foodreservation.com/", "checkLoginResponse");
    private final static QName _CreateOrderItem_QNAME = new QName("http://service.foodreservation.com/", "createOrderItem");
    private final static QName _CreateOrderItemResponse_QNAME = new QName("http://service.foodreservation.com/", "createOrderItemResponse");
    private final static QName _CreateReservation_QNAME = new QName("http://service.foodreservation.com/", "createReservation");
    private final static QName _CreateReservationResponse_QNAME = new QName("http://service.foodreservation.com/", "createReservationResponse");
    private final static QName _DeleteTable_QNAME = new QName("http://service.foodreservation.com/", "deleteTable");
    private final static QName _DeleteTableResponse_QNAME = new QName("http://service.foodreservation.com/", "deleteTableResponse");
    private final static QName _GetUserRole_QNAME = new QName("http://service.foodreservation.com/", "getUserRole");
    private final static QName _GetUserRoleResponse_QNAME = new QName("http://service.foodreservation.com/", "getUserRoleResponse");
    private final static QName _Register_QNAME = new QName("http://service.foodreservation.com/", "register");
    private final static QName _RegisterResponse_QNAME = new QName("http://service.foodreservation.com/", "registerResponse");
    private final static QName _SearchMenu_QNAME = new QName("http://service.foodreservation.com/", "searchMenu");
    private final static QName _SearchMenuResponse_QNAME = new QName("http://service.foodreservation.com/", "searchMenuResponse");
    private final static QName _UpdateOrderStatus_QNAME = new QName("http://service.foodreservation.com/", "updateOrderStatus");
    private final static QName _UpdateOrderStatusResponse_QNAME = new QName("http://service.foodreservation.com/", "updateOrderStatusResponse");
    private final static QName _UpdateTable_QNAME = new QName("http://service.foodreservation.com/", "updateTable");
    private final static QName _UpdateTableResponse_QNAME = new QName("http://service.foodreservation.com/", "updateTableResponse");
    private final static QName _ViewAllReservations_QNAME = new QName("http://service.foodreservation.com/", "viewAllReservations");
    private final static QName _ViewAllReservationsResponse_QNAME = new QName("http://service.foodreservation.com/", "viewAllReservationsResponse");
    private final static QName _ViewMenuItems_QNAME = new QName("http://service.foodreservation.com/", "viewMenuItems");
    private final static QName _ViewMenuItemsResponse_QNAME = new QName("http://service.foodreservation.com/", "viewMenuItemsResponse");
    private final static QName _ViewOrderItems_QNAME = new QName("http://service.foodreservation.com/", "viewOrderItems");
    private final static QName _ViewOrderItemsResponse_QNAME = new QName("http://service.foodreservation.com/", "viewOrderItemsResponse");
    private final static QName _ViewReservations_QNAME = new QName("http://service.foodreservation.com/", "viewReservations");
    private final static QName _ViewReservationsResponse_QNAME = new QName("http://service.foodreservation.com/", "viewReservationsResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.foodreservation.service
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AddTable }
     * 
     */
    public AddTable createAddTable() {
        return new AddTable();
    }

    /**
     * Create an instance of {@link AddTableResponse }
     * 
     */
    public AddTableResponse createAddTableResponse() {
        return new AddTableResponse();
    }

    /**
     * Create an instance of {@link CancelReservation }
     * 
     */
    public CancelReservation createCancelReservation() {
        return new CancelReservation();
    }

    /**
     * Create an instance of {@link CancelReservationResponse }
     * 
     */
    public CancelReservationResponse createCancelReservationResponse() {
        return new CancelReservationResponse();
    }

    /**
     * Create an instance of {@link CheckLogin }
     * 
     */
    public CheckLogin createCheckLogin() {
        return new CheckLogin();
    }

    /**
     * Create an instance of {@link CheckLoginResponse }
     * 
     */
    public CheckLoginResponse createCheckLoginResponse() {
        return new CheckLoginResponse();
    }

    /**
     * Create an instance of {@link CreateOrderItem }
     * 
     */
    public CreateOrderItem createCreateOrderItem() {
        return new CreateOrderItem();
    }

    /**
     * Create an instance of {@link CreateOrderItemResponse }
     * 
     */
    public CreateOrderItemResponse createCreateOrderItemResponse() {
        return new CreateOrderItemResponse();
    }

    /**
     * Create an instance of {@link CreateReservation }
     * 
     */
    public CreateReservation createCreateReservation() {
        return new CreateReservation();
    }

    /**
     * Create an instance of {@link CreateReservationResponse }
     * 
     */
    public CreateReservationResponse createCreateReservationResponse() {
        return new CreateReservationResponse();
    }

    /**
     * Create an instance of {@link DeleteTable }
     * 
     */
    public DeleteTable createDeleteTable() {
        return new DeleteTable();
    }

    /**
     * Create an instance of {@link DeleteTableResponse }
     * 
     */
    public DeleteTableResponse createDeleteTableResponse() {
        return new DeleteTableResponse();
    }

    /**
     * Create an instance of {@link GetUserRole }
     * 
     */
    public GetUserRole createGetUserRole() {
        return new GetUserRole();
    }

    /**
     * Create an instance of {@link GetUserRoleResponse }
     * 
     */
    public GetUserRoleResponse createGetUserRoleResponse() {
        return new GetUserRoleResponse();
    }

    /**
     * Create an instance of {@link Register }
     * 
     */
    public Register createRegister() {
        return new Register();
    }

    /**
     * Create an instance of {@link RegisterResponse }
     * 
     */
    public RegisterResponse createRegisterResponse() {
        return new RegisterResponse();
    }

    /**
     * Create an instance of {@link SearchMenu }
     * 
     */
    public SearchMenu createSearchMenu() {
        return new SearchMenu();
    }

    /**
     * Create an instance of {@link SearchMenuResponse }
     * 
     */
    public SearchMenuResponse createSearchMenuResponse() {
        return new SearchMenuResponse();
    }

    /**
     * Create an instance of {@link UpdateOrderStatus }
     * 
     */
    public UpdateOrderStatus createUpdateOrderStatus() {
        return new UpdateOrderStatus();
    }

    /**
     * Create an instance of {@link UpdateOrderStatusResponse }
     * 
     */
    public UpdateOrderStatusResponse createUpdateOrderStatusResponse() {
        return new UpdateOrderStatusResponse();
    }

    /**
     * Create an instance of {@link UpdateTable }
     * 
     */
    public UpdateTable createUpdateTable() {
        return new UpdateTable();
    }

    /**
     * Create an instance of {@link UpdateTableResponse }
     * 
     */
    public UpdateTableResponse createUpdateTableResponse() {
        return new UpdateTableResponse();
    }

    /**
     * Create an instance of {@link ViewAllReservations }
     * 
     */
    public ViewAllReservations createViewAllReservations() {
        return new ViewAllReservations();
    }

    /**
     * Create an instance of {@link ViewAllReservationsResponse }
     * 
     */
    public ViewAllReservationsResponse createViewAllReservationsResponse() {
        return new ViewAllReservationsResponse();
    }

    /**
     * Create an instance of {@link ViewMenuItems }
     * 
     */
    public ViewMenuItems createViewMenuItems() {
        return new ViewMenuItems();
    }

    /**
     * Create an instance of {@link ViewMenuItemsResponse }
     * 
     */
    public ViewMenuItemsResponse createViewMenuItemsResponse() {
        return new ViewMenuItemsResponse();
    }

    /**
     * Create an instance of {@link ViewOrderItems }
     * 
     */
    public ViewOrderItems createViewOrderItems() {
        return new ViewOrderItems();
    }

    /**
     * Create an instance of {@link ViewOrderItemsResponse }
     * 
     */
    public ViewOrderItemsResponse createViewOrderItemsResponse() {
        return new ViewOrderItemsResponse();
    }

    /**
     * Create an instance of {@link ViewReservations }
     * 
     */
    public ViewReservations createViewReservations() {
        return new ViewReservations();
    }

    /**
     * Create an instance of {@link ViewReservationsResponse }
     * 
     */
    public ViewReservationsResponse createViewReservationsResponse() {
        return new ViewReservationsResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddTable }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AddTable }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.foodreservation.com/", name = "addTable")
    public JAXBElement<AddTable> createAddTable(AddTable value) {
        return new JAXBElement<AddTable>(_AddTable_QNAME, AddTable.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddTableResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AddTableResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.foodreservation.com/", name = "addTableResponse")
    public JAXBElement<AddTableResponse> createAddTableResponse(AddTableResponse value) {
        return new JAXBElement<AddTableResponse>(_AddTableResponse_QNAME, AddTableResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CancelReservation }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CancelReservation }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.foodreservation.com/", name = "cancelReservation")
    public JAXBElement<CancelReservation> createCancelReservation(CancelReservation value) {
        return new JAXBElement<CancelReservation>(_CancelReservation_QNAME, CancelReservation.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CancelReservationResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CancelReservationResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.foodreservation.com/", name = "cancelReservationResponse")
    public JAXBElement<CancelReservationResponse> createCancelReservationResponse(CancelReservationResponse value) {
        return new JAXBElement<CancelReservationResponse>(_CancelReservationResponse_QNAME, CancelReservationResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckLogin }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CheckLogin }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.foodreservation.com/", name = "checkLogin")
    public JAXBElement<CheckLogin> createCheckLogin(CheckLogin value) {
        return new JAXBElement<CheckLogin>(_CheckLogin_QNAME, CheckLogin.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckLoginResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CheckLoginResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.foodreservation.com/", name = "checkLoginResponse")
    public JAXBElement<CheckLoginResponse> createCheckLoginResponse(CheckLoginResponse value) {
        return new JAXBElement<CheckLoginResponse>(_CheckLoginResponse_QNAME, CheckLoginResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateOrderItem }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CreateOrderItem }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.foodreservation.com/", name = "createOrderItem")
    public JAXBElement<CreateOrderItem> createCreateOrderItem(CreateOrderItem value) {
        return new JAXBElement<CreateOrderItem>(_CreateOrderItem_QNAME, CreateOrderItem.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateOrderItemResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CreateOrderItemResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.foodreservation.com/", name = "createOrderItemResponse")
    public JAXBElement<CreateOrderItemResponse> createCreateOrderItemResponse(CreateOrderItemResponse value) {
        return new JAXBElement<CreateOrderItemResponse>(_CreateOrderItemResponse_QNAME, CreateOrderItemResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateReservation }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CreateReservation }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.foodreservation.com/", name = "createReservation")
    public JAXBElement<CreateReservation> createCreateReservation(CreateReservation value) {
        return new JAXBElement<CreateReservation>(_CreateReservation_QNAME, CreateReservation.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateReservationResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CreateReservationResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.foodreservation.com/", name = "createReservationResponse")
    public JAXBElement<CreateReservationResponse> createCreateReservationResponse(CreateReservationResponse value) {
        return new JAXBElement<CreateReservationResponse>(_CreateReservationResponse_QNAME, CreateReservationResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteTable }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DeleteTable }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.foodreservation.com/", name = "deleteTable")
    public JAXBElement<DeleteTable> createDeleteTable(DeleteTable value) {
        return new JAXBElement<DeleteTable>(_DeleteTable_QNAME, DeleteTable.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteTableResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DeleteTableResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.foodreservation.com/", name = "deleteTableResponse")
    public JAXBElement<DeleteTableResponse> createDeleteTableResponse(DeleteTableResponse value) {
        return new JAXBElement<DeleteTableResponse>(_DeleteTableResponse_QNAME, DeleteTableResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserRole }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetUserRole }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.foodreservation.com/", name = "getUserRole")
    public JAXBElement<GetUserRole> createGetUserRole(GetUserRole value) {
        return new JAXBElement<GetUserRole>(_GetUserRole_QNAME, GetUserRole.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserRoleResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetUserRoleResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.foodreservation.com/", name = "getUserRoleResponse")
    public JAXBElement<GetUserRoleResponse> createGetUserRoleResponse(GetUserRoleResponse value) {
        return new JAXBElement<GetUserRoleResponse>(_GetUserRoleResponse_QNAME, GetUserRoleResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Register }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Register }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.foodreservation.com/", name = "register")
    public JAXBElement<Register> createRegister(Register value) {
        return new JAXBElement<Register>(_Register_QNAME, Register.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RegisterResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.foodreservation.com/", name = "registerResponse")
    public JAXBElement<RegisterResponse> createRegisterResponse(RegisterResponse value) {
        return new JAXBElement<RegisterResponse>(_RegisterResponse_QNAME, RegisterResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchMenu }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SearchMenu }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.foodreservation.com/", name = "searchMenu")
    public JAXBElement<SearchMenu> createSearchMenu(SearchMenu value) {
        return new JAXBElement<SearchMenu>(_SearchMenu_QNAME, SearchMenu.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchMenuResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SearchMenuResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.foodreservation.com/", name = "searchMenuResponse")
    public JAXBElement<SearchMenuResponse> createSearchMenuResponse(SearchMenuResponse value) {
        return new JAXBElement<SearchMenuResponse>(_SearchMenuResponse_QNAME, SearchMenuResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateOrderStatus }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UpdateOrderStatus }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.foodreservation.com/", name = "updateOrderStatus")
    public JAXBElement<UpdateOrderStatus> createUpdateOrderStatus(UpdateOrderStatus value) {
        return new JAXBElement<UpdateOrderStatus>(_UpdateOrderStatus_QNAME, UpdateOrderStatus.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateOrderStatusResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UpdateOrderStatusResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.foodreservation.com/", name = "updateOrderStatusResponse")
    public JAXBElement<UpdateOrderStatusResponse> createUpdateOrderStatusResponse(UpdateOrderStatusResponse value) {
        return new JAXBElement<UpdateOrderStatusResponse>(_UpdateOrderStatusResponse_QNAME, UpdateOrderStatusResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateTable }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UpdateTable }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.foodreservation.com/", name = "updateTable")
    public JAXBElement<UpdateTable> createUpdateTable(UpdateTable value) {
        return new JAXBElement<UpdateTable>(_UpdateTable_QNAME, UpdateTable.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateTableResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UpdateTableResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.foodreservation.com/", name = "updateTableResponse")
    public JAXBElement<UpdateTableResponse> createUpdateTableResponse(UpdateTableResponse value) {
        return new JAXBElement<UpdateTableResponse>(_UpdateTableResponse_QNAME, UpdateTableResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ViewAllReservations }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ViewAllReservations }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.foodreservation.com/", name = "viewAllReservations")
    public JAXBElement<ViewAllReservations> createViewAllReservations(ViewAllReservations value) {
        return new JAXBElement<ViewAllReservations>(_ViewAllReservations_QNAME, ViewAllReservations.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ViewAllReservationsResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ViewAllReservationsResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.foodreservation.com/", name = "viewAllReservationsResponse")
    public JAXBElement<ViewAllReservationsResponse> createViewAllReservationsResponse(ViewAllReservationsResponse value) {
        return new JAXBElement<ViewAllReservationsResponse>(_ViewAllReservationsResponse_QNAME, ViewAllReservationsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ViewMenuItems }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ViewMenuItems }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.foodreservation.com/", name = "viewMenuItems")
    public JAXBElement<ViewMenuItems> createViewMenuItems(ViewMenuItems value) {
        return new JAXBElement<ViewMenuItems>(_ViewMenuItems_QNAME, ViewMenuItems.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ViewMenuItemsResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ViewMenuItemsResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.foodreservation.com/", name = "viewMenuItemsResponse")
    public JAXBElement<ViewMenuItemsResponse> createViewMenuItemsResponse(ViewMenuItemsResponse value) {
        return new JAXBElement<ViewMenuItemsResponse>(_ViewMenuItemsResponse_QNAME, ViewMenuItemsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ViewOrderItems }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ViewOrderItems }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.foodreservation.com/", name = "viewOrderItems")
    public JAXBElement<ViewOrderItems> createViewOrderItems(ViewOrderItems value) {
        return new JAXBElement<ViewOrderItems>(_ViewOrderItems_QNAME, ViewOrderItems.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ViewOrderItemsResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ViewOrderItemsResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.foodreservation.com/", name = "viewOrderItemsResponse")
    public JAXBElement<ViewOrderItemsResponse> createViewOrderItemsResponse(ViewOrderItemsResponse value) {
        return new JAXBElement<ViewOrderItemsResponse>(_ViewOrderItemsResponse_QNAME, ViewOrderItemsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ViewReservations }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ViewReservations }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.foodreservation.com/", name = "viewReservations")
    public JAXBElement<ViewReservations> createViewReservations(ViewReservations value) {
        return new JAXBElement<ViewReservations>(_ViewReservations_QNAME, ViewReservations.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ViewReservationsResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ViewReservationsResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.foodreservation.com/", name = "viewReservationsResponse")
    public JAXBElement<ViewReservationsResponse> createViewReservationsResponse(ViewReservationsResponse value) {
        return new JAXBElement<ViewReservationsResponse>(_ViewReservationsResponse_QNAME, ViewReservationsResponse.class, null, value);
    }

}
