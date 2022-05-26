package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class PanelSecimController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane AnchorPane1;

    @FXML
    private ImageView imgAdmin;

    @FXML
    private ImageView imgUser;

    @FXML
    void imgAdmin_OnMouseClicked(MouseEvent event) {
    	try {
    		Stage stage = new Stage();
			BorderPane pane1=(BorderPane) FXMLLoader.load(getClass().getResource("AdminLogin.fxml"));
			//baþka forma geçme
			Scene scene= new Scene(pane1);
			stage.setScene(scene);
			stage.show();
			
			//önceki formu kapatma
			Node  source = (Node)  event.getSource();
		    Stage stage1 = (Stage)source.getScene().getWindow();
		    stage1.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			
		}

    }

    @FXML
    void imgUser_OnMouseClicked(MouseEvent event) {
    	try {
    		Stage stage = new Stage();
			BorderPane pane1=(BorderPane) FXMLLoader.load(getClass().getResource("Login.fxml"));
			//baþka forma geçme
			Scene scene= new Scene(pane1);
			stage.setScene(scene);
			stage.show();
			
			//önceki formu kapatma
			Node  source = (Node)  event.getSource();
		    Stage stage1 = (Stage)source.getScene().getWindow();
		    stage1.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			
		}

    }

    @FXML
    void initialize() {
     
    }

}
