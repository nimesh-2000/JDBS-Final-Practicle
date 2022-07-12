package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import model.Student;
import util.CrudUtil;
import view.tm.StudentTM;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentFormController {
    public AnchorPane studentContext;
    public JFXTextField txtStudentId;
    public JFXTextField txtStudentName;
    public JFXTextField txtContact;
    public JFXTextField txtEmail;
    public JFXTextField txtNIC;
    public JFXTextField txtAddress;
    public TableView<StudentTM> tblStudentManage;
    public JFXButton btnSaveStudent;


    public void initialize() throws SQLException, ClassNotFoundException {
        tblStudentManage.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("student_id"));
        tblStudentManage.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("student_name"));
        tblStudentManage.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("email"));
        tblStudentManage.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("contact"));
        tblStudentManage.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblStudentManage.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("nic"));
        tblStudentManage.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("delete"));
        tblStudentManage.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("update"));


        loadAllStudents();

        
//        btnSaveStudent.setDisable(true);



    }

    private void loadAllStudents() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM Student");
        ObservableList<StudentTM> obList = FXCollections.observableArrayList();

        
        while (result.next()){
            Button btn1=new Button("Delete");
            Button btn2=new Button("Update");


            btn1.setOnAction(event -> {
                StudentTM selectedItem= tblStudentManage.getSelectionModel().getSelectedItem();
                txtStudentId.setText(selectedItem.getStudent_id());


                deleteStudentOnAction();
                txtStudentId.setText("");


                try {
                    loadAllStudents();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            });

            btn2.setOnAction(event -> {
                StudentTM selectedItem= tblStudentManage.getSelectionModel().getSelectedItem();
                txtStudentId.setText(selectedItem.getStudent_id());
                txtStudentName.setText(selectedItem.getStudent_name());
                txtEmail.setText(selectedItem.getEmail());
                txtContact.setText(selectedItem.getContact());
                txtAddress.setText(selectedItem.getAddress());
                txtNIC.setText(selectedItem.getNic());
                try {
                    search();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                btnSaveStudent.setText("Update");


            });

            obList.add(
                    new StudentTM(
                            result.getString(1),
                            result.getString(2),
                            result.getString(3),
                            result.getString(4),
                            result.getString(5),
                            result.getString(6),
                            btn1,
                            btn2
                    )
            );
        }
        tblStudentManage.setItems(obList);
        clearAllTexts();
    }

    private void clearAllTexts() {
        txtStudentId.clear();
        txtStudentName.clear();
        txtEmail.clear();
        txtContact.clear();
        txtAddress.clear();
        txtNIC.clear();
        txtStudentId.requestFocus();
    }

    private void search() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM Student WHERE student_id=?",txtStudentId.getText());
        if (result.next()) {
            txtStudentName.setText(result.getString(2));
            txtEmail.setText(result.getString(3));
            txtContact.setText(result.getString(4));
            txtAddress.setText(result.getString(5));
            txtNIC.setText(result.getString(6));
        } else {
            new Alert(Alert.AlertType.WARNING, "Empty Result").show();
        }
    }

    private void deleteStudentOnAction() {
        try{

            if(CrudUtil.execute("DELETE FROM Student WHERE student_id=?",txtStudentId.getText())){
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted!").show();
            }else{
                new Alert(Alert.AlertType.WARNING, "Try Again!").show();
            }

        }catch (SQLException | ClassNotFoundException e){

        }
    }




    public void studentIdOnAction(ActionEvent actionEvent) {
        try {
            search();
        } catch (ClassNotFoundException |SQLException e) {
            e.printStackTrace();
        }
    }

    public void studentIdKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode()== KeyCode.ENTER){
            txtStudentName.requestFocus();
        }

    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        try {
            search();
        } catch (ClassNotFoundException |SQLException e) {
            e.printStackTrace();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (btnSaveStudent.getText().equals("Update")){
            Student s = new Student(
                    txtStudentId.getText(),txtStudentName.getText(), txtEmail.getText(),txtContact.getText(),txtAddress.getText(),txtNIC.getText());

            try{
                boolean isUpdated = CrudUtil.execute("UPDATE Student SET student_name=? , email=? , contact=? , address=? , nic=? WHERE student_id=?",s.getStudent_name(),s.getEmail(),s.getContact(),s.getAddress(),s.getNic());
                if (isUpdated){
                    new Alert(Alert.AlertType.CONFIRMATION, "Updated!").show();
                    loadAllStudents();
                    btnSaveStudent.setText("Save Student");
                }else{
                    new Alert(Alert.AlertType.WARNING, "Try Again!").show();
                }


            }catch (SQLException | ClassNotFoundException e){

            }
        }else {
            Student st = new Student(
                    txtStudentId.getText(),txtStudentName.getText(), txtEmail.getText(), txtContact.getText(),txtAddress.getText(),txtNIC.getText()
            );

            try {
                if (CrudUtil.execute("INSERT INTO Student VALUES (?,?,?,?,?,?)",st.getStudent_id(),st.getStudent_name(),st.getEmail(),st.getContact(),st.getAddress(),st.getNic())){
                    new Alert(Alert.AlertType.CONFIRMATION, "Saved!..").show();
                    btnSaveStudent.setDisable(true);
                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }

            clearAllTexts();
            loadAllStudents();
            //saveCustomer();
        }
    }
    }



