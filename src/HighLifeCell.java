public class HighLifeCell extends Cell{
    public HighLifeCell(boolean alive){
        super(alive);
    }

    @Override
    public boolean nextState(int liveNeighbours) {
        if(isAlive()){
            return liveNeighbours == 2 || liveNeighbours == 3;
        }
        return liveNeighbours == 3 || liveNeighbours == 6;
    }
}
