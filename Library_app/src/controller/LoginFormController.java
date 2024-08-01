package controller;



import java.io.IOException;
import java.net.URL;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
// import javafx.stage.Stage;
// import java.net.URL;
// import javafx.scene.Scene;
import javafx.stage.Stage;


public class LoginFormController {
    



    @FXML
    private JFXButton btnForgetPassword;

    @FXML
    private JFXButton btnLogin;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblPassword;

    @FXML
    private Label lblTitle;

    @FXML
    private JFXRadioButton btnRememberMe;

     @FXML
     private AnchorPane root;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPassword;

    @FXML
    private Label lblLMS;


     // Predefined values for username and password
     private final String Username = "Admin";
     private final String Password = "123456";
   

    //permission for login to the system
    @FXML
    void btnLoginOnAction(ActionEvent event)throws IOException {
        System.out.println("Logged");

        String enteredUsername = txtEmail.getText();
        String enteredPassword = txtPassword.getText();

        if (enteredUsername.equals(Username) && enteredPassword.equals(Password)) {
            showAlert(AlertType.INFORMATION, "Login Successful", "Welcome, " + enteredUsername + "!");
            //////////////////////////////////////////////////////////////////////////////////////////////////
            URL resource = this.getClass().getResource("/view/Home.fxml");
            Parent root = FXMLLoader.load(resource);
            Stage stage=new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            stage.setTitle("Home");

            // this.root.getChildren().clear();
            // Parent node = FXMLLoader.load(this.getClass().getResource("/view/Home.fxml"));
            // this.root.getChildren().add(node);

            // Set the title of the window
            
            //////////////////////////////////////////////////////////////////////////////////////////////////
        } else {
            showAlert(AlertType.ERROR, "Login Failed", "Invalid username or password.");
        }
    }  

    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    
}
