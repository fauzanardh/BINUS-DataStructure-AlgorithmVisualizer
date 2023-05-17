package binus.datastructure.algorithmvisualizer;

import java.util.ArrayList;

import javafx.util.Pair;

public class SortingContainer {
    private ArrayList<Integer> currentState;
    private ArrayList<Integer> nextState;
    private Pair<Integer, Integer> comparedElements;
    private Integer currentIndex;
    private String algorithm;
    private Integer totalComparison;
    private Integer totalSwap;
    private Boolean isSwapped;
    private Boolean isFinished;
    private Boolean started;

    public SortingContainer(
            ArrayList<Integer> currentState,
            ArrayList<Integer> nextState,
            Pair<Integer, Integer> comparedElements,
            Integer currentIndex,
            String algorithm,
            Integer totalComparison,
            Integer totalSwap,
            Boolean isSwapped,
            Boolean isFinished,
            Boolean started) {
        this.currentState = currentState;
        this.nextState = nextState;
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

    public ArrayList<Integer> getNextState() {
        return this.nextState;
    }

    public Pair<Integer, Integer> getComparedElements() {
        return this.comparedElements;
    }

    public Integer getCurrentIndex() {
        return this.currentIndex;
    }

    public void setCurrentIndex(Integer index) {
        this.currentIndex = index;
    }

    public String getAlgorithm() {
        return this.algorithm;
    }

    public Integer getTotalComparison() {
        return this.totalComparison;
    }

    public Integer getTotalSwap() {
        return this.totalSwap;
    }

    public Boolean getIsSwapped() {
        return this.isSwapped;
    }

    public Boolean getIsFinished() {
        return this.isFinished;
    }

    public String toString() {
        return String.format(
                "lastState: %s, currentState: %s, comparedElements: %s, currentIndex: %s, algorithm: %s, totalComparison: %s, totalSwap: %s, isSwapped: %s, isFinished: %s, started: %s",
                this.currentState.toString(),
                this.nextState.toString(),
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
