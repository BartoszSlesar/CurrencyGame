package com.bard.springcurrencygame.services;

import com.bard.springcurrencygame.config.CurrencyConfig;
import com.bard.springcurrencygame.model.CurrencyList;
import com.bard.springcurrencygame.model.CurrencySymbols;
import com.bard.springcurrencygame.model.Symbol;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CurrentCurrencyService {

    private final RestTemplate restTemplate;

    private final CurrencyConfig currencyConfig;

    private CurrencySymbols currencySymbols;

    public CurrentCurrencyService(CurrencyConfig currencyConfig) {
        this.restTemplate = new RestTemplate();
        this.currencyConfig = currencyConfig;
    }

    public Symbol getRandomCurrency() {
        return this.currencySymbols.getRandomSymbol();
    }


    private void getCurrencySymbols() {

        String endpoint = "http://api.exchangeratesapi.io/v1/symbols?access_key=" + currencyConfig.getCurrencyApiKey();

        this.currencySymbols = this.restTemplate.getForObject(endpoint, CurrencySymbols.class);

    }

    @Cacheable("currencies")
    public CurrencyList getCurrentCurrencyRates() {


        String endpoint = "http://api.exchangeratesapi.io/v1/latest?access_key=" + currencyConfig.getCurrencyApiKey();

        return this.restTemplate.getForObject(endpoint, CurrencyList.class);

    }


    @EventListener(ApplicationReadyEvent.class)
    public void populateCurrencySymbolsList() {
        this.getCurrencySymbols();
    }


}
