import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SimulationPanel extends JPanel {
    private final Grid<? extends Cell> grid;
    private final int cellSize;
    private int dragStartX, dragStartY;

    public SimulationPanel(Grid<? extends Cell> grid, int cellSize){
        this.grid = grid;
        this.cellSize = cellSize;
        setPreferredSize(new java.awt.Dimension(
                grid.getWidth()*cellSize, grid.getHeight() * cellSize));
        setBackground(Color.WHITE);

        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e){
                dragStartX = e.getX()/cellSize;
                dragStartY = e.getY()/cellSize;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                int dragEndX = e.getX()/cellSize;
                int dragEndY = e.getY()/cellSize;

                int minX = Math.max(0, Math.min(dragStartX, dragEndX));
                int minY = Math.max(0, Math.min(dragStartY, dragEndY));
                int maxX = Math.min(grid.getWidth() - 1, Math.max(dragStartX, dragEndX));
                int maxY = Math.min(grid.getHeight() - 1, Math.max(dragStartY, dragEndY));

                for (int y=minY; y<=maxY; y++){
                    for(int x=minX; x<=maxX; x++ ){
                        try {
                            grid.setAlive(x, y, true);
                        } catch (InvalidDimensionException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }

                repaint();
            }
        });
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
