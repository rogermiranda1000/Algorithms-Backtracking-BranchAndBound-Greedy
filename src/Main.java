import Algorithms.Curses;
import Algorithms.Horaris;
import Algorithms.CursesRelleus;
import Dades.Club;
import Dades.Race;

import java.io.FileNotFoundException;
import java.util.Arrays;

public class Main {

    public static void main(String []args) {
        long time = 0, endTime = -1;
        try {
            Race []races = FileController.loadFile("files/races/datasetXXXL.json", Race[].class);
            /*time = System.currentTimeMillis();
            Horaris h = new Horaris(races);
            h.run();
            endTime = System.currentTimeMillis();
            races = h.getSorted();
            System.out.println("Es poden organitzar " + races.length + " carreres.");
            System.out.println(Arrays.toString(races));*/

            /*Club []clubs = FileController.loadFile("files/clubs/datasetXS2.json", Club[].class);
            time = System.currentTimeMillis();
            CursesRelleus cursesRelleus = new CursesRelleus(Club.getAllAtletes(clubs));
            cursesRelleus.run();
            endTime = System.currentTimeMillis();
            System.out.println("[*] " + cursesRelleus.getDiferencia() + " - " + cursesRelleus.getBestTeams().toString());*/

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        time = System.currentTimeMillis();
        Curses c = new Curses(10, 600000);
        c.run();
        endTime = System.currentTimeMillis();
        System.out.println("[*] " + c.getBest());

        System.out.println("Found in " + (endTime - time) + "ms");
    }

}
