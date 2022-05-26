package application;
import com.Mysql.Util.VeritabaniUtil;
import com.baskaformlar.Util.*;
import application.LoginController;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class AnamenuController {
	public AnamenuController() {
		baglanti=VeritabaniUtil.Baglan();
	}
	
	
	Connection baglanti=null;
	ResultSet getirilen=null;
	PreparedStatement sorguIfadesi=null;
	String sql;

	public void baskaFormGetir(String formIsim) {
		
		
		try {
			AnchorPane pane= (AnchorPane)FXMLLoader.load(getClass().getResource(formIsim));
			AnchorSag.getChildren().setAll(pane);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane AnchorSag;

    @FXML
    private AnchorPane AnchorSol;

    @FXML
    private AnchorPane AnchorTumu;

    @FXML
    private Button btnCikis;

    @FXML
    private Button btnKutuphane;

    @FXML
    private Button btnMagaza;

    @FXML
    private Button btnOyunTalep;

    @FXML
    private Button btnProfil;

    @FXML
    private Button btnSatinAlim;

    @FXML
    private ImageView imgMenu;
     
    @FXML
    private Label lblID;

    @FXML
    private Label lblAdSoyad;

    @FXML
    private Label lblKullanýcýAd;

    @FXML
    void btnCikis_click(ActionEvent event) {

    }

    @FXML
    void btnKutuphane_click(ActionEvent event) {

    }

    @FXML
    void btnMagaza_click(ActionEvent event) {
    	baskaFormGetir("/com/baskaformlar/util/btnKullaniciMagaza.fxml");
    }

    @FXML
    void btnOyunTalep_click(ActionEvent event) {

    }

    @FXML
    void btnProfil_click(ActionEvent event) {
    	baskaFormGetir("/com/baskaformlar/util/btnProfil.fxml");
    }

    @FXML
    void btnSatinAlim_click(ActionEvent event) {

    }
    public int durum=0; // menü bar kontrol 
    //menü týklanýnca listenin gizlenmesi/geri getirilmesi
    @FXML
    void imgMenu_click(MouseEvent event) {
    	if(durum==0) {
    		FadeTransition fd1 = new FadeTransition(Duration.seconds(0.8),AnchorSol);
        	fd1.setFromValue(0);
        	fd1.setToValue(1);
        	fd1.play();
        	
        	TranslateTransition tt1= new TranslateTransition(Duration.seconds(0.8),AnchorSol);
        	tt1.setByX(-200);
        	tt1.play();
        	durum=1;
    	}
    	else {
    		TranslateTransition tt1= new TranslateTransition(Duration.seconds(0.5),AnchorSol);
        	tt1.setByX(+200);
        	tt1.play();
        	durum=0;
    	}
    	
    }

    @FXML
    void initialize() {
    	lblKullanýcýAd.setText(LoginController.giden);
    	
    	
    	
       
    }

}
