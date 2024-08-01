package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import dto.BookDto;
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
import tm.BookTM;

public class BooksController implements Initializable{

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnCreate;

    @FXML
    private JFXButton btnSearch;
    
    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TableColumn<BookTM, String> colAuthor;

    @FXML
    private TableColumn<BookTM, String> colBookId;

    @FXML
    private TableColumn<BookTM, String> colCopiesAvailable;

    @FXML
    private TableColumn<BookTM, String> colCtgId;

    @FXML
    private TableColumn<BookTM, String> colIsbn;

    @FXML
    private TableColumn<BookTM, String> colPublishedDate;

    @FXML
    private TableColumn<BookTM, String> colPublisher;

    @FXML
    private TableColumn<BookTM, String> colTitle;


    @FXML
    private TableView<BookTM> tblBook;

    @FXML
    private Label lblAuthor;

    @FXML
    private Label lblBookID;

    @FXML
    private Label lblBookTitle;

    @FXML
    private Label lblCopiesAvailable;

    @FXML
    private Label lblCtgId;

    @FXML
    private Label lblIsbn;

    @FXML
    private Label lblPublisher;

    @FXML
    private Label lblTitle;

    @FXML
    private AnchorPane root;
  
    @FXML
    private TextField txtAuthor;

    @FXML
    private TextField txtBookID;

    @FXML
    private TextField txtBookTitle;

    @FXML
    private TextField txtCtgId;

    @FXML
    private TextField txtCopiesAvailable;

    @FXML
    private TextField txtIsbn;

    @FXML
    private TextField txtPublisher;

    ////////////////////////////////////////////////
     private BookService bookService = (BookService)ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.BOOK);
    
    public BooksController() {
    }

    
    @FXML
     void btnCreateOnAction(ActionEvent event) {
         System.out.println("Create Book");
         createBook();
 
     }
    private void createBook() {
        try {
            String bookID=txtBookID.getText();
            String title=txtBookTitle.getText();
            String author=txtAuthor.getText();
            String ctgID=txtCtgId.getText();
            Integer copies=Integer.parseInt(txtCopiesAvailable.getText());
            String publisher=txtPublisher.getText();
            String isbn=txtIsbn.getText();
            
            if (ctgID != null && !ctgID.isEmpty() && bookID != null && !bookID.isEmpty()&&author != null && !author.isEmpty()&& copies != null && copies!=0) {
                BookDto bookDto = new BookDto(bookID, title, author, ctgID, copies, publisher,isbn);
                String resp = bookService.create(bookDto);
    
                showAlert(AlertType.INFORMATION, "Status", resp);
                clearForm();
                loadTable();
            } else {
                showAlert(AlertType.WARNING, "Error", "BookID,Title,Author,Category ID and Copies Available must be Included");
            }
        } catch (Exception e) {
            
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Error", "Error while saving " + e.getMessage());
        }
        
    }


    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        System.out.println("Delete");
        delete();

    }

    private void delete() {
        try {
            String bookID = txtBookID.getText();            
            String resp = bookService.delete(bookID);
            
            if ("Success".equals(resp)) {
                System.out.println("Book deleted successfully.");
                showAlert(AlertType.INFORMATION, "Status", "Book deleted successfully.");
                
            } else {
                System.out.println("Failed to delete book: " + resp);
                showAlert(AlertType.ERROR, "Delete Failed", "Failed to delete Book: " + resp);
            }
    
            clearForm();
            loadTable();
        } catch (Exception e) {
            
            showAlert(AlertType.ERROR, "Error", "An error occurred while deleting the book: " + e.getMessage());
        }
    }


    @FXML
    void btnSearchOnAction(ActionEvent event) {
        System.out.println("search");
        search();
        

    }
    
    private void search() {
    try {
        String BookID=txtBookID.getText();            
        BookDto bookDto= bookService.get(BookID);
    
    if (bookDto != null) {                    
        txtBookTitle.setText(bookDto.getTitle());
        txtAuthor.setText(bookDto.getAuthor());
        txtCtgId.setText(bookDto.getCtgId());
        txtCopiesAvailable.setText(Integer.toString(bookDto.getCopies()));
        txtPublisher.setText(bookDto.getPublisher());
        txtIsbn.setText(bookDto.getIsbn());
        
    } else {
        showAlert(Alert.AlertType.WARNING, "Not Found", "Book not found.");
    }


} catch (Exception e) {

showAlert(Alert.AlertType.ERROR, "Error", "An error occurred while searching for the Book: " + e.getMessage());
}
}
    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearForm();

    }


    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        System.out.println("update");
        update();

    }

    private void update() {
        try {
            String bookID=txtBookID.getText();
            String title=txtBookTitle.getText();
            String author=txtAuthor.getText();
            String ctgId=txtCtgId.getText();
            Integer copies=Integer.parseInt(txtCopiesAvailable.getText());
            String publisher=txtPublisher.getText();
            String isbn=txtIsbn.getText();

            if (ctgId != null && !ctgId.isEmpty() && bookID != null && !bookID.isEmpty()&&author != null && !author.isEmpty()&& copies != null&& copies!=0) {
                BookDto bookDto = new BookDto(bookID, title, author, ctgId, copies,publisher, isbn);
                String resp = bookService.update(bookDto);
                showAlert(AlertType.INFORMATION, "Status", resp);
                clearForm();
                loadTable();
            }else{
                showAlert(AlertType.ERROR, "Error", "BookID,Title,Author,Category ID and Copies Available must be Included\nIf Difficult to find details,First You can search by Book ID");

            }       
                                   
        } catch (Exception e) {
            showAlert(AlertType.ERROR, "Error", "An error occurred while updating the category: " + e.getMessage());
           
        }
    }


    private void loadTable() {
    
        try {
            colBookId.setCellValueFactory(new PropertyValueFactory<>("bookID"));
            colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
            colAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
            colCtgId.setCellValueFactory(new PropertyValueFactory<>("ctgId"));
            colCopiesAvailable.setCellValueFactory(new PropertyValueFactory<>("copies"));
            colPublisher.setCellValueFactory(new PropertyValueFactory<>("publisher"));
            colIsbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
            
        
            ArrayList<BookDto> bookDto = bookService.getAll();
            ObservableList<BookTM> bookTMList = FXCollections.observableArrayList();
            for(BookDto dto:bookDto){            
                BookTM bookTM = new BookTM(
                    dto.getBookID(),
                    dto.getTitle(),
                    dto.getAuthor(),
                    dto.getCtgId(),
                    dto.getCopies(),
                    dto.getPublisher(),
                    dto.getIsbn());
                bookTMList.add(bookTM);
            }
        System.out.println(bookTMList);
        tblBook.setItems((ObservableList<BookTM>) bookTMList);
        } catch (Exception e) {     
            System.out.println(e.getMessage());       
            showAlert(Alert.AlertType.ERROR, "Error", "Table Loading Error" + e.getMessage());
        }

        
    }


    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearForm() {
        txtBookID.setText("");
        txtBookTitle.setText("");
        txtAuthor.setText("");
        txtCtgId.setText("");
        txtCopiesAvailable.setText("");
        txtPublisher.setText("");
        txtIsbn.setText("");   
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

