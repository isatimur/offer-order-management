package ru.atc.aeroflot.ndc.service;

import org.iata.iata.edist.OrderCreateRQ;
import org.iata.iata.edist.OrderViewRS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import ru.atc.aeroflot.ndc.service.util.NDCUtil;

/**
 * Created by isati on 30.11.2016. Service provides simple mock result for creating an order and view order's response.
 */
@Service
public class OrderService {
    @Autowired
    ResourceLoader resourceLoader;

    private final Logger log = LoggerFactory.getLogger(OrderService.class);

    public OrderViewRS createOrder(OrderCreateRQ request) {
        return new OrderViewRS();
    }

    public OrderViewRS oneWay(OrderCreateRQ orderCreateRQ) {
        OrderViewRS orderViewRS = NDCUtil.getMockResponse(resourceLoader, OrderViewRS.class, "classpath:/Kronos/OneWay/06_OrderViewRS.xml");
        return orderViewRS;
    }

    public OrderCreateRQ oneWay() {
        OrderCreateRQ orderCreateRQ = NDCUtil.getMockResponse(resourceLoader, OrderCreateRQ.class, "classpath:/Kronos/OneWay/05_OrderCreateRQ.xml");
        return orderCreateRQ;
    }

}
