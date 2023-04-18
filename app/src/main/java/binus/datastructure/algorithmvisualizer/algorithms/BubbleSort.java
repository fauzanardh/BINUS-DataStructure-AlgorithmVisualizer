package binus.datastructure.algorithmvisualizer.algorithms;

import java.util.ArrayList;
import org.javatuples.Pair;
import binus.datastructure.algorithmvisualizer.SortingContainer;

public class BubbleSort extends Algorithm {
    public static SortingContainer step(SortingContainer lastStep) {
        // Get last state
        ArrayList<Integer> lastState = lastStep.getCurrentState();
        Integer lastIndex = lastStep.getCurrentIndex();

        // Step through the algorithm once
        if (lastIndex < (lastState.size() - 1)) {
            // Prepare for next state
            ArrayList<Integer> nextState;
            Integer nextIndex;
            Pair<Integer, Integer> comparedElements;
            Boolean isFinished = false;
            Boolean isSwapped = false;

            nextState = new ArrayList<Integer>(lastState);
            // Swap adjacent elements if they are in decreasing order
            if (lastState.get(lastIndex) > lastState.get(lastIndex + 1)) {
                Integer temp = nextState.get(lastIndex);
                nextState.set(lastIndex, nextState.get(lastIndex + 1));
                nextState.set(lastIndex + 1, temp);

                isSwapped = true;
            }

            // Set values for next step
            nextIndex = lastIndex + 1;
            comparedElements = new Pair<Integer, Integer>(lastIndex, nextIndex);
            isFinished = isFinished(nextState);

            return new SortingContainer(lastState, nextState, comparedElements, nextIndex, isSwapped, isFinished, true);
        } else if (!lastStep.getIsFinished()) {
            // Resets the index and start over if the list is not sorted yet
            lastStep.setCurrentIndex(0);
            return step(lastStep);
        } else {
            return lastStep;
        }
    }
}
