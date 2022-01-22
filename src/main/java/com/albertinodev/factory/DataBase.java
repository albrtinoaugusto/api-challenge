package com.albertinodev.factory;


import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Albertino Augusto */

public class DataBase 
{
    
    private final String ip;
    private final String port;
    private final String username;
    private final String password;
    
    private final String dataBaseName;
    private Connection connection;
    
    public DataBase(String dataBaseName, String ip, String port, String username, String password)
    {
        this.ip = ip;
        this.port = port;
        this.username = username;
        this.password = password;
        
        this.dataBaseName = dataBaseName;   
        this.connection = null;
    }

    
    public boolean createAndInicializeDatabase()
    {
        boolean done = false;
        
        try 
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String charaters = "mysql?zeroDateTimeBehavior=convertToNull";
            this.connection = DriverManager.getConnection("jdbc:mysql://" + this.ip + ":" + this.port + "/" + charaters, this.username, this.password);
            
            String create = "CREATE DATABASE IF NOT EXISTS " + this.dataBaseName;
            String use = "USE " + this.dataBaseName;
            String countries = "CREATE TABLE IF NOT EXISTS " +
                                "countries "+
                                "(" +
                                "   id           varchar(50)  not null, " +
                                "   name         varchar(100) not null, " +
                                "   capital      varchar(100) not null, " +
                                "   region       varchar(100) not null, " +
                                "   sub_region   varchar(100) not null, " +
                                "   area         varchar(100) not null, " +             
                                "   primary      key(id) " +
                                ")" +
                                "ENGINE=InnoDB DEFAULT CHARSET=utf8; ";
            
           
            try (Statement statement = connection.createStatement())
            {
                statement.executeUpdate(create);
                statement.executeUpdate(use);
                statement.executeUpdate(countries);
                
                System.out.println("DataBase Created");
                done = true;
                //Toolkit.getDefaultToolkit().beep();
            }
                           
            
        } 
        catch (SQLException | HeadlessException | ClassNotFoundException e)
        {
            System.out.println(e);
        }
        
        return done;
    }
        
    
    public Connection getConnection()
    {
        return connection;
    }
}
