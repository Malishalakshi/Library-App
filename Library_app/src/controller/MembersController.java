package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;


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
import service.custom.MemberService;
import tm.MemberTM;

public class MembersController implements Initializable {

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnCreate;

    @FXML
    private JFXButton btnSearchID;

    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TableColumn<MemberTM, String> colAddress;

    @FXML
    private TableColumn<MemberTM, String> colContactNum;

    @FXML
    private TableColumn<MemberTM, String> colDOB;

    @FXML
    private TableColumn<MemberTM, String> colEmail;

    @FXML
    private TableColumn<MemberTM, String> colMemberID;

    @FXML
    private TableColumn<MemberTM, String> colName;

    @FXML
    private TableView<MemberTM> tblMembers;


    @FXML
    private Label lblAddress;

    @FXML
    private Label lblContact;

    @FXML
    private Label lblDOB;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblLms;

    @FXML
    private Label lblMemberID;

    @FXML
    private Label lblName;

    @FXML
    private Label lblTitle;

    @FXML
    private AnchorPane root;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtDOB;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtMemberID;

    @FXML
    private TextField txtName;

    private MemberService memberService = (MemberService)ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.MEMBER);

    @FXML
    void btnCreateOnAction(ActionEvent event) {
        create();

    }

    private void create() {
        try {
            String memberID=txtMemberID.getText();
            String name=txtName.getText();
            String DOB=txtDOB.getText();
            String address=txtAddress.getText();
            String contactNum=txtContact.getText();
            String email=txtEmail.getText();
            
            if (memberID != null && !memberID.isEmpty() && name != null && !name.isEmpty()&& address != null && !address.isEmpty()&& contactNum != null && !contactNum.isEmpty() && DOB != null && !DOB.isEmpty()) {
                MemberDto memberDto = new MemberDto(memberID, name, DOB, address, contactNum, email);
                String resp = memberService.create(memberDto);
    
                showAlert(AlertType.INFORMATION, "Status", resp);
                clearForm();
                loadTable();
            } else {
                showAlert(AlertType.WARNING, "Error", "memberID,name,address & contact must be Included");
            }
        } catch (Exception e) {
            
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Error", "Error while saving " + e.getMessage());
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        System.out.println("delete");
        delete();

    }

    private void delete() {
        try {
            String memberID = txtMemberID.getText();            
            String resp = memberService.delete(memberID);
            
            if ("Success".equals(resp)) {
                System.out.println("Member deleted successfully.");
                showAlert(AlertType.INFORMATION, "Status", "Member deleted successfully.");
                
            } else {
                System.out.println("Failed to member book: " + resp);
                showAlert(AlertType.ERROR, "Delete Failed", "Failed to delete member" + resp);
            }
    
            clearForm();
            loadTable();
        } catch (Exception e) {
            
            showAlert(AlertType.ERROR, "Error", "An error occurred while deleting the member: " + e.getMessage());
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        search();

    }
    private void search() {
        try {
            String memberID=txtMemberID.getText();            
            MemberDto dto = memberService.get(memberID);
    
                if (dto != null) {                    
                    txtName.setText(dto.getMemberName());
                    txtDOB.setText(dto.getDob());
                    txtAddress.setText(dto.getAdrress());
                    txtContact.setText(dto.getContact());
                    txtEmail.setText(dto.getEmail());
                } else {
                    showAlert(Alert.AlertType.WARNING, "Not Found", "Member not found.");
                }
            
            
        } catch (Exception e) {
            
            showAlert(Alert.AlertType.ERROR, "Error", "An error occurred while searching for the member: " + e.getMessage());
        }
    }
    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearForm();

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        update();

    }
    private void update() {
        try {
            String memberID=txtMemberID.getText();
            String name=txtName.getText();
            String DOB=txtDOB.getText();
            String address=txtAddress.getText();
            String contactNum=txtContact.getText();
            String email=txtEmail.getText();

            if (memberID != null && !memberID.isEmpty() && name != null && !name.isEmpty()&& DOB != null && !DOB.isEmpty() && contactNum != null && !contactNum.isEmpty() && address != null && !address.isEmpty()) {
                MemberDto memberDto = new MemberDto(memberID, name, DOB, address, contactNum,email);
                String resp = memberService.update(memberDto);
                showAlert(AlertType.INFORMATION, "Status", resp);
                clearForm();
                loadTable();
            }else{
                showAlert(AlertType.ERROR, "Error", "MemberID,Name,DOB,Address and Contact number must be Included\nIf Difficult to find details,First You can search by Member ID");

            }       
                                   
        } catch (Exception e) {
            showAlert(AlertType.ERROR, "Error", "An error occurred while updating the members: " + e.getMessage());
           
        }
    }
    private void loadTable() {
         try {
            colMemberID.setCellValueFactory(new PropertyValueFactory<>("memberID"));
            colName.setCellValueFactory(new PropertyValueFactory<>("memberName"));
            colDOB.setCellValueFactory(new PropertyValueFactory<>("dob"));
            colAddress.setCellValueFactory(new PropertyValueFactory<>("adrress"));
            colContactNum.setCellValueFactory(new PropertyValueFactory<>("contact"));
            colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
            
        
            ArrayList<MemberDto> memberDto = memberService.getAll();
            ObservableList<MemberTM> memberTMList = FXCollections.observableArrayList();
            for(MemberDto dto:memberDto){            
                MemberTM memberTM = new MemberTM(
                    dto.getMemberID(),
                    dto.getMemberName(),
                    dto.getDob(),
                    dto.getAdrress(),
                    dto.getContact(),
                    dto.getEmail());
                    memberTMList.add(memberTM);
            }
        System.out.println(memberTMList);
        tblMembers.setItems((ObservableList<MemberTM>) memberTMList);
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
        txtMemberID.setText("");
        txtName.setText("");
        txtDOB.setText("");
        txtAddress.setText("");
        txtContact.setText("");
        txtEmail.setText("");
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

