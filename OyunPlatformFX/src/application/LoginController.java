package application;
import application.AnamenuController;

import java.io.IOException;


import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.sql.*;

import com.Mysql.Util.VeritabaniUtil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Dialog;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.sql.*;
public class LoginController {
	public LoginController() {
		baglanti=VeritabaniUtil.Baglan();
	}
	public static void AlertM(AlertType tip,String title,String content,String header) {
		Alert alert= new Alert(tip);
		alert.setTitle(title);
		alert.setContentText(content);
		alert.setHeaderText(header);
		alert.showAndWait();
	}
	
	
	Connection baglanti=null;
	ResultSet getirilen=null;
	PreparedStatement sorguIfadesi=null;
	String sql;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Hyperlink btnUyeOl;

    @FXML
    private Button btn_Giris;
    
    @FXML
    private Label lblkulad;

    @FXML
    private Hyperlink btn_SifreUnuttum;

    @FXML
    private ImageView imageFacebook;

    @FXML
    private ImageView imageInstagram;

    @FXML
    private ImageView imageKapat;

    @FXML
    private TextField txtGiris;

    @FXML
    private PasswordField txtSifre;
    
    

    @FXML
    void btnUyeOl_click(ActionEvent event) throws IOException {
    	Stage stage = new Stage();
		AnchorPane pane1=(AnchorPane) FXMLLoader.load(getClass().getResource("KullaniciUye.fxml"));
		Scene scene= new Scene(pane1);
		stage.setScene(scene);
		stage.show();  	

    }
 
    public static String giden="";
    @FXML
    void btn_Giris_click(ActionEvent event) {
    	sql="select * from tbl_kullanicilar where KullaniciAd=? and Sifre=?";
    	try {
    		sorguIfadesi=baglanti.prepareStatement(sql);
    		sorguIfadesi.setString(1, txtGiris.getText().trim());
    		sorguIfadesi.setString(2, VeritabaniUtil.MD5Sifrele(txtSifre.getText().trim()));
    		ResultSet getirilen=sorguIfadesi.executeQuery();
    		if(getirilen.next()) {
    			
				AlertM(AlertType.INFORMATION, "Ýste Oyun Platform", "Giriþ Baþarýlý. Sisteme Aktarýlýyorsunuz.", "Bilgi Mesajý");
				giden=txtGiris.getText();
				
				Stage stage = new Stage();
				AnchorPane pane1=(AnchorPane) FXMLLoader.load(getClass().getResource("Anamenu.fxml"));
				//baþka forma geçme
				Scene scene= new Scene(pane1);
				stage.setScene(scene);
				stage.show();
				
				
				//önceki formu kapatma
				Node  source = (Node)  event.getSource();
			    Stage stage1 = (Stage)source.getScene().getWindow();
			    stage1.close(); 
			    
			}
			else {
				
				AlertM(AlertType.ERROR, "Ýste Oyun Platform", "Kullanýcý adý veya Þifre Hatalý!", "Hata Mesajý");
			}
    		
    	} catch (Exception e) {
			// TODO: handle exception
    		System.out.println(e.getMessage());
		}
    }

    @FXML
    	void btnSifreUnuttum_click(ActionEvent event) {
    		try {
    			Stage stage = new Stage();
        		AnchorPane pane1=(AnchorPane) FXMLLoader.load(getClass().getResource("KullaniciSifre.fxml"));
        		Scene scene= new Scene(pane1);
        		stage.setScene(scene);
        		stage.show();

			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
			}
    }

    @FXML
    void initialize() {
    	txtGiris.setStyle("-fx-text-fill: red;");
    	txtSifre.setStyle("-fx-text-fill: red;");
    	
    }

}
