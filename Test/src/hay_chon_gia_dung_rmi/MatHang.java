package hay_chon_gia_dung_rmi;

import java.io.Serializable;

public class MatHang implements Serializable {

	private static final long serialVersionUID = 1L;
	private String maHangHoa;
	private String tenHangHoa;

	public MatHang() {
	}

	public MatHang(String maHangHoa, String tenHangHoa) {
		this.maHangHoa = maHangHoa;
		this.tenHangHoa = tenHangHoa;
	}

	public String getMaHangHoa() {
		return maHangHoa;
	}

	public void setMaHangHoa(String maHangHoa) {
		this.maHangHoa = maHangHoa;
	}

	public String getTenHangHoa() {
		return tenHangHoa;
	}

	public void setTenHangHoa(String tenHangHoa) {
		this.tenHangHoa = tenHangHoa;
	}

}
