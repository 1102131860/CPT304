package User;

public interface User {
    String getName();
    String getEmail();
    String getID();
    User copy();
    boolean equals(Object that);
    void update(String message);
    String toString();
}
