package domain;

import java.time.LocalDate;
import java.time.LocalTime;

public class Examination {
    private Long id;
    private Long idDoctor;
    private String cnpClient;
    private String clientName;
    private LocalDate date;
    private LocalTime time;

    public Examination(Long id, Long idDoctor, String cnpClient, String clientName, LocalDate date, LocalTime time) {
        this.id = id;
        this.idDoctor = idDoctor;
        this.cnpClient = cnpClient;
        this.clientName = clientName;
        this.date = date;
        this.time = time;
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

    public String getCnpClient() {
        return cnpClient;
    }

    public void setCnpClient(String cnpClient) {
        this.cnpClient = cnpClient;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String toString(){
        return cnpClient + ' ' + clientName + ' ' + date + ' ' + time;
    }
}
