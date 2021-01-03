import java.util.Arrays;

public class Club {
    private String name;
    private String nation;
    private int date;
    private Atletes[] at;

    /* OVERRIDED FUNCTIONS */
    @Override
    public String toString() {
        return "Club{" +
                "name='" + name + '\'' +
                ", nation='" + nation + '\'' +
                ", date=" + date +
                ", at[" + Arrays.toString(at) +
                '}';
    }
}