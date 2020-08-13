package quan_ly_thong_tin_sinh_vien_tcp;

import java.io.Serializable;
import java.util.Date;

public class Student implements Serializable {

    private int id;
    private String name;
    private Date birthday;
    private String country;

    public Student() {
    }

    public Student(int id, String name, Date birthday, String country) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.country = country;
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Student [birthday=" + birthday + ", country=" + country + ", id=" + id + ", name=" + name + "]";
    }

    
    
}