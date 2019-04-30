package com.murk.converter.dao;

import com.murk.converter.model.Classificator;
import com.murk.converter.utils.PropertiesReader;

import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import static com.murk.converter.dao.SqlHelper.INSERT_CLASSIFICATOR;

public class ClassificatorDao {

    private String url = PropertiesReader.readProperty("./help/db.properties","db_connection");
    private Properties props = new Properties();

    {
        props.setProperty("user", PropertiesReader.readProperty("./help/db.properties","login"));
        props.setProperty("password", PropertiesReader.readProperty("./help/db.properties","password"));

    }

    public void save(Map<Integer,Classificator> classificators)
    {
        System.out.println("Start save classificators in dao");
        try(Connection connection = DriverManager.getConnection(url,props))
        {
            connection.setAutoCommit(false);

            for (Map.Entry<Integer, Classificator> entry : classificators.entrySet()) {
                Integer id = entry.getKey();
                Classificator classificator = entry.getValue();

                save(classificator, connection);
                connection.commit();
            }

            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void save(Classificator classificator, Connection connection)
    {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_CLASSIFICATOR);)
        {
            statement.setInt(1,classificator.getId());
            statement.setShort(2,classificator.getNum());

            if (classificator.getParentId()== null)
            {
                statement.setNull(3, Types.SMALLINT);
            } else
                {
                    statement.setInt(3,classificator.getParentId());
                }

            statement.setString(4,classificator.getName());

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

