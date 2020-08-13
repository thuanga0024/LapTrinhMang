/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cau2_QuanLiNhanVien_TCP_DB;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Kira
 */
public class NhanVien implements Serializable{
    private String tenSv;
    private Date ngaysinh;
    private String chucdanh;
    
    public NhanVien(){
        
    }

    public NhanVien(String tenSv, Date ngaysinh, String chucdanh) {
        this.tenSv = tenSv;
        this.ngaysinh = ngaysinh;
        this.chucdanh = chucdanh;
    }

    public String getTenSv() {
        return tenSv;
    }

    public void setTenSv(String tenSv) {
        this.tenSv = tenSv;
    }

    public Date getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(Date ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getChucdanh() {
        return chucdanh;
    }

    public void setChucdanh(String chucdanh) {
        this.chucdanh = chucdanh;
    }

    @Override
    public String toString() {
        return "NhanVien{" + "tenSv= " + tenSv + ", ngaysinh= " + ngaysinh + ", chucdanh= " + chucdanh + '}';
    }
    
    
}
