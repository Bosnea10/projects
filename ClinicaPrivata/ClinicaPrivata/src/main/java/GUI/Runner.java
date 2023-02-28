package GUI;

import domain.Doctor;
import repository.DepartmentsRepository;
import repository.DoctorsRepository;
import repository.ExaminationsRepository;
import service.ServiceDepartments;
import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import service.ServiceDoctors;
import service.ServiceExaminations;

import java.io.IOException;
import java.util.ArrayList;

public class Runner extends Application {

    public void openDepartmentsWindow(ServiceDepartments serviceDepartments, ServiceDoctors serviceDoctors,
                                      ServiceExaminations serviceExaminations, Observable observable) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Runner.class.getResource("/GUI/Departments.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        DepartmentsController departmentsController = fxmlLoader.getController();
        departmentsController.setUpDepartments(serviceDepartments, serviceDoctors, serviceExaminations, observable);

        Stage stage = new Stage();
        stage.setTitle("Departments");
        stage.setScene(scene);
        stage.show();
    }

    public void openDoctorsWindow(ServiceDoctors serviceDoctors, ServiceExaminations serviceExaminations,
                                  Observable observable) throws IOException {
        ArrayList<Doctor> doctors = serviceDoctors.getAll();
        doctors.forEach(doctor->{
            FXMLLoader fxmlLoader = new FXMLLoader(Runner.class.getResource("/GUI/Doctors.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            DoctorController doctorsController = fxmlLoader.getController();
            doctorsController.setUpDoctorController(doctor.getId(), serviceExaminations);

            Stage stage = new Stage();
            stage.setTitle("Doctor " + doctor.getId());
            stage.setScene(scene);
            stage.show();

            observable.addObserver(doctorsController);
        });
    }


    @Override
    public void start(Stage stage) throws IOException {
        DepartmentsRepository departmentsRepository = new DepartmentsRepository(
                "jdbc:postgresql://localhost:5432/ClinicaPrivata",
                "postgres",
                "bogdan"
        );
        DoctorsRepository doctorsRepository = new DoctorsRepository(
                "jdbc:postgresql://localhost:5432/ClinicaPrivata",
                "postgres",
                "bogdan"
        );
        ExaminationsRepository examinationsRepository = new ExaminationsRepository(
                "jdbc:postgresql://localhost:5432/ClinicaPrivata",
                "postgres",
                "bogdan"
        );

        ServiceDepartments serviceDepartments = new ServiceDepartments(departmentsRepository);
        ServiceDoctors serviceDoctors = new ServiceDoctors(doctorsRepository);
        ServiceExaminations serviceExaminations = new ServiceExaminations(examinationsRepository);

        Observable observable = new Observable();

        openDepartmentsWindow(serviceDepartments, serviceDoctors, serviceExaminations, observable);
        openDoctorsWindow(serviceDoctors, serviceExaminations, observable);
    }

    public static void main(String[] args) {
        launch();
    }
}