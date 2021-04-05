package model;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {

    public final static long serialVersionUID = 1;
    String username;
    String password;
    Employee employee;

    public User(String username, String password, Employee employee) {
        this.username = Objects.requireNonNull(username);
        this.password = Objects.requireNonNull(password);
        this.employee = Objects.requireNonNull(employee);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
