package controller;

import java.io.IOException;


import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;


public class HomeController {
    @FXML
    private JFXButton btnBooks;

    @FXML
    private JFXButton btnBorrowingDetails;

    @FXML
    private JFXButton btnBorrowing;

    @FXML
    private JFXButton btnCategory;

    @FXML
    private JFXButton btnContact;

    @FXML
    private JFXButton btnMembers;

    @FXML
    private Label lblLMS;

    @FXML
    private Label lblTitle;

    @FXML
    private AnchorPane root;

    @FXML
    void btnBooksOnAction(ActionEvent event) throws IOException {
        System.out.println("Clicked on Books");
        this.root.getChildren().clear();
        Parent node = FXMLLoader.load(this.getClass().getResource("/view/Books.fxml"));
        this.root.getChildren().add(node);


    }

    @FXML
    void btnBorrowingsOnAction(ActionEvent event) {
        System.out.println("Clicked on Borrowings");

    }

    @FXML
    void btnCategoryOnAction(ActionEvent event) throws IOException {
        System.out.println("Clicked on Book Category");
        this.root.getChildren().clear();
        Parent node = FXMLLoader.load(this.getClass().getResource("/view/Category.fxml"));
        this.root.getChildren().add(node);
            
    }


    @FXML
    void btnMembersOnAction(ActionEvent event) throws IOException{
        System.out.println("Clicked on Members");
        this.root.getChildren().clear();
        Parent node = FXMLLoader.load(this.getClass().getResource("/view/Members.fxml"));
        this.root.getChildren().add(node);

    }

}
