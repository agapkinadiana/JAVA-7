package utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class SessionUtils {

    public static String getSessionId(HttpServletRequest request) {
        String sessionId = "";
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("JSESSIONID")) {
                sessionId = cookie.getValue();
                break;
            }
        }
        return sessionId;
    }
}
