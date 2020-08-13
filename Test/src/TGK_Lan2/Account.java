package TGK_Lan2;

import java.io.Serializable;
import java.util.Date;

public class Account implements Serializable {

    private int id;
    private float balance;
    private Date deposit_day;
    private Date withdraw_day;

    public Account() {
    }

    public Account(int id, float balance, Date deposit_day, Date withdraw_day) {
        this.id = id;
        this.balance = balance;
        this.deposit_day = deposit_day;
        this.withdraw_day = withdraw_day;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public Date getDeposit_day() {
        return deposit_day;
    }

    public void setDeposit_day(Date deposit_day) {
        this.deposit_day = deposit_day;
    }

    public Date getWithdraw_day() {
        return withdraw_day;
    }

    public void setWithdraw_day(Date withdraw_day) {
        this.withdraw_day = withdraw_day;
    }

    @Override
    public String toString() {
        return "Account [balance=" + balance + ", deposit_day=" + deposit_day + ", id=" + id + ", withdraw_day="
                + withdraw_day + "]";
    }
}