public class Node implements INode {

    private Node fatherNode;
    private boolean[] state; // 0: boat / 1:police / 2: thief / 3: father / 4: boy1 / 5: boy2 / 6: mother / 7: girl1 / 8: girl2

    public Node(Node fatherNode, boolean[] state) {
        this.state = state;
        this.fatherNode = fatherNode;
    }
    @Override
    public boolean[] getState() {
        return state;
    }

    @Override
    public void setState(boolean[] stateSet) {
        state = stateSet.clone();
    }

    @Override
    public void setFatherNode(Node node) {
        fatherNode = node;
    }

    @Override
    public Node getFatherNode(){
        return fatherNode;
    }

    @Override
    public boolean equals(Node targetNode) {
        for (int i = 0; i < state.length; i++) {
            if (state[i] != targetNode.state[i]) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {

        StringBuilder situations= new StringBuilder();

        situations.append("Region 1:\n");
        situations.append("\n");

        if (!state[1]) {
            situations.append("\t\tPolice\n");
        }
        if (!state[2]){
            situations.append("\t\tThief\n");
        }
        if (!state[3]){
            situations.append("\t\tFather\n");
        }
        if (!state[4]){
            situations.append("\t\tBoy1\n");
        }
        if (!state[5]){
            situations.append("\t\tBoy2\n");
        }
        if (!state[6]){
            situations.append("\t\tMother\n");
        }
        if (!state[7]){
            situations.append("\t\tGirl1\n");
        }
        if (!state[8]){
            situations.append("\t\tGirl2\n");
        }

        situations.append("\n");

        situations.append("Region2 :\n");
        situations.append("\n");

        if (state[1]) {
            situations.append("\t\tPolice\n");
        }
        if (state[2]){
            situations.append("\t\tThief\n");
        }
        if (state[3]){
            situations.append("\t\tFather\n");
        }
        if (state[4]){
            situations.append("\t\tBoy1\n");
        }
        if (state[5]){
            situations.append("\t\tBoy2\n");
        }
        if (state[6]){
            situations.append("\t\tMother\n");
        }
        if (state[7]){
            situations.append("\t\tGirl1\n");
        }
        if (state[8]){
            situations.append("\t\tGirl2\n");
        }

        situations.append("\n");

        situations.append("Boat location --->");
        if(state[0]) {
            situations.append("\tRegion2\n");
        } else{
            situations.append("\tRegion1\n");
        }
        return situations.toString();
    }

    @Override
    public Node Clone() {
        Node cloneNode = new Node(fatherNode, state);
        cloneNode.state = this.state;
        cloneNode.fatherNode = this.fatherNode;
        return cloneNode;
    }

}
