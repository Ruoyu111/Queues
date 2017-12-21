import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] a; // array of items
    private int n; // number of elements on queue
    private int tail; // tail position(enqueue index)

    // construct an empty randomized queue
    public RandomizedQueue() {
        a = (Item[]) new Object[2];
        n = 0;
        tail = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return n;
    }

    // resize the underlying array holding the elements
    private void resize(int capacity) {
        assert capacity >= n;
        if (capacity == 0) capacity = 2;
        Item[] temp = (Item[]) new Object[capacity];
        int i = 0;
        for (Item item : a) {
            if (item != null)
                temp[i++] = item;
        }
        tail = n;
        a = temp;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null)
            throw new IllegalArgumentException("Input is null.");
        if (tail == a.length)
            resize(2 * n);
        a[tail++] = item;
        n++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException("Queue underflow.");
        int randomIndex = randomIndex();
        Item item = a[randomIndex];
        a[randomIndex] = null;
        n--;
        // shrink size of array if necessary
        if (n > 0 && n <= tail / 2)
            resize(2 * n);
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty())
            throw new NoSuchElementException("Queue underflow.");
        int randomIndex = randomIndex();
        return a[randomIndex];
    }

    // find a random not null index
    private int randomIndex() {
        int randomIndex = StdRandom.uniform(tail);
        while (a[randomIndex] == null) {
            randomIndex = StdRandom.uniform(tail);
        }
        return randomIndex;
    }

    @Override
    public Iterator<Item> iterator() {
        return new RqIterator();
    }

    private class RqIterator implements Iterator<Item> {
        private int index;
        private final Item[] copy;

        public RqIterator() {
            copy = (Item[]) new Object[n];
            for (int i = 0, j = 0; i < tail; i++) {
                if (a[i] != null) copy[j++] = a[i];
            }
            StdRandom.shuffle(copy);
            index = 0;
        }

        @Override
        public boolean hasNext() {
            return index < n;
        }

        @Override
        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            return copy[index++];
        }
    }

}
