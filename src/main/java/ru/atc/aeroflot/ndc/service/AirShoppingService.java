package ru.atc.aeroflot.ndc.service;

import org.iata.iata.edist.AirShoppingRQ;
import org.iata.iata.edist.AirShoppingRS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import ru.atc.aeroflot.ndc.service.util.NDCUtil;

/**
 * Created by isati on 30.11.2016. Service provides simple mock result of searching offers.
 */
@Service
public class AirShoppingService {

    @Autowired
    private ResourceLoader resourceLoader;

    private final Logger log = LoggerFactory.getLogger(AirShoppingService.class);

    public AirShoppingRS findOffers(AirShoppingRQ request) {
        return new AirShoppingRS();
    }

    public AirShoppingRS oneWay(AirShoppingRQ request) {
        AirShoppingRS airShoppingRS = NDCUtil.getMockResponse(resourceLoader,AirShoppingRS.class, "classpath:/Kronos/OneWay/02_AirshoppingRS.xml");
        return airShoppingRS;
    }

    public AirShoppingRQ oneWayRequestExample() {
        AirShoppingRQ airShoppingRQ = NDCUtil.getMockResponse(resourceLoader,AirShoppingRQ.class, "classpath:/Kronos/OneWay/01_AirshoppingRQ.xml");
        return airShoppingRQ;
    }

    public AirShoppingRS twoWayTrip(AirShoppingRQ request) {
        AirShoppingRS airShoppingRS = NDCUtil.getMockResponse(resourceLoader,AirShoppingRS.class, "classpath:/Kronos/RoundTrip/02_AirshoppingRS_RoundTrip.xml");
        return airShoppingRS;
    }

}
