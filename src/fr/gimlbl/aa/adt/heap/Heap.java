package fr.gimlbl.aa.adt.heap;

import fr.gimlbl.aa.adt.Pair;

/**
 * A heap. Each node of the heap has a weight (represented as an integer) and a value (of an arbitrary type).
 * This is a recursive data structure.
 *
 * @param <V> Type of values
 */
public interface Heap<V> {
    boolean isEmpty();

    /**
     * Returns true if the root of this heap is a leaf, i.e. there is no left and right child.
     */
    boolean isLeaf();

    /**
     * Insert a node with given weight and value.
     * @param weight Node weight
     * @param value Node value
     */
    void insert(int weight, V value);

    /**
     * Get the weight of root node. Returns {@link Integer#MAX_VALUE} if the heap is empty.
     * @return Root weight
     */
    int getRootWeight();

    /**
     * Get the value of root node.
     * @return Root value, null if the heap is empty
     */
    V getRootValue();

    /**
     * Removes the root node and returns its weight and value.
     * @return A pair containing the weight and the value of the root node, null if the heap is empty
     */
    Pair<Integer, V> extractRoot();
}
