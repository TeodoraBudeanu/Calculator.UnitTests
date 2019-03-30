package main.model;

import java.util.*;

public class StatisticsRepo {

    List<StatisticsRepoEntry> repo = new ArrayList<>();

    public void addNewStatisticsEntry(String methodName, long startedAt, long endedAt) {
        repo.add(new StatisticsRepoEntry(methodName, startedAt, endedAt));
    }

    public long calculateAvgDuration() {
        long sum = 0;
        for (StatisticsRepoEntry e : repo) {
            sum += e.duration;
        }
        return sum / repo.size();
    }

    public long assertNoOfMethods() {
        Set<String> set = new HashSet<>();
        for (StatisticsRepoEntry e : repo) {
            set.add(e.methodName);
        }
        return set.size();
    }

    public long mostMethodsDuration() {
        Map<Long, Integer> map = new HashMap<>();
        for (StatisticsRepoEntry e : repo) {
            if (map.values().contains(e.duration)) {
                map.put(e.duration, map.get(e.methodName) + 1);
            } else
                map.put(e.duration, 1);
        }
        int max = 0;
        for (Map.Entry<Long, Integer> e : map.entrySet()) {
            if (e.getValue() > max) {
                max = e.getValue();
            }
        }
        for (Map.Entry<Long, Integer> e : map.entrySet()){
            if (e.getValue() == max){
                return e.getKey();
            }
        }
        return -1;
    }

    public int totalNo() {
        return repo.size();
    }

    public int size() {
        return repo.size();
    }

    public void printAll() {
        for (StatisticsRepoEntry e : repo) {
            System.out.println(e);
        }
    }

    public void printAverageDuration() {
        System.out.println("\n" + "Average duration: " + calculateAvgDuration() + "ms");
    }

    public void print() {
        printAll();
        printAverageDuration();
        printNoOfMethods();
        printTotalNo();
        printMostMethodsDuration();
    }

    public void printNoOfMethods() {
        System.out.println("\n" + "Number of methods entered: " + assertNoOfMethods());
    }


    public void printTotalNo() {
        System.out.println("Total number of entries: " + totalNo());
    }

    public void printMostMethodsDuration(){
        System.out.println("The duration encountered in most of the methods is: " + mostMethodsDuration());
    }
}
