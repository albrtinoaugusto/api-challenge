
package com.albertinodev.dao;


import com.albertinodev.factory.Connector;
import com.albertinodev.model.Country;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Albertino Augusto */

public class CountryDAO {
    
    private Country country;
    private Statement statement;
    private ResultSet resultSet;
    private Connection connection;
    
    public CountryDAO()
    {
        this.country = null;
        
        this.statement = null;
        this.resultSet = null;
        this.connection = new Connector().getConnection();
    }
    
    
    public boolean saveCountry(Country country)
    {
        boolean done = false;
        
        try 
        {
            this.statement = this.connection.createStatement();
            String sql = "INSERT INTO countries VALUES('" +  country.getId() + "', '" + country.getName() + "', '" + country.getCapital()+ "', '" + country.getRegion() + "', '" + country.getSubRegion() + "', '" + country.getArea() + "')";
            this.statement.executeUpdate(sql);
            done = true;
        }
        catch (SQLException e) 
        {
            System.err.println("Error: " + e);
        }
        finally
        {
            new Connector().closeConnections(this.statement, this.resultSet, this.connection);
        }

        return done; 
    }
    
    
    public boolean updateCountry(Country country)
    {
        
        boolean done = false;
        String querySQL = "UPDATE countries SET ";
        
        if (country.getName().length() > 0) {
            querySQL += "name='" + country.getName() + "' , ";
        }
        
        if (country.getCapital().length() > 0) {
            querySQL += "capital='" + country.getCapital() + "' , ";
        }
        
        if (country.getRegion().length() > 0) {
            querySQL += "region='" + country.getRegion() + "' , ";
        }
        
        if (country.getSubRegion().length() > 0) {
            querySQL += "sub_region='" + country.getSubRegion() + "' , ";
        }
        
        if (country.getArea().length() > 0) {
            querySQL += "area='" + country.getArea() + "' , ";
        }
        
        String whereClouse = "where id='" + country.getId() + "'";
        querySQL += whereClouse;
                
        querySQL = querySQL.replace(" , "+ whereClouse, " " + whereClouse);
        
        try 
        {
            System.out.println(querySQL);
            this.statement = this.connection.createStatement();
            this.statement.executeUpdate(querySQL);
            done = true;
        }
        catch (SQLException e) 
        {
            System.out.println("Error: " + e);
        }
        finally
        {
            new Connector().closeConnections(this.statement, this.resultSet, this.connection);
        }

        return done; 
    }
    
    
   
    public boolean deleteCountry(String id)
    {
        boolean done = false;
        
        try 
        {
            this.statement = this.connection.createStatement();
            this.statement.executeUpdate("DELETE FROM countries where id='" + id + "'");
            done = true;
        }
        catch (SQLException e) 
        {
            System.out.println("Error: " + e);
        }
        finally
        {
            new Connector().closeConnections(this.statement, this.resultSet, this.connection);
        }

        return done; 
    }
    
    
    public int countCountries()
    {
        int number = 0;
        
        try 
        {
            this.statement = this.connection.createStatement();
            this.resultSet = this.statement.executeQuery("select count(*) AS counter from countries");
            this.resultSet.next();
            
            number = Integer.parseInt(this.resultSet.getString("counter"));
        }
        catch (SQLException e) 
        {
            System.out.println("Error: " + e);
        }
        finally
        {
            new Connector().closeConnections(this.statement, this.resultSet, this.connection);
        }

        return number; 
    }
    
    
    public List getCountriesList()
    {
        List<Country> countriesList = new ArrayList<Country>();
        
        try 
        {
            this.statement = this.connection.createStatement();
            this.resultSet = this.statement.executeQuery("SELECT * FROM countries");
            
            while (this.resultSet.next()) 
            {    
                Country countryFromDB = new Country (
                    this.resultSet.getString("id"), 
                    this.resultSet.getString("name"), 
                    this.resultSet.getString("capital"), 
                    this.resultSet.getString("region"), 
                    this.resultSet.getString("sub_region"), 
                    this.resultSet.getString("area")
                );
                
                countriesList.add(countryFromDB);
            }
        }
        catch (SQLException e) 
        {
            System.out.println("Error: " + e);
        }
        finally
        {
            new Connector().closeConnections(this.statement, this.resultSet, this.connection);
        }

        return countriesList; 
    }
    
    
    public List getCountriesList(String field, String keyValue)
    {
        List<Country> countriesList = new ArrayList<Country>();
        
        try 
        {
            this.statement = this.connection.createStatement();
            this.resultSet = this.statement.executeQuery("SELECT * FROM countries where " + field + "='" + keyValue + "'");
            
            while (this.resultSet.next()) 
            {    
                Country countryFromDB = new Country (
                    this.resultSet.getString("id"), 
                    this.resultSet.getString("name"), 
                    this.resultSet.getString("capital"), 
                    this.resultSet.getString("region"), 
                    this.resultSet.getString("sub_region"), 
                    this.resultSet.getString("area")
                );
                
                countriesList.add(countryFromDB);
            }
        }
        catch (SQLException e) 
        {
            System.out.println("Error: " + e);
        }
        finally
        {
            new Connector().closeConnections(this.statement, this.resultSet, this.connection);
        }

        return countriesList; 
    }
      
    
    public List getOrderedCountriesList(String field)
    {
        List<Country> countriesList = new ArrayList<Country>();
        
        try 
        {
            this.statement = this.connection.createStatement();
            this.resultSet = this.statement.executeQuery("SELECT * FROM countries ORDER BY " + field + " ASC"); // Can be changed to DESC
            
            while (this.resultSet.next()) 
            {    
                Country countryFromDB = new Country (
                    this.resultSet.getString("id"), 
                    this.resultSet.getString("name"), 
                    this.resultSet.getString("capital"), 
                    this.resultSet.getString("region"), 
                    this.resultSet.getString("sub_region"), 
                    this.resultSet.getString("area")
                );
                
                countriesList.add(countryFromDB);
            }
        }
        catch (SQLException e) 
        {
            System.out.println("Error: " + e);
        }
        finally
        {
            new Connector().closeConnections(this.statement, this.resultSet, this.connection);
        }

        return countriesList; 
    }
    
}
