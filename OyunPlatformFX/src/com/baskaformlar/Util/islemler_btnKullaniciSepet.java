package com.baskaformlar.Util;

public class islemler_btnKullaniciSepet {
	private int id;
	private String oyunAd;
	private String kategori;
	private String yapimci;
	private double fiyat;
	
	public islemler_btnKullaniciSepet() {
		
	}
public islemler_btnKullaniciSepet(Integer id,String oyunAd,String kategori,String yapimci,Double fiyat) {
		this.id=id;
		this.oyunAd=oyunAd;
		this.kategori=kategori;
		this.yapimci=yapimci;
		this.fiyat=fiyat;
	}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getOyunAd() {
	return oyunAd;
}
public void setOyunAd(String oyunAd) {
	this.oyunAd = oyunAd;
}
public String getKategori() {
	return kategori;
}
public void setKategori(String kategori) {
	this.kategori = kategori;
}
public String getYapimci() {
	return yapimci;
}
public void setYapimci(String yapimci) {
	this.yapimci = yapimci;
}
public double getFiyat() {
	return fiyat;
}
public void setFiyat(double fiyat) {
	this.fiyat = fiyat;
}
}
