package TGK_Lan2;

import java.io.Serializable;
import java.util.List;

public class Message implements Serializable {
    
    public static final int GET_LIST_ACCOUNT = 1;
    public static final int REGIST_ACCOUNT = 2;
    public static final int WITHDRAW = 3;
    public static final int DEPOSIT = 4;

    private int choice;
    private Account account;
    private List<Account> list_account;
    private String reponse;

    public Message() {
    }

    public int getChoice() {
        return choice;
    }

    public void setChoice(int choice) {
        this.choice = choice;
    }

    public List<Account> getList_account() {
        return list_account;
    }

    public void setList_account(List<Account> list_account) {
        this.list_account = list_account;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

}