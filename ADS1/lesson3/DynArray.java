package com.iesmirnoff;


import java.lang.reflect.Array;

@SuppressWarnings("rawtypes, unchecked")
public class DynArray<T>
{
    public T [] array;
    public int count;
    public int capacity;
    Class clazz;

    public static final int MIN_SIZE = 16;
    public static final int INCREASE_FACTOR = 2;
    private static final float DECREASE_FACTOR = 1.5f;
    private static final float DECREASE_THRESHOLD = 0.5f;

    public DynArray(Class clz)
    {
        clazz = clz; // нужен для безопасного приведения типов
        // new DynArray<Integer>(Integer.class);
        makeArray(16);
    }

    public DynArray(Class clz, T[] source)
    {
        clazz = clz; // нужен для безопасного приведения типов
        // new DynArray<Integer>(Integer.class);
        count = source.length;
        capacity = Math.max(source.length, MIN_SIZE);
        array = (T[]) Array.newInstance(clazz, capacity);
        System.arraycopy(source, 0, array, 0, source.length);
    }

    public void makeArray(int new_capacity)
    {
        // array = (T[]) Array.newInstance(this.clazz, new_capacity);
        // ваш код
        if (array == null) {
            array = (T[]) Array.newInstance(clazz, MIN_SIZE);
            capacity = MIN_SIZE;
            count = 0;
        }
        if (array.length == new_capacity) {
            return;
        }
        capacity = Math.max(new_capacity, MIN_SIZE);
        T[] newArray = (T[]) Array.newInstance(clazz, capacity);
        count = Math.min(count, new_capacity);
        System.arraycopy(array, 0, newArray, 0, count);
        array = newArray;
    }

    public T getItem(int index)
    {
        // ваш код
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException();
        }
        return array[index];
    }

    public void append(T itm)
    {
        // ваш код
        if (count == capacity) {
            makeArray(capacity * INCREASE_FACTOR);
        }
        array[count++] = itm;
    }

    public void insert(T itm, int index)
    {
        // ваш код
        if (index < 0 || index > count) {
            throw new IndexOutOfBoundsException();
        }
        if (index == count) {
            append(itm);
            return;
        }
        if (count == capacity) {
            makeArray(capacity * INCREASE_FACTOR);
        }
        System.arraycopy(array, index, array, index + 1, count - index);
        array[index] = itm;
        count++;
    }

    public void remove(int index)
    {
        // ваш код
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException();
        }
        System.arraycopy(array, index + 1, array, index, count - (index + 1));
        count--;
        if (count < capacity * DECREASE_THRESHOLD) {
            int newCapacity = (int) (capacity / DECREASE_FACTOR);
            makeArray(newCapacity);
        }
    }
}
