/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package De4_cau2;

/**
 *
 * @author probx
 */
public class NhanVien {
    public String TenNV;
    public String NgaySinh;
    public String ChucDanh;

    public NhanVien(String TenNV, String NgaySinh, String ChucDanh) {
        this.TenNV = TenNV;
        this.NgaySinh = NgaySinh;
        this.ChucDanh = ChucDanh;
    }
    public String inThongTin(){
        return "Ten nhan vien: "+TenNV+"\t|\t"
                +"Ngay sinh: "+NgaySinh+"\t|\t"
                +"Chuc danh: "+ChucDanh;
    }
}
