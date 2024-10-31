package controller;



import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pojo.Account;
import service.AccountService;
import service.IAccountService;
import service.IStudentService;

public class LoginController {
	
	@FXML
	private IAccountService accountService = new AccountService() ;
	
	@FXML 
	private TextField txtUsername;
	 @FXML 
	 private TextField  txtPassword;
	
	@FXML 
	public void btnLoginOnAction() throws IOException {
		String username = txtUsername.getText();
		String password = txtPassword.getText();
		Account account = accountService.getAccount(username);
		if (account != null) {
			if (password.equals(account.getPassword())) {
				// close login screen
				Stage win = (Stage) txtUsername.getScene().getWindow();
				win.close();
				
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Student.fxml")) ;
				Parent root = loader.load();
				StudentController studentController = loader.getController();
				int role = account.getRoleId();
				studentController.setRoleId(role);
				
				
				Scene scene = new Scene(root,800,600);
				Stage primaryStage = new Stage();
				primaryStage.setTitle("Student Management");
				
				primaryStage.setWidth(800);
				
				primaryStage.setScene(scene);
				primaryStage.centerOnScreen();
				primaryStage.setScene(scene);
				primaryStage.show();
			}else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setContentText("You have no permission to access this function!");
				alert.show();
			}
		}else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setContentText("You have no permission to access this function!");
				alert.show();
		}
	}
	
	@FXML
	public void btnCancelOnAction() {
		Platform.exit();
	}
	
	
	
}
