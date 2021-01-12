package Dades;

import java.util.ArrayList;

public class Atletes {
    private String name;
    private String lastname;
    private int age;
    private String nation;
    private float distance;
    private float time;
    private String type;

    public String getName(){ return this.name; }
    public String getType(){ return this.type; }
    public float getVelocitatMitjana(){ return (this.distance/this.time); }
    public float getTime(){ return this.time; }
    public float getDistance(){ return this.distance; }
    public static float getVelocitatMitjanaEquip(ArrayList<Atletes> atletes) {
        float r = 0;
        for (Atletes at : atletes) r += at.getVelocitatMitjana();
        return r / atletes.size();
    }

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

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof Atletes)) return false;
        Atletes a = (Atletes) o;
        return  a.name.equalsIgnoreCase(this.name)
                && a.lastname.equalsIgnoreCase(this.lastname)
                && a.age == this.age
                && a.nation.equalsIgnoreCase(this.nation)
                && a.distance == this.distance
                && a.time == this.time
                && a.type.equalsIgnoreCase(this.type);
    }
}