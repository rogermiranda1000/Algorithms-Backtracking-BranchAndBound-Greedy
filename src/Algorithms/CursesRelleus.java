package Algorithms;

import Dades.Atletes;
import Dades.Club;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class CursesRelleus implements Runnable {
    private boolean[] binario;

    private final ArrayList<Atletes> atletes;
    private final ArrayList<Atletes> sprinter;
    private final ArrayList<Atletes> longDistance;
    private final ArrayList<Atletes> trailRunner;
    private final int numEquips;

    public CursesRelleus(ArrayList<Atletes> atletes){
        this.atletes = atletes;

        this.sprinter = new ArrayList<>();
        this.longDistance = new ArrayList<>();
        this.trailRunner = new ArrayList<>();

        for (Atletes at : this.atletes) {
            if (at.getType().equalsIgnoreCase("Trail Runner")) this.trailRunner.add(at);
            else if (at.getType().equalsIgnoreCase("Long distance Runner,")) this.longDistance.add(at);
            else if (at.getType().equalsIgnoreCase("Sprinter")) this.sprinter.add(at);
        }

        this.numEquips = Math.min(this.sprinter.size(), Math.min(this.trailRunner.size(), this.longDistance.size()));
    }
/*
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
 */
    private boolean tipusCorrectes(ArrayList<Atletes> a) {
        int[] tipus = new int[3];

        for (Atletes value : a) {
            if (value.getType().equalsIgnoreCase("Trail Runner")) tipus[0]++;
            if (value.getType().equalsIgnoreCase("Long distance Runner,")) tipus[1]++;
            if (value.getType().equalsIgnoreCase("Sprinter")) tipus[2]++;
        }
        return tipus[0] == 1 && tipus[1] == 1 && tipus[2] == 1;
    }

    public void run() {
        ArrayList<ArrayList<Atletes>> teams = new ArrayList<>();
        this.binario = new boolean[this.atletes.size()];
        generateAllBinary(teams, this.atletes.size(), 0);
        System.out.println();


        //Atletes[][] configuracions = new Atletes[countNumEquips()][3];



        //ArrayList<Atletes> aux = new ArrayList<>();
        //ArrayList<Atletes> team = new ArrayList<>();
        //ArrayList<ArrayList<Integer>> combination = new ArrayList<>();


        //genBin(combination);

        // TODO: guardar a variable
        //ArrayList<ArrayList<Atletes>> teams = new ArrayList<>();
        //backTraking((ArrayList<Atletes>) atletes.clone(),  aux, 0);
        //combinations(atletes, aux, 0);
    }
    /*
    public ArrayList<Atletes> backTraking(ArrayList<Atletes> a, ArrayList<ArrayList<Atletes>> combination, ArrayList<Atletes> team, int start){

        if (team.size() == 3) {
            return team;
        }

        team.add(a.get(start));
        for(int i=0; i < a.size(); i++){

            combination.add(backTraking(a, combination, team, (start+1)));
            team.clear();
        }
        System.out.println(team.toString());
        return team;



    }
    */

    private int getOnes(int n) {
        int count=0;
        for(int i=0; i < n; i++){
            if(this.binario[i]) count++;
        }
        return count;
    }

    public ArrayList<Atletes> getBinaryAtletes(int n){
        ArrayList<Atletes> team = new ArrayList<>();

        for(int i=0; i < n; i++){
            if(this.binario[i])  team.add(atletes.get(i));
        }
        return team;
    }

    public void generateAllBinary(final ArrayList<ArrayList<Atletes>> teams, int n, int i) {
        int ones = getOnes(i);
        // si hi han més de 3 uns està malament (poda)
        if (ones > 3) return;
        else if (ones == 3) {
            if (tipusCorrectes(getBinaryAtletes(i))) teams.add(getBinaryAtletes(i));
            return;
        }

        // si hi ha menys de 3 uns, segueix
        if (i == n) return;
        this.binario[i] = false;
        this.generateAllBinary(teams, n, i + 1);

        this.binario[i] = true;
        this.generateAllBinary(teams, n, i + 1);
    }


/*
    public void calculateBin(int index) {
        binario[index] = 0;
        while (binario[index] < 2) {

            if (index < binario.length-1) {
                calculateBin(index + 1);
            } else {

            }
            if(esFactible()){
                if(tipusCorrectes(getBinaryAtletes(binario))) {
                    teams.add(getBinaryAtletes(binario));
                }
            }

            binario[index]++;
        }
    }
    */

    /*
    public void backTraking(ArrayList<Atletes> a, ArrayList<ArrayList<Atletes>> teams, ArrayList<Atletes> combinacioEquip){
        if(){
            ArrayList<Atletes> aux = new ArrayList<>();

            teams.add()
        }
        for(int i=)

    }
*/
    /*
    public void backTraking(ArrayList<Atletes> a, ArrayList<Atletes> combination, ArrayList<ArrayList<Atletes>> team, int start){
        if (combination.size() == 3) {
            team.add(combination);
            return;
        }

        for(int i=start; i < a.size(); i++){
            ArrayList<Atletes> aux = new ArrayList<>(combination);
            aux.add(a.get(start));
            backTraking(a, combination, team, (i+1));
            aux.remove(aux.size());
        }
        //System.out.println(team.toString());
        return;

    }*/


/*
    public ArrayList<Atletes> combinations(ArrayList<Atletes> a, ArrayList<Atletes> combination, int k) {
        if (combination.size() == 3) {
            return combination;
        }

        ArrayList<ArrayList<Atletes>> combs = new ArrayList<>();

        for (int i = k; i < a.size(); i++) {
            ArrayList<Atletes> aux = new ArrayList<>(combination);
            combination.add(a.get(i));
            combs.add(combinations(a, combination, i+1));
            System.out.println(combs.get(0).toString());
            //combs.add(combinations((ArrayList<Atletes>) a.subList(i+1, a.size()), combination));
        }
        return combination;

    }*/



}
