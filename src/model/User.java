package model;

import java.io.Serializable;

public class User implements Serializable {

    public final static long serialVersionUID = 1;
    String username;
    String password;
    Employee employee;

    public User(String username, String password, Employee employee) {
        this.username = username;
        this.password = password;
        this.employee = employee;
    }
}
