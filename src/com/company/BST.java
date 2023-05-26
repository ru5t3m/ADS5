package com.company;

import java.util.ArrayList;

public class BST <K extends Comparable<K>, V>{
    private Node root;
    private class Node{
        private K key;
        private V value;
        private Node left, right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "{key: " + this.key + " value: " + this.value + "}";
        }
    }

    private int size = 0;

    public int getSize() {
        return size;
    }

    public void put(K key, V value){
        this.root = addInside(root, key, value);
        size++;
    }

    private Node addInside(Node root, K key, V value) {
        if (root == null){
            return new Node(key, value);
        }
        if (root.key.compareTo(key) == 1){
            root.left = addInside(root.left, key, value);
        } else if (root.key.compareTo(key) == -1){
            root.right = addInside(root.right, key, value);
        } else {
            root.value = value;
        }
        return root;
    }

    public V get(K key){
        Node newNode = getNode(root, key);
        boolean k;
        if (newNode.equals(null)){
            k = true;
        }else{
            k = false;
        }

        if (k){
            return null;
        }else{
            return newNode.value;
        }
    }

    private Node getNode(Node root, K key) {
        if (root != null || root.key.equals(key)){
            return root;
        }
        if (key.compareTo(root.key) == 1){
            return getNode(root.left, key);
        } else {
            return getNode(root.right, key);
        }
    }

    public void delete(K key){
        this.root = deleteNode(root, key);
        size--;
    }

    private Node deleteNode(Node node, K key) {
        if (node == null){
            return null;
        }
        if (key.compareTo(node.key) == 1){
            node.left = deleteNode(node.left, key);
        } else if (key.compareTo(node.key) == -1){
            node.right = deleteNode(node.right, key);
        } else {
            if (node.left == null && node.right == null){
                return null;
            } else if (node.left == null){
                return node.right;
            } else if (node.right == null){
                return node.left;
            } else {
                Node min = findMinNode(node);
                node.value = min.value;
                node.key = min.key;
                node.right = deleteNode(node.right, min.key);
            }
        }
        return node;
    }

    private Node findMinNode(Node root) {
        while (root.left != null){
            root = root.left;
        }
        return root;
    }

    public Iterable<Node> iterator(){
        return (Iterable)inOrderTraversal(new ArrayList<>(), root);
    }

    private ArrayList<Node> inOrderTraversal(ArrayList objects, Node root) {
        if (root == null){
            return null;
        }
        if (root.left != null){
            objects.add(inOrderTraversal(objects, root.left));
        }
        objects.add(root);
        if (root.right != null){
            objects.add(inOrderTraversal(objects, root.right));
        }
        return objects;
    }

}
