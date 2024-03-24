package com.bard.springcurrencygame.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "success"
})
public class CurrencySymbols {
    @JsonProperty("success")
    private String success;

    //    in case API Call was not invoked, and currencySymbols would return null
    private List<Symbol> currencySymbols = new ArrayList<>();

    @JsonProperty("success")
    public String getSuccess() {
        return success;
    }

    @JsonProperty("success")
    public void setSuccess(String success) {
        this.success = success;
    }

    @JsonProperty("symbols")
    public void setCurrencySymbols(Map<String, String> currencySymbols) {
        this.currencySymbols = currencySymbols
                .entrySet()
                .stream()
                .map((entry) -> new Symbol(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    @JsonProperty("symbols")
    public List<Symbol> getCurrencyRates() {
        return this.currencySymbols;
    }


    public Symbol getRandomSymbol() {
        Random random = new Random();
        int index = random.nextInt(this.currencySymbols.size());

        return !this.currencySymbols.isEmpty() ? this.currencySymbols.get(index) : new Symbol("PLN", "1");
    }
}
