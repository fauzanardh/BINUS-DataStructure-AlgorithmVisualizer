package binus.datastructure.algorithmvisualizer;

public class DoublyLinkedList {
    class Node {
        SortingContainer item;
        Node prev;
        Node next;
    
        public Node(SortingContainer item) {
            this.item = item;
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
