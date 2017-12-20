package nl.erends.advent.year2017;

//  p=<1609,-863,-779>, v=<-15,54,-69>, a=<-10,0,14>
//  p=<-391,1353,-387>, v=<-94,-42,0>, a=<14,-5,3>

import nl.erends.advent.util.FileIO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Day20 {
    
    public static void main(String[] args) {
        List<String> input = FileIO.getFileAsList("2017day20.txt");
        List<Particle> particles = new ArrayList<>();
        for (int index = 0; index < input.size(); index++) {
            particles.add(new Particle(index, input.get(index)));
        }
        boolean done = false;
        int closestID = 0;
        while (!done) {
            done = true;
            int smallestDistance = Integer.MAX_VALUE;
            for (Particle particle : particles) {
                if (particle.inScope) {
                    particle.update();
                }
                if (particle.inScope && particle.getDistance() < smallestDistance) {
                    done = false;
                    smallestDistance = particle.getDistance();
                    closestID = particle.getId();
                }
            }
        }
        System.out.println(closestID);

        particles = new ArrayList<>();
        for (int index = 0; index < input.size(); index++) {
            particles.add(new Particle(index, input.get(index)));
        }
        done = false;
        while (!done) {
            done = true;
            for (Particle particle : particles) {
                if (particle.inScope) {
                    done = false;
                    particle.update();
                }
            }
            for (Particle particle1 : particles) {
                for (Particle particle2 : particles) {
                    if (particle1.getId() != particle2.getId() &&
                            particle1.getX() == particle2.getX() &&
                            particle1.getY() == particle2.getY() &&
                            particle1.getZ() == particle2.getZ()) {
                        particle1.setCollided(true);
                        particle2.setCollided(true);
                    }
                }
            }
            particles.removeIf(Particle::isCollided);
        }
        System.out.println(particles.size());
    }
    
    
    
    
    private static class Particle {
        int id;
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
        
        int getId() {
            return id;
        }
        
        boolean isInScope() {
            return inScope;
        }
        
        int getDistance() {
            return distance;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getZ() {
            return z;
        }

        public boolean isCollided() {
            return collided;
        }
        
        public void setCollided(boolean collided) {
            this.collided = collided;
        }
    }
}