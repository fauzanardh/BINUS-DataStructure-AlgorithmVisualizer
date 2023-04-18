package binus.datastructure.algorithmvisualizer.algorithms;

import java.util.ArrayList;
import java.util.stream.IntStream;
import binus.datastructure.algorithmvisualizer.SortingContainer;

public abstract class Algorithm {
    public static SortingContainer step(SortingContainer lastStep) {
        throw new UnsupportedOperationException("This algorithm is not implemented yet.");
    }
    public static Boolean isFinished(ArrayList<Integer> list) {
        return IntStream.range(0, list.size() - 1).allMatch(i -> list.get(i) <= list.get(i + 1));
    }
}
