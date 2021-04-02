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

}
