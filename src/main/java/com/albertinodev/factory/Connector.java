package com.albertinodev.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Albertino Augusto */
public class Connector
{

    private final String ip = "remotemysql.com";
    private final String port = "3306";
    private final String username = "root";
    private final String password = "sBPfi9R1F8";
    private final String dataBaseName = "ufoCNL7gW4";   

    private Connection connection;

    public Connector()
    {
        this.connection = null;
    }

    
    public Connection getConnection()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection("jdbc:mysql://" + ip + ":" + port + "/" + dataBaseName, username, password);
            return this.connection;
        }
        catch (ClassNotFoundException | SQLException e)
        {
            System.out.println("Error: " + e);
            this.connection = new DataBase(dataBaseName, ip, port, username, password).getConnection();
            return this.connection;
        }
    }
   

    public void closeConnections(Statement statement, ResultSet resultSet, Connection connection)
    {

        try
        {
            if (statement != null)
            {
                statement.close();
            }

            if (resultSet != null)
            {
                resultSet.close();
            }

            if (connection != null)
            {
                connection.close();
            }
        }
        catch (SQLException ex)
        {
            System.out.println("Error: " + ex);
        }
    }

    
    public void closeConnections(PreparedStatement statement, ResultSet resultSet, Connection connection)
    {

        try
        {
            if (statement != null)
            {
                statement.close();
            }

            if (resultSet != null)
            {
                resultSet.close();
            }

            if (connection != null)
            {
                connection.close();
            }
        }
        catch (SQLException ex)
        {
            System.out.println("Error: " + ex);
        }
    }
}
