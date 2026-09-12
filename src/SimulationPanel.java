import javax.swing.*;
import java.awt.*;

public class SimulationPanel extends JPanel {
    private final Grid<? extends Cell> grid;
    private final int cellSize;

    public SimulationPanel(Grid<? extends Cell> grid, int cellSize){
        this.grid = grid;
        this.cellSize = cellSize;
        setPreferredSize(new java.awt.Dimension(
                grid.getWidth()*cellSize, grid.getHeight() * cellSize));
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(new Color(30, 30, 30));
        for(int y = 0; y<grid.getHeight(); y++){
            for(int x = 0; x<grid.getWidth(); x++){
                if(grid.getCell(x, y).isAlive()){
                    g.fillRect(x*cellSize, y*cellSize, cellSize-1, cellSize-1);
                }
            }
        }
    }
}
