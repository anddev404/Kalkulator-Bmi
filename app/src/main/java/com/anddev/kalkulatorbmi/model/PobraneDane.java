package com.anddev.kalkulatorbmi.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Marcin on 2018-03-15.
 */

public class PobraneDane {

    @Getter
    @Setter
    private int wiek;

    @Getter
    @Setter
    private float wzrost;

    @Getter
    @Setter
    private float waga;

    public PobraneDane(int wiek, float wzrost, float waga) {
        this.wiek = wiek;
        this.wzrost = wzrost;
        this.waga = waga;
    }
}
