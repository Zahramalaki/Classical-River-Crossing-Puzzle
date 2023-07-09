public interface INode {

    public static Node create(Node fatherNode, boolean[] state){
        return new Node(fatherNode, state);
    }

    public boolean[] getState();

    public void setState(boolean[] stateSet);

    public void setFatherNode(Node node);

    public Node getFatherNode();

    public boolean equals(Node targetNode);

    public String toString();

    public Node Clone();

}

