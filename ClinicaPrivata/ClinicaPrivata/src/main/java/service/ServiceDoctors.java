package service;

import domain.Department;
import domain.Doctor;
import repository.DepartmentsRepository;
import repository.DoctorsRepository;

import java.util.ArrayList;

public class ServiceDoctors {
    private final DoctorsRepository doctorsRepository;

    public ServiceDoctors(DoctorsRepository doctorsRepository){
        this.doctorsRepository = doctorsRepository;
    }

    public ArrayList<Doctor> getAll(){
        return doctorsRepository.getAll();
    }

    public ArrayList<Doctor> getDoctorsByDepartmentId(Long idDepartment){
        return doctorsRepository.getDoctorsByDepartmentId(idDepartment);
    }
}
