package binus.datastructure.algorithmvisualizer.models;

import java.util.HashMap;
import java.util.ArrayList;

import javafx.util.Pair;

import binus.datastructure.algorithmvisualizer.DoublyLinkedList;
import binus.datastructure.algorithmvisualizer.SortingContainer;
import binus.datastructure.algorithmvisualizer.algorithms.Algorithm;
import binus.datastructure.algorithmvisualizer.algorithms.BubbleSort;
import binus.datastructure.algorithmvisualizer.algorithms.SelectionSort;

public class SortingModel {
    private DoublyLinkedList data;
    private final HashMap<String, Algorithm> algorithms = new HashMap<>() {
        {
            put(BubbleSort.ALGORITHM_KEY, new BubbleSort());
            put(SelectionSort.ALGORITHM_KEY, new SelectionSort());
        }
    };
    public final HashMap<String, String> algorithmNameToKey = new HashMap<>() {
        {
            put(BubbleSort.ALGORITHM_NAME, BubbleSort.ALGORITHM_KEY);
            put(SelectionSort.ALGORITHM_NAME, SelectionSort.ALGORITHM_KEY);
        }
    };

    public SortingModel() {
        this.data = new DoublyLinkedList();
    }

    public DoublyLinkedList getData() {
        return this.data;
    }

    public ArrayList<String> getSortingAlgorithms() {
        return new ArrayList<String>(this.algorithmNameToKey.keySet());
    }

    public Boolean isFinished() throws NullPointerException {
        // Check if the data has tail
        if (this.data.getTail() == null) {
            throw new NullPointerException("The data is empty, initialize the sorting elements first!");
        }

        // Get the current step
        SortingContainer currentStep = this.data.getTail().getItem();

        // Check if the current step is finished
        return currentStep.getIsFinished();
    }

    // Perform a single step of the sorting algorithm
    public void step(String algorithm) throws NullPointerException {
        // Check if the data has tail
        if (this.data.getTail() == null) {
            throw new NullPointerException("The data is empty, initialize the sorting elements first!");
        }

        // Get the current step
        SortingContainer currentStep = this.data.getTail().getItem();

        // Check if the current step is finished
        if (currentStep.getIsFinished()) {
            return;
        }

        // Perform a single step of the sorting algorithm
        SortingContainer newStep = this.algorithms.get(algorithm).step(currentStep);

        // Add the new step to the data
        this.data.addNode(newStep);
    }

    public void reset() {
        this.data = new DoublyLinkedList();
    }

    public void initializeModel(ArrayList<Integer> currentState) {
        // Reset the data
        this.reset();

        // Create a new step
        Pair<Integer, Integer> comparedElements = new Pair<Integer, Integer>(-1, -1);
        SortingContainer currentStep = new SortingContainer(currentState, currentState, comparedElements, 0,
                "no_algorithm", 0, 0, false, false, false);

        // Add the new step to the data
        this.data.addNode(currentStep);
    }

    public void runAlgorithmFully(String algorithmKey) {
        try {
            while (!isFinished()) {
                step(algorithmKey);
            }
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }

        // Add the last step to the data
        // because the last step is not added when the algorithm is finished
        SortingContainer currenSortingContainer = this.data.getTail().getItem();
        currenSortingContainer.setIsFinished(false);
        SortingContainer lastStep = new SortingContainer(
                currenSortingContainer.getNextState(),
                currenSortingContainer.getNextState(),
                new Pair<>(-1, -1),
                currenSortingContainer.getCurrentIndex() + 1,
                currenSortingContainer.getAlgorithm(),
                currenSortingContainer.getTotalComparison(),
                currenSortingContainer.getTotalSwap(),
                currenSortingContainer.getIsFinished(),
                currenSortingContainer.getIsSwapped(),
                true);
        this.data.addNode(lastStep);
    }
}
