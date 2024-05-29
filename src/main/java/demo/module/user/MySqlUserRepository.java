package demo.module.user;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Implement tất cả method của UserRepository và làm việc với MySql database.
 * */
public class MySqlUserRepository implements UserRepository{

    DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
    // Tên hằng số
    private final String MYSQL_CONNECTION_STRING = "jdbc:mysql://localhost:3306/java_mysql";
    private final String MYSQL_USERNAME = "root";
    private final String MYSQL_PASSWORD = "";

    @Override
    public ArrayList<User> findAll() {
        ArrayList<User> users = new ArrayList<>();
        try {
            // 1. Mở kết nối đến database.
            Connection connection =
                    DriverManager.getConnection(MYSQL_CONNECTION_STRING, MYSQL_USERNAME, MYSQL_PASSWORD);
            // 2. Tạo câu lệnh prepareStatement
            String prepareSql = "select * from users where status = 1";
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
                user.setDob(LocalDate.parse(dob,formatter));
                user.setStatus(status);
                users.add(user);
            }
            // 4. Đóng kết nối.
            connection.close();
        } catch (SQLException e) {
            System.out.println("Có lỗi xảy ra, vui lòng thử lại sau.");
        }
        return users;
    }

    @Override
    public User findById(Long id) {
        User user = null;
        try {
            // 1. Mở kết nối đến database.
            Connection connection =
                    DriverManager.getConnection(MYSQL_CONNECTION_STRING, MYSQL_USERNAME, MYSQL_PASSWORD);
            // 2. Tạo câu lệnh prepareStatement
            String prepareSql = "select * from users where id = ?"; // có tham số id.
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
                user = new User();
                user.setId(id);
                user.setUsername(username);
                user.setPassword(password);
                user.setFullName(fullName);
                user.setIdentityCard(identityCard);
                user.setPhone(phone);
                user.setDob(LocalDate.parse(dob,formatter));
                user.setStatus(status);
            }
            // 4. Đóng kết nối.
            connection.close();
        } catch (SQLException e) {
            System.out.println("Có lỗi xảy ra, vui lòng thử lại sau.");
        }
        return user;
    }

    @Override
    public User save(User user) {
        try {
            // 1. Mở kết nối đến database.
            Connection connection =
                    DriverManager.getConnection(MYSQL_CONNECTION_STRING, MYSQL_USERNAME, MYSQL_PASSWORD);
            // 2. Tạo câu lệnh prepareStatement
            String prepareSql =
                    "insert into users " + "(username, password, full_name, identity_card, phone, dob, status) " + "values " + "(?, ?, ?, ?, ?, ?, ?)";
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
            System.out.println("Save success!");
            // 4. Đóng kết nối.
            connection.close();
        } catch (SQLException e) {
            System.out.println("Có lỗi xảy ra, vui lòng thử lại sau.");
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User update(User user) {
        try {
            // 1. Mở kết nối đến database.
            Connection connection =
                    DriverManager.getConnection(MYSQL_CONNECTION_STRING, MYSQL_USERNAME, MYSQL_PASSWORD);
            // 2. Tạo câu lệnh prepareStatement
            String prepareSql =
                    "update users set username = ?, password = ?, full_name = ?, " + "identity_card = ?, phone = ?, dob = ?, status = ? where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(prepareSql);
            // 3. Thực thi câu lệnh
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFullName());
            preparedStatement.setString(4, user.getIdentityCard());
            preparedStatement.setString(5, user.getPhone());
            preparedStatement.setString(6, user.getDob().toString());
            preparedStatement.setInt(7, user.getStatus());
            preparedStatement.setLong(8, user.getId());
            preparedStatement.execute();
            System.out.println("Updated success!");
            // 4. Đóng kết nối.
            connection.close();
        } catch (SQLException e) {
            System.out.println("Có lỗi xảy ra, vui lòng thử lại sau.");
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void deleteById(Long id) {
        // delete from users where id = ? // hard delete.
        // update users set status = -1 where id = ? // soft delete.
        try {
            // 1. Mở kết nối đến database.
            Connection connection =
                    DriverManager.getConnection(MYSQL_CONNECTION_STRING, MYSQL_USERNAME, MYSQL_PASSWORD);
            // 2. Tạo câu lệnh prepareStatement
            String prepareSql =
                    "update users set status = -1 where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(prepareSql);
            // 3. Thực thi câu lệnh
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            System.out.println("Deleted success!");
            // 4. Đóng kết nối.
            connection.close();
        } catch (SQLException e) {
            System.out.println("Có lỗi xảy ra, vui lòng thử lại sau.");
            e.printStackTrace();
        }
    }
}
