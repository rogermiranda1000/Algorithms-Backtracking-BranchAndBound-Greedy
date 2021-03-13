import Algorithms.Curses;
import Algorithms.Horaris;
import Algorithms.Equips;
import Dades.Club;
import Dades.Race;

import java.io.FileNotFoundException;
import java.util.Arrays;

public class Main {

    public static void main(String []args) {
        try {
            //Main.runAlgorithm(()->Main.runGreedy("files/races/datasetXXXL.json"), 20);

            //Main.runAlgorithm(()->Main.runBacktracking("files/clubs/datasetXS2.json"), 20);
            //Main.runAlgorithm(()->Main.runBacktracking("files/clubs/datasetS.json"), 1);

            Main.runAlgorithm(()->Main.runBAndB(10, 600000), 3);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private static void runAlgorithm(Algorisme algorisme, int times) throws FileNotFoundException {
        long total = 0;
        for (int x = 0; x < times; x++) total += algorisme.run();
        total /= times;
        System.out.println("Found in " + total + "ms");
    }

    private static long runBAndB(int num, int timeout) throws FileNotFoundException {
        long time, endTime;
        time = System.currentTimeMillis();
        Curses c = new Curses(num, timeout);
        c.run();
        endTime = System.currentTimeMillis();
        //System.out.println("[*] " + c.getBest());
        return endTime-time;
    }

    private static long runBacktracking(String path) throws FileNotFoundException {
        long time, endTime;
        Club []clubs = FileController.loadFile(path, Club[].class);
        time = System.currentTimeMillis();
        Equips equips = new Equips(Club.getAllAtletes(clubs));
        equips.run();
        endTime = System.currentTimeMillis();
        //System.out.println("[*] " + equips.getDiferencia() + " - " + equips.getBestTeams().toString());
        return endTime-time;
    }

    private static long runGreedy(String path) throws FileNotFoundException {
        long time, endTime;
        Race []races = FileController.loadFile(path, Race[].class);
        time = System.currentTimeMillis();
        Horaris h = new Horaris(races);
        h.run();
        endTime = System.currentTimeMillis();
        races = h.getSorted();
        System.out.println("Es poden organitzar " + races.length + " carreres.");
        //System.out.println(Arrays.toString(races));
        return endTime-time;
    }
}
