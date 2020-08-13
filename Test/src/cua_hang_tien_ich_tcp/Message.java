package cua_hang_tien_ich_tcp;

import java.io.Serializable;

public class Message implements Serializable {

    public static final int SEARCH_PRODUCT = 1;

    private Product product;
    private int choice;
    
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getChoice() {
        return choice;
    }

    public void setChoice(int choice) {
        this.choice = choice;
    }

}