package quan_ly_thu_vien;

import java.io.Serializable;

class Book implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private String publisher;
    private int total;
    private int amount_borrowed;

    public Book() {
    }

    public Book(int id, String name, String publisher, int total, int amount_borrowed) {
        this.id = id;
        this.name = name;
        this.publisher = publisher;
        this.total = total;
        this.amount_borrowed = amount_borrowed;
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

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getAmount_borrowed() {
        return amount_borrowed;
    }

    public void setAmount_borrowed(int amount_borrowed) {
        this.amount_borrowed = amount_borrowed;
    }

    @Override
    public String toString() {
        return "Book [amount_borrowed=" + amount_borrowed + ", id=" + id + ", name=" + name + ", publisher=" + publisher
                + ", total=" + total + "]";
    }
}