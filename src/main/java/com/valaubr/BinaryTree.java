
public class BinaryTree implements Interfaces.BinaryTree {

    private Node root;

    private class Node implements Comparable<Integer> {
        Integer value;
        Node left;
        Node right;

        public Node(Integer value) {
            this.value = value;
        }

        @Override
        public int compareTo(Integer param) {
            if (value > param) return -1;
            else if (value < param) return 1;
            else return 0;
        }
    }

    @Override
    public boolean add(Integer value) {
        root = add(root, value);
        return root != null;
    }

    private Node add(Node node, Integer value) {
        if (node == null) {
            return new Node(value);
        }

        switch (node.compareTo(value)) {
            case 1:
                node.right = add(node.right, value);
                break;
            case -1:
                node.left = add(node.left, value);
                break;
            default:
                return node;
        }
        return node;
    }

    /**
     *
     * @param value
     * @return
     */
    @Override
    public boolean delete(Integer value) {
        try {
            root = delete(root, value);
            return true;
        } catch (NullPointerException e) {
            return false;
        }

    }

    private Node delete(Node node, Integer value) {
        switch (node.compareTo(value)) {
            case 1:
                node.right = delete(node.right, value);
                break;
            case -1:
                node.left = delete(node.left, value);
                break;
            default:
                node = checkSituation(node);
                break;
        }
        return node;
    }

    private Node checkSituation(Node node) {
        //Немного поясню, для себя и для Вас
        //как известно в бинарном дереве поиска можетбыть три случая при удалении:
        //1) Нет детей.
        if (node.right == null && node.left == null) {
            node = null;
        }
        //2) есть только один сын.
        else if (node.left != null && node.right == null) {
            node = node.left;
        } else if (node.left == null && node.right != null) {
            node = node.right;
        }
        //3) есть 2 сына.
        else {
            node.value = checkRightNode(node.right).value;
            node.right = delete(node.right, getMin(node.right).value);
        }
        return node;
    }

    private Node checkRightNode(Node node) {
        //Кажется где то я чего то перемудрил, получилось слишком много строк, ну да в принципе, вроде все верно.
        if (node.left == null) {
            return node;
        } else {
            return getMin(node);
        }
        /*P.S. самый левый сын правого сына всегда соответствует условиям бинарного дерева, т.к. он всегда больше
        левого сына "корня" и всегда меньше правого сына корня.*/
    }

    private Node getMin(Node node) {
        if (node.left != null) {
            node = getMin(node);
        }
        return node;
    }

    /**
     *
     * @param value
     * @return
     */
    @Override
    public boolean search(Integer value) {
        return search(root, value);
    }

    private boolean search(Node node, Integer value) {
        if (node == null) {
            return false;
        }

        switch (node.compareTo(value)) {
            case 1:
                return search(node.right, value);
            case -1:
                return search(node.left, value);
            default:
                return true;
        }
    }

    public Integer getFather(Integer value){
        if (getFather(root, value) != null) {
            return getFather(root, value).value;
        } else {
            return null;
        }
    }
    
    public Integer getLeftChild(Integer value){
        if (getLeftChild(root, value) != null){
            return getLeftChild(root, value).value;
        } else {
            return null;
        }
    }
    
    public Integer getRightChild(Integer value) {
        if (getRightChild(root, value) != null){
            return getRightChild(root, value).value;
        } else {
            return null;
        }
    }

    private Node getFather(Node node, Integer value){
        Node returned = null;
        if (root.compareTo(value) == 0 || node == null) {
            return null;
        }
        
        if (node.compareTo(value) > 0 && !node.right.value.equals(value)) {
            returned = getFather(node.right, value);
        } else if (node.compareTo(value) < 0 && !node.left.value.equals(value)) {
            returned = getFather(node.left, value);
        } else if (node.left.value.equals(value) || node.right.value.equals(value)) {
            returned = node;
        }
        return returned;
    }

    private Node getLeftChild(Node node, Integer value){
        Node returned = null;
        if (node == null) {
            return null;
        }

        switch (node.compareTo(value)) {
            case 1:
                returned = getLeftChild(node.right, value);
                break;
            case -1:
                returned = getLeftChild(node.left, value);
                break;
            default:
                returned = node.left;
                break;
        }
        return returned;
    }
    
    private Node getRightChild(Node node, Integer value){
        Node returned = null;
        if (root.compareTo(value) == 0 || node == null) {
            return null;
        }
        
        switch (node.compareTo(value)) {
            case 1:
                returned = getRightChild(node.right, value);
                break;
            case -1:
                returned = getRightChild(node.left, value);
                break;
            default:
                returned = node.right;
                break;
        }
        return returned;
    }
}
