package binus.datastructure.algorithmvisualizer.algorithms;

import java.util.ArrayList;

import javafx.util.Pair;

import binus.datastructure.algorithmvisualizer.SortingContainer;

public class SelectionSort extends Algorithm {
    public static final String ALGORITHM_NAME = "Selection Sort";
    public static final String ALGORITHM_KEY = "selection_sort";

    @Override
    public SortingContainer step(SortingContainer lastStep) {
        // Get last state
        ArrayList<Integer> currentState = lastStep.getNextState();
        Integer lastIndex = lastStep.getCurrentIndex();

        // Step through the algorithm once
        if (lastIndex < (currentState.size() - 1)) {
            // Prepare for next state
            ArrayList<Integer> nextState;
            Integer nextIndex;
            Pair<Integer, Integer> comparedElements;
            Boolean isSwapped = false;
            Boolean isFinished = false;
            Integer totalComparison = lastStep.getTotalComparison();
            Integer totalSwap = lastStep.getTotalSwap();

            Integer minIndex = lastIndex;
            // Search for the minimum value
            for (int i = lastIndex + 1; i < currentState.size(); i++) {
                // Increment the total comparison
                totalComparison++;

                // Compare the current value with the minimum value
                if (currentState.get(i) < currentState.get(minIndex)) {
                    minIndex = i;
                }
            }

            nextState = new ArrayList<Integer>(currentState);
            if (minIndex != lastIndex) {
                // Swap the minimum value with the current value;
                Integer temp = nextState.get(minIndex);
                nextState.set(minIndex, nextState.get(lastIndex));
                nextState.set(lastIndex, temp);

                // Increment the total comparison
                totalComparison++;

                // Increment the total swap
                totalSwap++;

                // Set the swapped flag
                isSwapped = true;
            }

            // Set values for next step
            nextIndex = lastIndex + 1;
            comparedElements = new Pair<Integer, Integer>(lastIndex, minIndex);
            isFinished = isFinished(nextState);

            return new SortingContainer(currentState, nextState, comparedElements, nextIndex, "selection_sort",
                    totalComparison, totalSwap, isSwapped, isFinished, true);
        }

        return lastStep;
    }
}
