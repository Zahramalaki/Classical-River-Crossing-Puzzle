public class Main {
    public static int transferIndex;
    public static void show(Node node){
        transferIndex++;
        System.out.println("Transfer index: "+ transferIndex);
        System.out.println();
        System.out.println(node.toString());
        System.out.println("**************************************");
    }
    public static void showPath(Node node){
        if (node.getFatherNode()!=null){
            showPath(node.getFatherNode());
        }
        show(node);
    }

    public static void main(String[] args) {

        SearchAlgorithm goSearch = ISearchAlgorithm.CreateSearcher();
        boolean[] fatherState= new boolean[9];
        Node father = INode.create(null,fatherState);
        for (int i = 0; i <fatherState.length ; i++) {
            fatherState[0]=false;
        }
        father.setState(fatherState);
        Node node= goSearch.search(father);

        System.out.println("**************************************");
        if(node !=null){
            showPath(node);
        } else {
            System.out.println("No answer found!");
        }
    }

}