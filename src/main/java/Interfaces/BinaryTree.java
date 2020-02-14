package Interfaces;

public interface BinaryTree {
    public void add(Integer value);

    public void delete(Integer value);

    public boolean search(Integer value);

    public void draw();

    public String[] getNodeAroundCurrent(Integer value);
}
