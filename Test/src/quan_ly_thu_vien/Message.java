package quan_ly_thu_vien;

import java.io.Serializable;
import java.util.List;

public class Message implements Serializable {
    
    public static final int SHOW_INFO = 1;
    public static final int BORROW_BOOK = 2;
    public static final int RETURN_BOOK = 3;

    private int choice;
    private Book book;
    private List<Book> listBook;
    private String nameBook;
    private String reponse;

    public Message() {
    }

    public int getChoice() {
        return choice;
    }

    public void setChoice(int choice) {
        this.choice = choice;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public List<Book> getListBook() {
        return listBook;
    }

    public void setListBook(List<Book> listBook) {
        this.listBook = listBook;
    }

    public String getNameBook() {
        return nameBook;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

}