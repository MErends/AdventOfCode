package nl.erends.advent.util;

public class Assert {
    
    public static void assertEquals(int primitive, Integer wrapper) {
        org.junit.Assert.assertEquals(primitive, wrapper.intValue());
    }

    public static void assertEquals(long primitive, Long wrapper) {
        org.junit.Assert.assertEquals(primitive, wrapper.longValue());
    }
}
