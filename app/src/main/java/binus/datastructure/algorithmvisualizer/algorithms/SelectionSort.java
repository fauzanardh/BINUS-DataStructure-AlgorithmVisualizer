package binus.datastructure.algorithmvisualizer.algorithms;

import java.util.ArrayList;
import org.javatuples.Pair;
import binus.datastructure.algorithmvisualizer.SortingContainer;

public class SelectionSort extends Algorithm {
    public static SortingContainer step(SortingContainer lastStep) {
        // Get last state
        ArrayList<Integer> lastState = lastStep.getCurrentState();
        Integer lastIndex = lastStep.getCurrentIndex();

        // Step through the algorithm once
        if (lastIndex < lastState.size()) {
            // Prepare for next state
            ArrayList<Integer> nextState;
            Integer nextIndex;
            Pair<Integer, Integer> comparedElements;
            Boolean isFinished;

            Integer minIndex = lastIndex;
            // Search for the minimum value
            for (int i = lastIndex + 1; i < lastState.size(); i++) {
                if (lastState.get(i) < lastState.get(minIndex)) {
                    minIndex = i;
                }
            }
            
            nextState = new ArrayList<Integer>(lastState);
            if (minIndex != lastIndex) {
                // Swap the minimum value with the current value;
                Integer temp = nextState.get(minIndex);
                nextState.set(minIndex, nextState.get(lastIndex));
                nextState.set(lastIndex, temp);
            }

            // Set values for next step
            nextIndex = lastIndex + 1;
            comparedElements = new Pair<Integer, Integer>(lastIndex, minIndex);
            isFinished = isFinished(nextState);

            return new SortingContainer(nextState, comparedElements, nextIndex, isFinished, true);
        }

        return lastStep;
    }
}
