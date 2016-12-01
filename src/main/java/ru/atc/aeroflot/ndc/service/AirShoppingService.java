package ru.atc.aeroflot.ndc.service;

import org.iata.iata.edist.AirShoppingRQ;
import org.iata.iata.edist.AirShoppingRS;
import org.springframework.stereotype.Service;

/**
 * Created by isati on 30.11.2016. Service provides simple mock result of searching offers.
 */
@Service
public class AirShoppingService {

    public AirShoppingRS findOffers(AirShoppingRQ request) {
        return new AirShoppingRS();
    }

    public AirShoppingRS oneWay(AirShoppingRQ request) {
        return new AirShoppingRS();
    }

    public AirShoppingRS twoWayTrip(AirShoppingRQ request) {
        return new AirShoppingRS();
    }

}
