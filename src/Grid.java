import java.util.Random;

public class Grid<T extends Cell> {
    private  final int width;
    private  final int height;

    private final CellFactory<T> factory;

    private final T[][] cells;

    @SuppressWarnings("unchecked")
    public Grid(int width, int height, CellFactory<T> factory) throws InvalidDimensionException{
        if(width <=0 || height<=0){
            throw new InvalidDimensionException(
                "The dimensions of the grid must be greater than 0!"
            );
        }
        this.width = width;
        this.height = height;
        this.factory = factory;

        this.cells =  (T[][]) new Cell[this.height][this.width];

        for(int i = 0; i<height; i++){
            for(int j = 0; j<width; j++){
                cells[i][j] = factory.create(false);
            }
        }
    }

    public Grid(int width, int height, CellFactory<T> factory, double liveProbability) throws InvalidDimensionException{
        this(width, height, factory);
        Random rng = new Random();
        for(int y = 0; y<height; y++){
            for(int x = 0; x<width; x++){
                if(rng.nextDouble()<liveProbability){
                    setAlive(x, y, true);
                }
            }
        }
    }

    public int getWidth(){return width;}

    public int getHeight(){return height;}

    public T getCell(int x, int y){
        return cells[y][x];
    }

    public void setAlive(int x, int y, boolean alive) throws InvalidDimensionException{
        if(x<0 || x>=width || y<0 || y>=height){
            throw new InvalidDimensionException("The  cell is out of bound!");
        }
        cells[y][x] = factory.create(alive);
    }

    private int countLiveNeighbours(int x, int y){
        int count = 0;

        for(int dy = -1; dy<=1; dy++){
            for(int dx = -1; dx<=1; dx++){
                if(dy == 0 && dx == 0) continue;
                int nx = Math.floorMod(x+dx, width);
                int ny = Math.floorMod(y+dy, height);
                if(cells[ny][nx].isAlive()) count++;
            }
        }

        return count;
    }

    public void step(){
        boolean[][] nextAlive = new boolean[height][width];

        for(int y = 0; y<height; y++){
            for(int x = 0; x<width; x++){
                nextAlive[y][x] = cells[y][x].nextState(countLiveNeighbours(x, y));
            }
        }

        for(int y = 0; y<height; y++){
            for(int x = 0; x<width; x++){
                cells[y][x].commit(nextAlive[y][x]);
            }
        }
    }
}
