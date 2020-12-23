package dao;

import models.Comment;
import utils.DaoUtils;

import javax.servlet.ServletContext;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CommentDao {

    private final Connection connection;


    public CommentDao(ServletContext context) {
        try {
            Properties properties = DaoUtils.getDbProperties();
            Class.forName(properties.getProperty("driverClassName"));

            this.connection = DriverManager.getConnection(
                    properties.getProperty("url"),
                    properties.getProperty("username"),
                    properties.getProperty("password")
            );
            DaoUtils.Migrate(this.connection, context);
        } catch (SQLException | ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }

    public List<Comment> get(int referenceId) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "select * from java_lab18.`comments` where reference_id = ?"
        );
        preparedStatement.setInt(1, referenceId);

        ResultSet resultSet = preparedStatement.executeQuery();
        List<Comment> comments = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String sessionId = resultSet.getString(3);
            Date stamp = resultSet.getDate(4);
            String comment = resultSet.getString(5);
            comments.add(new Comment(id, referenceId, sessionId, stamp, comment));
        }
        return comments;
    }

    public Comment getById(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "select * from java_lab18.`comments` where id = ?"
        );
        preparedStatement.setInt(1, id);
        ResultSet result = preparedStatement.executeQuery();

        result.next();
        return new Comment(
                result.getInt(1),
                result.getInt(2),
                result.getString(3),
                result.getDate(4),
                result.getString(5)
        );
    }

    public Comment create(Comment comment) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into java_lab18.`comments` values (?, ?, ?, ?, ?)"
        );
        java.util.Date today = new java.util.Date();
        preparedStatement.setInt(1, comment.getId());
        preparedStatement.setInt(2, comment.getReferenceId());
        preparedStatement.setString(3, comment.getSessionId());
        preparedStatement.setDate(4, new Date(today.getYear(), today.getMonth(), today.getDate()));
        preparedStatement.setString(5, comment.getComment());
        preparedStatement.execute();

        return comment;
    }

    public Comment update(Comment comment) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "update java_lab18.`comments` set comment = ? where id = ?"
        );
        preparedStatement.setString(1, comment.getComment());
        preparedStatement.setInt(2, comment.getId());
        preparedStatement.executeUpdate();

        return getById(comment.getId());
    }

    public Comment delete(int id) throws SQLException {
        Comment comment = getById(id);

        PreparedStatement preparedStatement = connection.prepareStatement(
                "delete from java_lab18.`comments` where id = ?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();

        return comment;
    }
}
