package model;

public class FinalProduct {

    String size;
    Double price;

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
