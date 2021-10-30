package nl.erends.advent.util;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class Assert {
    
    public static void assertEquals(int primitive, Integer wrapper) {
        assertThat(wrapper, is(primitive));
    }

    public static void assertEquals(long primitive, Long wrapper) {
        assertThat(wrapper, is(primitive));
    }
}
