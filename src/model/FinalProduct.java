package model;

import java.io.Serializable;

public class FinalProduct implements Serializable {

    public final static long serialVersionUID = 1;
    private String size;
    private Double price;

    public FinalProduct(String s, Double p){
        size = s;
        price = p;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setPrice(Double p){
        this.price = p;
    }

    public Double getPrice(){
        return price;
    }
}
