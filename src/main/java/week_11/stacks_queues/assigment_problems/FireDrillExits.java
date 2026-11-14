package week_11.stacks_queues.assigment_problems;

/**
 * Problem 5: The Fire Drill Exits
 *
 * Multi-source breadth-first search: all exits enter the queue at distance 0.
 * Each reachable open room is visited once, giving O(rows * columns) time.
 */
public class FireDrillExits {

    public static int[][] exitDistance(char[][] plan) {
        if (plan == null) {
            throw new IllegalArgumentException("Floor plan cannot be null.");
        }

        int rows = plan.length;
        if (rows == 0) {
            return new int[0][0];
        }

        if (plan[0] == null) {
            throw new IllegalArgumentException("Floor plan rows cannot be null.");
        }

        int columns = plan[0].length;

        for (char[] row : plan) {
            if (row == null || row.length != columns) {
                throw new IllegalArgumentException("Floor plan must be rectangular.");
            }
        }

        int[][] distance = new int[rows][columns];

        // -1 means wall or a room not reachable from any exit.
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                distance[r][c] = -1;
            }
        }

        // A primitive array is used as an O(1) FIFO queue of cell positions.
        int[] queue = new int[rows * columns];
        int head = 0;
        int tail = 0;

        // Start BFS simultaneously from every exit.
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                if (plan[r][c] == 'E') {
                    distance[r][c] = 0;
                    queue[tail++] = r * columns + c;
                }
            }
        }

        int[] rowStep = {-1, 1, 0, 0};
        int[] columnStep = {0, 0, -1, 1};

        while (head < tail) {
            int position = queue[head++];
            int r = position / columns;
            int c = position % columns;

            for (int direction = 0; direction < 4; direction++) {
                int nextRow = r + rowStep[direction];
                int nextColumn = c + columnStep[direction];

                if (nextRow < 0 || nextRow >= rows
                        || nextColumn < 0 || nextColumn >= columns) {
                    continue;
                }

                // Ignore walls and cells already assigned a shortest distance.
                if (plan[nextRow][nextColumn] == '#'
                        || distance[nextRow][nextColumn] != -1) {
                    continue;
                }

                distance[nextRow][nextColumn] = distance[r][c] + 1;
                queue[tail++] = nextRow * columns + nextColumn;
            }
        }

        return distance;
    }

    public static void main(String[] args) {
        char[][] plan = {
            {'.', '#', '.', 'E'},
            {'.', '.', '.', '#'},
            {'E', '#', '.', '.'}
        };

        int[][] result = exitDistance(plan);

        for (int[] row : result) {
            for (int c = 0; c < row.length; c++) {
                if (c > 0) {
                    System.out.print(" ");
                }
                System.out.print(row[c]);
            }
            System.out.println();
        }
    }
}
