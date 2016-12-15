package ru.atc.aeroflot.ndc.web.rest;

/**
 * Created by isati on 01.12.2016.
 */

import com.codahale.metrics.annotation.Timed;
import org.iata.iata.edist.AirShoppingRQ;
import org.iata.iata.edist.AirShoppingRS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.atc.aeroflot.ndc.service.AirShoppingService;

import javax.inject.Inject;

/**
 * REST controller for managing users.
 * <p>
 * <p>This class accesses the User entity, and needs to fetch its collection of authorities.</p>
 * <p>
 * For a normal use-case, it would be better to have an eager relationship between User and Authority,
 * and send everything to the client side: there would be no View Model and DTO, a lot less code, and an outer-join
 * which would be good for performance.
 * </p>
 * <p>
 * We use a View Model and a DTO for 3 reasons:
 * <ul>
 * <li>We want to keep a lazy association between the user and the authorities, because people will
 * quite often do relationships with the user, and we don't want them to get the authorities all
 * the time for nothing (for performance reasons). This is the #1 goal: we should not impact our users'
 * application because of this use-case.</li>
 * <li> Not having an outer join causes n+1 requests to the database. This is not a real issue as
 * we have by default a second-level cache. This means on the first HTTP call we do the n+1 requests,
 * but then all authorities come from the cache, so in fact it's much better than doing an outer join
 * (which will get lots of data from the database, for each HTTP call).</li>
 * <li> As this manages users, for security reasons, we'd rather have a DTO layer.</li>
 * </ul>
 * <p>Another option would be to have a specific JPA entity graph to handle this case.</p>
 */
@RestController
@RequestMapping("/api")
public class AirShoppingResource {

    private final Logger log = LoggerFactory.getLogger(AirShoppingResource.class);

    @Inject
    AirShoppingService shoppingService;

    @GetMapping(path = "/offers", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @Timed
    public AirShoppingRS getAllOffers() {
        //TODO create AirShoppingRQ request
        AirShoppingRS airShoppingRS = shoppingService.findOffers(null);
        return airShoppingRS;
    }

    //TODO just leave a method post of offers and based on the content implement recognition and BPM functions
    @PostMapping(path = "/offers", consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @Timed
    public AirShoppingRS getOffersOneWay(@RequestBody AirShoppingRQ shoppingRQ) {
        //TODO create AirShoppingRQ request
        AirShoppingRS airShoppingRS = shoppingService.oneWay(shoppingRQ);
        return airShoppingRS;
    }


    @GetMapping(path = "/offers/request", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @Timed
    public AirShoppingRQ getOffersOneWayRequestExample() {
        //TODO create AirShoppingRQ request
        AirShoppingRQ airShoppingRQ = shoppingService.oneWayRequestExample();
        return airShoppingRQ;
    }

    @GetMapping(path = "/offers/roundtrip/request", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @Timed
    public AirShoppingRQ getRoundtripRequest() {
        //TODO create AirShoppingRQ request
        AirShoppingRQ airShoppingRQ = shoppingService.roundTripRequest();
        return airShoppingRQ;
    }

    @GetMapping(path = "/offers/roundtrip/response", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @Timed
    public AirShoppingRS getRoundtripResponse() {
        //TODO create AirShoppingRQ request
        AirShoppingRS airShoppingRS = shoppingService.roundTripResponse();
        return airShoppingRS;
    }


    @GetMapping(path = "/offers/onewaytrip", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @Timed
    public AirShoppingRS getOffersOneWay() {
        //TODO create AirShoppingRQ request
        AirShoppingRS airShoppingRS = shoppingService.oneWay(null);
        return airShoppingRS;
    }

    @GetMapping(path = "/offers/twowaytrip", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @Timed
    public AirShoppingRS getOffersTwoWayTrip() {
        //TODO create AirShoppingRQ request
        AirShoppingRS airShoppingRS = shoppingService.twoWayTrip(null);
        return airShoppingRS;
    }
}
