package cua_hang_tien_ich_tcp;

import java.io.Serializable;

public class Product implements Serializable {

    private int id;
    private String name;
    private float price;
    private int amount;

    public Product() {
    }

    public Product(int id, String name, float price, int amount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.amount = amount;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Product [amount=" + amount + ", id=" + id + ", name=" + name + ", price=" + price + "]";
    }

}