package nl.erends.advent.year2019;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FFTTest {

    @Test
    public void phasesTest() {
        String signal = "12345678";
        signal = FFT.apply(signal, 1);
        assertEquals("48226158", signal);

        signal = FFT.apply(signal, 1);
        assertEquals("34040438", signal);

        signal = FFT.apply(signal, 1);
        assertEquals("03415518", signal);

        signal = FFT.apply(signal, 1);
        assertEquals("01029498", signal);
    }
}