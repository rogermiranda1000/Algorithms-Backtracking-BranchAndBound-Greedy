package Algorithms;

import Dades.Race;

import java.util.*;

/**
 * Greedy
 */
public class Horaris implements Runnable {
    private final Race[] list;
    private Race[] sorted;

    public Horaris(Race []races) {
        ArrayList<Race> list = new ArrayList<>(Arrays.asList(races));
        this.list = list.stream().sorted().toArray(Race[]::new); // s'ordena per data final
    }

    public Race []getSorted() {
        return this.sorted;
    }

    @Override
    public void run() {
        ArrayList<Race> result = new ArrayList<>();
        Date last = null;

        // agafem sempre i quan no es solapin
        for (Race r : this.list) {
            if (last == null || !r.getStart().before(last)) {
                result.add(r);
                last = r.getEnd();
            }
        }

        this.sorted = result.toArray(Race[]::new);
    }
}
