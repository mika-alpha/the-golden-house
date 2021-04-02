package model;

import java.io.Serializable;

public class Person implements Serializable {

    public final static long serialVersionUID = 1;
    String names;
    String lastNames;
    long idNumber;
    User createdBy;
    User lastModifiedBy;

    public Person(String names, String lastNames, long idNumber, User createdBy, User lastModifiedBy) {
        this.names = names;
        this.lastNames = lastNames;
        this.idNumber = idNumber;
        this.createdBy = createdBy;
        this.lastModifiedBy = lastModifiedBy;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getLastNames() {
        return lastNames;
    }

    public void setLastNames(String lastNames) {
        this.lastNames = lastNames;
    }

    public long getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(long idNumber) {
        this.idNumber = idNumber;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public User getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(User lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }
}
