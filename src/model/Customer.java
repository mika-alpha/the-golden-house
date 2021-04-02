package model;
import java.util.Comparator;

public class Customer extends Person implements Comparable<Customer>, Comparator<Customer>{
    String address;
    String phoneNumber;
    String comments;

    public Customer(String names, String lastNames, long idNumber, User createdBy, User lastModifiedBy, String address, String phoneNumber, String comments) {
        super(names, lastNames, idNumber, createdBy, lastModifiedBy);
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.comments = comments;
    }

    @Override
    public int compareTo(Customer o) {
        return names.compareTo(o.getNames()); //this compareTo is the java.lang.String default one
    }

    @Override
    public int compare(Customer o1, Customer o2) {
        return o1.lastNames.compareTo(o2.lastNames); //this compareTo is the java.lang.String default one
    }
}
