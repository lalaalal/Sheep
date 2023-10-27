package com.lalaalal.sheep.util;

import java.util.function.Consumer;

public class BinaryTree<T> {
    private Node<T> root;

    public BinaryTree() {

    }

    public BinaryTree(T rootData) {
        root = new Node<>(rootData);
    }

    private BinaryTree(Node<T> root) {
        this.root = root;
    }

    public void addRoot(T data, MovingOption movingOption) {
        Node<T> newNode = new Node<>(data);
        if (root != null) {
            root.parent = newNode;
        }
        if (movingOption == MovingOption.LEFT)
            newNode.left = root;
        else
            newNode.right = root;
        root = newNode;
    }

    public T getLeft() {
        return root.left.data;
    }

    public BinaryTree<T> getLeftSubtree() {
        return new BinaryTree<>(root.left);
    }

    public T getRight() {
        return root.right.data;
    }

    public BinaryTree<T> getRightSubtree() {
        return new BinaryTree<>(root.right);
    }

    public void setLeft(T data) {
        root.left = new Node<>(data);
    }

    public void setLeft(BinaryTree<T> subtree) {
        root.left = subtree.root;
        subtree.root.parent = root;
    }

    public void setRight(T data) {
        root.right = new Node<>(data);
    }

    public void setRight(BinaryTree<T> subtree) {
        root.right = subtree.root;
        subtree.root.parent = root;

    }

    public boolean isEmpty() {
        return root == null;
    }

    public void preorderTraverse(Consumer<T> consumer) {
        preorderTraverse(root, consumer);
    }

    private void preorderTraverse(Node<T> current, Consumer<T> consumer) {
        if (current == null)
            return;

        consumer.accept(current.data);
        preorderTraverse(current.left, consumer);
        preorderTraverse(current.right, consumer);
    }

    public enum MovingOption {
        LEFT, RIGHT
    }
}
