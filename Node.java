package indy;

public class Node<BridgeBlock> {

    //          <indy.BridgeBlock> ?????
    private Node<BridgeBlock> next;
    private BridgeBlock element;

    /**
     * This is the constructor for node
     */
    public Node(BridgeBlock element){
        this.next = null;
        this.element = element;
    }

    /**
     * This method returns the next node
     */
    public Node<BridgeBlock> getNext(){
        return this.next;
    }

    /**
     * This method sets the next node by using a node as a parameter
     */
    public void setNext(Node<BridgeBlock> next){
        this.next = next;
    }

    /**
     * This method returns the element;
     */
    public BridgeBlock getElement(){
        return this.element;
    }

    public void setElement(BridgeBlock element){
        this.element = element;
    }
}
