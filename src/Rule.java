public interface Rule  {
    boolean next(boolean alive, int liveNeighbours);
}
