package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Order implements Serializable {

    public final static long serialVersionUID = 1;
    String code;
    String status;
    ArrayList<Product> products;
    ArrayList<Integer> quantities;
    Customer customer;
    Employee deliveredBy;
    LocalDate orderDate;
    String comments;
    User createdBy;
    User lastModifiedBy;

    public Order(String s, ArrayList<Product> p, ArrayList<Integer> q, Customer c, Employee e, String com, User cb){
        status = s;
        products = p;
        quantities = q;
        customer = c;
        deliveredBy = e;
        comments = com;
        orderDate = LocalDate.now();
        createdBy = cb;
    }

    public String getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }

    public ArrayList<Product> getProducts() {
        return products;
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
}
