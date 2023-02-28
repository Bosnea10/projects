package GUI;

import java.util.ArrayList;

public class Observable {
    ArrayList<DoctorController> observers;

    public Observable(){
        observers = new ArrayList<>();
    }

    public void addObserver(DoctorController doctorController){
        observers.add(doctorController);
    }

    public void notifyControllers(){
        observers.forEach(doctorController -> doctorController.update());
    }

}
