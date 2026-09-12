public class SimulationStats {
    public int generationCount;
    public int aliveCount;

    public SimulationStats(int generationCount, int aliveCount){
        this.generationCount = generationCount;
        this.aliveCount = aliveCount;
    }

    public void printSummary(){
        System.out.println("Simulation Stats: ");
        System.out.println("Generation count: "+generationCount);
        System.out.println("Alive count: "+aliveCount);
    }
}
