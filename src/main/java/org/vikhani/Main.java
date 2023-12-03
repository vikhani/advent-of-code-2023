package org.vikhani;

import org.vikhani.solutions.Conundrum;
import org.vikhani.solutions.Trebuchet;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Trebuchet trebuchetChallenge = new Trebuchet();
        System.out.println("Day 1 Problem 1: " + trebuchetChallenge.solveFirstProblem());
        System.out.println("Day 1 Problem 2: " + trebuchetChallenge.solveSecondProblem());

        var conundrum = new Conundrum();
        System.out.println("Day 2 Problem 1: " + conundrum.solveFirstProblem());
        System.out.println("Day 2 Problem 2: " + conundrum.solveSecondProblem());
    }
}