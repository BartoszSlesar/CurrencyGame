package com.bard.springcurrencygame.config;


import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@ConfigurationProperties("currency-game")
@Configuration
@Setter
@Getter
public class CurrencyConfig {
    private String currencyApiKey;

    private Logger logger = LoggerFactory.getLogger(CurrencyConfig.class);

    @CacheEvict(value = "currency", allEntries = true)
    @Scheduled(fixedRateString = "${caching.spring.currencyData}")
    public void emptyCurrencyCache() {
        logger.info("emptying currency cache");
    }
}
