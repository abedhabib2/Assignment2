/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.assignment2.Controller;

import com.example.assignment2.App;
import com.example.assignment2.Model.Account;
import com.example.assignment2.Model.User;
import com.example.assignment2.View.ViewManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author Yahya
 */
public class AccountsManagmentController implements Initializable {
  public static Account account;
  private Stage stage;
    @FXML
    private TableColumn<Account, String > DataTD;

    @FXML
    private TextField accontSearchTF;

    @FXML
    private TableColumn<Account, Integer> accountNumberTD;

    @FXML
    private Button accountsPageBtn;

    @FXML
    private TableColumn<Account, Double> balanceTD;

    @FXML
    private Button createNewAccountrBtn;

    @FXML
    private TableColumn<Account, String> currencyTD;

    @FXML
    private Button deleteSelectedAccountBtn;

    @FXML
    private TableColumn<Account, Integer> idTD;

    @FXML
    private Button operationsPageBtn;

    @FXML
    private Button searchAccountBtn;

    @FXML
    private Button showAllAccountsBtn;

    @FXML
    private TableView<Account> tableView;

    @FXML
    private Button updateSelectedAccountBtn;

    @FXML
    private TableColumn<Account, String> userNameTD;

    @FXML
    private Button usersManagmentPageBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idTD.setCellValueFactory(new PropertyValueFactory<>("id"));
        accountNumberTD.setCellValueFactory(new PropertyValueFactory<>("account_number"));
        userNameTD.setCellValueFactory(new PropertyValueFactory<>("username"));
        currencyTD.setCellValueFactory(new PropertyValueFactory<>("currency"));
         balanceTD.setCellValueFactory(new PropertyValueFactory<>("balance"));
        DataTD.setCellValueFactory(new PropertyValueFactory<>("creation_date"));



    }    

    @FXML
    private void showUsersManagmentPage(ActionEvent event) {
         ViewManager.adminPage.changeSceneToUsersManagment();
    }

    @FXML
    private void showAccountsPage(ActionEvent event) throws IOException {

    }

    @FXML
    private void showOperationsPage(ActionEvent event) {
    }

    @FXML
    private void showAccountCreationPage(ActionEvent event) throws IOException {
        Stage stage=new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("View/CreateAccount.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("CreateAccountPage");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void showAllAccounts(ActionEvent event) throws SQLException {
        ObservableList<Account> accounts= FXCollections.observableArrayList(Account.getAllAccount());
        tableView.setItems(accounts);
    }

    @FXML
    private void updateSelectedAccount(ActionEvent event) throws IOException {
        if(tableView.getSelectionModel().getSelectedItem()!=null){
             account = tableView.getSelectionModel().getSelectedItem();

            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("View/UpdateAccount.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage=new Stage();
            stage.setScene(scene);
            stage.setTitle("Edit Account");
            stage.show();
        }else{
            Alert warnAlert = new Alert(Alert.AlertType.WARNING);
            warnAlert.setTitle("Select an Account");
            warnAlert.setContentText("Please select an Account from the table view");
            warnAlert.show();
        }
    }

    @FXML
    private void deleteSelectedAccount(ActionEvent event) {
        if(tableView.getSelectionModel().getSelectedItem() != null){
            //store the selected user from the TableView in new user object
            Account account = tableView.getSelectionModel().getSelectedItem();

            //show an confirmation alert and make the deletion on confirm event
            Alert deleteConfirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteConfirmAlert.setTitle("Account delete");
            deleteConfirmAlert.setContentText("Are you sure to delete this Account ?");
            deleteConfirmAlert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    try {
                        //delete the selected user from database table using delete method in our User model
                        account.delete();
                        tableView.getItems().remove(account);
                    } catch (SQLException ex) {
                    }
                    Alert deletedSuccessAlert = new Alert(Alert.AlertType.INFORMATION);
                    deletedSuccessAlert.setTitle("Account deleted");
                    deletedSuccessAlert.setContentText("Account deleted");
                    deletedSuccessAlert.show();
                }
            });

        }else{
            Alert warnAlert = new Alert(Alert.AlertType.WARNING);
            warnAlert.setTitle("Select an Account");
            warnAlert.setContentText("Please select an Account from the table view");
            warnAlert.show();
        }
    }

    @FXML
    private void searchForAnAccount(ActionEvent event) throws SQLException {
        ObservableList<Account> accounts=FXCollections.observableArrayList(Account.getOneAccount(Integer.parseInt(accontSearchTF.getText())));
        tableView.setItems(accounts);
    }
    
}
