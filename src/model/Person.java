package model;

import java.io.Serializable;

public class Person implements Serializable {

    public final static long serialVersionUID = 1;
    String names;
    String lastNames;
    String idNumber;
    User createdBy;
    User lastModifiedBy;

    public Person(String names, String lastNames, String idNumber, User createdBy, User lastModifiedBy) {
        this.names = names;
        this.lastNames = lastNames;
        this.idNumber = idNumber;
        this.createdBy = createdBy;
        this.lastModifiedBy = lastModifiedBy;
    }
}
