package ru.atc.aeroflot.ndc.service.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ResourceLoader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.*;

/**
 * Created by isati on 01.12.2016.
 */
public class NDCUtil {

    private static final ExecutorService executorService = Executors.newFixedThreadPool(2);

    private static final Logger log = LoggerFactory.getLogger(NDCUtil.class);

    public static <T> T getMockResponse(ResourceLoader resourceLoader, Class<T> t, String filePath) {
        try {
            Future<T> future = executorService.submit(new NDCUtil().new CallableUnmarshalling<T>(filePath, resourceLoader));
            return future.get();
        } catch (InterruptedException e) {
            log.error("Interrupted exception on filePath: " + filePath);
        } catch (ExecutionException e) {
            log.error("Execution exception on filePath: " + filePath);
        }
        return null;
    }


    private class CallableUnmarshalling<T> implements Callable<T> {
        private String filePath;
        private ResourceLoader resourceLoader;
        /**
         * JaxbContext for NDC schema
         */
        private final JAXBContext context = null;

        public CallableUnmarshalling(String filePath, ResourceLoader resourceLoader) {
            this.filePath = filePath;
            this.resourceLoader = resourceLoader;
        }

        /**
         * Returns JAXBContext for IATA NDC Schema.
         * Creates the context if it does not exist.
         *
         * @return JAXBContext instance representing NDC schema
         * @throws RuntimeException if context creation failed.
         */
        public JAXBContext getJaxbContext() throws RuntimeException {
            if (context != null) return context;
            try {
                return JAXBContext.newInstance("org.iata.iata.edist");
            } catch (JAXBException e) {
                log.error("Failure creating JAXB context", e);
                throw new RuntimeException(e);
            }
        }

        private <T> T unmarshallRequest(InputStream response) {
            try {
                JAXBContext context = getJaxbContext();
                Unmarshaller unmarshaller = context.createUnmarshaller();
                return (T) unmarshaller.unmarshal(response);
            } catch (JAXBException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public T call() throws Exception {
            try (InputStream inputStream = resourceLoader.getResource(filePath).getInputStream()) {
                return unmarshallRequest(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
                return null;
           }
    }

}
