package model;

public class OrderProduct {

    String name;
    Double price;
    Integer quantity;
    String size;

    public OrderProduct(String name, Double price, Integer quantity, String size) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.size = size;
    }


    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getSize() {
        return size;
    }
}
