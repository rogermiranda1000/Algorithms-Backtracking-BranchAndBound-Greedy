package Algorithms;

import Dades.Atletes;
import Dades.Club;

import java.util.ArrayList;

public class CursesRelleus {
    private Club[] clubs;


    public CursesRelleus(Club[] clubs){
        this.clubs = clubs;
    }

    public int countNumEquips(){
        int result=0;
        for(int i=0; i < clubs.length; i++){
            result += clubs[i].getAtletes().length;
        }
        System.out.println("Hi han " + result + " participants!");
        return result/3;
    }

    float mitjana(Atletes[] a){
        float result = 0;
        for(int i=0; i < 3; i++){
            result += a[0].getVelocitatMitjana();
        }
        return (result/3);
    }

    boolean tipusCorrectes(Atletes[][] a){
        int[] tipus = {0, 0, 0};
        for(int j=0; j < a.length; j++) {
            tipus[0] = 0;
            tipus[1] = 0;
            tipus[2] = 0;
            for (int i = 0; i < 3; i++) {
                if (a[j][i].getType() == "Trail Runner") tipus[0]++;
                if (a[j][i].getType() == "Long distance Runner,") tipus[1]++;
                if (a[j][i].getType() == "Sprinter") tipus[2]++;
            }
            if (tipus[0] != 1 || tipus[1] != 1 || tipus[2] != 1) return false;
        }
        return true;
    }

    public void buscarMillorSolucio(Club[] c){
        ArrayList<Atletes> atletes = new ArrayList<>();
        Atletes[][] configuracions = new Atletes[countNumEquips()][3];

        for(int i=0; i < c.length; i++){
            for(int j=0; j < c[i].getAtletes().length; j++){
                atletes.add(c[i].getAtletes()[j]);
            }
        }
        ArrayList<Atletes> aux= new ArrayList<>();

        System.out.println(combinations(atletes, aux, 0).toString());
    }

    public void backTraking(ArrayList<Atletes> a, Atletes[][] conf){
        System.out.println("Test");



    }



    public ArrayList<Atletes> combinations(ArrayList<Atletes> a, ArrayList<Atletes> combination, int k) {
        if (combination.size() == 3) {
            return combination;
        }

        ArrayList<ArrayList<Atletes>> combs = new ArrayList<>();
        for (int i = k; i < a.size(); i++) {
            ArrayList<Atletes> aux = new ArrayList<>(combination);
            combination.add(a.get(i));
            combs.add(combinations(a, combination, i+1));
            //combs.add(combinations((ArrayList<Atletes>) a.subList(i+1, a.size()), combination));

        }
        return combination;


    }



}
