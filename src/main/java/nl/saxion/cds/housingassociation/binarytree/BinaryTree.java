package nl.saxion.cds.housingassociation.binarytree;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class BinaryTree {
    private PriorityQueue<Node> topNodes = new PriorityQueue<>();
    public static class Node implements Comparable<Node> {
        public int value;
        Node left, right;

        public Node(int value){
            this.value = value;
            left = null;
            right = null;
        }

        @Override
        public int compareTo(Node o) {
            return o.value - value;
        }
    }

    public void insert(Node node, int value) {
        if (value < node.value) { if (node.left != null) { insert(node.left, value); } else { System.out.println(" Inserted " + value + " to left of " + node.value); node.left = new Node(value); } } else if (value > node.value) {
            if (node.right != null) {
                insert(node.right, value);
            } else {
                System.out.println("  Inserted " + value + " to right of "
                        + node.value);
                node.right = new Node(value);
            }
        }
    }

    private Node deleteRecursive(Node current, int value) {
        if (current == null) {
            return null;
        }

        if (value == current.value) {
            // Case 1: no children
            if (current.left == null && current.right == null) {
                return null;
            }

            // Case 2: only 1 child
            if (current.right == null) {
                return current.left;
            }

            if (current.left == null) {
                return current.right;
            }

            // Case 3: 2 children
            int smallestValue = findSmallestValue(current.right);
            current.value = smallestValue;
            current.right = deleteRecursive(current.right, smallestValue);
            return current;
        }
        if (value < current.value) {
            current.left = deleteRecursive(current.left, value);
            return current;
        }

        current.right = deleteRecursive(current.right, value);
        return current;
    }

    private int findSmallestValue(Node root) {
        return root.left == null ? root.value : findSmallestValue(root.left);
    }

    public void traverseInOrder(Node node) {
        if (node != null) {
            traverseInOrder(node.left);
            System.out.print(" " + node.value);
            traverseInOrder(node.right);
        }
    }

    //largestElement() will find out the largest node in the binary tree
    public int largestElement(Node root) {
        int leftMax, rightMax;
        //Max will store temp's data
        int max = root.value;

        //It will find largest element in left subtree
        if(root.left != null) {
            leftMax = largestElement(root.left);
            //Compare max with leftMax and store greater value into max
            max = Math.max(max, leftMax);
        }

        //It will find largest element in right subtree
        if(root.right != null) {
            rightMax = largestElement(root.right);
            //Compare max with rightMax and store greater value into max
            max = Math.max(max, rightMax);
        }

        return max;
    }

    public Node largestNode(Node root) {
        Node leftMax, rightMax;
        //Max will store temp's data
        Node max = root;

        //It will find largest element in left subtree
        if(root.left != null) {
            leftMax = largestNode(root.left);
            //Compare max with leftMax and store greater value into max
            if (leftMax.value > max.value) {
                max = leftMax;
            }
        }

        //It will find largest element in right subtree
        if(root.right != null) {
            rightMax = largestNode(root.right);
            //Compare max with rightMax and store greater value into max
            if (rightMax.value > max.value) {
                max = rightMax;
//                System.out.println(rightMax.value);
            }
        }

        return max;
    }

    public List<Integer> largestElements(Node root, int limit) {
        List<Integer> nodes = new ArrayList<>();
        int leftMax, rightMax;
        //Max will store temp's data
        topNodes = new PriorityQueue<>();
        largestNode(root);
//        Collections.sort(topNodes);
        for (Node topNode : topNodes) {
            System.out.println("node: " + topNode.value);
        }

        int count = 0;
        while(nodes.size() < limit) {
            nodes.add(largestElement(root));
            deleteRecursive(root, largestElement(root));
            count++;
        }

        for (Integer node : nodes) {
            System.out.println(node);
        }

        return nodes;
    }

}
