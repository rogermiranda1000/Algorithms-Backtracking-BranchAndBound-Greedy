package Algorithms;

import Dades.Atletes;
import Dades.Club;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class CursesRelleus implements Runnable {
    private boolean[] binario;
    private ArrayList<ArrayList<Atletes>> bestTeams;

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

    public ArrayList<ArrayList<Atletes>> getBinaryTeams(ArrayList<ArrayList<Atletes>> teams, int index){
        ArrayList<ArrayList<Atletes>> teamsCombination = new ArrayList<>();

        for(int i=0; i < index; i++){
            if(this.binario[i]) teamsCombination.add(teams.get(i));
        }
        return teamsCombination;
    }

    private static float getDiferenciaMitjana(ArrayList<ArrayList<Atletes>> teams){
        float mitjanaPetitEquip = Float.MAX_VALUE, mitjanaGranEquip = Float.MIN_VALUE, aux;

        for (ArrayList<Atletes> team : teams) {
            aux = Atletes.getVelocitatMitjanaEquip(team);
            if (mitjanaPetitEquip > aux) mitjanaPetitEquip = aux;
            if (mitjanaGranEquip < aux) mitjanaGranEquip = aux;
        }

        return mitjanaGranEquip-mitjanaPetitEquip;
    }

    private static boolean samePerson(ArrayList<ArrayList<Atletes>> teams) {
        ArrayList<Atletes> used = new ArrayList<>();
        for (ArrayList<Atletes> team : teams) {
            for (Atletes at : team) {
                if (!used.contains(at)) used.add(at);
                else return true;
            }
        }
        return false;
    }

    private void calculateBestCombination(final ArrayList<ArrayList<Atletes>> teams, int i){
        int ones = getOnes(this.binario, i);
        if (ones > this.numEquips) return;
        else {
            ArrayList<ArrayList<Atletes>> t = this.getBinaryTeams(teams, i);
            if (CursesRelleus.samePerson(t)) return; // si una persona s'utilitza dos cops, no té sentit seguir

            if (ones == this.numEquips) {
                if (this.bestTeams.size() == 0 || CursesRelleus.getDiferenciaMitjana(t) < CursesRelleus.getDiferenciaMitjana(this.bestTeams)) this.bestTeams = t;
                return;
            }
        }

        //si hi ha menys de numEquips segueix
        if (i == teams.size()) return;
        this.binario[i] = false;
        calculateBestCombination(teams, i + 1);

        this.binario[i] = true;
        calculateBestCombination(teams, i + 1);
    }

    private boolean tipusCorrectes(ArrayList<Atletes> a) {
        int[] tipus = new int[3];

        for (Atletes value : a) {
            if (value.getType().equalsIgnoreCase("Trail Runner")) tipus[0]++;
            if (value.getType().equalsIgnoreCase("Long distance Runner,")) tipus[1]++;
            if (value.getType().equalsIgnoreCase("Sprinter")) tipus[2]++;
        }
        return tipus[0] <= 1 && tipus[1] <= 1 && tipus[2] <= 1;
    }

    public void run() {
        ArrayList<ArrayList<Atletes>> teams = new ArrayList<>();
        this.binario = new boolean[this.atletes.size()];
        this.generateAllBinary(teams, this.atletes.size(), 0);
        this.binario = new boolean[teams.size()];
        this.bestTeams = new ArrayList<>();
        this.calculateBestCombination(teams, 0);
    }

    public ArrayList<ArrayList<Atletes>> getBestTeams() {
        return this.bestTeams;
    }


    private int getOnes(boolean []array, int n) {
        int count=0;
        for(int i=0; i < n; i++){
            if(array[i]) count++;
        }
        return count;
    }

    public ArrayList<Atletes> getBinaryAtletes(int n){
        ArrayList<Atletes> team = new ArrayList<>();

        for(int i=0; i < n; i++){
            if(this.binario[i])  team.add(this.atletes.get(i));
        }
        return team;
    }

    public void generateAllBinary(final ArrayList<ArrayList<Atletes>> teams, int n, int i) {
        int ones = getOnes(this.binario, i);
        // si hi han més de 3 uns està malament (poda)
        if (ones > 3 || !tipusCorrectes(getBinaryAtletes(i))) return;
        // si hi ha 3 uns, i tipusCorrectes dona true, hi ha 1 corredor de cada tipus
        else if (ones == 3) {
            teams.add(getBinaryAtletes(i));
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
