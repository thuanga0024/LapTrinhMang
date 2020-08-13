/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package De7_cau2;

/**
 *
 * @author probx
 */
public class KhachHang {
    public String MaKH;
    public String Ten_KH;
    public String LoaiPhong;

    public KhachHang(String MaKH, String Ten_KH, String LoaiPhong) {
        this.MaKH = MaKH;
        this.Ten_KH = Ten_KH;
        this.LoaiPhong = LoaiPhong;
    }
    
    public String inThongTin(){
        return "Ma khach hang: "+MaKH+"\t|\t"
                +"Ten khach hang: "+Ten_KH+"\t|\t"
                +"Loai phong: "+LoaiPhong;
    }
    public int tinhTienPhong(int n){
        int tienPhong = 0;
        
            if(LoaiPhong.equals("S")){
                tienPhong = n*500000;
            }else if(LoaiPhong.equals("A")){
                tienPhong = n*250000;
            }else if(LoaiPhong.equals("B")){
                tienPhong = n*100000;
            
        }
        return tienPhong;
    }
}
