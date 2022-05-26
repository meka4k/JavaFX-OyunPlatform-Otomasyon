package application;

import java.net.URL;
import java.util.ResourceBundle;

import com.Mysql.Util.FonksiyonlarUtil;
import com.Mysql.Util.VeritabaniUtil;
import com.mysql.cj.protocol.Resultset;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

import java.sql.*;

public class AdminSifreController {
		public AdminSifreController() {
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
		    private TextField txtAd;

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
    	sql="update tbl_admin set password=? where isim=? and Soyisim=? and username=?"; 
    	
    		try {
    			if(!(txtAd.getText().isEmpty() || txtSoyad.getText().isEmpty() || txtKulAd.getText().isEmpty() ||
        				txtSifre.getText().isEmpty() || txtSifreTekrar.getText().isEmpty())) {
    				
    				if(txtSifre.getText().equals(txtSifreTekrar.getText())) { 
    					
    					sorguIfadesi=baglanti.prepareStatement(sql);
    					sorguIfadesi.setString(1, VeritabaniUtil.MD5Sifrele(txtSifre.getText().trim()));
    					sorguIfadesi.setString(2, txtAd.getText().trim());
    					sorguIfadesi.setString(3, txtSoyad.getText().trim());
    					sorguIfadesi.setString(4, txtKulAd.getText().trim()); 
    					sorguIfadesi.executeUpdate();
        				 }else {
        	    				AlertM(AlertType.ERROR, "Ýste Oyun Platform", "Þifreler uyuþmuyor!", "Uyarý Mesajý");
        	    			}
        				 		
				AlertM(AlertType.INFORMATION, "Ýste Oyun Platfrom", "Baþarýyla Güncellendi", "BÝLGÝ!");
				
    			}
    			else {
    				AlertM(AlertType.ERROR, "Ýste Oyun Platform", "Bilgiler Eksik Lütfen Kontrol Ediniz.!", "HATA!");
    			}
				
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
			}
    	

    }

    @FXML
    void initialize() {
       

    }

}
