public interface ISearchAlgorithm {
    static SearchAlgorithm CreateSearcher(){
        return new SearchAlgorithm();
    }
    public Node search(Node starterNode);

}
