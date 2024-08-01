package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class BorrowingController {
    
    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnSearchBookID;

    @FXML
    private JFXButton btnSearchBorrowingID;

    @FXML
    private JFXButton btnSearchMemberID;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TableColumn<?, ?> colBookID;

    @FXML
    private TableColumn<?, ?> colBorrowDate;

    @FXML
    private TableColumn<?, ?> colBorrowingID;

    @FXML
    private TableColumn<?, ?> colDueDate;

    @FXML
    private TableColumn<?, ?> colFine;

    @FXML
    private TableColumn<?, ?> colMemberID;

    @FXML
    private TableColumn<?, ?> colReturndate;

    @FXML
    private Label lblBookID;

    @FXML
    private Label lblBorrowDate;

    @FXML
    private Label lblBorrowingID;

    @FXML
    private Label lblBorrowingID1;

    @FXML
    private Label lblBorrowingID11;

    @FXML
    private Label lblDueDate;

    @FXML
    private Label lblFine;

    @FXML
    private Label lblMemberID;

    @FXML
    private Label lblReturndate;

    @FXML
    private Label lblTitle;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<?> tblBorrowing;

    @FXML
    private TextField txtBookID;

    @FXML
    private TextField txtBorrowDate;

    @FXML
    private TextField txtBorrowingID;

    @FXML
    private TextField txtDueDate;

    @FXML
    private TextField txtFine;

    @FXML
    private TextField txtMemberID;

    @FXML
    private TextField txtReturnedDate;

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    @FXML
    void btnCreateOnAction(ActionEvent event) {
        create();
    }

    private void create() {
        
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchBookIDOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchBorrowIDOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchMemberIDOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }
}