package com.keyin.airports;

import com.keyin.data.Database;

import java.util.ArrayList;
import java.util.List;

public class AirportManager {
    private Database database;

    public List<Airport> getAirportsByCityName(String cityName) {
        City city = database.getCityByName(cityName);
        List<Airport> airports = database.getAllAirports();

        List<Airport> airportsToReturn = new ArrayList<Airport>();

        for (Airport a : airports) {
            if (a.getCity().getName().equals(city.getName())) {
                airportsToReturn.add(a);
            }
        }

        return airportsToReturn;
    }

    public Database getDatabase() {
        return database;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }
}
