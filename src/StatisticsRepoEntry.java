public class StatisticsRepoEntry {

    String MethodName;
    long startedAt;
    long EndedAt;
    long duration;

    public StatisticsRepoEntry(String methodName, long startedAt, long endedAt) {
        if (methodName == null){
            throw new NullPointerException();
        }
        MethodName = methodName;
        this.startedAt = startedAt;
        EndedAt = endedAt;
        duration = endedAt - startedAt;
    }

    @Override
    public String toString() {
        return "Method: " + MethodName + " - duration: " + duration + "ms";
    }


}
