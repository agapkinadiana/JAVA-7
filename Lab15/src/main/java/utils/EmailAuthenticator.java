package utils;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class EmailAuthenticator extends Authenticator {

    private final String email;
    private final String password;


    public EmailAuthenticator(String email, String password) {
        this.email = email;
        this.password = password;
    }


    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(email, password);
    }
}
