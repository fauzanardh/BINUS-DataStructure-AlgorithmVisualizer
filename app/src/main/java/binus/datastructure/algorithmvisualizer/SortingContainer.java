package binus.datastructure.algorithmvisualizer;

import java.util.ArrayList;
import org.javatuples.Pair;

public class SortingContainer {
    private ArrayList<Integer> lastState;
    private ArrayList<Integer> currentState;
    private Pair<Integer, Integer> comparedElements;
    private Integer currentIndex;
    private Boolean isSwapped;
    private Boolean isFinished;
    private Boolean started;

    public SortingContainer(
            ArrayList<Integer> lastState,
            ArrayList<Integer> currentState,
            Pair<Integer, Integer> comparedElements,
            Integer currentIndex,
            Boolean isSwapped,
            Boolean isFinished,
            Boolean started) {
        this.lastState = lastState;
        this.currentState = currentState;
        this.comparedElements = comparedElements;
        this.currentIndex = currentIndex;
        this.isSwapped = isSwapped;
        this.isFinished = isFinished;
        this.started = started;
    }

    public ArrayList<Integer> getCurrentState() {
        return this.currentState;
    }

    public Integer getCurrentIndex() {
        return this.currentIndex;
    }

    public void setCurrentIndex(Integer index) {
        this.currentIndex = index;
    }

    public Boolean getIsFinished() {
        return this.isFinished;
    }

    public String toString() {
        return String.format(
                "lastState: %s, currentState: %s, comparedElements: %s, currentIndex: %s, isSwapped: %s, isFinished: %s, started: %s",
                this.lastState.toString(),
                this.currentState.toString(),
                this.comparedElements.toString(),
                this.currentIndex.toString(),
                this.isSwapped.toString(),
                this.isFinished.toString(),
                this.started.toString());
    }
}
