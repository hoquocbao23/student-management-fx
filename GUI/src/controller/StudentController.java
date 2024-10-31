package controller;

import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pojo.Student;
import service.IStudentService;
import service.StudentService;

public class StudentController implements Initializable {

	@FXML
	private javafx.scene.control.Button btnAddStudent;

	@FXML
	private javafx.scene.control.Button btnDeleteStudent;

	@FXML
	private javafx.scene.control.Button btnUpdateStudent;

	@FXML
	private Button btnLogout;

	@FXML
	private javafx.scene.control.TextField txtStudentId;

	@FXML
	private javafx.scene.control.TextField txtFirstName;

	@FXML
	private javafx.scene.control.TextField txtLastName;

	@FXML
	private javafx.scene.control.TextField txtStudentMark;

	@FXML
	private TableColumn<Student, Integer> cStudentId;

	@FXML
	private TableColumn<Student, String> cFirstName;

	@FXML
	private TableColumn<Student, String> cLastName;

	@FXML
	private TableColumn<Student, String> cStudentMark;

	@FXML
	private TableView tableStudent;

	private ObservableList<Student> tableModel;

	private IStudentService studentService;

	private int roleId;

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
		switch (this.roleId) {
		case 2:
			this.btnAddStudent.setDisable(true);
			this.btnDeleteStudent.setDisable(true);
			break;
		case 3:
			this.btnAddStudent.setDisable(true);
			this.btnDeleteStudent.setDisable(true);
			this.btnUpdateStudent.setDisable(true);

		}
	}

	public StudentController() {
		studentService = new StudentService();
		// table model will response a list student
		tableModel = FXCollections.observableArrayList(studentService.findAll());
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		// set value of column from student pojo
		cStudentId.setCellValueFactory(new PropertyValueFactory<>("id"));
		cFirstName.setCellValueFactory(new PropertyValueFactory<>("firstname"));
		cLastName.setCellValueFactory(new PropertyValueFactory<>("lastname"));
		cStudentMark.setCellValueFactory(new PropertyValueFactory<>("mark"));

		// set response to tableview
		tableStudent.setItems(tableModel);
		tableStudent.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {

			@Override
			public void changed(ObservableValue observableValue, Object oldValue, Object index) {
				if (tableStudent.getSelectionModel().getSelectedItem() != null) {
					TableViewSelectionModel selectionModel = tableStudent.getSelectionModel();
					ObservableList selectedCell = selectionModel.getSelectedCells();
					TablePosition tablePosition = (TablePosition) selectedCell.get(0);
					Object studentId = tablePosition.getTableColumn().getCellData(index);
					try {
						Student student = studentService.getStudentById(Integer.valueOf(studentId.toString()));
						showStudent(student);
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("Error");
					}
				}

			}

		});
	}

	@FXML
	public void btnAddStudentOnAction() throws IOException {

		String firstname = txtFirstName.getText();
		String lastname = txtLastName.getText();
		String smark = txtStudentMark.getText();
		
		if (firstname.isBlank() && lastname.isBlank() && firstname.length() == 0 && lastname.length() == 0) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Firstname hoặc Lastname không thể để trống!");
			alert.show();
		}
		else if (smark.isBlank()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Vui lòng nhập điểm");
			alert.show();
		}
		else if (Integer.parseInt(txtStudentMark.getText()) < 0 || Integer.parseInt(txtStudentMark.getText()) > 10  ) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Điểm không hợp lệ");
			alert.show();
		}else {
			Student newStudent = new Student();
			newStudent.setFirstname(firstname);
			newStudent.setLastname(lastname);
			newStudent.setMark(Integer.parseInt(txtStudentMark.getText()));

			studentService.save(newStudent);
			refreshTable();
		}
	}

	@FXML
	public void btnUpdateStudentOnAction() {
		
		int id = Integer.parseInt(txtStudentId.getText());
		String firstname = txtFirstName.getText();
		String lastname = txtLastName.getText();
		String smark = txtStudentMark.getText();
		if (firstname.isBlank() && lastname.isBlank() && firstname.length() == 0 && lastname.length() == 0) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Firstname hoặc Lastname không thể để trống!");
			alert.show();
		}
		else if (Integer.parseInt(txtStudentMark.getText()) < 0 || Integer.parseInt(txtStudentMark.getText()) > 10) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Điểm không hợp lệ!");
			alert.show();
		} else {
			Student findStudent = studentService.getStudentById(id);
			findStudent.setFirstname(firstname);
			findStudent.setLastname(lastname);
			findStudent.setMark(Integer.parseInt(txtStudentMark.getText()));
			studentService.updateStudent(findStudent);
			refreshTable();
		}
	}

	@FXML
	public void btnDeleteStudentOnAction() {
		int id = Integer.parseInt(txtStudentId.getText());
		studentService.delete(id);
		;
		refreshTable();
		clear();
	}

	@FXML
	public void btnCancelOnAction() {
		clear();
	}

	@FXML
	public void btnLogoutOnAction() throws IOException {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("logout");
		alert.setHeaderText("Do you want to logout ?");
		if (alert.showAndWait().get() == ButtonType.OK) {
			// close manager screen
			// close login screen
			Stage win = (Stage) txtFirstName.getScene().getWindow();
			win.close();
			Stage primaryStage = new Stage();
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("../view/Login.fxml"));
			Scene scene = new Scene(root, 400, 400);
			scene.getStylesheets().add(getClass().getResource("../view/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		}

	}

	public void showStudent(Student student) {
		txtStudentId.setText(String.valueOf(student.getId()));
		txtFirstName.setText(student.getFirstname());
		txtLastName.setText(student.getLastname());
		txtStudentMark.setText(String.valueOf(student.getMark()));
	}

	public void refreshTable() {
		tableModel = FXCollections.observableArrayList(studentService.findAll());
		tableStudent.setItems(tableModel);
	}

	public void clear() {
		txtStudentId.clear();
		txtFirstName.clear();
		txtLastName.clear();
		txtStudentMark.clear();
	}

}
