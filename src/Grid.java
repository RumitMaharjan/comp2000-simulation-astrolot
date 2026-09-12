public class Grid {
    private  final int width;
    private  final int height;

    private ClassicCell[][] cells;

    public Grid(int width, int height) throws InvalidDimensionException{
        if(width <=0 || height<=0){
            throw new InvalidDimensionException(
                "The dimensions of the grid must be greater than 0!"
            );
        }
        this.width = width;
        this.height = height;

        this.cells = new ClassicCell[this.height][this.width];

        for(int i = 0; i<height; i++){
            for(int j = 0; j<width; j++){
                cells[i][j] = new ClassicCell(false);
            }
        }
    }

    public int getWidth(){return width;}

    public int getHeight(){return height;}

    public ClassicCell getCell(int x, int y){
        return cells[y][x];
    }
}
