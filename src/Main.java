import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args){
        try{
            int width = 60, height = 40, cellSize = 12;

            Grid grid = new Grid(width, height, Rules.HIGHLIFE, 0.2);

            grid.setAlive(1, 0, true);
            grid.setAlive(2, 1, true);
            grid.setAlive(0, 2, true);
            grid.setAlive(1, 2, true);
            grid.setAlive(2, 2, true);

            SimulationPanel panel = new SimulationPanel(grid, cellSize);

            JFrame frame = new JFrame("COMP2000 Simulation GOL");

            JLabel instructions = new JLabel("Drag on the grid to bring cells to life.", SwingConstants.CENTER);
            JButton resetButton = new JButton("Reset");
            JButton clearButton = new JButton("Clear");
            JButton pauseButton = new JButton("Pause");
            JButton startButton = new JButton("Start");
            JLabel stats = new JLabel();

            resetButton.addActionListener(e->{
                grid.randomize(0.2);
            });
            clearButton.addActionListener(e->{
                grid.clear();
            });

            Timer timer = new Timer(120, e->{
                grid.step();
            });

            grid.addListener(g->panel.repaint());

            grid.addListener(g->  stats.setText("Generation: " + g.getGeneration() + "   Alive: " + g.countAlive())
            );

            pauseButton.addActionListener(e->{
               timer.stop();
            });

            startButton.addActionListener(e->{
                timer.start();
            });

            JPanel controls = new JPanel();

            controls.add(instructions);
            controls.add(resetButton);
            controls.add(clearButton);
            controls.add(startButton);
            controls.add(pauseButton);

            frame.add(panel, BorderLayout.CENTER);
            frame.add(controls, BorderLayout.SOUTH);
            frame.add(stats, BorderLayout.NORTH);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null);

            frame.setVisible(true);

            timer.start();

        } catch (InvalidDimensionException e) {
            System.err.println("Could not start the simulation: "+ e.getMessage());
        }
    }
}
