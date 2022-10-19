package com.keyin.airports;

import com.keyin.data.Database;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class AirportManagerTest {
    @Mock
    private Database mockDatabase;

    @Test
    public void testGetAirportsByCityName() {
        City stJohns = new City();
        stJohns.setId(1);
        stJohns.setName("St. John's");

        City gander = new City();
        gander.setId(2);
        gander.setName("Gander");

        List<Airport> airportList = new ArrayList<Airport>();

        Airport stJohnAirport = new Airport();
        stJohnAirport.setId(1);
        stJohnAirport.setCity(stJohns);
        stJohnAirport.setCode("YYT");

        airportList.add(stJohnAirport);

        Mockito.when(mockDatabase.getCityByName("St. John's")).thenReturn(stJohns);
        Mockito.when(mockDatabase.getCityByName("Gander")).thenReturn(gander);
        Mockito.when(mockDatabase.getAllAirports()).thenReturn(airportList);

        AirportManager airportManagerUnderTest = new AirportManager();

        airportManagerUnderTest.setDatabase(mockDatabase);

        Assertions.assertEquals(1, airportManagerUnderTest.getAirportsByCityName("St. John's").size());

        Assertions.assertEquals(0, airportManagerUnderTest.getAirportsByCityName("Gander").size());

        Airport ganderAirport = new Airport();
        ganderAirport.setId(2);
        ganderAirport.setCity(gander);
        ganderAirport.setCode("YQX");

        airportList.add(ganderAirport);

        Assertions.assertEquals(1, airportManagerUnderTest.getAirportsByCityName("Gander").size());
        Assertions.assertEquals(1, airportManagerUnderTest.getAirportsByCityName("St. John's").size());
    }

}
