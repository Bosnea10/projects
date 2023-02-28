package GUI;

import domain.Doctor;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import service.ServiceDoctors;
import service.ServiceExaminations;

import java.time.LocalDate;
import java.time.LocalTime;

import static java.time.LocalTime.*;

public class ExaminationController {
    private ServiceExaminations serviceExaminations;
    private ServiceDoctors serviceDoctors;
    private Observable observable;
    @FXML
    public DatePicker datePicker;
    @FXML
    public Spinner<LocalTime> spinnerTime;

    @FXML
    public ComboBox<Doctor> comboBoxDoctor;
    @FXML
    public Button buttonMakeAppointment;
    @FXML
    public TextField textFieldCnp;
    @FXML
    public TextField textFieldName;

    public void setUpExaminationController(ServiceDoctors serviceDoctors, ServiceExaminations serviceExaminations,
                                           Long idDepartment, Observable observable){
        this.serviceExaminations = serviceExaminations;
        this.serviceDoctors = serviceDoctors;
        serviceDoctors.getDoctorsByDepartmentId(idDepartment).forEach(doctor->
                comboBoxDoctor.getItems().add(doctor)
        );
        spinnerTime.setEditable(true);
        spinnerTime.setValueFactory(new SpinnerValueFactory<LocalTime>() {
            @Override
            public void decrement(int steps) {
                this.setValue(this.getValue().minusMinutes(steps));
            }

            @Override
            public void increment(int steps) {
                this.setValue(this.getValue().minusMinutes(-steps));
            }
        });
        spinnerTime.getValueFactory().setValue(now());

        this.observable = observable;
    }

    public void onClickAppointment(){
        Long idDoctor = comboBoxDoctor.getValue().getId();
        String cnpClient = textFieldCnp.getText();
        String clientName = textFieldName.getText();
        LocalDate date = datePicker.getValue();
        LocalTime time = spinnerTime.getValue();
        serviceExaminations.addExamination(idDoctor, cnpClient,  clientName, date, time);

        observable.notifyControllers();
    }


}
