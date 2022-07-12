package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
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

        
        btnSaveStudent.setDisable(true);



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
                search();
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
    }

    private void search() {
    }

    private void deleteStudentOnAction() {
    }


    public void studentIdOnAction(ActionEvent actionEvent) {
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
    }
}
