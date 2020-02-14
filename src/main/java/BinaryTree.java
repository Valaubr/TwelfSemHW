public class BinaryTree implements Interfaces.BinaryTree {

    private Node root;

    private class Node implements Comparable<Integer> {
        Integer value;
        Node left;
        Node right;

        public Node(Integer value) {
            this.value = value;
        }

        public int compareTo(Integer param) {
            if (value > param) return -1;
            else if (value < param) return 1;
            else return 0;
        }
    }

    public void add(Integer value) {
        root = add(root, value);
    }

    private Node add(Node node, Integer value) {
        if (node == null) {
            return new Node(value);
        }

        if (node.compareTo(value) == 1) {
            node.right = add(node.right, value);
        } else if (node.compareTo(value) == -1) {
            node.left = add(node.left, value);
        } else {
            return node;
        }
        return node;
    }

    public void delete(Integer value) {
        try {
            root = delete(root, value);
        } catch (NullPointerException e) {
            System.out.println("Попытка удаления в пустом дереве ничего не даст :(");
        }

    }

    private Node delete(Node node, Integer value) {
        if (node.compareTo(value) == 1) {
            node.right = delete(node.right, value);
        } else if (node.compareTo(value) == -1) {
            node.left = delete(node.left, value);
        } else {
            node = checkSituation(node);
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

    public boolean search(Integer value) {
        return search(root, value);
    }

    private boolean search(Node node, Integer value) {
        if (node == null) {
            return false;
        }

        if (node.compareTo(value) == 1) {
            return search(node.right, value);
        } else if (node.compareTo(value) == -1) {
            return search(node.left, value);
        } else {
            return true;
        }
    }

    @Override
    public void draw() {
        draw(root);
    }

    private void draw(Node node) {
        /*- Буду искать в глубину.
         *- Но ты можешь искать в ширину, смотреть по слоям удобнее!
         *- Хочу искать в глубину.*/
        if (node != null) {
            System.out.print(" " + node.value);
            draw(node.left);
            draw(node.right);
        }
    }

    @Override
    public String[] getNodeAroundCurrent(Integer value) {
        return getNodeAroundCurrent(root, value);
    }

    private String[] getNodeAroundCurrent(Node node, Integer value) {
        String[] str = new String[3];
        for (int i = 0; i < 3; i++) {
            str[i] = "";
        }
        if (node == null) {
            return str;
        }

        if (node.compareTo(value) == 1 && !node.right.value.equals(value)) {
            getNodeAroundCurrent(node.right, value);
        } else if (node.compareTo(value) == -1 && !node.left.value.equals(value)) {
            getNodeAroundCurrent(node.left, value);
        } else if (node.left.value.equals(value)) {
            str[0] = node.value.toString();
            if (node.left.left != null) {
                str[1] = node.left.left.value.toString();
            }
            if (node.left.right != null) {
                str[2] = node.left.right.value.toString();
            }
        } else if (node.right.value.equals(value)) {
            str[0] = node.value.toString();
            if (node.right.left != null) {
                str[1] = node.right.left.value.toString();
            }
            if (node.right.right != null) {
                str[2] = node.right.right.value.toString();
            }
        }
        return str;
    }
}
