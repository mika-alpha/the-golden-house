package model;

import java.io.Serializable;
import java.util.ArrayList;

public class BaseProduct implements Serializable {

    public final static long serialVersionUID = 1;
    String name;
    ProductType type;
    ArrayList<Ingredient> ingredients;
    ArrayList<FinalProduct> variations;
    User createdBy;
    User lastModifiedBy;

    public BaseProduct(String n, ProductType t, ArrayList<Ingredient> i){
        variations = new ArrayList<>();
        name = n;
        type = t;
        ingredients = i;
    }

    public String getName() {
        return name;
    }

    public ProductType getType() {
        return type;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }


    public String getTypeName(){
        return type.getName();
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<FinalProduct> getVariations(){
        return variations;
    }
}
