
package com.bard.springcurrencygame.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Map;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "timestamp",
        "base",
        "date"
})
@Getter
public class CurrencyList {

    @JsonProperty("timestamp")
    private String timestamp;
    @JsonProperty("base")
    private String base;
    @JsonProperty("date")
    private String date;

    private double baseToPln;

    private Map<String, Double> currencyRates;

    @JsonProperty("timestamp")
    public String getTimestamp() {
        return timestamp;
    }

    @JsonProperty("timestamp")
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @JsonProperty("base")
    public String getBase() {
        return base;
    }

    @JsonProperty("base")
    public void setBase(String base) {
        this.base = base;
    }

    @JsonProperty("date")
    public String getDate() {
        return date;
    }

    @JsonProperty("date")
    public void setDate(String date) {
        this.date = date;
    }

    @JsonProperty("rates")
    private void setCurrencyRates(Map<String, String> currencyRates) {
        this.baseToPln = Double.parseDouble(currencyRates.get("PLN"));
        double rate = 1.0 / this.baseToPln;
        this.currencyRates = currencyRates
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> {
                    double entryCurrency = Double.parseDouble(entry.getValue());
                    return Math.round((1.0 / (entryCurrency * rate)) * 100.0) / 100.0;
                })).entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 0)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));


    }

    @JsonProperty("rates")
    public Map<String, Double> getCurrencyRates() {
        return this.currencyRates;
    }


    public double getRate(String symbol) {

        return this.currencyRates.get(symbol);

    }

}
