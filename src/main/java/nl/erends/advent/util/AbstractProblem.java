package nl.erends.advent.util;

import org.apache.log4j.Logger;

public abstract class AbstractProblem<T, U> {
    
    protected T input;
    
    protected U answer2;
    
    private static final Logger LOG = Logger.getLogger(AbstractProblem.class);
    
    public void setInput(T input) {
        this.input = input;
    }
    
    public void setAndSolve(T input) {
        setInput(input);
        solve();
    }
    
    public void solve() {
        Timer.start1();
        LOG.info(solve1());
        Timer.end1();
        Timer.start2();
        LOG.info(solve2());
        Timer.end2();
        Timer.printStats();
    }
    
    public abstract U solve1();

    public U solve2() {
        if (answer2 == null) {
            solve1();
        }
        if (answer2 == null) {
            LOG.warn("Part 2 not implemented!");
        }
        return answer2;
    }
}
