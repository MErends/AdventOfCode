package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * --- Day 22: Wizard Simulator 20XX ---
 * <p>On each of your turns, you select one of your spells to cast. If you
 * cannot afford to cast any spell, you lose. Spells cost mana. Your spells are
 * Magic Missile, Drain, Shield, Poison, and Recharge. Effects apply at the
 * start of both the player's turns and the boss' turns. What is the least
 * amount of mana you can spend and still win the fight?
 * <p><a href="https://adventofcode.com/2021/day/22">2015 Day 22</a>
 */
public class Day22 extends AbstractProblem<List<String>, Integer> {

    private int bossDamage;
    private int minMana = Integer.MAX_VALUE;
    private final List<Gamestate> gamestates = new ArrayList<>();
    private boolean hardMode = false;

    void main() {
        new Day22().setAndSolve(Util.readInput(2015, 22));
    }

    @Override
    protected Integer solve1() {
        int bossHP = Integer.parseInt(input.getFirst().split(" ")[2]);
        bossDamage = Integer.parseInt(input.get(1).split(" ")[1]);
        gamestates.add(new Gamestate(500, 50, bossHP));
        while (!gamestates.isEmpty()) {
            List<Gamestate> playerTurn = new ArrayList<>(gamestates);
            gamestates.clear();
            playerTurn.forEach(Gamestate::doPlayerAttack);
            gamestates.forEach(Gamestate::doBossAttack);
        }
        return minMana;
    }

    @Override
    public Integer solve2() {
        hardMode = true;
        minMana = Integer.MAX_VALUE;
        return solve1();
    }

    private class Gamestate {

        int mana;
        int hp;
        int bossHP;
        int shieldTurns;
        int poisonTurns;
        int rechargeTurns;
        int manaSpent;

        void checkEnd() {
            if (hp <= 0 || manaSpent >= minMana) {
                mana = Integer.MIN_VALUE;
            } else if (bossHP <= 0) {
                minMana = manaSpent;
            }
        }

        void doTimers() {
            shieldTurns = Math.max(0, shieldTurns - 1);
            if (poisonTurns > 0) {
                bossHP -= 3;
                poisonTurns--;
                checkEnd();
            }
            if (rechargeTurns > 0) {
                mana += 101;
                rechargeTurns--;
            }
        }
        
        void doBossAttack() {
            doTimers();
            int damage = bossDamage;
            if (shieldTurns > 0) {
                damage -= 7;
            }
            hp -= Math.max(1, damage);
            checkEnd();
        }
        
        void doPlayerAttack() {
            if (hardMode) {
                hp -= 1;
                checkEnd();
            }
            doTimers();
            if (mana >= 53) {
                Gamestate gamestate = new Gamestate(this);
                gamestate.mana -= 53;
                gamestate.bossHP -= 4;
                gamestate.manaSpent += 53;
                gamestates.add(gamestate);
            }
            if (mana >= 73) {
                Gamestate gamestate = new Gamestate(this);
                gamestate.mana -= 73;
                gamestate.bossHP -= 2;
                gamestate.hp += 2;
                gamestate.manaSpent += 73;
                gamestates.add(gamestate);
            }
            if (mana >= 113 && shieldTurns == 0) {
                Gamestate gamestate = new Gamestate(this);
                gamestate.mana -= 113;
                gamestate.shieldTurns = 6;
                gamestate.manaSpent += 113;
                gamestates.add(gamestate);
            }
            if (mana >= 173 && poisonTurns == 0) {
                Gamestate gamestate = new Gamestate(this);
                gamestate.mana -= 173;
                gamestate.poisonTurns = 6;
                gamestate.manaSpent += 173;
                gamestates.add(gamestate);
            }
            if (mana >= 229 && rechargeTurns == 0) {
                Gamestate gamestate = new Gamestate(this);
                gamestate.mana -= 229;
                gamestate.rechargeTurns = 5;
                gamestate.manaSpent += 229;
                gamestates.add(gamestate);
            }
        }

        Gamestate(int mana, int hp, int bossHP) {
            this.mana = mana;
            this.hp = hp;
            this.bossHP = bossHP;
        }

        Gamestate(Gamestate other) {
            this.mana = other.mana;
            this.hp = other.hp;
            this.bossHP = other.bossHP;
            this.shieldTurns = other.shieldTurns;
            this.poisonTurns = other.poisonTurns;
            this.rechargeTurns = other.rechargeTurns;
            this.manaSpent = other.manaSpent;
        }
    }
}
