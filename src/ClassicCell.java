public class ClassicCell extends Cell{
    public ClassicCell(boolean alive){
        super(alive);
    }

    @Override
    public boolean nextState(int liveNeighbours) {
        if(isAlive()){
            return liveNeighbours == 2 || liveNeighbours == 3;
        }
        return liveNeighbours == 3;
    }
}
