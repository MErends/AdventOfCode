package nl.erends.advent.year2015;

import nl.erends.advent.util.FileIO;

import java.util.List;

public class Day2 {


    public static void main(String[] args) {
        int lint = 0;
        List<String> regels = FileIO.getFileAsList("C:\\Users\\marke\\IdeaProjects\\Adventofcode\\resource\\2015day2.txt");
        for (String regel : regels) {
            lint += omtrek(regel);
            lint += inhoud(regel);
        }
        System.out.print(lint);
    }

    static int oppervlakte(String regel) {
        String[] dimensies = regel.split("x");
        int breedte = Integer.parseInt(dimensies[0]);
        int hoogte = Integer.parseInt(dimensies[1]);
        int lengte = Integer.parseInt(dimensies[2]);
        int oppervlakte = 2*breedte*hoogte + 2*breedte*lengte + 2*hoogte*lengte;
        return oppervlakte;
    }

    static int kleinsteVlak(String regel) {
        String[] dimensies = regel.split("x");
        int breedte = Integer.parseInt(dimensies[0]);
        int hoogte = Integer.parseInt(dimensies[1]);
        int lengte = Integer.parseInt(dimensies[2]);
        int kleinstevak = breedte * hoogte;
        kleinstevak = Math.min(kleinstevak, hoogte * lengte);
        kleinstevak = Math.min(kleinstevak, breedte * lengte);
        return kleinstevak;
    }

    static int omtrek(String regel) {
        String[] dimensies = regel.split("x");
        int breedte = Integer.parseInt(dimensies[0]);
        int hoogte = Integer.parseInt(dimensies[1]);
        int lengte = Integer.parseInt(dimensies[2]);
        int kleinsteomtrek = 2 * breedte + 2 * hoogte;
        kleinsteomtrek = Math.min(kleinsteomtrek, 2*hoogte + 2 * lengte);
        kleinsteomtrek = Math.min(kleinsteomtrek, 2*breedte + 2 * lengte);
        return kleinsteomtrek;
    }

    static int inhoud(String regel) {
        String[] dimensies = regel.split("x");
        int breedte = Integer.parseInt(dimensies[0]);
        int hoogte = Integer.parseInt(dimensies[1]);
        int lengte = Integer.parseInt(dimensies[2]);
        return breedte*hoogte*lengte;
    }
}
