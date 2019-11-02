package nl.erends.advent.util;

public interface BiProblem<T,U,V> {
    U solve1(T t);
    
    V solve2(T t);
}
