package org.vikhani;

import org.vikhani.solutions.Conundrum;
import org.vikhani.solutions.GearRatios;
import org.vikhani.solutions.Scratchcards;
import org.vikhani.solutions.SeedToLocation;
import org.vikhani.solutions.Trebuchet;
import org.vikhani.solutions.WaitForIt;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Trebuchet trebuchetChallenge = new Trebuchet();
        System.out.println("Day 1 Problem 1: " + trebuchetChallenge.solveFirstProblem());
        System.out.println("Day 1 Problem 2: " + trebuchetChallenge.solveSecondProblem());

        var conundrum = new Conundrum();
        System.out.println("Day 2 Problem 1: " + conundrum.solveFirstProblem());
        System.out.println("Day 2 Problem 2: " + conundrum.solveSecondProblem());

        var gearRatios = new GearRatios();
        System.out.println("Day 3 Problem 1: " + gearRatios.solveFirstProblem());

        var scratchcards = new Scratchcards();
        System.out.println("Day 4 Problem 1: " + scratchcards.solveFirstProblem());

        var seeds = new SeedToLocation();
        System.out.println("Day 5 Problem 1: " + seeds.solveFirstProblem());
        var wait = new WaitForIt();
        System.out.println("Day 6 Problem 1: " + wait.solveFirstProblem());
        System.out.println("Day 6 Problem 2: " + wait.solveSecondProblem());
    }
}