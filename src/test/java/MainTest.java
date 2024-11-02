import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {
    static final char DEAD = Main.DEAD;
    static final char ALIVE = Main.ALIVE;

    private char[][] matrix;

    @BeforeEach
    void setUp() {
        matrix = new char[][] {
                {DEAD, DEAD, ALIVE, ALIVE},
                {DEAD, ALIVE, DEAD, DEAD},
                {DEAD, DEAD, ALIVE, DEAD},
                {DEAD, DEAD, DEAD, DEAD},
        };
    }

    @Test
    void testIsAllDead_AllDeadCells() {
        char[][] deadMatrix = {
                {DEAD, DEAD, DEAD, DEAD},
                {DEAD, DEAD, DEAD, DEAD},
                {DEAD, DEAD, DEAD, DEAD},
                {DEAD, DEAD, DEAD, DEAD},
        };
        assertTrue(Main.isAllDead(deadMatrix));
    }

    @Test
    void testIsAllDead_HasAliveCells() {
        assertFalse(Main.isAllDead(matrix));
    }

    @Test
    void testIsSameMatrix_SameMatrices() {
        char[][] identicalMatrix = {
                {DEAD, DEAD, ALIVE, ALIVE},
                {DEAD, ALIVE, DEAD, DEAD},
                {DEAD, DEAD, ALIVE, DEAD},
                {DEAD, DEAD, DEAD, DEAD},
        };
        assertTrue(Main.isSameMatrix(matrix, identicalMatrix));
    }

    @Test
    void testIsSameMatrix_DifferentMatrices() {
        char[][] differentMatrix = {
                {DEAD, DEAD, DEAD, ALIVE},
                {DEAD, ALIVE, DEAD, DEAD},
                {DEAD, DEAD, ALIVE, DEAD},
                {DEAD, DEAD, DEAD, DEAD},
        };
        assertFalse(Main.isSameMatrix(matrix, differentMatrix));
    }

    @Test
    void testInitEmpty() {
        char[][] emptyMatrix = new char[4][4];
        Main.initEmpty(emptyMatrix);
        for (char[] row : emptyMatrix) {
            for (char cell : row) {
                assertEquals(DEAD, cell);
            }
        }
    }

    @Test
    void testCheckCells_CenterCell() {
        int[] result = Main.checkCells(matrix, 1, 1); // Célula central en (1,1)
        assertEquals(6, result[0]); // Células muertas alrededor
        assertEquals(2, result[1]); // Células vivas alrededor
    }

    @Test
    void testCheckCells_EdgeCell() {
        int[] result = Main.checkCells(matrix, 0, 0); // Célula de borde en (0,0)
        assertEquals(2, result[0]); // Células muertas alrededor
        assertEquals(1, result[1]); // Células vivas alrededor
    }

    @Test
    void testNextState_CellAliveWithTwoNeighbors() {
        int[] neighbors = {5, 2}; // 5 muertas, 2 vivas
        assertEquals(ALIVE, Main.nextState(neighbors, ALIVE));
    }

    @Test
    void testNextState_CellAliveWithThreeNeighbors() {
        int[] neighbors = {4, 3}; // 4 muertas, 3 vivas
        assertEquals(ALIVE, Main.nextState(neighbors, ALIVE));
    }

    @Test
    void testNextState_CellAliveWithLessThanTwoNeighbors() {
        int[] neighbors = {6, 1}; // 6 muertas, 1 viva
        assertEquals(DEAD, Main.nextState(neighbors, ALIVE));
    }

    @Test
    void testNextState_CellAliveWithMoreThanThreeNeighbors() {
        int[] neighbors = {3, 4}; // 3 muertas, 4 vivas
        assertEquals(DEAD, Main.nextState(neighbors, ALIVE));
    }

    @Test
    void testNextState_CellDeadWithThreeNeighbors() {
        int[] neighbors = {5, 3}; // 5 muertas, 3 vivas
        assertEquals(ALIVE, Main.nextState(neighbors, DEAD));
    }

    @Test
    void testNextState_CellDeadWithLessThanThreeNeighbors() {
        int[] neighbors = {6, 2}; // 6 muertas, 2 vivas
        assertEquals(DEAD, Main.nextState(neighbors, DEAD));
    }
}
