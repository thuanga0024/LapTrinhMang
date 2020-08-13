/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package De6_cau2;

/**
 *
 * @author probx
 */
public class Nha {
    public int SoNha;
    public int GiaBan;
    public String TinhTrang;

    public Nha(int SoNha, int GiaBan, String TinhTrang) {
        this.SoNha = SoNha;
        this.GiaBan = GiaBan;
        this.TinhTrang = TinhTrang;
    }
    
     public String inThongTin(){
         return "So nha: "+SoNha+"\t|\t"
                 +"Gia ban: "+GiaBan+"\t|\t"
                 +"Tinh trang: "+TinhTrang;
     }
}
