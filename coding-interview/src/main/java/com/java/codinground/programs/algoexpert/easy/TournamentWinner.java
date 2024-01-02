package com.java.codinground.programs.algoexpert.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Imagine there is an algorithms tournament taking place in which multiple teams compete against each other.
 * In each competition there will be two teams that compete and there will be one winner and one loser.
 * Each team will compete against all other teams exactly once.
 * Every time a team wins a competition, it gets 3 points; when it loses, it gets 0 points.
 * It's guaranteed that the tournament always has at least two teams and there will be only one tournament winner.
 *
 * We are given two inputs, the competitions array and the results array,
 * and we need to write a function that returns the winner of the tournament, or more specifically,
 * the name of the team that has the most number of points.
 * The competitions array is an array of pairs, representing all of the competitions in the tournament.
 * Inside of these pairs, we have two strings.
 * The first string is the name of the home team, the second string is the name of the away team.
 * The results array represents the winner of each of these competitions.
 * Inside the results array, a 1 means that the home team won and a 0 means the away team won.
 * The results array is the same length as the competitions array,
 * and the indices in the results array correspond with the indices in the competitions array.
 */
public class TournamentWinner {

    private static int HOME_TEAM_WON = 1;

    public static void main(String[] args) {

        ArrayList<ArrayList<String>> competitions = new ArrayList<ArrayList<String>>();
        ArrayList<String> competition1 = new ArrayList<String>(Arrays.asList("HTML", "C#"));
        ArrayList<String> competition2 = new ArrayList<String>(Arrays.asList("C#", "Python"));
        ArrayList<String> competition3 = new ArrayList<String>(Arrays.asList("Python", "HTML"));
        competitions.add(competition1);
        competitions.add(competition2);
        competitions.add(competition3);
        ArrayList<Integer> results = new ArrayList<Integer>(Arrays.asList(0, 0, 1));
        String expected = "Python";
        System.out.println("The winner is : " + tournamentWinner(competitions, results));
    }

    private static String tournamentWinner(ArrayList<ArrayList<String>> competitions, ArrayList<Integer> results) {
        Map<String, Integer> points = new HashMap<>();
        String currentBestTeam = "DEFAULT_TEAM";
        points.put(currentBestTeam, 0);

        for (int i=0; i<results.size(); i++){
            ArrayList<String> competition = competitions.get(i);
            Integer result = results.get(i);
            String homeTeam = competition.get(0);
            String awayTeam = competition.get(1);

            String winningTeam = (result == HOME_TEAM_WON) ? homeTeam : awayTeam;
            points.put(winningTeam, points.getOrDefault(winningTeam, 0)+3);
            if (points.get(winningTeam) > points.get(currentBestTeam)){
                currentBestTeam = winningTeam;
            }
        }

       // String winner = points.entrySet().stream().max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).get().getKey();
        return  currentBestTeam;
    }
}
