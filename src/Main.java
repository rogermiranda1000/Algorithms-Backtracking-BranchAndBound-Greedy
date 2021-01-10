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
            Club []clubs = FileController.loadFile("files/clubs/datasetS.json", Club[].class);
            Race []races = FileController.loadFile("files/races/datasetS.json", Race[].class);

            Horaris h = new Horaris(races);
            h.run();
            races = h.getSorted();
            System.out.println("Es poden organitzar " + races.length + " carreres.");
            System.out.println(Arrays.toString(races));



            CursesRelleus cR = new CursesRelleus(clubs);
            cR.run();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //Curses c = new Curses(10, 5000);
        //c.run();
        //System.out.println("[*] " + c.getBest());




    }

}
