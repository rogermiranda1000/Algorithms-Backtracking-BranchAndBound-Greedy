import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileController {
    private static final Gson gson = new GsonBuilder().setDateFormat("HH:mm").create();

    public static <T> T loadFile(File f, Class<T> c) throws FileNotFoundException, JsonSyntaxException {
        return gson.fromJson(FileController.readFile(f), c);
    }
    public static <T> T loadFile(String path, Class<T> c) throws FileNotFoundException, JsonSyntaxException {
        return FileController.loadFile(new File(path), c);
    }

    private static String readFile(File f) throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        Scanner s = new Scanner(f);

        while (s.hasNext()) sb.append(s.nextLine());

        return sb.toString();
    }
}
