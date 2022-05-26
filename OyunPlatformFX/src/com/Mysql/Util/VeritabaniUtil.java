package com.Mysql.Util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class VeritabaniUtil {
	static Connection con=null;
	public static Connection Baglan() {
		try {
			//"jdbc:mysql://ServerIPAdres/db_isim","kullaniciAd","sifre"
			con=DriverManager.getConnection("jdbc:mysql://localhost/oyunplatformdb","root","");
			return con;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage().toString());
			return null; 
		}
	}
	

	//MD5 ÞÝFRELEME
	
	public static String MD5Sifrele(String icerik) {
		try {
			MessageDigest md=MessageDigest.getInstance("MD5");
			
			//byte olarak oku
			byte[] sifrelenmis=md.digest(icerik.getBytes());
			BigInteger no = new BigInteger(1,sifrelenmis);
			
			//hex hesapla
			String hashIcerik=no.toString(16);
			while(hashIcerik.length()<32) {
				hashIcerik="0"+hashIcerik;
			}
			return hashIcerik;
			
			
		} catch (NoSuchAlgorithmException e) {
			// TODO: handle exception
			
			throw new RuntimeException(e);
		}
	}
	
	
	
}
