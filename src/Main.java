import java.io.FileNotFoundException;

public class Main {

    public static void main(String []args) {
        try {
            Club []clubs = FileController.loadFile("files/clubs/datasetS.json", Club[].class);
            Race []races = FileController.loadFile("files/races/datasetS.json", Race[].class);
            System.out.println();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
