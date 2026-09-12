import javax.swing.*;

public class Main {
    public static void main(String[] args){
        try{
            int width = 60, height = 40, cellSize = 12;

            Grid<ClassicCell> grid = new Grid<>(width, height, ClassicCell::new);

            SimulationPanel panel = new SimulationPanel(grid, cellSize);

            JFrame frame = new JFrame("COMP2000 Simulation GOL");

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(panel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            Timer timer = new Timer(120, e->{
                grid.step();
                panel.repaint();
            });

        } catch (InvalidDimensionException e) {
            System.err.println("Could not start the simulation: "+ e.getMessage());
        }
    }
}
