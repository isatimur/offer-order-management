package ru.atc.aeroflot.ndc.service.util;

/**
 * Created by isati on 05.12.2016.
 */

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.*;
import java.util.Arrays;
import java.util.stream.Collectors;

public class XmlValidatorWithSchemaFactory {
    static String xmlFilename = "C:\\work-projects\\aeroflot\\offer-order-management\\ndc-jaxb\\src\\main\\resources\\AirShoppingRS-RoundTrip.xml";
    static String schemaFilename = "C:\\work-projects\\aeroflot\\offer-order-management\\ndc-jaxb\\src\\main\\resources\\AirShoppingRQ.xsd";

    public static void main(String[] args) throws Exception {
        XmlValidatorWithSchemaFactory validator
                = new XmlValidatorWithSchemaFactory();
        Document document = validator.loadXml(xmlFilename);

        try {
            validator.validate(document, schemaFilename);
            System.out.println("The file is valid!");
        } catch (Exception e) {
            System.err.println("The file is invalid! Reason:");
            System.err.println(e.getMessage());
        }
    }

    public void validate(Document document, String schemaFile)
            throws SAXException, IOException {
        SchemaFactory factory = SchemaFactory.newInstance(
                XMLConstants.W3C_XML_SCHEMA_NS_URI);

        StreamSource[] xsdSources = Arrays.stream(new String[]{schemaFilename})
                .map(StreamSource::new)
                .collect(Collectors.toList())
                .toArray(new StreamSource[1]);

        try {
            final Schema schema = factory.newSchema(xsdSources);
            final Validator validator = schema.newValidator();
            System.out.println("Validating " + xmlFilename + " against XSDs "
                    + schemaFilename + "...");
            validator.validate(new StreamSource(new File(xmlFilename)));
        } catch (IOException | SAXException exception)  // JDK 7 multi-exception catch
        {
        }
        System.out.println("Validation process completed.");
    }

    private DocumentBuilder createDocumentBuilder()
            throws ParserConfigurationException {
        DocumentBuilderFactory builderFactory
                = DocumentBuilderFactory.newInstance();
        builderFactory.setNamespaceAware(true);
        return builderFactory.newDocumentBuilder();
    }

    private Document loadXml(String xmlToValidate) throws Exception {
        DocumentBuilder builder = createDocumentBuilder();
        return builder.parse(getInputStream(xmlToValidate));
    }

    private InputStream getInputStream(String filename) {
        try {
            return new FileInputStream(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
