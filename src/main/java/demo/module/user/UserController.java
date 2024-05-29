package demo.module.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class UserController {

    UserRepository userRepository = new MySqlUserRepository();

    public void create(){
        Scanner scanner = new Scanner(System.in);
        User user = new User();
        System.out.println("Please enter user information.");
        System.out.println("Enter username: ");
        user.setUsername(scanner.nextLine());
        System.out.println("Enter password: ");
        user.setPassword(scanner.nextLine());
        System.out.println("Enter fullname");
        user.setFullName(scanner.nextLine());
        System.out.println("Enter identityCard: ");
        user.setIdentityCard(scanner.nextLine());
        System.out.println("Enter phone: ");
        user.setPhone(scanner.nextLine());
        System.out.println("Enter dob: ");
        user.setDob(LocalDate.parse(scanner.nextLine()));
        System.out.println("Enter status:");
        user.setStatus(scanner.nextInt());
        scanner.nextLine();
        userRepository.save(user);
    }

    public void showList(){
        ArrayList<User> users = userRepository.findAll();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Show list users.");
        System.out.println("------------------------------------");
        System.out.printf("%-10s || %-30s || %-30s\n", "Id", "Full Name", "Username");
        System.out.println("===============================================================================");
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            System.out.printf("%-10s || %-30s || %-30s\n", user.getId(), user.getFullName(), user.getUsername());
        }
        System.out.println("Enter to continue.");
        scanner.nextLine();
    }

    public void update(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter user id to update:");
        long id = scanner.nextLong();
        scanner.nextLine();
        User user = userRepository.findById(id);
        if(user == null){
            System.out.println("User not found.");
            return;
        }
        System.out.println("Found 1 user");
        System.out.println(user.getId() + " - " + user.getFullName() + " - " + user.getUsername());
        System.out.println("Please enter new information.");
        System.out.println("Enter password: ");
        user.setPassword(scanner.nextLine());
        System.out.println("Enter fullname");
        user.setFullName(scanner.nextLine());
        System.out.println("Enter identityCard: ");
        user.setIdentityCard(scanner.nextLine());
        System.out.println("Enter phone: ");
        user.setPhone(scanner.nextLine());
        System.out.println("Enter dob: ");
        user.setDob(LocalDate.parse(scanner.nextLine()));
        System.out.println("Enter status:");
        user.setStatus(scanner.nextInt());
        scanner.nextLine();
        userRepository.update(user);
    }

    public void delete(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter user id to delete:");
        long id = scanner.nextLong();
        scanner.nextLine();
        User user = userRepository.findById(id);
        if(user == null){
            System.out.println("User not found.");
            return;
        }
        System.out.println("Found 1 user");
        System.out.println(user.getId() + " - " + user.getFullName() + " - " + user.getUsername());
        System.out.println("Do you want to delete the user (y/n)?");
        String choice =  scanner.nextLine();
        if(choice.equalsIgnoreCase("y")){
            userRepository.deleteById(id);
        }
    }
}