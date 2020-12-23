package utils;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DaoUtils {

    public static boolean migrated = false;


    public static void Migrate(Connection connection, ServletContext context) {
        if (!migrated) {
            try {
                String createDbCommand = new String(
                        Files.readAllBytes(Paths.get(context.getRealPath("/WEB-INF/db/create-database.sql"))),
                        StandardCharsets.UTF_8
                );
                String createReferencesCommand = new String(
                        Files.readAllBytes(Paths.get(context.getRealPath("/WEB-INF/db/create-references.sql"))),
                        StandardCharsets.UTF_8
                );
                String createCommentsCommand = new String(
                        Files.readAllBytes(Paths.get(context.getRealPath("/WEB-INF/db/create-comments.sql"))),
                        StandardCharsets.UTF_8
                );
                connection.createStatement().execute(createDbCommand);
                connection.createStatement().execute(createReferencesCommand);
                connection.createStatement().execute(createCommentsCommand);
                migrated = true;
            } catch (SQLException | IOException error) {
                System.out.println("Error");
                error.printStackTrace();
            }
        }
    }

    public static Properties getDbProperties() {
        Properties properties = new Properties();

        String hostEnv = System.getenv("DbHost");
        String portEnv = System.getenv("DbPort");
        String optionsEnv = System.getenv("DbOptions");
        String usernameEnv = System.getenv("DbUsername");
        String passwordEnv = System.getenv("DbPassword");

        String url = "jdbc:mysql://"
                + (hostEnv == null ? "localhost" : hostEnv) + ":"
                + (portEnv == null ? "3306" : portEnv) + "?"
                + (optionsEnv == null ? "serverTimezone=UTC" : optionsEnv);

        System.out.println("Db url: " + url);

        properties.put("url", url);
        properties.put("username", usernameEnv == null ? "root" : usernameEnv);
        properties.put("password", passwordEnv == null ? "root" : passwordEnv);
        properties.put("driverClassName", "com.mysql.jdbc.Driver");
        return properties;
    }
}
