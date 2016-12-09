package ru.atc.aeroflot.ndc.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.iata.iata.edist.MsgDocumentType;
import org.iata.iata.edist.MsgPartiesType;
import org.iata.iata.edist.OrderCreateParameters;
import org.iata.iata.edist.OrderCreateRQ;

import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigInteger;

/**
 * A DTO representing a order, with his authorities.
 */
public class OrderDTO {
    @JsonProperty(required = true)
    private MsgDocumentType document;

    @JsonProperty(required = true)
    private MsgPartiesType party;

    @JsonProperty(required = true)
    private String version;

    protected OrderCreateParameters orderCreateParameters;

    @JsonProperty(required = true)
    protected OrderCreateRQ.Query query;

    protected String echoToken;
    protected XMLGregorianCalendar timeStamp;
    protected String target;
    protected String transactionIdentifier;
    protected BigInteger sequenceNmbr;
    protected String transactionStatusCode;
    protected Boolean retransmissionIndicator;
    protected String correlationID;
    protected Boolean asynchronousAllowedInd;
    protected String language;

    public OrderDTO() {
    }

    public OrderDTO(MsgDocumentType document, MsgPartiesType party, String version, OrderCreateParameters orderCreateParameters, OrderCreateRQ.Query query, String echoToken, XMLGregorianCalendar timeStamp, String target, String transactionIdentifier, BigInteger sequenceNmbr, String transactionStatusCode, Boolean retransmissionIndicator, String correlationID, Boolean asynchronousAllowedInd, String language) {
        this.document = document;
        this.party = party;
        this.version = version;
        this.orderCreateParameters = orderCreateParameters;
        this.query = query;
        this.echoToken = echoToken;
        this.timeStamp = timeStamp;
        this.target = target;
        this.transactionIdentifier = transactionIdentifier;
        this.sequenceNmbr = sequenceNmbr;
        this.transactionStatusCode = transactionStatusCode;
        this.retransmissionIndicator = retransmissionIndicator;
        this.correlationID = correlationID;
        this.asynchronousAllowedInd = asynchronousAllowedInd;
        this.language = language;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
            "document=" + document +
            ", party=" + party +
            ", version='" + version + '\'' +
            ", orderCreateParameters=" + orderCreateParameters +
            ", query=" + query +
            ", echoToken='" + echoToken + '\'' +
            ", timeStamp=" + timeStamp +
            ", target='" + target + '\'' +
            ", transactionIdentifier='" + transactionIdentifier + '\'' +
            ", sequenceNmbr=" + sequenceNmbr +
            ", transactionStatusCode='" + transactionStatusCode + '\'' +
            ", retransmissionIndicator=" + retransmissionIndicator +
            ", correlationID='" + correlationID + '\'' +
            ", asynchronousAllowedInd=" + asynchronousAllowedInd +
            ", language='" + language + '\'' +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderDTO orderDTO = (OrderDTO) o;

        if (document != null ? !document.equals(orderDTO.document) : orderDTO.document != null) return false;
        if (party != null ? !party.equals(orderDTO.party) : orderDTO.party != null) return false;
        if (version != null ? !version.equals(orderDTO.version) : orderDTO.version != null) return false;
        return query != null ? query.equals(orderDTO.query) : orderDTO.query == null;

    }

    @Override
    public int hashCode() {
        int result = document != null ? document.hashCode() : 0;
        result = 31 * result + (party != null ? party.hashCode() : 0);
        result = 31 * result + (version != null ? version.hashCode() : 0);
        result = 31 * result + (query != null ? query.hashCode() : 0);
        return result;
    }
}
