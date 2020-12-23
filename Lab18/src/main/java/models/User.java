package models;


public class User {

    public String username;
    public String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
