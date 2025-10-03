package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.List;

public class Day20 extends AbstractProblem<List<String>, Integer> {
    
    void main() {
        new Day20().setAndSolve(Util.readInput(2017, 20));
    }
    
    @Override
    public Integer solve1() {
        List<Particle> particles = readParticles();
        boolean done = false;
        int closestID = 0;
        while (!done) {
            done = true;
            int smallestDistance = Integer.MAX_VALUE;
            for (Particle particle : particles) {
                if (particle.inScope) {
                    particle.update();
                }
                if (particle.inScope && particle.distance < smallestDistance) {
                    done = false;
                    smallestDistance = particle.distance;
                    closestID = particle.id;
                }
            }
        }
        return closestID;
    }
    
    @Override
    public Integer solve2() {
        List<Particle> particles = readParticles();
        boolean done = false;
        while (!done || particles.size() == 1) {
            done = updateParticles(particles);
            for (Particle particle1 : particles) {
                for (Particle particle2 : particles) {
                    if (particle1.id != particle2.id &&
                            particle1.x == particle2.x &&
                            particle1.y == particle2.y &&
                            particle1.z == particle2.z) {
                        particle1.collided = true;
                        particle2.collided = true;
                    }
                }
            }
            particles.removeIf(p -> p.collided);
        }
        return particles.size();
    }

    private boolean updateParticles(List<Particle> particles) {
        boolean done = true;
        for (Particle particle : particles) {
            if (particle.inScope) {
                done = false;
                particle.update();
            }
        }
        return done;
    }

    private List<Particle> readParticles() {
        List<Particle> particles = new ArrayList<>();
        for (int index = 0; index < input.size(); index++) {
            particles.add(new Particle(index, input.get(index)));
        }
        return particles;
    }
    
    private static class Particle {
        final int id;
        int distance;
        int x;
        int y;
        int z;
        int vx;
        int vy;
        int vz;
        final int ax;
        final int ay;
        final int az;
        boolean inScope;
        boolean collided;
        
        Particle(int id, String input) {
            String[] values = input.split("[<>, ]");
            this.id = id;
            x = Integer.parseInt(values[1]);
            y = Integer.parseInt(values[2]);
            z = Integer.parseInt(values[3]);
            vx = Integer.parseInt(values[7]);
            vy = Integer.parseInt(values[8]);
            vz = Integer.parseInt(values[9]);
            ax = Integer.parseInt(values[13]);
            ay = Integer.parseInt(values[14]);
            az = Integer.parseInt(values[15]);
            distance = Math.abs(x) + Math.abs(y) + Math.abs(z);
            inScope = true;
            collided = false;
        }
        
        void update() {
            if (inScope) {
                vx += ax;
                vy += ay;
                vz += az;
                x += vx;
                y += vy;
                z += vz;
                distance = Math.abs(x) + Math.abs(y) + Math.abs(z);
                if (distance < 0) inScope = false;
            }
        }
    }
}
