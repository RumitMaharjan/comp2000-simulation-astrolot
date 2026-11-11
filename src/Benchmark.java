public class Benchmark {

    public static void main(String[] args) throws InvalidDimensionException {
        System.out.println(Runtime.getRuntime().availableProcessors() + " cores");

        Grid warm = new Grid(1000, 1000, Rules.CLASSIC, 0.3);
        for (int i = 0; i < 5; i++) {
            warm.step();
            warm.stepParallel();
        }

        int[][] sizes = { {1000, 1000, 20}, {200, 200, 300}, {60, 40, 2000} };

        for (int[] s : sizes) {
            int w = s[0];
            int h = s[1];
            int steps = s[2];

            Grid seq = new Grid(w, h, Rules.CLASSIC, 0.3);
            long start = System.nanoTime();
            for (int i = 0; i < steps; i++) {
                seq.step();
            }
            long seqMs = (System.nanoTime() - start) / 1_000_000;

            Grid par = new Grid(w, h, Rules.CLASSIC, 0.3);
            start = System.nanoTime();
            for (int i = 0; i < steps; i++) {
                par.stepParallel();
            }
            long parMs = (System.nanoTime() - start) / 1_000_000;

            System.out.println(w + "x" + h + ", " + steps + " steps: sequential "
                    + seqMs + " ms, parallel " + parMs + " ms");
        }

        checkSame();
    }

    private static void checkSame() throws InvalidDimensionException {
        int w = 120;
        int h = 90;
        Grid a = new Grid(w, h, Rules.HIGHLIFE, 0.3);
        Grid b = new Grid(w, h, Rules.HIGHLIFE);

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                b.setAlive(x, y, a.isAlive(x, y));
            }
        }

        for (int i = 0; i < 50; i++) {
            a.step();
            b.stepParallel();
        }

        int different = 0;
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                if (a.isAlive(x, y) != b.isAlive(x, y)) {
                    different++;
                }
            }
        }
        System.out.println("cells that differ after 50 generations: " + different);
    }
}