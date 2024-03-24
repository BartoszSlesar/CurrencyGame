package com.bard.springcurrencygame.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class Currency {

    private String name;

    private double value;
}
