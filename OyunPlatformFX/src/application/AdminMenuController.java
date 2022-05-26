package application;
import com.baskaformlar.Util.*;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class AdminMenuController {
	
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
    private Button btnAnasayfa;

    @FXML
    private Button btnCikis;

    @FXML
    private Button btnMagaza;

    @FXML
    private Button btnMenu2;

    @FXML
    private Button btnMenu3;

    @FXML
    private Button btnMenu4;

    @FXML
    private Button btnMusteri;

    @FXML
    private Button btnOyunEkle;

    @FXML
    private Button btnOyunTalep;

    @FXML
    private Button btnSatinAlim;

    @FXML
    private ImageView imgMenu;

    @FXML
    private Label lblAdSoyad;

    @FXML
    private Label lblKullanýcýAd;

    @FXML
    void btnAnasayfa_click(ActionEvent event) {
    	baskaFormGetir("/com/baskaformlar/util/btnAnasayfa.fxml");
    }

    @FXML
    void btnCikis_click(ActionEvent event) {

    }

    @FXML
    void btnMagaza_click(ActionEvent event) {
    	baskaFormGetir("/com/baskaformlar/util/btnOyunMagaza.fxml");
    }

    @FXML
    void btnMenu2_click(ActionEvent event) {

    }

    @FXML
    void btnMenu3_click(ActionEvent event) {

    }

    @FXML
    void btnMenu4_click(ActionEvent event) {

    }

    @FXML
    void btnMusteri_click(ActionEvent event) {
    	baskaFormGetir("/com/baskaformlar/util/btnMusteriGetir.fxml");

    }

    @FXML
    void btnOyunEkle_click(ActionEvent event) {
    	baskaFormGetir("/com/baskaformlar/util/btnOyunEkle.fxml");
    }

    @FXML
    void btnOyunTalep_click(ActionEvent event) {

    }

    @FXML
    void btnSatinAlim_click(ActionEvent event) {

    }
    public int durum=0;
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
    	baskaFormGetir("/com/baskaformlar/util/btnAnasayfa.fxml");

    }

}
