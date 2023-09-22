package com.iesmirnoff;
import java.util.*;

public class Deque<T>
{
    private final LinkedList<T> buffer;

    public Deque()
    {
        buffer = new LinkedList<>();
    }

    public Deque(T[] array)
    {
        buffer = new LinkedList<>(Arrays.asList(array));
    }

    public void addFront(T item)
    {
        buffer.addFirst(item);
    }

    public void addTail(T item)
    {
        buffer.addLast(item);
    }

    public T removeFront()
    {
        try {
            return buffer.removeFirst();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public T removeTail()
    {
        try {
            return buffer.removeLast();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public int size()
    {
        return buffer.size();
    }

    public boolean checkPalindrome() {
        while (size() > 1) {
            T front = removeFront();
            T tail = removeTail();
            try {
                if (!front.equals(tail)) {
                    return false;
                }
            } catch (NullPointerException e) {
                if (front != tail) {
                    return false;
                }
            }
        }
        return true;
    }
}