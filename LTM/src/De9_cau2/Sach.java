/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package De9_cau2;

/**
 *
 * @author probx
 */
public class Sach {
    public String MaSach;
    public String TenSach;
    public String NguoiMuon;

    public Sach(String MaSach, String TenSach, String NguoiMuon) {
        this.MaSach = MaSach;
        this.TenSach = TenSach;
        this.NguoiMuon = NguoiMuon;
    }
    public String inThongTin(){
        return "Ma sach: "+MaSach+"\t|\t"+"Ten sach: "+TenSach+"\t|\t"+"Nguoi muon: "+NguoiMuon;
    }
}
