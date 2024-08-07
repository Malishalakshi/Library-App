package controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import dto.BookDto;
import dto.BorrowingDto;
import dto.MemberDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import service.ServiceFactory;
import service.custom.BookService;
import service.custom.BorrowingService;
import service.custom.MemberService;
import tm.BorrowingTM;


public class BorrowingController implements Initializable {
    
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
    private TableColumn<BorrowingTM,String> colBookID;

    @FXML
    private TableColumn<BorrowingTM,String> colBorrowDate;

    @FXML
    private TableColumn<BorrowingTM,String> colBorrowingID;

    @FXML
    private TableColumn<BorrowingTM,String> colDueDate;

    @FXML
    private TableColumn<BorrowingTM,Double> colFine;

    @FXML
    private TableColumn<BorrowingTM,String> colMemberID;

    @FXML
    private TableColumn<BorrowingTM,String> colReturndate;

    @FXML
    private Label lblBookID;

    @FXML
    private Label lblBorrowDate;

    @FXML
    private Label lblBorrowingID;

    @FXML
    private Label lblMemberDetails;

    @FXML
    private Label lblBookDetails;

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
    private TableView<BorrowingTM> tblBorrowing;

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

    private BorrowingService borrowingService = (BorrowingService)ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.BORROWINGS);
    private BookService bookService = (BookService)ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.BOOK);
    private MemberService memberService = (MemberService)ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.MEMBER);
   
    public BorrowingController() {
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearForm();
    }

    private void clearForm() {
       txtBorrowingID.setText("");
       txtMemberID.setText("");
       txtBookID.setText("");
       txtBorrowDate.setText("");
       txtDueDate.setText("");
       txtReturnedDate.setText("");
       txtFine.setText("");
       lblMemberDetails.setText("");
       lblBookDetails.setText("");      
    }

    @FXML
    void btnCreateOnAction(ActionEvent event) {
        create();
    }

 private void create() {
        try {
            String borrowingID = txtBorrowingID.getText();
            String bookID = txtBookID.getText();
            String memberID = txtMemberID.getText();
            LocalDate borrowDate = LocalDate.parse(txtBorrowDate.getText());
            LocalDate dueDate = LocalDate.parse(txtDueDate.getText());
            LocalDate returnedDate = txtReturnedDate.getText().isEmpty() ? null : LocalDate.parse(txtReturnedDate.getText());
            Double fine = txtFine.getText().isEmpty() ? 0.0 : Double.parseDouble(txtFine.getText());

            if (borrowingID != null && !borrowingID.isEmpty() && bookID != null && !bookID.isEmpty() &&
                memberID != null && !memberID.isEmpty() && borrowDate != null && dueDate != null) {
                BorrowingDto borrowingDto = new BorrowingDto(borrowingID, bookID, memberID, borrowDate, dueDate, returnedDate, fine);
                String resp = borrowingService.create(borrowingDto);

                showAlert(AlertType.INFORMATION, "Status", resp);
                clearForm();
                loadTable();
            } else {
                showAlert(AlertType.WARNING, "Error", "All fields must be filled.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Error", "Error while saving: " + e.getMessage());
        }
    }

   

    private void loadTable() {
        try {
            colBookID.setCellValueFactory(new PropertyValueFactory<>("bookId"));
            colBorrowingID.setCellValueFactory(new PropertyValueFactory<>("borrowingID"));
            colMemberID.setCellValueFactory(new PropertyValueFactory<>("memberId"));
            colBorrowDate.setCellValueFactory(new PropertyValueFactory<>("borrowDate"));
            colDueDate.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
            colReturndate.setCellValueFactory(new PropertyValueFactory<>("returnedDate"));
            colFine.setCellValueFactory(new PropertyValueFactory<>("fine"));

            ArrayList<BorrowingDto> borrowingDtoList = borrowingService.getAll();
            ObservableList<BorrowingTM> borrowingTMList = FXCollections.observableArrayList();
        for (BorrowingDto dto : borrowingDtoList) {
            BorrowingTM borrowingTM = new BorrowingTM(
                dto.getBorrowingId(),
                dto.getBookId(),
                dto.getMemberId(),
                dto.getBorrowDate(),
                dto.getDueDate(),
                dto.getReturnedDate(),
                dto.getFine());
            borrowingTMList.add(borrowingTM);
        }
        System.out.println(borrowingTMList);
        tblBorrowing.setItems((ObservableList<BorrowingTM>) borrowingTMList);
        } catch (Exception e) {     
            System.out.println(e.getMessage());       
            showAlert(Alert.AlertType.ERROR, "Error", "Table Loading Error" + e.getMessage());
        }

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        delete();
    }

    private void delete() {
        try {
            String borrowID = txtBorrowingID.getText();            
            String resp = borrowingService.delete(borrowID);
            
            if ("Success".equals(resp)) {
                System.out.println("Borrowing deleted successfully.");
                showAlert(AlertType.INFORMATION, "Status", "Borrowing deleted successfully.");
                
            } else {
                System.out.println("Failed to delete borrowing: " + resp);
                showAlert(AlertType.ERROR, "Delete Failed", "Failed to delete Borrowing: " + resp);
            }
    
            clearForm();
            loadTable();
        } catch (Exception e) {
            
            showAlert(AlertType.ERROR, "Error", "An error occurred while deleting the borrowing: " + e.getMessage());
        }
    }

    @FXML
    void btnSearchBookIDOnAction(ActionEvent event) {
        searchBookID();
    }

    private void searchBookID() {
        try {
            String BookID=txtBookID.getText();            
            BookDto bookDto= bookService.get(BookID);
        
        if (bookDto != null) {                    
            lblBookDetails.setText(bookDto.getTitle()+" | "+bookDto.getAuthor());
            
        } else {
            showAlert(Alert.AlertType.WARNING, "Not Found", "Book not found.");
        }
      
    } catch (Exception e) {
            
        showAlert(AlertType.ERROR, "Error", "An error occurred while Searching book details: " + e.getMessage());
    }
}
    
    @FXML
    void btnSearchBorrowIDOnAction(ActionEvent event) {
        searchBorrowID();
    }

    private void searchBorrowID() {
        try {
            String borrowingID = txtBorrowingID.getText();
            BorrowingDto borrowingDto = borrowingService.get(borrowingID);

            if (borrowingDto != null) {
                txtBookID.setText(borrowingDto.getBookId());
                txtMemberID.setText(borrowingDto.getMemberId());
                txtBorrowDate.setText(borrowingDto.getBorrowDate().toString());
                txtDueDate.setText(borrowingDto.getDueDate().toString());
                txtReturnedDate.setText(borrowingDto.getReturnedDate() != null ? borrowingDto.getReturnedDate().toString() : "");
                txtFine.setText(Double.toString(borrowingDto.getFine()));
            } else {
                showAlert(Alert.AlertType.WARNING, "Not Found", "No record found for the given Borrowing ID.");
            }
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "An error occurred while searching by Borrowing ID: " + e.getMessage());
        }
    }


    @FXML
    void btnSearchMemberIDOnAction(ActionEvent event) {
        searchMemberID();
    }
    
    private void searchMemberID() {
        try {
            String MemberID=txtMemberID.getText();            
            MemberDto memberDto= memberService.get(MemberID);
        
        if (memberDto != null) {                    
            lblMemberDetails.setText(memberDto.getMemberName());
            
        } else {
            showAlert(Alert.AlertType.WARNING, "Not Found", "Member not found.");
        }
      
    } catch (Exception e) {
            
        showAlert(AlertType.ERROR, "Error", "An error occurred while Searching member details: " + e.getMessage());
    }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        update();
    }

    private void update() {
        try {
            String borrowingID = txtBorrowingID.getText();
            String bookID = txtBookID.getText();
            String memberID = txtMemberID.getText();
            LocalDate borrowDate = LocalDate.parse(txtBorrowDate.getText());
            LocalDate dueDate = LocalDate.parse(txtDueDate.getText());
            LocalDate returnedDate = txtReturnedDate.getText().isEmpty() ? null : LocalDate.parse(txtReturnedDate.getText());
            Double fine = txtFine.getText().isEmpty() ? 0.0 : Double.parseDouble(txtFine.getText());

            if (borrowingID != null && !borrowingID.isEmpty() && bookID != null && !bookID.isEmpty() &&
                memberID != null && !memberID.isEmpty() && borrowDate != null && dueDate != null) {
                BorrowingDto borrowingDto = new BorrowingDto(borrowingID, bookID, memberID, borrowDate, dueDate, returnedDate, fine);
                String resp = borrowingService.update(borrowingDto);

                showAlert(AlertType.INFORMATION, "Status", resp);
                clearForm();
                loadTable();
            } else {
                showAlert(AlertType.ERROR, "Error", "All fields must be filled.");
            }
        } catch (Exception e) {
            showAlert(AlertType.ERROR, "Error", "An error occurred while updating the borrowing record: " + e.getMessage());
        }
    }

    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            loadTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}