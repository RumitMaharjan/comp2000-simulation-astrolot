import javax.swing.*;

public class Main {
    public static void main(String[] args){
        try{
            int width = 60, height = 40, cellSize = 12;

            Grid<HighLifeCell> grid = new Grid<>(width, height, HighLifeCell::new, 0.2);

            grid.setAlive(1, 0, true);
            grid.setAlive(2, 1, true);
            grid.setAlive(0, 2, true);
            grid.setAlive(1, 2, true);
            grid.setAlive(2, 2, true);

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

            timer.start();

        } catch (InvalidDimensionException e) {
            System.err.println("Could not start the simulation: "+ e.getMessage());
        }
    }
}
