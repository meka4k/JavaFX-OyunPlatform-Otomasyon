package com.baskaformlar.Util;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.Mysql.Util.VeritabaniUtil;

import application.LoginController;
import application.islemler_kullanici;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class btnProfilController {
	public btnProfilController() {
		baglanti=VeritabaniUtil.Baglan();
	} 
	
	Connection baglanti=null;
	ResultSet getirilen=null;
	PreparedStatement sorguIfadesi=null;
	String sql;
	
	 public void DegerleriGetir(TableView tablo,String sql) {
	    	
	    	ObservableList<islemler_kullanici> kayitlar=FXCollections.observableArrayList();
	    	try {
				sorguIfadesi=baglanti.prepareStatement(sql);
				ResultSet getirilen=sorguIfadesi.executeQuery();
				while(getirilen.next()) {
					kayitlar.add(new islemler_kullanici(getirilen.getInt("ID"),getirilen.getString("Ad"),getirilen.getString("Soyad"),getirilen.getString("KullaniciAd"),
							getirilen.getString("Sifre"),getirilen.getString("Email")));
					
				}
				
				col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
				col_isim.setCellValueFactory(new PropertyValueFactory<>("ad"));
				col_soyisim.setCellValueFactory(new PropertyValueFactory<>("soyAd"));
				col_kulad.setCellValueFactory(new PropertyValueFactory<>("kullaniciAd"));
				col_sifre.setCellValueFactory(new PropertyValueFactory<>("sifre"));
				col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
				
				tableview_profil.setItems(kayitlar);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage().toString());
			}
	    }
	 
	 public void AlertM(AlertType bilgi,String title,String content,String header) {
			Alert alert= new Alert(bilgi);
			alert.setTitle(title);
			alert.setContentText(content);
			alert.setHeaderText(header);
			alert.showAndWait();
		}
	 public void Temizle() {
		 	txtAd.clear();
	    	txtSoyad.clear();
	    	txtKulAd.clear();
	    	txtSifre.clear();
	    	txtEmail.clear();
	    	txtID.clear();
	 }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnGuncelle;

    @FXML
    private Button btnListele;

    @FXML
    private Button btnTemizle;

    @FXML
    private TableColumn<islemler_kullanici, String> col_email;

    @FXML
    private TableColumn<islemler_kullanici, Integer> col_id;

    @FXML
    private TableColumn<islemler_kullanici, String> col_isim;

    @FXML
    private TableColumn<islemler_kullanici, String> col_kulad;

    @FXML
    private TableColumn<islemler_kullanici, String> col_sifre;

    @FXML
    private TableColumn<islemler_kullanici, String> col_soyisim;

    @FXML
    private TableView<islemler_kullanici> tableview_profil;

    @FXML
    private TextField txtAd;

    @FXML
    private TextField txtEmail;
    
    @FXML
    private Label lbl1;

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtKulAd;

    @FXML
    private TextField txtSifre;

    @FXML
    private TextField txtSoyad;

    @FXML
    void btnGuncelle_click(ActionEvent event) {
    	sql="update tbl_kullanicilar set Ad=?,Soyad=?,KullaniciAd=?,Sifre=?,Email=? where ID=?";
    	try {
			sorguIfadesi=baglanti.prepareStatement(sql);
			sorguIfadesi.setString(1, txtAd.getText().trim());
			sorguIfadesi.setString(2, txtSoyad.getText().trim());
			sorguIfadesi.setString(3, txtKulAd.getText().trim());
			sorguIfadesi.setString(4, VeritabaniUtil.MD5Sifrele(txtSifre.getText().trim()) );
			sorguIfadesi.setString(5, txtEmail.getText().trim());
			sorguIfadesi.setString(6, txtID.getText().trim());
			
			sorguIfadesi.executeUpdate();
			AlertM(AlertType.INFORMATION, "Ýste Oyun Platform", "Kullanýcý Bilgileri Güncellendi", "Bilgi");		
			Temizle();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
    }

    @FXML
    void btnListele_click(ActionEvent event) {
    	sql="Select * from tbl_kullanicilar";
    	DegerleriGetir(tableview_profil,sql);
    	Temizle();
    }

    @FXML
    void btnTemizle_click(ActionEvent event) {
    	Temizle();
    }

    @FXML
    void tableview_mouse_click(MouseEvent event) {
    	islemler_kullanici kayit = new islemler_kullanici();
    	kayit=(islemler_kullanici) tableview_profil.getItems().get(tableview_profil.getSelectionModel().getSelectedIndex());
    	txtAd.setText(kayit.getAd());
    	txtSoyad.setText(kayit.getSoyad());
    	txtKulAd.setText(kayit.getKulAd());
    	txtSifre.setText(kayit.getSifre());
    	txtEmail.setText(kayit.getEmail());
    	txtID.setText(String.valueOf(kayit.getId()));
    }
    
    @FXML
    void initialize() {
    	lbl1.setText(LoginController.giden);
    	sql="select * from tbl_kullanicilar where KullaniciAd=?";
    	try {
			sorguIfadesi=baglanti.prepareStatement(sql);
			sorguIfadesi.setString(1, lbl1.getText());
			sorguIfadesi.executeQuery();
    	}
    	catch (Exception e) {
			// TODO: handle exception
		}
    	DegerleriGetir(tableview_profil, sql);
    }

}
