package main.model;

public class StatisticsRepoEntry {

    String methodName;
    long startedAt;
    long endedAt;
    long duration;

    public StatisticsRepoEntry(String methodName, long startedAt, long endedAt) {
        if (methodName == null){
            throw new NullPointerException();
        }
        this.methodName = methodName;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        duration = endedAt - startedAt;
    }

    @Override
    public String toString() {
        return "Method: " + methodName + " - duration: " + duration + "ms";
    }


}
