package ru.atc.aeroflot.ndc.service.mapper;

import org.iata.iata.edist.OrderCreateRQ;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.beans.factory.ObjectFactory;

/**
 * Created by isati on 07.12.2016.
 */
@Mapper(componentModel = "spring", uses = ObjectFactory.class, nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
public interface OrderMapper {
    OrderCreateRQ orderCreateRQToOrderDTO(OrderCreateRQ orderCreateRQ);
}
