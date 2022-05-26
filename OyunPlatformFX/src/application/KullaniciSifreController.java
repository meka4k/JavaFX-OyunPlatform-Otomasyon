package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.Mysql.Util.VeritabaniUtil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class KullaniciSifreController {
	public KullaniciSifreController() {
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
    private Button btn_Kaydet;

    @FXML
    private TextField txtAd;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtKulAd;

    @FXML
    private PasswordField txtSifre;

    @FXML
    private PasswordField txtSifreTekrar;

    @FXML
    private TextField txtSoyad;

    @FXML
    void btn_Kaydet_click(ActionEvent event) {
    	sql="update tbl_kullanicilar set Sifre=? where Ad=? and Soyad=? and KullaniciAd=? and Email=?";
    	
    	try {
    		if(!(txtEmail.getText().isEmpty() || txtAd.getText().isEmpty() || txtSoyad.getText().isEmpty() ||
    				txtSifre.getText().isEmpty() || txtSifreTekrar.getText().isEmpty() || txtKulAd.getText().isEmpty())) {
				
				if(txtSifre.getText().equals(txtSifreTekrar.getText())) { 
					
					sorguIfadesi=baglanti.prepareStatement(sql);
					sorguIfadesi.setString(1, VeritabaniUtil.MD5Sifrele(txtSifre.getText().trim()));
					sorguIfadesi.setString(2, txtAd.getText().trim());
					sorguIfadesi.setString(3, txtSoyad.getText().trim());
					sorguIfadesi.setString(4, txtKulAd.getText().trim());
					sorguIfadesi.setString(5, txtEmail.getText().trim());
					sorguIfadesi.executeUpdate();
					AlertM(AlertType.INFORMATION, "Ýste Oyun Platfrom", "Baþarýyla Güncellendi", "BÝLGÝ!");
    				 }else {
    	    				AlertM(AlertType.ERROR, "Ýste Oyun Platform", "Þifreler uyuþmuyor!", "Uyarý Mesajý");
    	    			}
	
			}
			else {
				AlertM(AlertType.ERROR, "Ýste Oyun Platform", "Bilgiler Eksik Lütfen Kontrol Ediniz.!", "HATA!");
			}
													
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	
    }

    @FXML
    void initialize() {
       

    }

}
