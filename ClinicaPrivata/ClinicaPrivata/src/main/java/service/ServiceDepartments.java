package service;

import domain.Department;
import repository.DepartmentsRepository;

import java.util.ArrayList;

public class ServiceDepartments {
    private final DepartmentsRepository departmentsRepository;

    public ServiceDepartments(DepartmentsRepository departmentsRepository){
        this.departmentsRepository = departmentsRepository;
    }

    public ArrayList<Department> getAll(){
        return departmentsRepository.getAll();
    }
}
