/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cau2_QLSV_TCP_SQL;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Kira
 */
public class SinhVien implements Serializable {
    private String tenSV;
    private Date ngaysinh;
    private String chucdanh;

    public String getTenSV() {
        return tenSV;
    }

    public void setTenSV(String tenSV) {
        this.tenSV = tenSV;
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
    
    public SinhVien(){
        
    }

    public SinhVien(String tenSV, Date ngaysinh, String chucdanh) {
        this.tenSV = tenSV;
        this.ngaysinh = ngaysinh;
        this.chucdanh = chucdanh;
    }

    @Override
    public String toString() { 
        return "SinhVien{" + "tenSV= " + tenSV + ", ngaysinh= " + ngaysinh + ", chucdanh= " + chucdanh + '}';
    }
    
}
