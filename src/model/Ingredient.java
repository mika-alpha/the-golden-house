package model;

import java.io.Serializable;

public class Ingredient implements Serializable {

    public final static long serialVersionUID = 1;
    String name;
    boolean available;
    User createdBy;
    User lastModifiedBy;

    public Ingredient(String n){
        name = n;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAvailable(boolean a){
        available = a;
    }

}
