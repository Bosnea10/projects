package GUI;

import domain.Examination;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import service.ServiceExaminations;

import java.util.ArrayList;

public class DoctorController {
    private Long idCurrentDoctor;
    private ServiceExaminations serviceExaminations;

    public void setUpDoctorController(Long currentDoctor, ServiceExaminations serviceExaminations){
        this.idCurrentDoctor = currentDoctor;
        this.serviceExaminations = serviceExaminations;
        loadFutureExaminations();
    }

    private void loadFutureExaminations(){
        listViewExaminations.getItems().clear();
        ArrayList<Examination> examinations = serviceExaminations.getAllByIdDoctor(idCurrentDoctor);
        examinations.forEach(examination -> listViewExaminations.getItems().add(examination.toString()));
    }

    public void update(){
        loadFutureExaminations();
    }

    @FXML
    public ListView <String> listViewExaminations;
}
