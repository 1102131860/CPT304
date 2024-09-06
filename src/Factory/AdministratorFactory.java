package Factory;

import User.Administrator;

public class AdministratorFactory extends UserFactory {
    @Override
    public Administrator getUser(String name, String email, String ID) {
        System.out.println("A new Student has been created");
        return new Administrator(name, email, ID);
    }
}
