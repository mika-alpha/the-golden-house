package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

enum Status{
    REQUESTED("SOLICITADO"), IN_PROCESS("EN PROCESO"), SENT("ENVIADO"), DELIVERED("ENTREGADO"), CANCELED("CANCELADO");
    String status;
    Status(String s){
        status = s;
    }
}


public class Order implements Serializable {

    public final static long serialVersionUID = 1;
    String code;
    Status status;
    ArrayList<BaseProduct> baseProducts;
    ArrayList<Integer> quantities;
    ArrayList<Integer> selectedSp;
    Customer customer;
    Employee deliveredBy;
    LocalDate orderDate;
    String comments;
    User createdBy;
    User lastModifiedBy;

    public Order(Customer c, Employee e, String com, User cb){
        quantities = new ArrayList<>();
        baseProducts = new ArrayList<>();
        selectedSp = new ArrayList<>();
        status = Status.REQUESTED;
        customer = c;
        deliveredBy = e;
        comments = com;
        createdBy = cb;
    }

    public String getCode() {
        return code;
    }

    public String getStatus() {
        return status.status;
    }

    public ArrayList<BaseProduct> getProducts() {
        return baseProducts;
    }

    public ArrayList<Integer> getQuantities() {
        return quantities;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Employee getDeliveredBy() {
        return deliveredBy;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public String getComments() {
        return comments;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public User getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setBaseProducts(ArrayList<BaseProduct> baseProducts) {
        this.baseProducts = baseProducts;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setDeliveredBy(Employee deliveredBy) {
        this.deliveredBy = deliveredBy;
    }

    public void setOrderDate() {
        orderDate = LocalDate.now();
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public ArrayList<Integer> getSelectedSp(){
        return selectedSp;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void generateCode(String code){
        if (!code.equals("")) {
            StringBuilder sb = new StringBuilder(code);
            sb.trimToSize();
            char alpha = sb.charAt(0);
            String numeric = sb.substring(1, sb.length());
            int num = Integer.parseInt(numeric);
            if (num == 9999) {
                alpha++;
                numeric = String.format("%04d",0);
            } else {
                num++;
                numeric = String.format("%04d", num);
            }
            setCode(alpha + numeric);
        } else {
            setCode("A0000");
        }
    }



}
