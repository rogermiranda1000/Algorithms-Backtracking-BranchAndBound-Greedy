package Algorithms;

import Dades.Atletes;
import Dades.Club;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class CursesRelleus implements Runnable {
    private ArrayList<Atletes> atletes;
    private int[] binario;
    private ArrayList<ArrayList<Atletes>> teams;



    public CursesRelleus(ArrayList<Atletes> atletes){
        this.atletes = atletes;
    }
/**
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
 **/
    boolean tipusCorrectes(ArrayList<Atletes> a){
        int[] tipus = {0, 0, 0};
        for(int j=0; j < a.size(); j++) {
            tipus[0] = 0;
            tipus[1] = 0;
            tipus[2] = 0;
            for (int i = 0; i < 3; i++) {
                if (a.get(i).getType().equalsIgnoreCase("Trail Runner")) tipus[0]++;
                if (a.get(i).getType().equalsIgnoreCase("Long distance Runner,")) tipus[1]++;
                if (a.get(i).getType().equalsIgnoreCase("Sprinter")) tipus[2]++;
            }
            if (tipus[0] != 1 || tipus[1] != 1 || tipus[2] != 1) return false;
        }
        return true;
    }

    public void run() {
        /*
        ArrayList<Atletes> Sprinter = new ArrayList<>();
        ArrayList<Atletes> LD = new ArrayList<>();
        ArrayList<Atletes> TR = new ArrayList<>();

        for (int i = 0; i < this.clubs.length; i++) {
            for (int j = 0; j < this.clubs[i].getAtletes().length; j++) {
                if (clubs[i].getAtletes()[j].getType().equalsIgnoreCase("Trail Runner"))
                    TR.add(clubs[i].getAtletes()[j]);
                if (clubs[i].getAtletes()[j].getType().equalsIgnoreCase("Long distance Runner,"))
                    LD.add(clubs[i].getAtletes()[j]);
                if (clubs[i].getAtletes()[j].getType().equalsIgnoreCase("Sprinter"))
                    Sprinter.add(clubs[i].getAtletes()[j]);
            }
        }
         */






        this.teams = new ArrayList<>();
        init(this.atletes);
        generateAllBinary(this.atletes.size(), 0);
        //calculateBin(0);



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
/*
    public ArrayList<Atletes> backtracking(){

    }
*/
    public void init(ArrayList<Atletes> a) {
        this.binario = new int[a.size()];
    }

    public boolean esFactible(){
        int count=0;

        for(int i=0; i < this.binario.length; i++){
            if(this.binario[i] == 1) count++;
        }
        if(count == 3) return true;
        return false;
    }

    public ArrayList<Atletes> getBinaryAtletes(){
        ArrayList<Atletes> team = new ArrayList<>();

        for(int i=0; i < binario.length; i++){
            if(binario[i] == 1){
                team.add(atletes.get(i));
            }
        }
        return team;
    }

    public void generateAllBinary(int n, int i) {
        if (i == n) {
            if(esFactible()){
                if(tipusCorrectes(getBinaryAtletes())){
                    teams.add(getBinaryAtletes());
                    System.out.println(getBinaryAtletes().toString());
                }
            }
            return;
        }


        binario[i] = 0;
        generateAllBinary(n, i + 1);


        binario[i] = 1;
        generateAllBinary(n, i + 1);
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
