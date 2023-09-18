package com.iesmirnoff;
import java.util.*;

public class Stack<T>
{
    private final LinkedList<T> buffer;

    public Stack()
    {
        // инициализация внутреннего хранилища стека
        buffer = new LinkedList<>();
    }

    public Stack(T[] array)
    {
        // инициализация внутреннего хранилища стека
        buffer = new LinkedList<>(Arrays.asList(array));
    }

    public int size()
    {
        // размер текущего стека
        return buffer.size();
    }

    public T pop()
    {
        // ваш код
        if (buffer.isEmpty()) {
            return null;
        }
        return buffer.removeFirst();
    }

    public void push(T val)
    {
        // ваш код
        buffer.push(val);
    }

    public T peek()
    {
        // ваш код
        return buffer.peek();
    }
}