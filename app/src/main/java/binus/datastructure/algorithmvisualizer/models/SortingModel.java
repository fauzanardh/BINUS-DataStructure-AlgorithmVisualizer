package binus.datastructure.algorithmvisualizer.models;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import org.javatuples.Pair;

import binus.datastructure.algorithmvisualizer.DoublyLinkedList;
import binus.datastructure.algorithmvisualizer.SortingContainer;
import binus.datastructure.algorithmvisualizer.algorithms.Algorithm;
import binus.datastructure.algorithmvisualizer.algorithms.BubbleSort;
import binus.datastructure.algorithmvisualizer.algorithms.SelectionSort;

public class SortingModel {
    private DoublyLinkedList data;
    private HashMap<String, Algorithm> algorithms = new HashMap<>() {
        {
            put("bubble_sort", new BubbleSort());
            put("selection_sort", new SelectionSort());
        }
    };

    public SortingModel() {
        this.data = new DoublyLinkedList();
    }

    public DoublyLinkedList getData() {
        return this.data;
    }

    public Boolean isFinished() {
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
    public void step(String algorithm) {
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

    public void initializeModelRandom(Integer size) {
        // Reset the data
        this.reset();

        // Generate a random array
        ArrayList<Integer> currentState = new ArrayList<Integer>();
        for (int i = 0; i < size; i++) {
            currentState.add(ThreadLocalRandom.current().nextInt(0, size + 1));
        }

        // Create a new step
        Pair<Integer, Integer> comparedElements = new Pair<Integer, Integer>(-1, -1);
        SortingContainer currentStep = new SortingContainer(currentState, currentState, comparedElements, 0,
                "no_algorithm", 0, 0, false, false, false);

        // Add the new step to the data
        this.data.addNode(currentStep);
    }
}
