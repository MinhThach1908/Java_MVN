package demo.module.user;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class UserRepositoryImpl {

    public void save(User user) {
        // 1. Mở kết nối đến database.
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_mysql", "root", "");
        // 2. Tạo câu lệnh
        Statement stt = connection.createStatement();
        // 3. Thực thi câu lệnh
        String strSql = String.format("insert into users (username, password, full_name, identity_card, phone, dob, status)" + "values ('%s', '%s', '%s', '%s', '%s', '%s', %d)", user.getUsername(), user.getPassword(), user.getFullName(), user.getIdentityCard(), user.getPhone(), user.getDob().toString(), user.getStatus());
        // 4. Đóng kết nối.
        connection.close();
        } catch (SQLException e) {
            System.out.println("Có lỗi ra, vui lòng thử lại sau.");
            e.printStackTrace();
        }
    }

    public void save2(User user) {
        // 1. Mở kết nối đến database.
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_mysql", "root", "");
            // 2. Tạo câu lệnh prepareStatement
            String prepareSql = "insert into users" + "(username, password, full_name, identity_card, phone, dob, status) " + "values " + "(?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(prepareSql);
            // 3. Thực thi câu lệnh
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFullName());
            preparedStatement.setString(4, user.getIdentityCard());
            preparedStatement.setString(5, user.getPhone());
            preparedStatement.setString(6, user.getDob().toString());
            preparedStatement.setInt(7, user.getStatus());
            preparedStatement.execute();
            // 4. Đóng kết nối.
            connection.close();
        } catch (SQLException e) {
            System.out.println("Có lỗi ra, vui lòng thử lại sau.");
            e.printStackTrace();
        }
    }

    public ArrayList<User> findAll(){
        ArrayList<User> users = new ArrayList<>();
        try {
            // 1. Mở kết nối đến database.
            Connection connection =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/java2",
                            "root",
                            "root");
            // 2. Tạo câu lệnh prepareStatement
            String prepareSql = "select * from users";
            PreparedStatement preparedStatement = connection.prepareStatement(prepareSql);
            // 3. Thực thi câu lệnh
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Long id = resultSet.getLong("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String fullName = resultSet.getString("full_name");
                String identityCard = resultSet.getString("identity_card");
                String phone = resultSet.getString("phone");
                String dob = resultSet.getString("dob");
                int status = resultSet.getInt("status");
                User user = new User();
                user.setId(id);
                user.setUsername(username);
                user.setPassword(password);
                user.setFullName(fullName);
                user.setIdentityCard(identityCard);
                user.setPhone(phone);
                user.setDob(LocalDate.parse(dob));
                user.setStatus(status);
                users.add(user);
            }
            // 4. Đóng kết nối.
            connection.close();
        } catch (SQLException e) {
            System.out.println("Có lỗi xảy ra, vui lòng thử lại sau.");
            e.printStackTrace();
        }
        return users;
    }

    public User findById(Long id){
        try {
            // 1. Mở kết nối đến database.
            Connection connection =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/java2",
                            "root",
                            "root");
            // 2. Tạo câu lệnh prepareStatement
            String prepareSql = "select * from users where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(prepareSql);
            preparedStatement.setLong(1, id);
            // 3. Thực thi câu lệnh
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String fullName = resultSet.getString("full_name");
                String identityCard = resultSet.getString("identity_card");
                String phone = resultSet.getString("phone");
                String dob = resultSet.getString("dob");
                int status = resultSet.getInt("status");
                User user = new User();
                user.setId(id);
                user.setUsername(username);
                user.setPassword(password);
                user.setFullName(fullName);
                user.setIdentityCard(identityCard);
                user.setPhone(phone);
                user.setDob(LocalDate.parse(dob));
                user.setStatus(status);
                return user;
            }
            // 4. Đóng kết nối.
            connection.close();
        } catch (SQLException e) {
            System.out.println("Có lỗi xảy ra, vui lòng thử lại sau.");
            e.printStackTrace();
        }
        return null;
    }
}
