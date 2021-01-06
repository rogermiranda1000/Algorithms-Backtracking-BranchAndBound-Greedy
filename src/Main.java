import Algorithms.Curses;
import Dades.Club;
import Dades.Race;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String []args) {
        try {
            Club []clubs = FileController.loadFile("files/clubs/datasetS.json", Club[].class);
            Race []races = FileController.loadFile("files/races/datasetS.json", Race[].class);
            Curses c = new Curses(10, 5000);
            c.run();
            System.out.println("[*] " + c.getBest());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
