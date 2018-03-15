package com.anddev.kalkulatorbmi;

import com.anddev.kalkulatorbmi.model.PobraneDane;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Marcin on 2018-03-15.
 */

public class Kalkulator {

    @Getter
    @Setter
    private PobraneDane pobraneDane;

    public Kalkulator(PobraneDane pobraneDane) {
        this.pobraneDane = pobraneDane;
    }

    public Float obliczBmi() {

        try {

            float wzrostWMetrach = zamienCmNaMetry(pobraneDane.getWzrost());
            float kwadratWzrostu = getKwadratWzrostu(wzrostWMetrach);

            if (kwadratWzrostu != 0) {
                return pobraneDane.getWaga() / kwadratWzrostu;

            } else {
                return 0f;
            }

        } catch (Exception e) {

            return 0f;
        }


    }

    private float zamienCmNaMetry(float cm) {

        return cm / 100;
    }

    private float getKwadratWzrostu(float wzrost) {
        return wzrost * wzrost;

    }

}
