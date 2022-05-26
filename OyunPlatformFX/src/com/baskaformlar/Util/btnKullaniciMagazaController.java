package com.baskaformlar.Util;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.Mysql.Util.VeritabaniUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class btnKullaniciMagazaController {
	public btnKullaniciMagazaController() {
		baglanti=VeritabaniUtil.Baglan();
	} 
	
	Connection baglanti=null;
	ResultSet getirilen=null;
	PreparedStatement sorguIfadesi=null;
	String sql="select * from tbl_oyunlar";
	
	 public void DegerleriGetir(TableView tablo,String sql) {
	    	
	    	ObservableList<islemler_btnOyunEkle> kayitlar=FXCollections.observableArrayList();
	    	try {
				sorguIfadesi=baglanti.prepareStatement(sql);
				ResultSet getirilen=sorguIfadesi.executeQuery();
				while(getirilen.next()) {
					kayitlar.add(new islemler_btnOyunEkle(getirilen.getInt("ID"),getirilen.getString("OyunAd"),getirilen.getString("OyunBilgi"),getirilen.getString("Yapimci"),
							getirilen.getString("Kategori"),getirilen.getDouble("Fiyat")));
					
				}
				
				col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
				col_oyunAd.setCellValueFactory(new PropertyValueFactory<>("oyunAd"));
				col_oyunBilgi.setCellValueFactory(new PropertyValueFactory<>("oyunBilgi"));
				col_oyunYapimci.setCellValueFactory(new PropertyValueFactory<>("yapimci"));
				col_kategori.setCellValueFactory(new PropertyValueFactory<>("kategori"));
				col_fiyat.setCellValueFactory(new PropertyValueFactory<>("fiyat"));
				
				tableview_oyunEkle.setItems(kayitlar);
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

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnSatinAl;

    @FXML
    private Button btnSepet;

    @FXML
    private TableColumn<islemler_btnOyunEkle, Double> col_fiyat;

    @FXML
    private TableColumn<islemler_btnOyunEkle, Integer> col_id;

    @FXML
    private TableColumn<islemler_btnOyunEkle, String> col_kategori;

    @FXML
    private TableColumn<islemler_btnOyunEkle, String> col_oyunAd;

    @FXML
    private TableColumn<islemler_btnOyunEkle, String> col_oyunBilgi;

    @FXML
    private TableColumn<islemler_btnOyunEkle, String> col_oyunYapimci;

    @FXML
    private TableView<islemler_btnOyunEkle> tableview_oyunEkle;

    @FXML
    private TextField txtAd;

    @FXML
    private TextField txtArama;

    @FXML
    private TextField txtFiyat;

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtKategori;

    @FXML
    private TextField txtYapimci;


    @FXML
    void btnSatinAl_click(ActionEvent event) {

    }

    @FXML
    void btnSepet_click(ActionEvent event) {
    	sql="insert into tbl_siparisler (OyunAd,Yapimci,Kategori,Fiyat) values(?,?,?,?)";
    	try {
    		sorguIfadesi=baglanti.prepareStatement(sql);
			sorguIfadesi.setString(1, txtAd.getText());
			sorguIfadesi.setString(2, txtYapimci.getText());
			sorguIfadesi.setString(3, txtKategori.getText());
			sorguIfadesi.setString(4, txtFiyat.getText());			
			sorguIfadesi.executeUpdate();
			
			AlertM(AlertType.INFORMATION, "Ýste Oyun Platform", "Oyun Sepetinize Eklendi.", "Bilgi");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
    }

    @FXML
    void tableview_mouse_click(MouseEvent event) {
    	islemler_btnOyunEkle kayit = new islemler_btnOyunEkle();
    	kayit=(islemler_btnOyunEkle) tableview_oyunEkle.getItems().get(tableview_oyunEkle.getSelectionModel().getSelectedIndex());
    	txtAd.setText(kayit.getOyunAd());
    	txtFiyat.setText(String.valueOf(kayit.getFiyat()));
    	txtKategori.setText(kayit.getKategori());
    	txtYapimci.setText(kayit.getYapimci());
    	txtID.setText(String.valueOf(kayit.getId()));
    }

    @FXML
    void txtArama_KeyPressed(KeyEvent event) {
    	if(txtArama.getText().equals("")) {
    		sql="select * from tbl_oyunlar";
    	}
    	else {
    		sql="select * from tbl_oyunlar where OyunAd like '%"+txtArama.getText()+"%' or OyunBilgi like '%"+txtArama.getText()+"%' or Fiyat like '%"+txtArama.getText()+"%'"
    				+ " or Kategori like '%"+txtArama.getText()+"%'";
    		
    	}
    	DegerleriGetir(tableview_oyunEkle,sql);
    }

    @FXML
    void initialize() {
    	sql="Select * from tbl_oyunlar";
    	DegerleriGetir(tableview_oyunEkle,sql);

    }

}
