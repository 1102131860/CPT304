package Factory;

import User.User;
import java.util.Scanner;

abstract public class UserFactory {
    public User createUser(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please type in the name.");
        String name = scanner.nextLine();
        System.out.println("Please type in the email.");
        String email = scanner.nextLine();
        System.out.println("Please type in the ID.");
        String ID = scanner.nextLine();
        return getUser(name, email, ID);
    };

    public abstract User getUser(String name, String email, String ID);
}
