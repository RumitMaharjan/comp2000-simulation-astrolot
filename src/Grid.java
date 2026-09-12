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

    public int getWidth(){return width;}

    public int getHeight(){return height;}

    public T getCell(int x, int y){
        return cells[y][x];
    }
}
