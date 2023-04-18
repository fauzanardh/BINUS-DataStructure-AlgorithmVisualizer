package binus.datastructure.algorithmvisualizer;

import java.util.ArrayList;
import org.javatuples.Pair;

public class SortingContainer {
    private ArrayList<Integer> currentState;
    private Pair<Integer, Integer> comparedElements;
    private Integer currentIndex;
    private Boolean isFinished;
    private Boolean started;

    public SortingContainer(
            ArrayList<Integer> currentState,
            Pair<Integer, Integer> comparedElements,
            Integer currentIndex,
            Boolean isSwapped,
            Boolean started) {
        this.currentState = currentState;
        this.comparedElements = comparedElements;
        this.currentIndex = currentIndex;
        this.started = started;
        this.isFinished = isSwapped;
    }

    public ArrayList<Integer> getCurrentState() {
        return this.currentState;
    }

    public Integer getCurrentIndex() {
        return this.currentIndex;
    }

    public String toString() {
        return String.format(
                "currentState: %s, comparedElements: %s, currentIndex: %s, started: %s, isFinished: %s",
                this.currentState.toString(),
                this.comparedElements.toString(),
                this.currentIndex.toString(),
                this.started.toString(),
                this.isFinished.toString());
    }
}
