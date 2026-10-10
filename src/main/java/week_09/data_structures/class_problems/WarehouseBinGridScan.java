package week_09.data_structures.class_problems;

public class WarehouseBinGridScan {
    public static final class Summary {
        public final long total;
        public final int maxRow, maxCol, maxValue;
        Summary(long total, int maxRow, int maxCol, int maxValue) {
            this.total = total; this.maxRow = maxRow; this.maxCol = maxCol; this.maxValue = maxValue;
        }
        @Override public String toString() {
            return "total=" + total + ", maxCoordinate=(" + maxRow + ", " + maxCol + "), maxValue=" + maxValue;
        }
    }
    /** O(rows*cols) time and O(1) auxiliary space; ties keep the first row-major coordinate. */
    public static Summary warehouseSummary(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0)
            throw new IllegalArgumentException("Grid must be non-empty");
        long total = 0;
        int max = Integer.MIN_VALUE, maxRow = 0, maxCol = 0;
        for (int r = 0; r < grid.length; r++) {
            if (grid[r] == null || grid[r].length != grid[0].length)
                throw new IllegalArgumentException("Grid must be rectangular");
            for (int c = 0; c < grid[r].length; c++) {
                int value = grid[r][c];
                if (value < 0) throw new IllegalArgumentException("Bin counts must be non-negative");
                total += value;
                if (value > max) { max = value; maxRow = r; maxCol = c; }
            }
        }
        return new Summary(total, maxRow, maxCol, max);
    }
    public static void main(String[] args) {
        System.out.println(warehouseSummary(new int[][]{{4,9,2},{7,1,6},{3,12,5}}));
    }
}