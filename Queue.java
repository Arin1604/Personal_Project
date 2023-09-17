package indy;

public class Queue<BridgeBlock> {

    private MyLinkedList<BridgeBlock> list;

    /**
     * This is the constructor for Queue, a wrapper class for MyLinkedList
     */
    public Queue(){
        this.list = new MyLinkedList<BridgeBlock>();
    }


    /**
     * This method adds a node to the end of the list.
     */
    public void enqueue(BridgeBlock newNode){
        this.list.addLast(newNode);


    }
    /**
     * This method removes the first element of the list and returns it
     */
    public BridgeBlock dequeue(){
        return this.list.removeFirst();

    }

    public boolean isEmpty(){
        return this.list.isEmpty();

    }

    public int size(){
        return this.list.size();

    }

    /**
     * This method gets the node from the head.
     */
    public BridgeBlock peek(){
        return this.list.getHead().getElement();
    }
    /**
     * This method returns the list
     */
    public MyLinkedList<BridgeBlock> getList(){
        return this.list;
    }


}
