package uz.pdp.frontend;

import lombok.SneakyThrows;
import uz.pdp.backend.model.user.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Demo {
    @SneakyThrows
    public static void main(String[] args) {
        Class.forName("org.postgresql.Driver");

        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/online_shop", "postgres", "");

        Statement statement = connection.createStatement();
//        ResultSet resultSet = statement.executeQuery("select add_user('user11','password11')");
//        while (resultSet.next()) {
//            System.out.println(resultSet.getBoolean(1));
//        }

        ResultSet res = statement.executeQuery("select * from authuser");

        List<User> users = new ArrayList<>();
        while (res.next()) {
            users.add(new User(res.getString(1), res.getString(2), res.getString(3), Double.parseDouble(res.getString(4)), res.getString(5)));
        }

        users.forEach(System.out::println);

    }
}
