/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cau2_QuanLiMuaBanNha_TCP_DP;

/**
 *
 * @author Kira
 */
public class House {
    private int soNha;
    private int giaban;
    private String tinhtrang;
    
    public House(){
        
    }

    public House(int soNha, int giaban, String tinhtrang) {
        this.soNha = soNha;
        this.giaban = giaban;
        this.tinhtrang = tinhtrang;
    }
    

    public int getSoNha() {
        return soNha;
    }

    public void setSoNha(int soNha) {
        this.soNha = soNha;
    }

    public int getGiaban() {
        return giaban;
    }

    public void setGiaban(int giaban) {
        this.giaban = giaban;
    }

    public String getTinhtrang() {
        return tinhtrang;
    }

    public void setTinhtrang(String tinhtrang) {
        this.tinhtrang = tinhtrang;
    }

    @Override
    public String toString() {
        return "House{" + "soNha=" + soNha + ", giaban=" + giaban + ", tinhtrang=" + tinhtrang + '}';
    }
    
    
}
