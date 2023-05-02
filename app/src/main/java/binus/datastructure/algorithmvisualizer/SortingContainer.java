package binus.datastructure.algorithmvisualizer;

import java.util.ArrayList;

import org.javatuples.Pair;

public class SortingContainer {
    private ArrayList<Integer> lastState;
    private ArrayList<Integer> currentState;
    private Pair<Integer, Integer> comparedElements;
    private Integer currentIndex;
    private String algorithm;
    private Integer totalComparison;
    private Integer totalSwap;
    private Boolean isSwapped;
    private Boolean isFinished;
    private Boolean started;

    public SortingContainer(
            ArrayList<Integer> lastState,
            ArrayList<Integer> currentState,
            Pair<Integer, Integer> comparedElements,
            Integer currentIndex,
            String algorithm,
            Integer totalComparison,
            Integer totalSwap,
            Boolean isSwapped,
            Boolean isFinished,
            Boolean started) {
        this.lastState = lastState;
        this.currentState = currentState;
        this.comparedElements = comparedElements;
        this.currentIndex = currentIndex;
        this.algorithm = algorithm;
        this.totalComparison = totalComparison;
        this.totalSwap = totalSwap;
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

    public Integer getTotalComparison() {
        return this.totalComparison;
    }

    public Integer getTotalSwap() {
        return this.totalSwap;
    }

    public Boolean getIsFinished() {
        return this.isFinished;
    }

    public String toString() {
        return String.format(
                "lastState: %s, currentState: %s, comparedElements: %s, currentIndex: %s, algorithm: %s, totalComparison: %s, totalSwap: %s, isSwapped: %s, isFinished: %s, started: %s",
                this.lastState.toString(),
                this.currentState.toString(),
                this.comparedElements.toString(),
                this.currentIndex.toString(),
                this.algorithm,
                this.totalComparison.toString(),
                this.totalSwap.toString(),
                this.isSwapped.toString(),
                this.isFinished.toString(),
                this.started.toString());
    }
}
