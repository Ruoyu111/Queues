import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private int n; // size of the deque
    private final Node first, last; // sentinal nodes

    // helper doubly linked list class
    private class Node {
        private Item item;
        private Node previous;
        private Node next;
    }

    // construct an empty deque
    public Deque() {
        n = 0;
        first = new Node();
        last = new Node();
        first.next = last;
        last.previous = first;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the deque
    public int size() {
        return n;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null)
            throw new IllegalArgumentException("Input is null.");
        Node oldFirst = first.next;
        Node newComer = new Node();
        newComer.item = item;
        newComer.previous = first;
        newComer.next = oldFirst;
        first.next = newComer;
        oldFirst.previous = newComer;
        n++;
    }

    // add the item to the end
    public void addLast(Item item) {
        if (item == null)
            throw new IllegalArgumentException("Input is null.");
        Node oldLast = last.previous;
        Node newComer = new Node();
        newComer.item = item;
        newComer.previous = oldLast;
        newComer.next = last;
        last.previous = newComer;
        oldLast.next = newComer;
        n++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException("Deque underflow.");
        Node oldFirst = first.next;
        Item item = oldFirst.item;
        first.next = oldFirst.next;
        oldFirst.next.previous = first;
        n--;
        return item;
    }

    // remove and return the item from the end
    public Item removeLast() {
        if (isEmpty())
            throw new NoSuchElementException("Deque underflow.");
        Node oldLast = last.previous;
        Item item = oldLast.item;
        last.previous = oldLast.previous;
        oldLast.previous.next = last;
        n--;
        return item;
    }

    // return an iterator over items in order from front to end
    @Override
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first.next;

        @Override
        public boolean hasNext() {
            return current != last;
        }

        @Override
        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

}
