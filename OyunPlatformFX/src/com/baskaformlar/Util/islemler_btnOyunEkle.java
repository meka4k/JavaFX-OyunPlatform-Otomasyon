package com.baskaformlar.Util;

import javax.imageio.stream.FileImageInputStream;

public class islemler_btnOyunEkle {
	private int id;
	private String oyunAd;
	private String oyunBilgi;
	private String yapimci;
	private String kategori;
	private Double fiyat;
	
	
	public islemler_btnOyunEkle() {
		
	}
	public islemler_btnOyunEkle(Integer id,String oyunAd,String oyunBilgi,String yapimci,String kategori,Double fiyat) {
		this.id=id;
		this.oyunAd=oyunAd;
		this.oyunBilgi=oyunBilgi;
		this.yapimci=yapimci;
		this.kategori=kategori;
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
	public String getOyunBilgi() {
		return oyunBilgi;
	}
	public void setOyunBilgi(String oyunBilgi) {
		this.oyunBilgi = oyunBilgi;
	}
	public String getYapimci() {
		return yapimci;
	}
	public void setYapimci(String yapimci) {
		this.yapimci = yapimci;
	}
	public String getKategori() {
		return kategori;
	}
	public void setKategori(String kategori) {
		this.kategori = kategori;
	}

	public Double getFiyat() {
		return fiyat;
	}
	public void setFiyat(Double fiyat) {
		this.fiyat = fiyat;
	}

}
