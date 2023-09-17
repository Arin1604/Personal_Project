package indy;

public class MyLinkedList<BridgeBlock> {
    private Node<BridgeBlock> head;
    private Node<BridgeBlock> tail;
    private int size;

    /**
     * This is the constructor for MyLinkedList;
     */
    public MyLinkedList(){
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * This method is a standard method of the linked list
     */
    public Node<BridgeBlock> addFirst(BridgeBlock el) {
        this.size++;
        Node<BridgeBlock> newNode = new Node<BridgeBlock>(el);
        newNode.setNext(this.head);
        this.head = newNode;
        if (size == 1) {
            this.tail = newNode;
        }
        return newNode;
    }

    /**
     * This method adds an element to the last of the linked list
     */

    public Node<BridgeBlock> addLast(BridgeBlock el) {
        Node<BridgeBlock> newNode = new Node<BridgeBlock>(el);
        if (this.size == 0) {
            this.head = newNode;
            this.tail = newNode;
        }
        else {
            this.tail.setNext(newNode);
            this.tail = newNode;
        }
        this.size++;
        return newNode;

    }

    /**
     * This method removes the first element of a linked list
     */
    public BridgeBlock removeFirst() {
        if (this.size == 0) {
            System.out.println("List is empty");
            return null;
        }
        BridgeBlock removed = this.head.getElement();
        this.head = this.head.getNext();
        this.size--;
        if (this.size == 0) {
            this.tail = null;
        }
        return removed;

    }

    /**
     * This method removes the last element of the linked list
     */
    public BridgeBlock removeLast() {
        BridgeBlock removed = null;
        if (this.size == 0) {
            System.out.println("List is empty");
        } else if (this.size == 1) {
            removed = this.head.getElement();
            this.head = null;
            this.tail = null;
            this.size = 0;
        } else {
            Node curr = this.head;
            Node prev = null;
            while (curr.getNext() != null) {

                prev = curr;
                curr = curr.getNext();
            }
            removed = (BridgeBlock) curr.getElement();
            prev.setNext(null);
            this.tail = prev;
            this.size--;
        }
        return removed;

    }
    public BridgeBlock remove(BridgeBlock itemToRemove) {
        if (this.isEmpty()) {
            System.out.println("List is empty");
            return null;
        }
        if (itemToRemove.equals(this.head.getElement())) {
            return this.removeFirst();
        }
        if (itemToRemove.equals(this.tail.getElement())) {
            return this.removeLast();
        }

        Node<BridgeBlock> curr = this.head.getNext();
        Node<BridgeBlock> prev = this.head;
        while (curr != null) {
            if (curr.getElement().equals(itemToRemove)) {
                prev.setNext(curr.getNext());
                this.size--;
                return curr.getElement();
            }
            prev = curr;
            curr = curr.getNext();
        }
        return null;
    }

    public Node<BridgeBlock> search(BridgeBlock e1) {
        Node<BridgeBlock> curr = this.head;
        while (curr != null) {
            if (curr.getElement().equals(e1)) {
                return curr;
            }
            curr = curr.getNext();
        }
        return null;
    }

    /**
     * This method returns the size of the linked list
     */
    public int size() {
        return this.size;
    }

    /**
     * This method helps us determine is the list is empty or not
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * This method returns the head
     */
    public Node<BridgeBlock> getHead() {
    return this.head;
    }
    public Node<BridgeBlock> getTail() {
    return this.tail;
    }
}
