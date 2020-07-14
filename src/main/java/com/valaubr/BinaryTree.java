package com.valaubr;

import com.valaubr.interfaces.InterfaceOfBinaryTree;

import java.util.Objects;

public class BinaryTree implements InterfaceOfBinaryTree {

    private Node root;

    private static class Node implements Comparable<Integer> {
        private Integer value;
        private Node left;
        private Node right;

        private Node(Integer value) {
            this.value = value;
        }

        @Override
        public int compareTo(Integer param) {
            return param.compareTo(value);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Node)) return false;
            Node node = (Node) o;
            return Objects.equals(hashCode(), node.hashCode());
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }

    @Override
    public boolean add(Integer value) {
        if (search(value)) { //усложняем добавление до 2log2(n) что вообще то не хорошо
            return false; // с другой стороны, сообщаем пользователю что такой элемент есть...
        } else {
            root = add(root, value);
            return true;
        }
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

    @Override
    public boolean delete(Integer value) {
        if (search(value)) {
            delete(root, value);
            return true;
        } else {
            return false;
        }
    }

    private Node delete(Node node, Integer value) {
        switch (node.compareTo(value)) {
            case 1:
                return node.right = delete(node.right, value);
            case -1:
                return node.left = delete(node.left, value);
            default:
                node = pictureSituation(node);
        }
        return node;
    }

    private Node pictureSituation(Node node) {
        //Немного поясню, для себя и для Вас
        //как известно в бинарном дереве поиска можетбыть три случая при удалении:
        //1) Нет детей.
        if (node.right == null && node.left == null) {
            node = null;
        } else if (node.left != null && node.right == null) { //2) есть только один сын.
            node = node.left;
        } else if (node.left == null && node.right != null) {
            node = node.right;
        } else { //3) есть 2 сына.
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
            node = getMin(node.left);
        }
        return node;
    }

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

    public Integer getFather(Integer value) {
        Node father = getFather(root, value);
        return father != null ? father.value : null;
    }

    public Integer getLeftChild(Integer value) {
        Node leftChild = getLeftChild(root, value);
        return leftChild != null ? leftChild.value : null;
    }

    public Integer getRightChild(Integer value) {
        Node rightChild = getRightChild(root, value);
        return rightChild != null ? rightChild.value : null;
    }

    private Node getFather(Node node, Integer value) {
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

    private Node getLeftChild(Node node, Integer value) {
        Node returned;
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

    private Node getRightChild(Node node, Integer value) {
        Node returned;
        if (node == null) {
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