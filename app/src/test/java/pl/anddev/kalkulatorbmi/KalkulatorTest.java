package pl.anddev.kalkulatorbmi;

import pl.anddev.kalkulatorbmi.model.PobraneDane;

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

    @Test
    public void obliczWageIdealna() throws Exception {

        Kalkulator kalkulator = new Kalkulator(null);
        assertEquals(0f, kalkulator.obliczWageIdealna(), 0.01f);

        kalkulator = new Kalkulator(new PobraneDane(0, 0, 0));
        assertEquals(0f, kalkulator.obliczWageIdealna(), 0.01f);
        assertNotEquals(1, kalkulator.obliczWageIdealna());

        kalkulator = new Kalkulator(new PobraneDane(20, 0, 80));
        assertEquals(0f, kalkulator.obliczWageIdealna(), 0.01f);
        assertNotEquals(1, kalkulator.obliczWageIdealna());

        kalkulator = new Kalkulator(new PobraneDane(20, 160, 0));
        assertEquals(55.68f, kalkulator.obliczWageIdealna(), 0.01f);
        assertNotEquals(1, kalkulator.obliczWageIdealna());

        kalkulator = new Kalkulator(new PobraneDane(20, 180, 100));
        assertEquals(70.46f, kalkulator.obliczWageIdealna(), 0.01f);
        assertNotEquals(30f, kalkulator.obliczWageIdealna(), 0.01f);

    }

    @Test
    public void obliczWageMinimalna() throws Exception {

        Kalkulator kalkulator = new Kalkulator(null);
        assertEquals(0f, kalkulator.obliczWageMinimalna(), 0.01f);

        kalkulator = new Kalkulator(new PobraneDane(0, 0, 0));
        assertEquals(0f, kalkulator.obliczWageMinimalna(), 0.01f);
        assertNotEquals(1, kalkulator.obliczWageMinimalna());

        kalkulator = new Kalkulator(new PobraneDane(20, 0, 80));
        assertEquals(0f, kalkulator.obliczWageMinimalna(), 0.01f);
        assertNotEquals(1, kalkulator.obliczWageMinimalna());

        kalkulator = new Kalkulator(new PobraneDane(20, 160, 0));
        assertEquals(47.36f, kalkulator.obliczWageMinimalna(), 0.01f);
        assertNotEquals(1, kalkulator.obliczWageMinimalna());

        kalkulator = new Kalkulator(new PobraneDane(20, 180, 100));
        assertEquals(59.93f, kalkulator.obliczWageMinimalna(), 0.01f);
        assertNotEquals(30f, kalkulator.obliczWageMinimalna(), 0.01f);

    }

    @Test
    public void obliczWageMaksymalna() throws Exception {

        Kalkulator kalkulator = new Kalkulator(null);
        assertEquals(0f, kalkulator.obliczWageMaksymalna(), 0.01f);

        kalkulator = new Kalkulator(new PobraneDane(0, 0, 0));
        assertEquals(0f, kalkulator.obliczWageMaksymalna(), 0.01f);
        assertNotEquals(1, kalkulator.obliczWageMaksymalna());

        kalkulator = new Kalkulator(new PobraneDane(20, 0, 80));
        assertEquals(0f, kalkulator.obliczWageMaksymalna(), 0.01f);
        assertNotEquals(1, kalkulator.obliczWageMaksymalna());

        kalkulator = new Kalkulator(new PobraneDane(20, 160, 0));
        assertEquals(64f, kalkulator.obliczWageMaksymalna(), 0.01f);
        assertNotEquals(1, kalkulator.obliczWageMaksymalna());

        kalkulator = new Kalkulator(new PobraneDane(20, 180, 100));
        assertEquals(80.99f, kalkulator.obliczWageMaksymalna(), 0.01f);
        assertNotEquals(30f, kalkulator.obliczWageMaksymalna(), 0.01f);

    }

}