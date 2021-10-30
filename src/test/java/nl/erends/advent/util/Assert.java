package nl.erends.advent.util;

import org.junit.jupiter.api.Assertions;

public class Assert {
    
    public static void assertEquals(int primitive, Integer wrapper) {
        Assertions.assertEquals(primitive, wrapper.intValue());
    }

    public static void assertEquals(long primitive, Long wrapper) {
        Assertions.assertEquals(primitive, wrapper.longValue());
    }
}
