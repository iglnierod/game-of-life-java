import java.util.Arrays;

public class Main {
    static final char DEAD = '·';
    static final char ALIVE = '✖';
    static final double DEAD_ALIVE_RATIO = 0.5;
    static final int DEFAULT_MATRIX_HEIGHT = 30;
    static final int DEFAULT_MATRIX_WIDTH = 100;

    public static void main(String[] args) throws InterruptedException {
        int height = DEFAULT_MATRIX_HEIGHT;
        int width = DEFAULT_MATRIX_WIDTH;

        if (args.length >= 2) {
            try {
                height = Integer.parseInt(args[0]);
                width = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                System.out.println("Argumentos inválidos, usando valores por defecto.");
                height = DEFAULT_MATRIX_HEIGHT;
                width = DEFAULT_MATRIX_WIDTH;
            }
        }

        char[][] matrix = new char[height][width];
        initRandom(matrix);
        printMatrix(matrix);
        boolean isAllDead = isAllDead(matrix);
        boolean isSameMatrix = false;
        while (!isAllDead && !isSameMatrix) {
            char[][] newMatrix = new char[matrix.length][matrix[0].length];
            initEmpty(newMatrix);
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    int[] result = checkCells(matrix, i, j);
                    newMatrix[i][j] = nextState(result, matrix[i][j]);
                }
            }
            isAllDead = isAllDead(newMatrix);
            isSameMatrix = isSameMatrix(matrix, newMatrix);
            matrix = newMatrix;
            Thread.sleep(150);
            printMatrix(matrix);
        }
        System.out.println("SE HA TERMINADO LA VIDA!");
    }

    static boolean isSameMatrix(char[][] matrix, char[][] newMatrix) {
        if(matrix.length != newMatrix.length || matrix[0].length != newMatrix[0].length)
            return false;

        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] != newMatrix[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    static boolean isAllDead(char[][] matrix) {
        for(char[] chars : matrix) {
            for(char c: chars) {
                if (c == ALIVE) return false;
            }
        }
        return true;
    }

    static void initEmpty(char[][] matrix) {
        for (char[] chars : matrix) {
            Arrays.fill(chars, DEAD);
        }
    }

    static void initRandom(char[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = (Math.random() < DEAD_ALIVE_RATIO) ? DEAD : ALIVE;
            }
        }
    }

    static void printMatrix(char[][] matrix) {
        // ANSI escape code to clear the console and move the cursor to the top
        System.out.print("\033[H\033[2J");
        System.out.flush();

        for (char[] chars : matrix) {
            for (char c : chars) {
                System.out.print(c);
            }
            System.out.println();
        }
        System.out.println("-".repeat(matrix[0].length));
    }

    static int[] checkCells(char[][] matrix, int row, int col) {
        int[] result = new int[]{0, 0}; // [dead, alive]
        char c = matrix[row][col];
        char right;
        if (col + 1 <= matrix[0].length - 1) {
            right = matrix[row][col + 1];
            result[right == ALIVE ? 1 : 0]++;
        }

        char left;
        if (col - 1 >= 0) {
            left = matrix[row][col - 1];
            result[left == ALIVE ? 1 : 0]++;
        }

        char above;
        if (row - 1 >= 0) {
            above = matrix[row - 1][col];
            result[above == ALIVE ? 1 : 0]++;
        }

        char below;
        if (row + 1 <= matrix.length - 1) {
            below = matrix[row + 1][col];
            result[below == ALIVE ? 1 : 0]++;
        }

        char topLeft;
        if (row - 1 >= 0 && col - 1 >= 0) {
            topLeft = matrix[row - 1][col - 1];
            result[topLeft == ALIVE ? 1 : 0]++;
        }

        char bottomLeft;
        if (row + 1 <= matrix.length - 1 && col - 1 >= 0) {
            bottomLeft = matrix[row + 1][col - 1];
            result[bottomLeft == ALIVE ? 1 : 0]++;
        }

        char topRight;
        if (row - 1 >= 0 && col + 1 <= matrix[0].length - 1) {
            topRight = matrix[row - 1][col + 1];
            result[topRight == ALIVE ? 1 : 0]++;
        }

        char bottomRight;
        if (row + 1 <= matrix.length - 1 && col + 1 <= matrix[0].length - 1) {
            bottomRight = matrix[row + 1][col + 1];
            result[bottomRight == ALIVE ? 1 : 0]++;
        }

        return result;
    }

    static char nextState(int[] result, char cell) {
        int deadCells = result[0];
        int aliveCells = result[1];
        //System.out.println("dead: " + deadCells + ", alive: " + aliveCells + ", suma: " + (deadCells+aliveCells));
        if (cell == ALIVE) {
            return switch (aliveCells) {
                case 2, 3 -> ALIVE;
                default -> DEAD;
            };
        } else {
            return aliveCells == 3 ? ALIVE : DEAD;
        }
    }
}
