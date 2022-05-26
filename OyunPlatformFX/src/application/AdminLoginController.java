package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.Mysql.Util.FonksiyonlarUtil;
import com.Mysql.Util.VeritabaniUtil;
import com.mysql.cj.protocol.Resultset;

import javafx.css.Style;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.sql.*;

public class AdminLoginController {
	public AdminLoginController() {
		baglanti=VeritabaniUtil.Baglan();		
		
	}
	public void AlertM(AlertType bilgi,String title,String content,String header) {
		Alert alert= new Alert(bilgi);
		alert.setTitle(title);
		alert.setContentText(content);
		alert.setHeaderText(header);
		alert.showAndWait();
	}
	Connection baglanti=null;
	Resultset getirilen=null;
	PreparedStatement sorguIfadesi=null;
	String sql;
	String alert;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Hyperlink btnUyeOl;

    @FXML
    private Button btn_Giris;

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
    void btnUyeOl_click(ActionEvent event) {

    }

    @FXML
    void btn_Giris_click(ActionEvent event) {
    	sql="select * from tbl_admin where username=? and password=?";
    	try {
    		sorguIfadesi=baglanti.prepareStatement(sql);
    		sorguIfadesi.setString(1, txtGiris.getText().trim());
    		sorguIfadesi.setString(2, VeritabaniUtil.MD5Sifrele(txtSifre.getText().trim()));
    		ResultSet getirilen=sorguIfadesi.executeQuery(); 
    		if(getirilen.next()) {
    			
				AlertM(AlertType.INFORMATION, "Ýste Oyun Platform", "Giriþ Baþarýlý. Sisteme Aktarýlýyorsunuz.", "Bilgi Mesajý");
				
				
				
				Stage stage = new Stage();
				AnchorPane pane1=(AnchorPane) FXMLLoader.load(getClass().getResource("AdminMenu.fxml"));
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
		}
    	
    	}
    	

    

    @FXML
    void btn_SifreUnuttum_click(ActionEvent event) {
    	try {
    		Stage stage = new Stage();
			AnchorPane pane1=(AnchorPane) FXMLLoader.load(getClass().getResource("AdminSifre.fxml"));
			Scene scene= new Scene(pane1);
			stage.setScene(scene);
			stage.show();
			btn_SifreUnuttum.setVisited(false); 
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

    }

    @FXML
    void imgFB_click(MouseEvent event) {

    }

    @FXML
    void imgInsta_click(MouseEvent event) {

    }

    @FXML
    void initialize() {
    	
    }

}
