package com.albertinodev.services;

import com.albertinodev.dao.CountryDAO;

/**
 *
 * @author Augusto Marrengula */

public class Generator
{
    
    private int generatedNumber = 0;
    private String generatedWord = "";
    
    private int randomizer;
    private int maxRandomNumber = 900000;
    
    public Generator()
    {
        double randomNumber = Math.random();
        randomizer = (int) (1 + randomNumber * (maxRandomNumber - 1));
        
        this.generatedWord = "SomIDGen";
        this.generatedNumber = randomizer;
    }
    
    // Metodo para gerar ID
    public String generateCustomId()
    {
        int count = new CountryDAO().countCountries() + 1;
        return this.generatedWord + "CounTrY" + count + "kEy" + count + this.generatedNumber;
    }

}
