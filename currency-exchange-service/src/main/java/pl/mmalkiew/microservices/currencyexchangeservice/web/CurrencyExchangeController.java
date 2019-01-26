package pl.mmalkiew.microservices.currencyexchangeservice.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.mmalkiew.microservices.currencyexchangeservice.model.ExchangeValue;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyExchangeController.class);

    private static final String LOCAL_SERVER_PORT_PROPERTY = "local.server.port";

    private final Environment environment;

    public CurrencyExchangeController(Environment environment) {
        this.environment = environment;
    }

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public ExchangeValue retrieveExchangeValue(@PathVariable String from,
                                               @PathVariable String to){


        LOGGER.info("from {} to {} ", from, to);

        ExchangeValue exchangeValue = new ExchangeValue();
        exchangeValue.setFrom(from);
        exchangeValue.setTo(to);
        exchangeValue.setConversionMultiple(new BigDecimal(65));
        exchangeValue.setPort(Integer.parseInt(environment.getProperty(LOCAL_SERVER_PORT_PROPERTY)));

        LOGGER.info("{} -> model exchange value", exchangeValue);

        return exchangeValue;
    }
}
