package com.lalaalal.sheep.util;

class Node<T> {
    public final T data;
    public Node<T> parent;
    public Node<T> left;
    public Node<T> right;

    public Node(T data) {
        this.data = data;
    }

    public boolean isRoot() {
        return parent == null;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }
}
