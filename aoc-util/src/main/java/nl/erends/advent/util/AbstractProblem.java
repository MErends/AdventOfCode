package nl.erends.advent.util;

import nl.erends.advent.imagery.Animator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AbstractProblem<T, U> {
    
    protected T input;
    
    protected U answer2;

    protected boolean part2;
    
    protected static final Logger LOG = LogManager.getLogger(AbstractProblem.class);
    
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
        if (answer2 == null) {
            Timer.start2();
        }
        LOG.info(solve2());
        Timer.end2();
        Timer.printStats();
        Animator.makeGif();
    }
    
    protected abstract U solve1();

    public U solve2() {
        if (answer2 == null) {
            part2 = true;
            U candidate = solve1();
            if (answer2 == null) {
                answer2 = candidate;
            }
        }
        return answer2;
    }
}
