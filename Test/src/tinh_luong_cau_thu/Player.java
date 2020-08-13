package tinh_luong_cau_thu;

import java.io.Serializable;

public class Player implements Serializable {
    private int id;
    private String name;
    private int year_of_birth;
    private String position;
    private float salary;

    public Player() {
    }

    public Player(int id, String name, int year_of_birth, String position, float salary) {
        this.id = id;
        this.name = name;
        this.year_of_birth = year_of_birth;
        this.position = position;
        this.salary = salary;
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

    public int getYear_of_birth() {
        return year_of_birth;
    }

    public void setYear_of_birth(int year_of_birth) {
        this.year_of_birth = year_of_birth;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Player [id=" + id + ", name=" + name + ", position=" + position + ", salary=" + salary
                + ", year_of_birth=" + year_of_birth + "]";
    }

}