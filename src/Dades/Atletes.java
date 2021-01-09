package Dades;

public class Atletes {
    private String name;
    private String lastname;
    private int age;
    private String nation;
    private float distance;
    private float time;
    private String type;

    /* OVERRIDED FUNCTIONS */
    @Override
    public String toString() {
        return "Dades.Atletes{" +
                "name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", age=" + age +
                ", nation='" + nation + '\'' +
                ", distance=" + distance +
                ", time=" + time +
                ", type='" + type + '\'' +
                '}';
    }

    public String getName(){ return this.name; }
    public String getType(){ return this.type; }
    public float getVelocitatMitjana(){ return (this.distance/this.time); }
    public float getTime(){ return this.time; }
    public float getDistance(){ return this.distance; }
}