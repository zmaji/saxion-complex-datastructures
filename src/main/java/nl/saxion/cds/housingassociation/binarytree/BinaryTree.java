package nl.saxion.cds.housingassociation.binarytree;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class BinaryTree {

    /** Class is made with the help of
     * https://www.baeldung.com/java-binary-tree,
     * https://www.javatpoint.com/binary-tree-java,
     * https://www.programiz.com/java-programming/examples/binary-tree-implementation,
     * https://www.codexpedia.com/java/java-binary-tree-implementation/,
     * https://www.javatpoint.com/java-program-to-find-the-largest-element-in-a-binary-tree
     * Comments and implementation made by Nils Kimenai and Maurice ten Teije */

    private PriorityQueue<Node> topNodes = new PriorityQueue<>();

    public static class Node implements Comparable<Node> {
        public int value;
        Node left, right;

        public Node(int value){
            this.value = value;
            left = null;
            right = null;
        }

        // Compares two Nodes based on value
        @Override
        public int compareTo(Node o) {
            return o.value - value;
        }
    }

    // Insert a Node to the Tree
    public void insert(Node node, int value) {
        // If the given value is lower than the value of the Node
        if (value < node.value) {
            // If the Node on the left is not empty
            if (node.left != null) {
                // Insert value into the left Node
                insert(node.left, value);
            } else {
                // Else create a new Node on the left of the Node
                System.out.println(" Inserted " + value + " to left of " + node.value);
                node.left = new Node(value);
            }
            // If the given value is higher than the value of the Node
        } else if (value > node.value) {
            // If the Node on the right is not empty
            if (node.right != null) {
                // Insert value into the right Node
                insert(node.right, value);
            } else {
                // Else create a new Node on the right of the Node
                System.out.println("  Inserted " + value + " to right of " + node.value);
                node.right = new Node(value);
            }
        }
    }

    // Deletes a Node from the Tree based on a Node and a value (recursive method)
    private Node deleteRecursive(Node current, int value) {
        if (current == null) {
            return null;
        }


        if (value == current.value) {
            // If the current Node has no children
            if (current.left == null && current.right == null) {
                return null;
            }

            // If the current Node has one child on the left
            if (current.right == null) {
                return current.left;
            }

            // If the current Node has one child on the right
            if (current.left == null) {
                return current.right;
            }

            // If the current Node has two children
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

    // Finds the Node with the smallest value (recursive method)
    private int findSmallestValue(Node root) {
        return root.left == null ? root.value : findSmallestValue(root.left);
    }

    // Prints out all the Nodes of the Tree (recursive method)
    public void traverseInOrder(Node node) {
        if (node != null) {
            traverseInOrder(node.left);
            System.out.print(" " + node.value);
            traverseInOrder(node.right);
        }
    }

    // Finds the largest element on the Tree based on the highest value in two Subtrees
    public int largestElement(Node root) {
        int leftMax, rightMax;
        int max = root.value;

        // Finds the largest element in the left Subtree
        if(root.left != null) {
            leftMax = largestElement(root.left);
            // Compare the value of the Root with the value of the biggest element in the left Subtree
            max = Math.max(max, leftMax);
        }

        // Finds the largest element in the right Subtree
        if(root.right != null) {
            rightMax = largestElement(root.right);
            // Compare the value of the Root with the value of the biggest element in the right Subtree
            max = Math.max(max, rightMax);
        }

        return max;
    }


    // Finds the largest Node on the Tree based on the highest value in two Subtrees
    public Node largestNode(Node root) {
        Node leftMax, rightMax;
        Node max = root;

        // Finds the largest Node in the left Subtree
        if(root.left != null) {
            leftMax = largestNode(root.left);
            // Compare the value of the Root with the value of the biggest element in the left Subtree
            if (leftMax.value > max.value) {
                max = leftMax;
            }
        }

        // Finds the largest element in the right Subtree
        if(root.right != null) {
            rightMax = largestNode(root.right);
            // Compare the value of the Root with the value of the biggest element in the right Subtree
            if (rightMax.value > max.value) {
                max = rightMax;
            }
        }

        return max;
    }

    // Create a List with largestElements up to a given limit
    public List<Integer> largestElements(Node root, int limit) {

        // Initialise a new List for Nodes
        List<Integer> nodes = new ArrayList<>();

        // Initialise a new Priority Queue for top Nodes
        topNodes = new PriorityQueue<>();

        // Look for the largest Node from the Root
        largestNode(root);

        // Print every Node with their value in the topNodes Priority Queue
        for (Node topNode : topNodes) {
            System.out.println("node: " + topNode.value);
        }

        int count = 0;
        // While the sized of the Nodes List is lower than the given limit
        while(nodes.size() < limit) {
            // Add the Node with the largest value to the list
            nodes.add(largestElement(root));
            // Delete the Node from the Tree
            deleteRecursive(root, largestElement(root));
            count++;
        }

        // Print every Node from the Node List
        for (Integer node : nodes) {
            System.out.println(node);
        }

        // Return the Node List
        return nodes;
    }

}
