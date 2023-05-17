package binus.datastructure.algorithmvisualizer;

import java.util.ArrayList;
import java.util.Collections;

public class DoublyLinkedList {
    private Integer size = 0;
    public class Node {
        private SortingContainer item;
        private Node prev;
        private Node next;
    
        public Node(SortingContainer item) {
            this.item = item;
        }

        public SortingContainer getItem() {
            return item;
        }

        public Node getNext() {
            return next;
        }

        public Node getPrev() {
            return prev;
        }
    }

    // Initialize head and tail as null
    Node head, tail = null;

    public void addNode(SortingContainer item) {
        // Create a new node
        Node newNode = new Node(item);

        // if list is empty, head and tail points to newNode
        if (head == null) {
            head = tail = newNode;
            // head's previous will be null
            head.prev = null;
            // tail's next will be null
            tail.next = null;
        } else {
            // add newNode to the end of the list, tail->next set to newNode
            tail.next = newNode;
            // newNode->prev set to tail
            newNode.prev = tail;
            // newNode becomes new tail
            tail = newNode;
        }

        // Size increment
        size++;
    }

    public Node getHead() {
        return head;
    }

    public Node getTail() {
        return tail;
    }

    public Integer getTotalNode() {
        return size;
    }

    public Integer getMaxValue() {
        // Search for the maximum value from the head
        Node current = head;
        Integer maxValue = 0;
        while (current != null) {
            Integer currentValue = Collections.max(current.item.getNextState());
            if (currentValue > maxValue) {
                maxValue = currentValue;
            }
            current = current.next;
        }
        return maxValue;
    }

    public void display() {
        // Node current will point to head
        Node current = head;
        if (head == null) {
            System.out.println("List is empty");
            return;
        }
        System.out.println("Nodes of doubly linked list: ");
        while (current != null) {
            // Prints each node by incrementing pointer
            System.out.println(current.item);
            current = current.next;
        }
    }
}
