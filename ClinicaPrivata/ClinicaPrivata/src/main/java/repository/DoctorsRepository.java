package repository;

import domain.Department;
import domain.Doctor;

import java.sql.*;
import java.util.ArrayList;

public class DoctorsRepository {
    private String url;

    private String username;

    private String password;

    public DoctorsRepository(String url, String username, String password){
        this.url = url;
        this.username = username;
        this.password = password;
    }

    private Doctor getDoctorFromResultSet(ResultSet resultSet) throws SQLException {
        Long id = (long)resultSet.getObject("id");
        String name = resultSet.getString("name");
        Long idDepartment = (long)resultSet.getObject("idDepartment");
        Long experience = (long)resultSet.getObject("experience");
        boolean resident = resultSet.getBoolean("resident");

        return new Doctor(id, name, idDepartment, experience, resident);
    }

    public ArrayList<Doctor> getBySqlQuery(String sqlQuery){
        ArrayList < Doctor > doctors = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection(this.url, this.username, this.password)){
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next())
                doctors.add(getDoctorFromResultSet(resultSet));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return doctors;
    }

    public ArrayList<Doctor> getAll(){
        String sqlQuery = "Select * from doctors";
        return getBySqlQuery(sqlQuery);
    }


    public ArrayList<Doctor> getDoctorsByDepartmentId(Long idDepartment){
        String sqlQuery = "Select * from doctors where idDepartment = " + idDepartment;
        return getBySqlQuery(sqlQuery);
    }
}
