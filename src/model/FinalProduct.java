package model;

public class FinalProduct {

    String productName;
    String size;
    Double price;

    public FinalProduct(String s, Double p, String n){
        size = s;
        price = p;
        productName = n;
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
