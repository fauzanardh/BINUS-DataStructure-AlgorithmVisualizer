/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package binus.datastructure.algorithmvisualizer;

import java.util.ArrayList;
import org.javatuples.Pair;
import binus.datastructure.algorithmvisualizer.algorithms.SelectionSort;

public class App {
    public String getGreeting() {
        DoublyLinkedList list = new DoublyLinkedList();

        ArrayList<Integer> currentState = new ArrayList<Integer>();
        currentState.add(3);
        currentState.add(2);
        currentState.add(4);
        currentState.add(1);
        Pair<Integer, Integer> comparedElements = new Pair<Integer, Integer>(-1, -1);
        Integer currentIndex = 0;
        Boolean isFinished = false;

        SortingContainer step0 = new SortingContainer(currentState, comparedElements, currentIndex, isFinished, false);
        list.addNode(step0);

        SortingContainer step1 = SelectionSort.step(step0);
        list.addNode(step1);

        SortingContainer step2 = SelectionSort.step(step1);
        list.addNode(step2);

        SortingContainer step3 = SelectionSort.step(step2);
        list.addNode(step3);

        list.display();

        return "Hello World!";
    }

    public static void main(String[] args) {
        System.out.println(new App().getGreeting());
    }
}
