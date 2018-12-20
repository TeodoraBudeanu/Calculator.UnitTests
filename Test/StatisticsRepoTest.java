import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StatisticsRepoTest {

    StatisticsRepo repo;

    @Before
    public void setRepo() {
        repo = new StatisticsRepo();
    }

    @Test
    public void testWhenInitializedIsEmpty() {
        assertEquals(0, repo.size());
    }

    @Test
    public void testWhenEntryIsAddedSizeIncreases() {
        repo.addNewStatisticsEntry("name", 0, 1);
        assertEquals(1, repo.size());
    }

    @Test(expected = NullPointerException.class)
    public void testWhenFieldInEntryIsNullThrowsNullPointerException() {
        repo.addNewStatisticsEntry(null, 0, 1);
        assertEquals(1, repo.size());
    }

    @Test
    public void testWhenAddedMultipleEntriesCalculatesAverageDuration(){
        repo.addNewStatisticsEntry("1", 0, 6);
        repo.addNewStatisticsEntry("2", 0, 2);
        repo.addNewStatisticsEntry("3", 0, 4);
        assertEquals(4, repo.calculateAvgDuration());
    }

    @Test
    public void testWhenAddedSameMethodMultipleTimesAssertsNoOfMethods(){
        repo.addNewStatisticsEntry("1", 0, 6);
        repo.addNewStatisticsEntry("1", 0, 2);
        repo.addNewStatisticsEntry("1", 0, 4);
        assertEquals(1, repo.assertNoOfMethods());
    }

    @Test
    public void testWhenAddedDifferentMethodsAssertsNoOfMethods(){
        repo.addNewStatisticsEntry("1", 0, 6);
        repo.addNewStatisticsEntry("2", 0, 2);
        repo.addNewStatisticsEntry("3", 0, 4);
        assertEquals(3, repo.assertNoOfMethods());
    }

    @Test
    public void testWhenAddedMultipleEntriesReturnesTotalNumberOfEntries(){
        repo.addNewStatisticsEntry("1", 0, 6);
        repo.addNewStatisticsEntry("2", 0, 2);
        assertEquals(2, repo.totalNo());
    }

    @Test
    public void testWhenAddedMultipleEntriesReturnesDurationThatOccuresMost(){
        repo.addNewStatisticsEntry("1", 0, 2);
        repo.addNewStatisticsEntry("2", 0, 2);
        repo.addNewStatisticsEntry("3", 0, 3);
        assertEquals(2, repo.mostMethodsDuration());
    }

    @Test
    public void testWhenAddedMultipleEntriesAndHasEqualNumberOfDurationsReturnesTheOneWithLowerValue(){
        repo.addNewStatisticsEntry("3", 0, 1);
        repo.addNewStatisticsEntry("1", 0, 3);
        repo.addNewStatisticsEntry("3", 0, 3);
        repo.addNewStatisticsEntry("2", 0, 1);
        assertEquals(1, repo.mostMethodsDuration());
    }

}

