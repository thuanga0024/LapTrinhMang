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
    private String tenNV;
    private Date ngaysinh;
    private String chucdanh;
    
    public NhanVien(){
        
    }

    public NhanVien(String tenNV, Date ngaysinh, String chucdanh) {
        this.tenNV = tenNV;
        this.ngaysinh = ngaysinh;
        this.chucdanh = chucdanh;
    }
    
    
    
    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
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
        return "NhanVien{" + "tenNV=" + tenNV + ", ngaysinh=" + ngaysinh + ", chucdanh=" + chucdanh + '}';
    }
    
    
    
    
}
