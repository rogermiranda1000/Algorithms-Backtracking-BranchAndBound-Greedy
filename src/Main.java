import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    private static String readFile(File f) throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        Scanner s = new Scanner(f);

        while (s.hasNext()) sb.append(s.nextLine());

        return sb.toString();
    }

    public static void main(String []args) {
        try {
            Gson gson = new GsonBuilder()
                    .setDateFormat("HH:mm")
                    .create();

            Club []clubs = gson.fromJson(Main.readFile(new File("files/clubs/datasetS.json")), Club[].class);

            Race []races = gson.fromJson(Main.readFile(new File("files/races/datasetS.json")), Race[].class);
            System.out.println();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
