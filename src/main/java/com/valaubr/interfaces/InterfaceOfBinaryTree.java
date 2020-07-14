package com.valaubr.interfaces;

public interface InterfaceOfBinaryTree {
    /**
     * add new value in node or find collision.
     * @param value
     * @return boolean
     */
    boolean add(Integer value);

    /**
     * remove node from tree.
     * @param value
     * @return boolean
     */
    boolean delete(Integer value);

    /**
     * search value in tree.
     * @param value
     * @return boolean
     */
    boolean search(Integer value);
}
