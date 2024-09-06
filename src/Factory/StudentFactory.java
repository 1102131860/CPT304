package Factory;

import User.Student;

public class StudentFactory extends UserFactory {
    @Override
    public Student getUser(String name, String email, String ID) {
        System.out.println("A new Student has been created");
        return new Student(name, email, ID);
    }
}
