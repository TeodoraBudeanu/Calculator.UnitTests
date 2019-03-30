package main.model;

public class StatisticsRepoEntry {

    private String methodName;
    private long startedAt;
    private long endedAt;
    private long duration;

    public StatisticsRepoEntry(String methodName, long startedAt, long endedAt) {
        if (methodName == null) {
            throw new NullPointerException();
        }
        this.methodName = methodName;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        duration = endedAt - startedAt;
    }

    public String getMethodName() {
        return methodName;
    }

    public long getStartedAt() {
        return startedAt;
    }

    public long getEndedAt() {
        return endedAt;
    }

    public long getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return "Method: " + methodName + " - duration: " + duration + "ms";
    }


}
