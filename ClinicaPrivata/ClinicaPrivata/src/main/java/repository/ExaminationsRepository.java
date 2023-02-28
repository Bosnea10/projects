package repository;

import domain.Department;
import domain.Examination;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class ExaminationsRepository {
    private String url;

    private String username;

    private String password;

    public ExaminationsRepository(String url, String username, String password){
        this.url = url;
        this.username = username;
        this.password = password;
    }

    private Examination getExaminationFromResultSet(ResultSet resultSet) throws SQLException {
        Long id = (long)resultSet.getObject("id");
        Long idDoctor = (long)resultSet.getObject("idDoctor");
        String cnpClient = resultSet.getString("cnpClient");
        String clientName = resultSet.getString("clientName");
        LocalDate date = resultSet.getDate("date").toLocalDate();
        LocalTime time = resultSet.getTime("time").toLocalTime();

        return new Examination(id, idDoctor, cnpClient, clientName, date, time);
    }

    public ArrayList<Examination> getAllByIdDoctor(Long idDoctor){
        String sqlQuery = "Select * from examinations where iddoctor = " + idDoctor;

        ArrayList < Examination > employees = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection(this.url, this.username, this.password)){
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next())
                employees.add(getExaminationFromResultSet(resultSet));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return employees;
    }

    public Long getNumberOfExaminations(){
        String sqlQuery = "Select count(id) from examinations";
        try(Connection connection = DriverManager.getConnection(this.url, this.username, this.password)){
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next())
                return resultSet.getLong("count");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return -1L;
    }

    public void addExamination(Examination examination){
        String sqlQuery = "Insert into examinations values(" + examination.getId() + ", " + examination.getIdDoctor() +
                ", '" + examination.getCnpClient() + "', '" + examination.getClientName() + "', '" + examination.getDate() +
                "', '" + examination.getTime() + "') ";
        System.out.println(sqlQuery);
        try(Connection connection = DriverManager.getConnection(this.url, this.username, this.password)){
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            ResultSet resultSet = statement.executeQuery();

        } catch (SQLException e) {
            //throw new RuntimeException(e);
        }
    }
}
