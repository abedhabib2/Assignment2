package com.example.assignment2.Controller;

import com.example.assignment2.Model.Account;
import com.example.assignment2.View.ViewManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class UpdateAccountController implements Initializable {
    private  Account oldAccount;

    @FXML
    private TextField accountNumberTF;

    @FXML
    private TextField balanceTF;

    @FXML
    private DatePicker creationDateTF;

    @FXML
    private TextField currencyTF;

    @FXML
    private TextField userIdTF;

    @FXML
    private TextField usernameTF;

    @FXML
    void cancelUserCreation(ActionEvent event) {
        ViewManager.adminPage.changeSceneToAccountsManagment();
    }

    @FXML
    void save(ActionEvent event) throws SQLException {
        Account account=new Account();
        account.setAccount_number(Integer.parseInt(accountNumberTF.getText()));
        account.setUsername(usernameTF.getText());
        account.setCreation_date(creationDateTF.getValue().toString());
        account.setUser_id(Integer.parseInt(userIdTF.getText()));
        account.setCurrency(currencyTF.getText());
        account.setBalance(Double.parseDouble(balanceTF.getText()));
        account.setId(oldAccount.getId());
        account.update();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Account  Edit");
        alert.setContentText("Account  Edit ");
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       this.oldAccount=AccountsManagmentController.account;
        userIdTF.setText(String.valueOf(oldAccount.getUser_id()));
        accountNumberTF.setText(String.valueOf(oldAccount.getAccount_number()));
        usernameTF.setText(oldAccount.getUsername());
        currencyTF.setText(oldAccount.getCurrency());
        balanceTF.setText(String.valueOf(oldAccount.getBalance()));
        creationDateTF.setValue(LocalDate.parse(oldAccount.getCreation_date(),DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }
}
