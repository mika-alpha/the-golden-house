package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Product implements Serializable {

    public final static long serialVersionUID = 1;
    String name;
    ProductType type;
    ArrayList<Ingredient> ingredients;
    ArrayList<String> sizes;
    ArrayList<Double> prices;
    User createdBy;
    User lastModifiedBy;

    public Product(String n, ProductType t, ArrayList<Ingredient> i){
        name = n;
        type = t;
        ingredients = i;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
