/**
 * Maksym Hontar (c) 2014
 * Created by mhontar on 13.09.14.
 */
public class Percolation {
    // general idea is to make 2 extra nodes except grid nodes
    // when top node is connected with bottom node -> then grid percolates

    // indexes for very top and very bottom nodes
    public static final int VERY_TOP = 0;
    public static final int VERY_BOTTOM = VERY_TOP+1;

    // grid size
    private final int N;
    private final WeightedQuickUnionUF grid;
    private final boolean [][] openSites;

    /**
     * create N-by-N grid, with all sites blocked
     * @param N size of grid
     */
    public Percolation(final int N){
        if(N<=0)
           throw new IllegalArgumentException();
        this.N = N;
        this.openSites = new boolean[N][N];
        this.grid = new WeightedQuickUnionUF(N*N+2);
    }

    /**
     * open site (row i, column j) if it is not already
     * @param i row
     * @param j column
     */
    public void open(int i, int j){
        validateArguments(i, j);
        if(isOpen(i,j)) return;
        openSites[i-1][j-1] = true;
        connectNodeWithNeighbours(i, j);
    }

    /**
     * Check if there are opened neighbour nodes and connect if so
     * @param i row
     * @param j column
     */
    private void connectNodeWithNeighbours(int i, int j) {
        //checks if there is full neighbour on top
        connectNodeWithTopNeighbour(i, j);
        //checks if there is full neighbour on bottom
        connectNodeWithBottomNeighbour(i, j);
        //checks if there is full neighbour on left
        connectNodeWithLeftNeighbour(i, j);
        //checks if there is full neighbour on right
        connectNodeWithRightNeighbour(i, j);
    }

    /**
     * Check if there is opened neighbour node on right side and connect if so
     * @param i row
     * @param j column
     */
    private void connectNodeWithRightNeighbour(int i, int j) {
        if(j<N && isOpen(i, j + 1))
            grid.union(convertToNodeRepresentation(i, j + 1), convertToNodeRepresentation(i, j));
    }

    /**
     * Check if there is opened neighbour node on left side and connect if so
     * @param i row
     * @param j column
     */
    private void connectNodeWithLeftNeighbour(int i, int j) {
        if(j>1 && isOpen(i, j - 1))
            grid.union(convertToNodeRepresentation(i, j - 1), convertToNodeRepresentation(i, j));
    }

    /**
     * Check if there is opened neighbour node on bottom side and connect if so
     * @param i row
     * @param j column
     */
    private void connectNodeWithBottomNeighbour(int i, int j) {
        // if last row - connect with VERY BOTTOM
        if (i==N)
            grid.union(convertToNodeRepresentation(i, j),VERY_BOTTOM);
        else if (isOpen(i+1,j))
            grid.union(convertToNodeRepresentation(i + 1, j), convertToNodeRepresentation(i, j));

    }

    /**
     * Check if there is opened neighbour node on top side and connect if so
     * @param i row
     * @param j column
     */
    private void connectNodeWithTopNeighbour(int i, int j) {
        // if first row - connect with VERY TOP
        if(i==1)
            grid.union(convertToNodeRepresentation(i, j),VERY_TOP);
        else if (isOpen(i-1,j))
            grid.union(convertToNodeRepresentation(i - 1, j), convertToNodeRepresentation(i, j));
    }


    /**
     * is site (row i, column j) open?
     * @param i row
     * @param j column
     * @return whether site is opepened
     */
    public boolean isOpen(int i, int j)  {
        validateArguments(i, j);
        return openSites[i-1][j-1];
    }

    /**
     * is site (row i, column j) full?
     * @param i row
     * @param j column
     * @return whether site is full
     */
    public boolean isFull(int i, int j){
        validateArguments(i, j);
        return grid.connected(0, convertToNodeRepresentation(i, j));
    }

    /**
     * does the system percolate?
     * @return whether percolates
     */
    public boolean percolates(){
        return grid.connected(0,1);
    }

    /**
     * @throws java.lang.IndexOutOfBoundsException in case i>=1 && i<=N && j>=1 && j<=N
     * @param i row
     * @param j column
     */
    private void validateArguments(int i, int j) {
        if(!(i>=1 && i<=N && j>=1 && j<=N))
            throw new IndexOutOfBoundsException("{"+i+","+j+"} arguments should be in range [1,"+N+"];");
    }

    /**
     * converts [row,column] representation to serial number of node, used in graph
     * 0 - TOP node
     * 1 - BOTTOM node
     * [2 .. (N*N) +2] other nodes
     *
     * @param i row
     * @param j column
     * @return converted representation
     */
    private int convertToNodeRepresentation(int i, int j){
        return 2+(i-1)*N+j-1;
    }
}
