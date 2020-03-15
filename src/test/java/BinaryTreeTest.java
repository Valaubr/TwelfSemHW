
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author valaubr
 */
public class BinaryTreeTest {
    /**
     * Test of add method, of class BinaryTree.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        BinaryTree instance = createBinaryTree();
        assertTrue(instance.add(Integer.MIN_VALUE));
    }

    /**
     * Test of delete method, of class BinaryTree.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        BinaryTree instance = createBinaryTree();
        instance.add(2);
        instance.add(8);
        instance.add(45);
        
        assertTrue(instance.delete(2));
        assertTrue(instance.delete(8));
        assertTrue(instance.delete(45));
        assertFalse(instance.delete(49));
        
    }

    /**
     * Test of search method, of class BinaryTree.
     */
    @Test
    public void testSearch() {
        System.out.println("search");
        BinaryTree instance = createBinaryTree();
        assertTrue(instance.search(6));
        assertFalse(instance.search(100));
    }

    /**
     * Test of getFather method, of class BinaryTree.
     */
    @Test
    public void testGetFather() {
        System.out.println("getFather");
        BinaryTree instance = createBinaryTree();
        assertEquals(6, instance.getFather(8).intValue());
        assertNull(instance.getFather(6));
    }

    /**
     * Test of getLeftChild method, of class BinaryTree.
     */
    @Test
    public void testGetLeftChild() {
        System.out.println("getLeftChild");
        BinaryTree instance = createBinaryTree();
        assertEquals(4 ,instance.getLeftChild(6).intValue());
        assertEquals(7 ,instance.getLeftChild(8).intValue());
        assertEquals(3 ,instance.getLeftChild(4).intValue());
        assertNull(instance.getLeftChild(3));
        assertNull(instance.getLeftChild(5));
        assertNull(instance.getLeftChild(7));
    }

    /**
     * Test of getRightChild method, of class BinaryTree.
     */
    @Test
    public void testGetRightChild() {
        System.out.println("getRightChild");
        BinaryTree instance = createBinaryTree();
        assertEquals(5 ,instance.getRightChild(4).intValue());
        assertEquals(9 ,instance.getRightChild(8).intValue());
        assertNull(instance.getRightChild(9));
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
