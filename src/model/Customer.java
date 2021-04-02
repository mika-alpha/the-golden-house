package model;

public class Customer extends Person {
    String address;
    String phoneNumber;
    String comments;

    public Customer(String names, String lastNames, long idNumber, User createdBy, User lastModifiedBy, String address, String phoneNumber, String comments) {
        super(names, lastNames, idNumber, createdBy, lastModifiedBy);
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.comments = comments;
    }
}
