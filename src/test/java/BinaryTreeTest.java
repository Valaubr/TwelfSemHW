import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BinaryTreeTest {

    @org.junit.jupiter.api.Test
    void delete() {
        BinaryTree noI = new BinaryTree();
        assertDoesNotThrow(() -> noI.delete(20));
        BinaryTree bt = createBinaryTree();
        assertTrue(bt.search(9));
        bt.delete(9);
        assertFalse(bt.search(9));
    }

    @org.junit.jupiter.api.Test
    void search() {
        //давайте считать что тест на поиск сразу тестирует и добавление
        //(логично что без добавления ничего особо и не найдешь)
        BinaryTree bt = createBinaryTree();
        assertTrue(bt.search(6));
        assertTrue(bt.search(4));

        assertFalse(bt.search(1));
    }

    @org.junit.jupiter.api.Test
    void getAround() {
        BinaryTree bt = createBinaryTree();
        String[] s = {"6", "7", "9"};
        assertEquals(Arrays.toString(s), Arrays.toString(bt.getNodeAroundCurrent(8)));
    }

    private BinaryTree createBinaryTree() {
        BinaryTree bt = new BinaryTree();

        bt.add(6);
        bt.add(4);
        bt.add(8);
        bt.add(3);
        bt.add(5);
        bt.add(7);
        bt.add(9);

        return bt;
    }
}