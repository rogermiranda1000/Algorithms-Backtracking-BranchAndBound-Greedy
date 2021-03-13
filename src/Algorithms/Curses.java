package Algorithms;

import Excepcions.InvalidCombinationException;
import edu.salleurl.RaceHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * B&B
 */
public class Curses implements Runnable {
    private class Comb implements Comparable<Comb> {
        private final int[] config;
        private int k;
        private long cost;
        private ArrayList<Integer> contains;

        public Comb(int []config, int k) {
            this.config = config.clone();
            this.k = k;

            this.cost = calculateCost();

            this.contains = new ArrayList<>();
            for (int x = 0; x < k; x++) this.contains.add(this.config[x]);
        }

        public Comb(Comb c) {
            this.config = c.config.clone();
            this.k = c.k;

            this.cost = c.cost;

            this.contains = new ArrayList<>(c.contains.size());
            this.contains.addAll(c.contains);
        }

        public Comb add(int value) throws InvalidCombinationException {
            if (this.contains.contains(value)) throw new InvalidCombinationException();

            this.config[k] = value;
            this.k++;

            this.cost = calculateCost();
            this.contains.add(value);

            return this;
        }

        private long calculateCost() {
            if (this.k < this.config.length) return RaceHelper.estimate(this.config, this.k - 1);
            return RaceHelper.partialValue(this.config, this.k - 1);
        }

        @Override
        public int compareTo(Comb o) {
            return (int)(this.cost - o.cost);
        }

        @Override
        public String toString() {
            return Arrays.toString(config) + " (" + this.cost + ")";
        }
    }


    private final int n;
    private final PriorityQueue<Comb> queue;
    private Comb best;
    private long timeout;
    private long last;

    private Comb []generate(Comb c) {
        ArrayList<Comb> gen = new ArrayList<>();

        for (int x = 1; x <= this.n; x++) {
            try {
                if (c == null) gen.add(new Comb(new int[this.n], 0).add(x));
                else gen.add(new Comb(c).add(x));
            } catch (InvalidCombinationException ex) {}
        }

        return gen.toArray(Comb[]::new);
    }

    public Curses(int trams, long timeout) {
        this.n = trams;
        this.timeout = timeout;
        this.last = System.currentTimeMillis();
        this.queue = new PriorityQueue<>();
    }
    public Curses(int trams) {
        this(trams, Long.MAX_VALUE);
    }

    public String getBest() {
        if (this.best == null) return null;
        return this.best.toString();
    }

    @Override
    public void run() {
        RaceHelper.init(this.n);

        for (Comb c : this.generate(null)) this.queue.add(c);

        Comb next = this.queue.remove();
        while (!this.queue.isEmpty() && (System.currentTimeMillis() - this.last) <= this.timeout) {
            for (Comb c : this.generate(next)) {
                if (c.k < this.n) this.queue.add(c);
                else if (best == null || c.cost < this.best.cost) {
                    this.best = c;
                    this.last = System.currentTimeMillis();
                    System.out.println(this.best.toString());
                }
            }

            next = this.queue.remove();
        }
    }
}
