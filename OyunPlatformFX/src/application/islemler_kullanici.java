package application;

public class islemler_kullanici {
	private int id;
	private String ad;
	private String soyad;
	private String kulAd;
	private String sifre;
	private String email;
	
	public islemler_kullanici() {
		
	}
	public islemler_kullanici(Integer id,String ad,String soyad,String kulAd,String sifre,String email) {
		this.id=id;
		this.ad=ad;
		this.soyad=soyad;
		this.kulAd=kulAd;
		this.sifre=sifre;
		this.email=email;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAd() {
		return ad;
	}
	public void setAd(String ad) {
		this.ad = ad;
	}
	public String getSoyad() {
		return soyad;
	}
	public void setSoyad(String soyad) {
		this.soyad = soyad;
	}
	public String getKulAd() {
		return kulAd;
	}
	public void setKulAd(String kulAd) {
		this.kulAd = kulAd;
	}
	public String getSifre() {
		return sifre;
	}
	public void setSifre(String sifre) {
		this.sifre = sifre;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
