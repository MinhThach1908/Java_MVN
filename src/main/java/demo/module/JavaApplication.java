package demo.module;

import demo.module.user.MySqlUserRepository;
import demo.module.user.User;
import demo.module.user.UserRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class JavaApplication {
    public static void main(String[] args) {
        UserRepository userRepository = new MySqlUserRepository();
        Scanner scanner = new Scanner(System.in);
        // Xử lý thêm user.
        User user = new User();
        System.out.println("Enter username: ");
        user.setUsername(scanner.nextLine());
        System.out.println("Enter password: ");
        user.setPassword(scanner.nextLine());
        System.out.println("Enter fullname: ");
        user.setFullName(scanner.nextLine());
        System.out.println("Enter identityCard: ");
        user.setIdentityCard(scanner.nextLine());
        System.out.println("Enter phone: ");
        user.setPhone(scanner.nextLine());
        System.out.println("Enter dob: ");
        user.setDob(LocalDate.parse(scanner.nextLine()));
        System.out.println("Enter status: ");
        user.setStatus(scanner.nextInt());
        scanner.nextLine();
        userRepository.save(user);
        // Xử lý hiển thị user.
        ArrayList<User> list = userRepository.findAll();
        for (User u : list){
            System.out.println(u.getId() + " - " + u.getUsername() + " - " + u.getDob());
        }
    }
}
