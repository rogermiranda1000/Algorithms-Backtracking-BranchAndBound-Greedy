package Algorithms;

import Dades.Atletes;
import Dades.Club;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class CursesRelleus implements Runnable {
    private boolean[] binario;
    private boolean[] binarioTeams;

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

    public int getNumTeams(){
        int mesPetit;
        mesPetit = sprinter.size();
        if(mesPetit > longDistance.size()) mesPetit=longDistance.size();
        if(mesPetit > trailRunner.size()) mesPetit = trailRunner.size();
        return mesPetit;
    }

    float mitjana(Atletes[] a){
        float result = 0;
        for(int i=0; i < 3; i++){
            result += a[0].getVelocitatMitjana();
        }
        return (result/3);
    }

    private int getOnesTeams(int n) {
        int count=0;
        for(int i=0; i < n; i++){
            if(this.binarioTeams[i]) count++;
        }
        return count;
    }

    public ArrayList<ArrayList<Atletes>> getBinaryTeams(ArrayList<ArrayList<Atletes>> teams){
        ArrayList<ArrayList<Atletes>> teamsCombination = new ArrayList<>();

        for(int i=0; i < teams.size(); i++){
            if(this.binario[i])  teamsCombination.add(teams.get(i));
        }
        return teamsCombination;
    }

    private float getDiferenciaMitjana(ArrayList<ArrayList<Atletes>> teams){
        //TODO: Quin valor poso per inicialitzar la variable hi ha alguna manera de obtindre el mes petit sense recorrer tot l'array o poso -999999/999999 i ale?
        float mitjanaPetitEquip, mitjanaGranEquip, aux;


        for (int i=0; i < teams.size(); i++){
            aux = 0;
            for(Atletes at : teams.get(i)){
                aux += at.getVelocitatMitjana();
            }
            aux/=3;
            if(mitjanaPetitEquip > aux) mitjanaPetitEquip = aux;
            if(mitjanaGranEquip < aux) mitjanaGranEquip = aux;
        }
        return mitjanaGranEquip-mitjanaPetitEquip;
    }

    private void calculateBestCombination(ArrayList<ArrayList<Atletes>> teams, int n, int i){
        int ones = getOnesTeams(i);
        if (ones > getNumTeams()) return;
        else if (ones == getNumTeams()) {
            if (i == n) {
                //agregariem la combinacio per posteriorment fer el calcul de la mitjana
                getDiferenciaMitjana(getBinaryTeams(teams));
                System.out.println("Hola");
                return;
            }
        }
        //si hi ha menys de numEquips segueix
        if (i == n) return;
        binarioTeams[i] = false;
        calculateBestCombination(teams, n, i + 1);


        binarioTeams[i] = true;
        calculateBestCombination(teams, n, i + 1);

    }
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
        this.binarioTeams = new boolean[getNumTeams()];
        calculateBestCombination(teams, teams.size(), 0);

    }


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
}
