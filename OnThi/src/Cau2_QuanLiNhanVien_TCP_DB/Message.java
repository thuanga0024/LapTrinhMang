/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cau2_QuanLiNhanVien_TCP_DB;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Kira
 */
public class Message implements Serializable {
    public static final int XEM_DANH_SACH =1;
    public static final int THEM_NHAN_VIEN=2;
    public static final int TIM_NHAN_VIEN_THEO_TEN=3;
    
    public int chon;
    
    private List<NhanVien> dsNV;
    
    public String Name;
    
    public NhanVien nhanvienAdd;
    
    public String Kq;

    public String getKq() {
        return Kq;
    }

    public void setKq(String Kq) {
        this.Kq = Kq;
    }
    
    
    public int getChon() {
        return chon;
    }

    public void setChon(int chon) {
        this.chon = chon;
    }

    public List<NhanVien> getDsNV() {
        return dsNV;
    }

    public void setDsNV(List<NhanVien> dsNV) {
        this.dsNV = dsNV;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public NhanVien getNhanvienAdd() {
        return nhanvienAdd;
    }

    public void setNhanvienAdd(NhanVien nhanvienAdd) {
        this.nhanvienAdd = nhanvienAdd;
    }
    
    
    
}
