package com.example.assignment2.Controller;

import com.example.assignment2.Model.Account;
import com.example.assignment2.View.ViewManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class CreateAccountController {
    @FXML
    private TextField accountNumberTF;

    @FXML
    private Button accountsPageBtn;

    @FXML
    private TextField balanceTF;

    @FXML
    private DatePicker creationDateTF;

    @FXML
    private TextField currencyTF;

    @FXML
    private Button operationsPageBtn;

    @FXML
    private TextField userIdTF;

    @FXML
    private TextField usernameTF;

    @FXML
    private Button usersManagmentPageBtn;
    @FXML
    void cancelUserCreation(ActionEvent event) {
        ViewManager.adminPage.changeSceneToAccountsManagment();

    }

    @FXML
    void create(ActionEvent event) throws SQLException, ClassNotFoundException {
        Account account=new Account();
        account.setAccount_number(Integer.parseInt(accountNumberTF.getText()));
        account.setUsername(usernameTF.getText());
        account.setCreation_date(creationDateTF.getValue().toString());
        account.setUser_id(Integer.parseInt(userIdTF.getText()));
        account.setCurrency(currencyTF.getText());
        account.setBalance(Double.parseDouble(balanceTF.getText()));
        account.save();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Account  inserted");
        alert.setContentText("Account  inserted");
        alert.showAndWait();
    }

    @FXML
    void showAccountsPage(ActionEvent event) {

    }

    @FXML
    void showOperationsPage(ActionEvent event) {

    }

    @FXML
    void showUsersManagmentPage(ActionEvent event) {

    }

}
