package com.anddev.kalkulatorbmi.tools;

/**
 * Created by Marcin on 2018-03-20.
 */

public class FloatUtils {

    public static float zaokraglijFloata(Float liczba, int ileLiczbPoPrzecinku) {

        try {//s
            liczba.intValue();
            Double mnoznik = Math.pow(10d, ileLiczbPoPrzecinku);
            Float l = liczba * mnoznik.floatValue();

            return l.intValue() / mnoznik.floatValue();

        } catch (Exception e) {
            return liczba;
        }

    }
}
