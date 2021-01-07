package Dades;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Race implements Comparable<Race> {
    private String name;
    private Date start;
    private Date end;

    public Date getStart() {
        return this.start;
    }

    public Date getEnd() {
        return this.end;
    }

    @Override
    public int compareTo(Race o) {
        return this.end.compareTo(o.end);
    }

    @Override
    public String toString() {
        DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        return name + ": " + formatter.format(this.start) + " - " + formatter.format(this.end);
    }
}
