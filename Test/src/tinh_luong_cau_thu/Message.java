package tinh_luong_cau_thu;

import java.io.Serializable;

public class Message implements Serializable {

    public static final int ADD_PLAYER = 1;
    public static final int ANY_PLAYER_SALARY = 2;

    private int choice;
    private Player player;
    private String response;

    public Message() {
    }

    public Message(int choice, Player player, String response) {
        this.choice = choice;
        this.player = player;
        this.response = response;
    }

    public static int getAddPlayer() {
        return ADD_PLAYER;
    }

    public static int getAnyPlayerSalary() {
        return ANY_PLAYER_SALARY;
    }

    public int getChoice() {
        return choice;
    }

    public void setChoice(int choice) {
        this.choice = choice;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}