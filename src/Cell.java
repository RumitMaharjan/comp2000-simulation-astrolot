public abstract class  Cell {
    private boolean alive;

    public Cell(boolean alive){
        this.alive = alive;
    }

    public boolean isAlive(){
        return alive;
    }

    public abstract boolean nextState(int liveNeighbours);

    public void commit(boolean willBeAlive){this.alive = willBeAlive;}
}