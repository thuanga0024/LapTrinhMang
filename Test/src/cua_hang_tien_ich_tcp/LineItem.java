package cua_hang_tien_ich_tcp;

import java.io.Serializable;

public class LineItem implements Serializable {

    private Product product;
    private int amount;

    public LineItem() {
    }

    public LineItem(Product product, int amount) {
        this.product = product;
        this.amount = amount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    
}