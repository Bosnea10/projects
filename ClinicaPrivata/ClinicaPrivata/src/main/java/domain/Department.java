package domain;

public class Department {
    private Long id;
    private String name;
    private Long idDoctor;
    private Long price;
    private Long maxTime;

    public Department(Long id, String name, Long idDoctor, Long price, Long maxTime){
        this.id = id;

        this.name = name;
        this.idDoctor = idDoctor;
        this.price = price;
        this.maxTime = maxTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(Long idDoctor) {
        this.idDoctor = idDoctor;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(Long maxTime) {
        this.maxTime = maxTime;
    }

    public String toString(){
        return id + " " + name + " " + idDoctor + " " + price + " " + maxTime ;
    }
}
