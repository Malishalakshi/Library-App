package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import dto.CategoryDto;
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
import service.custom.CategoryService;
import tm.BookCategoryTM;

public class CategoryController implements Initializable {

   

    @FXML
    private JFXButton btnCreate;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TableColumn<BookCategoryTM, String> colCtgName;

    @FXML
    private TableColumn<BookCategoryTM, String> colCtgId;

    

    @FXML
    private Label lblCtgId;

    @FXML
    private Label lblCtgName;

    @FXML
    private Label lblBookCategory;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<BookCategoryTM> tblCategory;

    @FXML
    private TextField txtCtgId;

    @FXML
    private TextField txtCtgName;
    

    @FXML
    private JFXButton btnSearch;
    

    

    
    
    private CategoryService categoryService = (CategoryService)ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.CATEGORY);
    
    public CategoryController() throws Exception {
              
    }    
    
    /////////////////////////////Action Buttons controlling//////////////////////////////////////
     @FXML
    void btnCreateOnAction(ActionEvent event) throws Exception {
        System.out.println("Create Category");
        create();             
    } 
    @FXML
    void btnDeleteOnAction(ActionEvent event)throws Exception {
        System.out.println("Delete Category");
        delete();    
    }

    @FXML
    void btnEditOnAction(ActionEvent event) throws Exception {
        System.out.println("Edit Category");
        update();
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) throws Exception {
        System.out.println("clicked on search");
        search();       
    }


    private void create()  {
        try {
            String ctgId = txtCtgId.getText();
            String ctgName = txtCtgName.getText();
    
            if (ctgId != null && !ctgId.isEmpty() && ctgName != null && !ctgName.isEmpty()) {
                CategoryDto categoryDto = new CategoryDto(ctgId, ctgName);
                String resp = categoryService.create(categoryDto);
    
                showAlert(AlertType.INFORMATION, "Status", resp);
                clearForm();
                loadTable();
            } else {
                showAlert(AlertType.WARNING, "Error", "Please enter Category Id and Name");
            }
        } catch (Exception e) {
            
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Error", "An error occurred: " + e.getMessage());
        }
    }
        
       
    private void delete() {
        try {
            String ctgID = txtCtgId.getText();            
            String resp = categoryService.delete(ctgID);
            
            if ("Success".equals(resp)) {
                System.out.println("Category deleted successfully.");
                showAlert(AlertType.INFORMATION, "Status", "Category deleted successfully.");
                
            } else {
                System.out.println("Failed to delete category: " + resp);
                showAlert(AlertType.ERROR, "Delete Failed", "Failed to delete category: " + resp);
            }
    
            clearForm();
            loadTable();
        } catch (Exception e) {
            
            showAlert(AlertType.ERROR, "Error", "An error occurred while deleting the category: " + e.getMessage());
        }
    } 
    
    private void update() {
        try {
            if (txtCtgId.getText() != null && !txtCtgId.getText().isEmpty() && txtCtgName.getText() != null && !txtCtgName.getText().isEmpty()) {
                CategoryDto categoryDto = new CategoryDto(txtCtgId.getText(),txtCtgName.getText());
                String resp = categoryService.update(categoryDto);
                showAlert(AlertType.INFORMATION, "Status", resp);
                clearForm();
                loadTable();
            }else{
                showAlert(AlertType.ERROR, "Error", "Please Enter Category ID and Name");

            }       
                                   
        } catch (Exception e) {
            showAlert(AlertType.ERROR, "Error", "An error occurred while updating the category: " + e.getMessage());
           
        }
    }

    private void search() {
        try {
            String ctgID=txtCtgId.getText();            
            CategoryDto dto = categoryService.get(ctgID);
    
                if (dto != null) {                    
                    txtCtgName.setText(dto.getCtgName());
                } else {
                    showAlert(Alert.AlertType.WARNING, "Not Found", "Category not found.");
                }
            
            
        } catch (Exception e) {
            
            showAlert(Alert.AlertType.ERROR, "Error", "An error occurred while searching for the category: " + e.getMessage());
        }
    }
    
    public void loadTable() throws Exception {
        try {
            colCtgId.setCellValueFactory(new PropertyValueFactory<>("ctgID"));
            colCtgName.setCellValueFactory(new PropertyValueFactory<>("ctgName"));
        
            ArrayList<CategoryDto> categoryDtos = categoryService.getAll();
            ObservableList<BookCategoryTM> catgoryTMList = FXCollections.observableArrayList();
            for(CategoryDto ctgCategoryDto:categoryDtos){
            
                BookCategoryTM categoryTM = new BookCategoryTM(
                            ctgCategoryDto.getCtgID(),
                            ctgCategoryDto.getCtgName());
                catgoryTMList.add(categoryTM);
            }
        System.out.println(catgoryTMList);
        tblCategory.setItems((ObservableList<BookCategoryTM>) catgoryTMList);
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
        txtCtgId.setText("");
        txtCtgName.setText("");
        
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





