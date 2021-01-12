package Dades;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Club {
    private String name;
    private String nation;
    private int date;
    private Atletes[] at;

    public static ArrayList<Atletes> getAllAtletes(Club[] clubs) {
        ArrayList<Atletes> r = new ArrayList<>();

        for (Club c : clubs) Collections.addAll(r, c.at);

        return r;
    }

    /* OVERRIDED FUNCTIONS */
    @Override
    public String toString() {
        return "Dades.Club{" +
                "name='" + name + '\'' +
                ", nation='" + nation + '\'' +
                ", date=" + date +
                ", at[" + Arrays.toString(at) +
                '}';
    }
}