import java.util.LinkedList;
public class SearchAlgorithm implements ISearchAlgorithm {
    private LinkedList<Node> unexpandedNodes = new LinkedList<Node>();
    private LinkedList<Node> expandedNodes = new LinkedList<Node>();
    private Node goalNode;
    private boolean isGoalFounded;


    // isValid method, by receiving a Node, determines the validity of that Node
    // according to the conditions defined in the problem.
    private boolean isValid(INode node) {
        boolean boat = node.getState()[0];
        boolean police = node.getState()[1];
        boolean thief = node.getState()[2];
        boolean father = node.getState()[3];
        boolean boy1 = node.getState()[4];
        boolean boy2 = node.getState()[5];
        boolean mother = node.getState()[6];
        boolean girl1 = node.getState()[7];
        boolean girl2 = node.getState()[8];

        if (father != mother) {
            if (father != boy1 || father != boy2) {
                return false;
            }
            if (mother != girl1 || mother != girl2) {
                return false;
            }
        }

        if (police != thief) {
            for (int i = 3; i < node.getState().length; i++) {
                if (police != node.getState()[i]) {
                    return false;
                }
            }
        }
        return true;
    }

    // isGoal method checks the finality of the input node.
    //(Node is in the final state if all entities are in region2)
    private boolean isGoal(INode node) {
        for (int i = 0; i < node.getState().length; i++) {
            if (!node.getState()[i]) {
                return false;
            }
        }
        return true;
    }

    // generateChildren method generates all possible child nodes of the input node
    private LinkedList<Node> generateChildren(Node node) {

        LinkedList<Node> children = new LinkedList<Node>();
        boolean boat = node.getState()[0];

        for (int i = 1; i < node.getState().length; i++) {

            boolean[] childeState1 = node.getState().clone();
            childeState1[0] = !boat;

            if (node.getState()[i] == boat) {
                childeState1[i] = !childeState1[i];
            } else {
                continue;
            }

            // create childern ...
            Node childeNode1 = INode.create(null,childeState1);
            childeNode1.setFatherNode(node);
            childeNode1.setState(childeState1);
            boolean driver1 = (i==1) || (i==3) || (i==6);
            if (driver1) {
                children.add(childeNode1);
            }

            for (int j = i + 1; j < node.getState().length; j++) {
                boolean[] childeState2 = childeState1.clone();
                if (node.getState()[j] == boat) {
                    childeState2[j] = !childeState2[j];
                } else {
                    continue;
                }
                Node childeNode2 = INode.create(null,childeState2);
                childeNode2.setFatherNode(node);
                childeNode2.setState(childeState2);
                boolean driver2 = (j==1) || (j==3) || (j==6);
                if (driver1 || driver2) {
                    children.add(childeNode2);
                }
            }
        }
        return children;
    }

    // Check if the input node is equal to another node in the expandedNodes list
    private boolean doesExist(INode node) {
        for (int i = 0; i < expandedNodes.size(); i++) {
            if (node.equals(expandedNodes.get(i))) {
                return true;
            }
        }
        return false;
    }

    // The search method looks for the answer to the problem using the following algorithm and returns the final
    // answer at the end

    @Override
    public Node search(Node starterNode) {
        unexpandedNodes.add(starterNode);

        Thread[] threads = new Thread[4];
        for (int i = 0; i < 4; i++) {
            threads[i] = new Thread(new multiThreadSearch());
            threads[i].start();
        }

        synchronized (this) {
            while (!isGoalFounded) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    // Thread interrupted
                    break;
                }
            }
        }

        for (Thread thread : threads) {
            thread.interrupt();
        }

        return goalNode;
    }

    private class multiThreadSearch implements Runnable {
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted() && !isGoalFounded) {
                Node target;
                synchronized (unexpandedNodes) {
                    if (unexpandedNodes.isEmpty()) {
                        break;
                    }
                    target = unexpandedNodes.removeLast();
                }

                if (doesExist(target)) {
                    continue;
                }

                if (!isValid(target)) {
                    synchronized (expandedNodes) {
                        expandedNodes.add(target);
                    }
                    continue;
                }

                if (isGoal(target)) {
                    synchronized (SearchAlgorithm.this) {
                        goalNode = target;
                        isGoalFounded = true;
                        SearchAlgorithm.this.notifyAll();
                    }
                    break;
                }

                synchronized (expandedNodes) {
                    expandedNodes.add(target);
                }

                LinkedList<Node> children = generateChildren(target);

                synchronized (unexpandedNodes) {
                    unexpandedNodes.addAll(children);
                }
            }
        }
    }
}
