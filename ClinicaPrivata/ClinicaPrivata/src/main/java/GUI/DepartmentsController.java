package GUI;

import domain.Department;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableIntegerValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import service.ServiceDepartments;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import service.ServiceDoctors;
import service.ServiceExaminations;

import java.io.IOException;
import java.util.ArrayList;

public class DepartmentsController {

    private ServiceDepartments serviceDepartments;
    private ServiceExaminations serviceExaminations;

    private Observable observable;

    private ServiceDoctors serviceDoctors;

    public void setUpDepartments(ServiceDepartments serviceDepartments, ServiceDoctors serviceDoctors,
                                 ServiceExaminations serviceExaminations, Observable observable){
        this.serviceDepartments = serviceDepartments;
        this.serviceDoctors = serviceDoctors;
        this.serviceExaminations = serviceExaminations;
        this.observable = observable;
        loadDepartments();
    }

    @FXML
    public ListView<String> listViewDepartments;

    @FXML
    public void listViewDepartmentsMouseClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Runner.class.getResource("/GUI/Examination.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        ExaminationController examinationController = fxmlLoader.getController();
        String department = listViewDepartments.getSelectionModel().getSelectedItem().toString();
        Long idDepartment = Long.parseLong(department.split(" ")[0]);
        examinationController.setUpExaminationController(serviceDoctors, serviceExaminations, idDepartment, observable);

        Stage stage = new Stage();
        stage.setTitle("Examination");
        stage.setScene(scene);
        stage.show();
    }

    public void loadDepartments(){
        ArrayList<Department> departments = serviceDepartments.getAll();
        departments.forEach(e-> listViewDepartments.getItems().add(e.toString()));
    }

}
