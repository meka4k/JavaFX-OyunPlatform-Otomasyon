package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

import com.Mysql.Util.VeritabaniUtil;
import com.mysql.cj.protocol.Resultset;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class KullaniciUyeController {
	public KullaniciUyeController() {
		baglanti=VeritabaniUtil.Baglan();

		
	}
	Connection baglanti=null;
	Resultset getirilen=null;
	PreparedStatement sorguIfadesi=null;
	String sql;
	
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
	    private Button btn_Kaydet;

	    @FXML
	    private PasswordField txtSifre;

	    @FXML
	    private PasswordField txtSifreTekrar;

	    @FXML
	    private TextField txt_email;

	    @FXML
	    private TextField txt_isim;

	    @FXML
	    private TextField txt_kulad;

	    @FXML
	    private TextField txt_soyad;

    @FXML
    void btn_Kaydet_click(ActionEvent event) {
    	sql="insert into tbl_kullanicilar (Ad,Soyad,KullaniciAd,Sifre,Email) values(?,?,?,?,?)";
    	try {
    		if(!(txt_email.getText().isEmpty() || txt_isim.getText().isEmpty() || txt_kulad.getText().isEmpty() ||
    				txtSifre.getText().isEmpty() || txtSifreTekrar.getText().isEmpty() || txt_soyad.getText().isEmpty()))
    				 {
    			if(txtSifre.getText().equals(txtSifreTekrar.getText())) {
    				sorguIfadesi=baglanti.prepareStatement(sql);
        			sorguIfadesi.setString(1, txt_isim.getText().trim());
        			sorguIfadesi.setString(2, txt_soyad.getText().trim());
        			sorguIfadesi.setString(3, txt_kulad.getText().trim());
        			sorguIfadesi.setString(4, VeritabaniUtil.MD5Sifrele(txtSifre.getText().trim()));
        			sorguIfadesi.setString(5, txt_email.getText().trim());

        			sorguIfadesi.executeUpdate();
        			AlertM(AlertType.INFORMATION, "Ýste Oyun Platform", "Kayýt Baþarýlý! Oyun Platforma Hoþgeldiniz.", "Bilgi Mesajý");
    			}else {
    				AlertM(AlertType.ERROR, "Ýste Oyun Platform", "Þifreler uyuþmuyor!", "Uyarý Mesajý");
    			}

    			
    		}else {
    			AlertM(AlertType.ERROR, "Ýste Oyun Platform", "Bilgiler Eksik Lütfen Kontrol Ediniz.", "Uyarý Mesajý");
    		}
    			
			
		} catch (Exception e) {
			// TODO: handle exception
		}

    }

    @FXML
    void initialize() {
        
    }

}
