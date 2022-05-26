package com.baskaformlar.Util;

public class islemler_btnMusteriGetir {
	private int id;
	private String isim;
	private String soyisim;
	private String kullaniciAd;
	private String sifre;
	private String email;
	
	
	public islemler_btnMusteriGetir() {
		
	}
	public islemler_btnMusteriGetir(Integer id, String isim, String soyisim, String kullaniciAd,String sifre,String email) {
		this.id=id;
		this.isim=isim;
		this.soyisim=soyisim;
		this.kullaniciAd=kullaniciAd;
		this.sifre=sifre;
		this.email=email;
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIsim() {
		return isim;
	}
	public void setIsim(String isim) {
		this.isim = isim;
	}
	public String getSoyisim() {
		return soyisim;
	}
	public void setSoyisim(String soyisim) {
		this.soyisim = soyisim;
	}
	public String getKullaniciAd() {
		return kullaniciAd;
	}
	public void setKullaniciAd(String kullaniciAd) {
		this.kullaniciAd = kullaniciAd;
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
