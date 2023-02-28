package domain;

public class Doctor {
    private Long id;
    private String name;
    private Long idDepartment;
    private Long experience;
    private boolean resident;

    public Doctor(Long id, String name, Long idDepartment, Long experience, boolean resident) {
        this.id = id;
        this.name = name;
        this.idDepartment = idDepartment;
        this.experience = experience;
        this.resident = resident;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(Long idDepartment) {
        this.idDepartment = idDepartment;
    }

    public Long getExperience() {
        return experience;
    }

    public void setExperience(Long experience) {
        this.experience = experience;
    }

    public boolean isResident() {
        return resident;
    }

    public void setResident(boolean resident) {
        this.resident = resident;
    }

    public String toString(){
        return name + " " + experience + " " + resident;
    }
}
