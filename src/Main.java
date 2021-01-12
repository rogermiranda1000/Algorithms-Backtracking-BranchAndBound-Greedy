import Algorithms.Curses;
import Algorithms.Horaris;
import Algorithms.CursesRelleus;
import Dades.Club;
import Dades.Race;

import java.io.FileNotFoundException;
import java.util.Arrays;

public class Main {

    public static void main(String []args) {
        try {
            /*Race []races = FileController.loadFile("files/races/datasetXS.json", Race[].class);
            Horaris h = new Horaris(races);
            h.run();
            races = h.getSorted();
            System.out.println("Es poden organitzar " + races.length + " carreres.");
            System.out.println(Arrays.toString(races));*/

            Club []clubs = FileController.loadFile("files/clubs/datasetS.json", Club[].class);
            CursesRelleus cR = new CursesRelleus(Club.getAllAtletes(clubs));
            cR.run();
            System.out.println(cR.getBestTeams().toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        /*Curses c = new Curses(10, 5000);
        c.run();
        System.out.println("[*] " + c.getBest());*/




    }

}
