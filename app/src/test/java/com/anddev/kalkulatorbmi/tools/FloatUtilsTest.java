package com.anddev.kalkulatorbmi.tools;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Marcin on 2018-03-20.
 */
public class FloatUtilsTest {
    @Test
    public void zaokraglijFloata() throws Exception {

        Float liczba = 4.568f;
        assertEquals(4.5f, FloatUtils.zaokraglijFloata(liczba, 1), 0f);

        liczba = 0f;
        assertEquals(0f, FloatUtils.zaokraglijFloata(liczba, 1), 0f);

        liczba = 0f;
        assertEquals(0f, FloatUtils.zaokraglijFloata(liczba, 3), 0f);

        liczba = 0f;
        assertEquals(0f, FloatUtils.zaokraglijFloata(liczba, 0), 0f);

        liczba = 0.12f;
        assertEquals(0.1f, FloatUtils.zaokraglijFloata(liczba, 1), 0f);

        liczba = 0.12f;
        assertEquals(0.12f, FloatUtils.zaokraglijFloata(liczba, 5), 0f);

        liczba = 0.12345678f;
        assertEquals(0.12345f, FloatUtils.zaokraglijFloata(liczba, 5), 0f);

        liczba = 4.9f;
        assertEquals(4.9f, FloatUtils.zaokraglijFloata(liczba, 1), 0f);

        liczba = 0.12345678f;
        assertEquals(0.1234000f, FloatUtils.zaokraglijFloata(liczba, 4), 0f);

        liczba = 4.8f;
        assertNotEquals(4.9f, FloatUtils.zaokraglijFloata(liczba, 1), 0f);

        liczba = 0.12345678f;
        assertNotEquals(0.12345f, FloatUtils.zaokraglijFloata(liczba, 4), 0f);

        liczba = 0.12345678f;
        assertEquals(0.1234000f, FloatUtils.zaokraglijFloata(liczba, 4), 0f);
    }

}