package repository;

import domain.Department;

import java.sql.*;
import java.util.ArrayList;

public class DepartmentsRepository {
    private String url;

    private String username;

    private String password;

    public DepartmentsRepository(String url, String username, String password){
        this.url = url;
        this.username = username;
        this.password = password;
    }

    private Department getDepartmentFromResultSet(ResultSet resultSet) throws SQLException {
        Long id = (long)resultSet.getObject("id");
        String name = resultSet.getString("name");
        Long idDoctor = (long)resultSet.getObject("idDoctor");
        Long price = (long)resultSet.getObject("price");
        Long maxTime = (long)resultSet.getObject("maxTime");

        return new Department(id, name, idDoctor, price, maxTime);
    }

    public ArrayList<Department> getAll(){
        String sqlQuery = "Select * from departments";


        ArrayList < Department > employees = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection(this.url, this.username, this.password)){
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next())
                employees.add(getDepartmentFromResultSet(resultSet));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return employees;
    }
}
