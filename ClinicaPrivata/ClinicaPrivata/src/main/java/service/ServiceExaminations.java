package service;

import domain.Doctor;
import domain.Examination;
import javafx.scene.control.ComboBox;
import repository.DoctorsRepository;
import repository.ExaminationsRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class ServiceExaminations {
    private final ExaminationsRepository examinationsRepository;

    public ServiceExaminations(ExaminationsRepository examinationsRepository) {
        this.examinationsRepository = examinationsRepository;
    }

    public ArrayList<Examination> getAllByIdDoctor(Long idDoctor){
        ArrayList<Examination> examinations = examinationsRepository.getAllByIdDoctor(idDoctor);
        System.out.println(examinations);
        examinations.sort((x, y)->{
            if(x.getDate().isEqual(y.getDate()))
                return x.getTime().compareTo(y.getTime());
            return x.getDate().compareTo(y.getDate());
        });
        return (ArrayList<Examination>) examinations.stream()
                .filter(examination -> {
                    if(examination.getDate().isAfter(LocalDate.now()))
                        return false;
                    if(examination.getDate().isEqual(LocalDate.now()))
                        return examination.getTime().isBefore(LocalTime.now());
                    return true;
                })
                .collect(Collectors.toList());
    }

    public void addExamination(Long idDoctor, String cnpClient, String clientName, LocalDate date, LocalTime time){
        Long id = examinationsRepository.getNumberOfExaminations() + 1;
        Examination examination = new Examination(id, idDoctor, cnpClient, clientName, date, time);
        examinationsRepository.addExamination(examination);
    }
}
