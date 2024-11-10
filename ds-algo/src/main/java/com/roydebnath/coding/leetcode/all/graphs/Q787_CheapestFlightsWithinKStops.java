package com.roydebnath.coding.leetcode.all.graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 787. Cheapest Flights Within K Stops
 * Medium
 *
 * Topics
 * Companies
 * There are n cities connected by some number of flights. You are given an array flights where flights[i] = [fromi, toi, pricei] indicates that there is a flight from city fromi to city toi with cost pricei.
 *
 * You are also given three integers src, dst, and k, return the cheapest price from src to dst with at most k stops. If there is no such route, return -1.
 *
 * We have n cities and a list of flights where each flight is represented by [from_i, to_i, price_i]. This indicates a flight from city from_i to city to_i at the cost price_i. Given additional parameters src, dst, and k, the goal is to find the minimum cost to travel from the source city src to the destination city dst with at most k stops in between. If no such route exists, the function should return -1.
 * */
public class Q787_CheapestFlightsWithinKStops {

    public int findCheapestPrice(int numOfCities, int[][] flights, int source, int destination, int maxStops) {

        ArrayList<ArrayList<Flight>> adjacencyList = new ArrayList<>();
        for (int i = 0; i < numOfCities; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        for (int[] flight : flights) {
            int fromCity = flight[0];
            int toCity = flight[1];
            int price = flight[2];
            adjacencyList.get(fromCity).add(new Flight(toCity, price));
        }

        Queue<Tuple> queue = new LinkedList<>();
        queue.add(new Tuple(0, source, 0));

        int[] costs = new int[numOfCities];
        for (int i = 0; i < numOfCities; i++) {
            costs[i] = Integer.MAX_VALUE;
        }
        costs[source] = 0;

        while (!queue.isEmpty()) {
            Tuple current = queue.poll();
            int stops = current.stops;
            int city = current.city;
            int cost = current.cost;

            if (stops > maxStops) continue;

            for (Flight flight : adjacencyList.get(city)) {
                int adjacentCity = flight.toCity;
                int edgeWeight = flight.price;

                if (cost + edgeWeight < costs[adjacentCity] && stops <= maxStops) {
                    costs[adjacentCity] = cost + edgeWeight;
                    queue.add(new Tuple(stops + 1, adjacentCity, cost + edgeWeight));
                }
            }
        }

        return costs[destination] == Integer.MAX_VALUE ? -1 : costs[destination];
    }

    class Flight {
        int toCity;
        int price;

        Flight(int toCity, int price) {
            this.toCity = toCity;
            this.price = price;
        }
    }

    class Tuple {
        int stops;
        int city;
        int cost;

        Tuple(int stops, int city, int cost) {
            this.stops = stops;
            this.city = city;
            this.cost = cost;
        }
    }
}
