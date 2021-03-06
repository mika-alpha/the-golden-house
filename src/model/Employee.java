package model;

import java.util.Objects;

public class Employee extends Person{


    public Employee(String names, String lastNames, long idNumber, User createdBy, User lastModifiedBy) {
        super(Objects.requireNonNull(names), Objects.requireNonNull(lastNames), idNumber, createdBy, lastModifiedBy);
    }
}
