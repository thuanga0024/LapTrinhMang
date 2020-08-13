/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cau2_QLSV_TCP_SQL;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Kira
 */
// Lớp này chứa các thông tin để truyền qua lại
public class Message implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final int XEM_DANH_SACH  =1;
    public static final int THEM_SINH_VIEN =2;
    public static final int TIM_SINH_VIEN  =3;
    
    private int chon;
    
    private List<SinhVien> dsSV;
    
    private SinhVien sinhvienAdd;
    
    public String Kq;
       
    private SinhVien searchName;
    
    public String getKq() {
        return Kq;
    }

    public void setKq(String Kq) {
        this.Kq = Kq;
    }

    public SinhVien getSearchName() {
        return searchName;
    }

    public void setSearchName(SinhVien searchName) {
        this.searchName = searchName;
    }
    
    

    public SinhVien getSinhvienAdd() {
        return sinhvienAdd;
    }

    public void setSinhvienAdd(SinhVien sinhvienAdd) {
        this.sinhvienAdd = sinhvienAdd;
    }
    
    
    public int getChon() {
        return chon;
    }

    public void setChon(int chon) {
        this.chon = chon;
    }

    public List<SinhVien> getDsSV() {
        return dsSV;
    }

    public void setDsSV(List<SinhVien> dsSV) {
        this.dsSV = dsSV;
    }
    
    
}
