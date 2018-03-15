package com.anddev.kalkulatorbmi;

import com.anddev.kalkulatorbmi.model.PobraneDane;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Marcin on 2018-03-15.
 */
public class KalkulatorTest {
    @Test
    public void obliczBmi() throws Exception {

        Kalkulator kalkulator = new Kalkulator(null);
        assertEquals(0f, kalkulator.obliczBmi(), 0.01f);

        kalkulator = new Kalkulator(new PobraneDane(0, 0, 0));
        assertEquals(0f, kalkulator.obliczBmi(), 0.01f);
        assertNotEquals(1, kalkulator.obliczBmi());

        kalkulator = new Kalkulator(new PobraneDane(20, 0, 80));
        assertEquals(0f, kalkulator.obliczBmi(), 0.01f);
        assertNotEquals(1, kalkulator.obliczBmi());

        kalkulator = new Kalkulator(new PobraneDane(20, 150, 0));
        assertEquals(0f, kalkulator.obliczBmi(), 0.01f);
        assertNotEquals(1, kalkulator.obliczBmi());

        kalkulator = new Kalkulator(new PobraneDane(20, 150, 80));
        assertEquals(35.56f, kalkulator.obliczBmi(), 0.01f);
        assertNotEquals(34f, kalkulator.obliczBmi(), 0.01f);

        kalkulator = new Kalkulator(new PobraneDane(20, 180, 100));
        assertEquals(30.86f, kalkulator.obliczBmi(), 0.01f);
        assertNotEquals(30f, kalkulator.obliczBmi(), 0.01f);

    }

}