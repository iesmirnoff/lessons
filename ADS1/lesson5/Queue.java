package com.iesmirnoff;
import java.util.*;

public class Queue<T>
{
    private final LinkedList<T> buffer;
    public Queue()
    {
        buffer = new LinkedList<>();
    }

    public Queue(T[] array)
    {
        buffer = new LinkedList<>(Arrays.asList(array));
    }

    public void enqueue(T item)
    {
        buffer.addFirst(item);
    }

    public T dequeue()
    {
        if (buffer.isEmpty()) {
            return null;
        }
        return buffer.removeLast();
    }

    public int size()
    {
        return buffer.size();
    }

    public void rotate(int quantity) {
        if (size() == 0) {
            return;
        }
        for (int i = 0; i < quantity; i++) {
            enqueue(dequeue());
        }
    }

}