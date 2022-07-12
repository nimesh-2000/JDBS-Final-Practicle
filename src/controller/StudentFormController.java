package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class StudentFormController {
    public AnchorPane studentContext;
    public TableView<StudentTM> tblStudent;
    public JFXTextField txtStudentId;
    public JFXTextField txtStudentName;
    public JFXTextField txtContact;
    public JFXTextField txtEmail;
    public JFXTextField txtNIC;
    public JFXTextField txtAddress;

    public void studentIdOnAction(ActionEvent actionEvent) {
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
    }
}
