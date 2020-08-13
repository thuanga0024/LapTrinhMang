/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cau2_QuanLiMuaBanNha_TCP_DP;

import java.util.List;

/**
 *
 * @author Kira
 */
public class Message {
    public static final int Hien_Thi_Danh_Sach =1;
    public static final int Them_Nha = 2;
    public static final int Mua_Nha = 3;
        
    private int chon;
        
    private List<House> dsNha;
    
    private int Kq;
    
    private House nhaAdd;
    
    private String TinhTrangNha;
    
    private int soNhaMua;

    public int getChon() {
        return chon;
    }

    public void setChon(int chon) {
        this.chon = chon;
    }

    public List<House> getDsNha() {
        return dsNha;
    }

    public void setDsNha(List<House> dsNha) {
        this.dsNha = dsNha;
    }

    public int getKq() {
        return Kq;
    }

    public void setKq(int Kq) {
        this.Kq = Kq;
    }

    public House getNhaAdd() {
        return nhaAdd;
    }

    public void setNhaAdd(House nhaAdd) {
        this.nhaAdd = nhaAdd;
    }

    public String getTinhTrangNha() {
        return TinhTrangNha;
    }

    public void setTinhTrangNha(String TinhTrangNha) {
        this.TinhTrangNha = TinhTrangNha;
    }

    public int getSoNhaMua() {
        return soNhaMua;
    }

    public void setSoNhaMua(int soNhaMua) {
        this.soNhaMua = soNhaMua;
    }
    
    
    
}
