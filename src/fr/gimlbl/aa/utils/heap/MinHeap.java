package fr.gimlbl.aa.utils.heap;

import fr.gimlbl.aa.utils.Pair;

/**
 * A balanced min heap. Each node has a weight that is lower than the weight of its children.
 *
 * @param <V> Type of node values
 */
public class MinHeap<V> implements Heap<V> {
    /**
     * Root weight
     */
    private int weight;
    /**
     * Root value
     */
    private V value;

    private MinHeap<V> left;
    private MinHeap<V> right;

    /**
     * Create an empty min heap.
     */
    public MinHeap() {
        weight = Integer.MAX_VALUE;
    }

    public MinHeap(int weight, V value) {
        this(weight, value, null, null);
    }

    public MinHeap(int weight, V value, MinHeap<V> left, MinHeap<V> right) {
        this.weight = weight;
        this.value = value;
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean isEmpty() {
        return weight == Integer.MAX_VALUE;
    }

    @Override
    public boolean isLeaf() {
        return !isEmpty() && left == null && right == null;
    }

    @Override
    public void insert(int weight, V value) {
        if (isEmpty()) {
            this.weight = weight;
            this.value = value;
        } else if (weight < this.weight) {
            int tmpWeight = this.weight;
            V tmpValue = this.value;

            this.weight = weight;
            this.value = value;

            insert(tmpWeight, tmpValue);
        } else {
            if (balanced()) {
                if (left == null) {
                    left = new MinHeap<>(weight, value);
                } else {
                    left.insert(weight, value);
                }
            } else {
                if (right == null) {
                    right = new MinHeap<>(weight, value);
                } else {
                    right.insert(weight, value);
                }
            }
        }
    }

    @Override
    public int getRootWeight() {
        return weight;
    }

    @Override
    public V getRootValue() {
        return value;
    }

    @Override
    public Pair<Integer, V> extractRoot() {
        if (isEmpty()) {
            return null;
        }

        Pair<Integer, V> result = new Pair<>(weight, value);
        Pair<Integer, V> newRoot = cutLeaf();
        if (!isEmpty()) {
            assert newRoot != null;
            setRoot(newRoot.getFirst(), newRoot.getSecond());
        }

        return result;
    }

    /**
     * Returns true if the heap is balanced, i.e. if the left child and the right child have the same number of nodes.
     */
    private boolean balanced() {
        if (left == null && right == null) {
            return true;
        } else if (right == null) {
            return false;
        } else {
            assert left != null;
            boolean leftBalanced = left.balanced();
            boolean rightBalanced = right.balanced();
            return (leftBalanced && rightBalanced) || (!leftBalanced && !rightBalanced);
        }
    }

    /**
     * Replace the current root by the given root. If the new root has a weight which is greater than left and right
     * child ones, it is moved in order to preserve min-heap property.
     * @param weight Weight of the new root
     * @param value Value of the new root
     */
    private void setRoot(int weight, V value) {
        if (isEmpty() || isLeaf() || (weight <= left.weight && (right == null || weight <= right.weight))) {
            this.weight = weight;
            this.value = value;
        } else {
            if (right == null || left.weight < right.weight) {
                this.weight = left.weight;
                this.value = left.value;
                left.setRoot(weight, value);
            } else {
                this.weight = right.weight;
                this.value = right.value;
                right.setRoot(weight, value);
            }
        }
    }

    /**
     * Extract a leaf.
     * @return Weight and value of the extracted leaf, null if the heap is empty
     */
    private Pair<Integer, V> cutLeaf() {
        if (isEmpty()) {
            return null;
        } else if (isLeaf()) {
            Pair<Integer, V> result = new Pair<>(weight, value);
            weight = Integer.MAX_VALUE;
            value = null;
            return result;
        } else {
            Pair<Integer, V> result;
            MinHeap<V> tmp;
            if (!balanced()) {
                tmp = left;
                if (left.isLeaf()) left = null;
            } else {
                tmp = right;
                if (right.isLeaf()) right = null;
            }
            result = tmp.cutLeaf();
            return result;
        }
    }
}
