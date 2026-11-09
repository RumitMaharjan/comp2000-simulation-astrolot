import java.util.Random;
import java.util.stream.IntStream;

public class Grid{
    private  final int width;
    private  final int height;

    private boolean[][] cells;

    private Rule rule;

    public Grid(int width, int height, Rule rule) throws InvalidDimensionException{
        if(width <=0 || height<=0){
            throw new InvalidDimensionException(
                "The dimensions of the grid must be greater than 0!"
            );
        }
        this.width = width;
        this.height = height;
        this.rule = rule;

        cells = new boolean[height][width];
    }

    public Grid(int width, int height, Rule rule, double liveProbability) throws InvalidDimensionException{
        this(width, height, rule);
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

    public void setAlive(int x, int y, boolean alive) throws InvalidDimensionException{
        if(x<0 || x>=width || y<0 || y>=height){
            throw new InvalidDimensionException("The  cell is out of bound!");
        }
        cells[y][x] = alive;
    }

    public boolean isAlive(int x, int y){
        return cells[y][x];
    }

    private int countLiveNeighbours(int x, int y){
        int count = 0;

        for(int dy = -1; dy<=1; dy++){
            for(int dx = -1; dx<=1; dx++){
                if(dy == 0 && dx == 0) continue;
                int nx = Math.floorMod(x+dx, width);
                int ny = Math.floorMod(y+dy, height);
                if(cells[ny][nx]) count++;
            }
        }

        return count;
    }

    public void step(){
        cells = IntStream.range(0, height)
                .mapToObj(y->{
                    boolean[] row = new boolean[width];
                    for(int x = 0; x<width; x++){
                        row[x] = rule.next(cells[y][x], countLiveNeighbours(x, y));
                    }
                    return row;
                })
                .toArray(boolean[][]::new);
    }

    public void clear(){
        for(int y = 0; y<height; y++){
            for(int x = 0; x<width; x++){
                cells[y][x] = false;
            }
        }
    }

    public long countAlive(){
        return IntStream.range(0, height)
                .mapToLong(y->{
                    int count = 0;
                    for(int x = 0; x<width; x++){
                        if(cells[y][x]) {
                            count++;
                        }
                    }
                    return count;
                })
                .sum();
    }

    public void randomize(double liveProbability ){
        Random rng = new Random();
        for(int y = 0; y<height; y++){
            for(int x = 0; x<width; x++){
                cells[y][x] = rng.nextDouble() < liveProbability;
            }
        }
    }
}
