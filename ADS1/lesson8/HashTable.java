package com.iesmirnoff;

public class HashTable
{
    public int size;
    public int step;
    public String [] slots;

    public HashTable(int sz, int stp)
    {
        size = sz;
        step = stp;
        slots = new String[size];
        for(int i=0; i<size; i++) slots[i] = null;
    }

    public int hashFun(String value)
    {
        return value.hashCode() % size;
    }

    public int seekSlot(String value)
    {
        int indexByHash = hashFun(value);
        if (slots[indexByHash] == null) {
            return indexByHash;
        }
        if (size % step == 0 || step % size == 0) {
            for (int shift = 0; shift < step; shift++) {
                for (int i = shift; i < size; i += step) {
                    if (slots[i] == null) {
                        return i;
                    }
                }
            }
        } else {
            for (int i = (indexByHash + step) % size; i != indexByHash; i = (i + step) % size) {
                if (slots[i] == null) {
                    return i;
                }
            }
        }
        return -1;
    }

    public int put(String value)
    {
        int index = seekSlot(value);
        if (index >= 0) {
            slots[index] = value;
        }
        return index;
    }

    public int find(String value)
    {
        if (value == null) {
            for (int i = 0; i < size; i++) {
                if (slots[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (value.equals(slots[i])) {
                    return i;
                }
            }
        }
        return -1;
    }
}