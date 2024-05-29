package demo.module;

import demo.module.user.*;

import java.util.Scanner;

public class JavaApplication {
    public static void main(String[] args) {
        createMenu();
    }

    public static void createMenu(){
        Scanner sc = new Scanner(System.in);
        UserController userController = new UserController();
        while(true){
            System.out.println("User Management Menu.");
            System.out.println("------------------------------------");
            System.out.println("1. Create User.");
            System.out.println("2. View List User.");
            System.out.println("3. Update User.");
            System.out.println("4. Delete User.");
            System.out.println("5. Exit Application.");
            System.out.println("------------------------------------");
            System.out.println("Please enter your choice: ");
            int choice = sc.nextInt();
            switch (choice){
                case 1:
                    userController.create();
                    break;
                case 2:
                    userController.showList();
                    break;
                case 3:
                    userController.update();
                    break;
                case 4:
                    userController.delete();
                    break;
                case 5:
                    System.out.println("Exit Application.");
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
            if(choice == 5){
                break;
            }
        }

    }
}
