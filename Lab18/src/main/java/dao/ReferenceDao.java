package dao;

import models.Reference;
import utils.DaoUtils;

import javax.servlet.ServletContext;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ReferenceDao {

    private final Connection connection;


    public ReferenceDao(ServletContext context) {
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

    public List<Reference> get(String filter) throws SQLException {
        if (filter == null) {
            filter = "";
        }
        String[] terms = filter.split(" ");
        ArrayList<String> queryTerms = new ArrayList<>();
        for (String term: terms) {
            queryTerms.add(" description like '%" + term + "%'");
        }
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(
                "select * from java_lab18.`references` where " + String.join(" or ", queryTerms)
        );
        List<Reference> references = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String url = resultSet.getString(2);
            String description = resultSet.getString(3);
            int minus = resultSet.getInt(4);
            int plus = resultSet.getInt(5);
            references.add(new Reference(id, url, description, minus, plus));
        }
        return references;
    }

    public Reference getById(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "select * from java_lab18.`references` where id = ?"
        );
        preparedStatement.setInt(1, id);
        ResultSet result = preparedStatement.executeQuery();

        result.next();
        return new Reference(
                result.getInt(1),
                result.getString(2),
                result.getString(3),
                result.getInt(4),
                result.getInt(5)
        );
    }

    public Reference create(Reference reference) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into java_lab18.`references` values (?, ?, ?, ?, ?)"
        );
        preparedStatement.setInt(1, reference.getId());
        preparedStatement.setString(2, reference.getUrl());
        preparedStatement.setString(3, reference.getDescription());
        preparedStatement.setInt(4, reference.getMinus());
        preparedStatement.setInt(5, reference.getPlus());
        preparedStatement.execute();

        return reference;
    }

    public Reference update(Reference reference) throws SQLException {
        StringBuilder sqlCommand = new StringBuilder("update java_lab18.`references` set ");
        if (reference.getUrl() != null && reference.getDescription() != null) {
            sqlCommand.append(" url = '").append(reference.getUrl()).append("',")
                    .append(" description = '").append(reference.getDescription()).append("'");
        }
        if (reference.getPlus() != -1) {
            sqlCommand.append(" plus = ").append(reference.getPlus() + 1);
        }
        if (reference.getMinus() != -1) {
            sqlCommand.append(" minus = ").append(reference.getMinus() + 1);
        }
        sqlCommand.append(" where id = ").append(reference.getId());
        connection.createStatement().executeUpdate(sqlCommand.toString());

        return getById(reference.getId());
    }

    public Reference delete(int id) throws SQLException {
        Reference reference = getById(id);

        PreparedStatement deleteCommentsStatement = connection.prepareStatement("delete from java_lab18.`comments` where ref_id = ?");
        deleteCommentsStatement.setInt(1, id);
        deleteCommentsStatement.execute();

        PreparedStatement deleteReferenceStatement = connection.prepareStatement("delete from java_lab18.`references` where id = ?");
        deleteReferenceStatement.setInt(1, id);
        deleteReferenceStatement.execute();

        return reference;
    }
}
