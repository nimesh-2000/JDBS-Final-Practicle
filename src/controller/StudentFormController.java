package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import view.tm.StudentTM;

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


        btnSaveStudent.setDisable(true);



    }
    public void studentIdOnAction(ActionEvent actionEvent) {
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
    }
}
