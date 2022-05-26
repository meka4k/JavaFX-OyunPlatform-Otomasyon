package com.baskaformlar.Util;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.ResourceBundle;

import com.Mysql.Util.VeritabaniUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class btnMusteriGetirController {
	public btnMusteriGetirController() {
		baglanti=VeritabaniUtil.Baglan();
	} 
	
	Connection baglanti=null;
	ResultSet getirilen=null;
	PreparedStatement sorguIfadesi=null;
	String sql="select * from tbl_kullanicilar";
	
	 public void DegerleriGetir(TableView tablo,String sql) {
	    	
	    	ObservableList<islemler_btnMusteriGetir> kayitlar=FXCollections.observableArrayList();
	    	try {
				sorguIfadesi=baglanti.prepareStatement(sql);
				ResultSet getirilen=sorguIfadesi.executeQuery();
				while(getirilen.next()) {
					kayitlar.add(new islemler_btnMusteriGetir(getirilen.getInt("ID"),getirilen.getString("Ad"),getirilen.getString("Soyad"),getirilen.getString("KullaniciAd"),
							getirilen.getString("Sifre"),getirilen.getString("Email")));
					
				}
				
				col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
				col_isim.setCellValueFactory(new PropertyValueFactory<>("isim"));
				col_soyisim.setCellValueFactory(new PropertyValueFactory<>("soyisim"));
				col_kulad.setCellValueFactory(new PropertyValueFactory<>("kullaniciAd"));
				col_sifre.setCellValueFactory(new PropertyValueFactory<>("sifre"));
				col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
				
				tableview_musteriler.setItems(kayitlar);
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
    private Button btnEkle;
    
    @FXML
    private TextField txtArama;

    @FXML
    private Button btnGuncelle;

    @FXML
    private Button btnListele;

    @FXML
    private Button btnSil;

    @FXML
    private Button btnTemizle;

    @FXML
    private TableColumn<islemler_btnMusteriGetir, String> col_email;

    @FXML
    private TableColumn<islemler_btnMusteriGetir, Integer> col_id;

    @FXML
    private TableColumn<islemler_btnMusteriGetir, String> col_isim;

    @FXML
    private TableColumn<islemler_btnMusteriGetir, String> col_kulad;

    @FXML
    private TableColumn<islemler_btnMusteriGetir, String> col_sifre;

    @FXML
    private TableColumn<islemler_btnMusteriGetir, String> col_soyisim;

    @FXML
    private TableView<islemler_btnMusteriGetir> tableview_musteriler;

    @FXML
    private TextField txtAd;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtKulAd;

    @FXML 
    private TextField txtSifre;

    @FXML
    private TextField txtSoyad; 

    @FXML
    void btnEkle_click(ActionEvent event) {
    	sql="insert into tbl_kullanicilar (Ad,Soyad,KullaniciAd,Sifre,Email) values(?,?,?,?,?)";
    	try {
    		sorguIfadesi=baglanti.prepareStatement(sql);
			sorguIfadesi.setString(1, txtAd.getText().trim());
			sorguIfadesi.setString(2, txtSoyad.getText().trim());
			sorguIfadesi.setString(3, txtKulAd.getText().trim());
			sorguIfadesi.setString(4, VeritabaniUtil.MD5Sifrele(txtSifre.getText().trim()) );
			sorguIfadesi.setString(5, txtEmail.getText().trim());
			sorguIfadesi.executeUpdate();
			
			AlertM(AlertType.INFORMATION, "Ýste Oyun Platform", "Kullanýcý Sisteme Kaydedildi.", "Bilgi");
			Temizle();
		} catch (Exception e) {
			// TODO: handle exception
		}
			
    } 

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
    	DegerleriGetir(tableview_musteriler,sql);
    	Temizle();
    }
    
    @FXML
    void txtArama_KeyPressed(KeyEvent event) {
    	if(txtArama.getText().equals("")) {
    		sql="select * from tbl_kullanicilar";
    	}
    	else {
    		sql="select * from tbl_kullanicilar where Ad like '%"+txtArama.getText()+"%' or Soyad like '%"+txtArama.getText()+"%' or KullaniciAd like '%"+txtArama.getText()+"%'";
    		
    	}
    	DegerleriGetir(tableview_musteriler,sql);
    }

    @FXML
    void btnSil_click(ActionEvent event) {
    	sql="delete from tbl_kullanicilar where id=?";
    	try {
    		Alert alert1= new Alert(AlertType.NONE);	     	
        	ButtonType btnYes= new ButtonType("Evet",ButtonData.YES);
        	ButtonType btnNo= new ButtonType("Hayýr",ButtonData.NO);
        	alert1.getButtonTypes().addAll(btnYes,btnNo);
        	alert1.setTitle("Ýste Oyun Platform");
        	alert1.setHeaderText("Uyarý Mesajý");
        	alert1.setContentText("Silmek istediðinize emin misiniz?.");
        	Optional<ButtonType> sonuc=alert1.showAndWait();
        	if(sonuc.get()==btnYes) {
        		sorguIfadesi=baglanti.prepareStatement(sql);
    			sorguIfadesi.setString(1, txtID.getText().trim());	
    			sorguIfadesi.executeUpdate();
    			AlertM(AlertType.INFORMATION, "Ýste Oyun Platform", "Kullanýcý Sistemden Silindi!", "Bilgi");
    			Temizle();
        	}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

    }

    @FXML
    void btnTemizle_click(ActionEvent event) {
    	Temizle();
    }

    @FXML
    void txtEmail_click(ActionEvent event) {

    }

    @FXML
    void txtSifre_click(ActionEvent event) {

    }
    @FXML
    void tableview_mouse_click(MouseEvent event) {
    	islemler_btnMusteriGetir kayit = new islemler_btnMusteriGetir();
    	kayit=(islemler_btnMusteriGetir) tableview_musteriler.getItems().get(tableview_musteriler.getSelectionModel().getSelectedIndex());
    	txtAd.setText(kayit.getIsim());
    	txtSoyad.setText(kayit.getSoyisim());
    	txtKulAd.setText(kayit.getKullaniciAd());
    	txtSifre.setText(kayit.getSifre());
    	txtEmail.setText(kayit.getEmail());
    	txtID.setText(String.valueOf(kayit.getId()));

    }

    @FXML
    void initialize() {
    	sql="Select * from tbl_kullanicilar";
    	DegerleriGetir(tableview_musteriler,sql);
    }

}
