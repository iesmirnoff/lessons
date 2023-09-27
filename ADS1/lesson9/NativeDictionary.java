package com.iesmirnoff;
import java.lang.reflect.Array;

@SuppressWarnings("rawtypes, unchecked")
class NativeDictionary<T>
{
    public int size;
    public String [] slots;
    public T [] values;

    public NativeDictionary(int sz, Class clazz)
    {
        size = sz;
        slots = new String[size];
        values = (T[]) Array.newInstance(clazz, this.size);
    }

    public int hashFun(String key)
    {
        int hashCode = key.hashCode();
        hashCode = hashCode >= 0 ? hashCode : hashCode * -1;
        return hashCode % size;
    }

    public boolean isKey(String key)
    {
        return find(key) >= 0;
    }

    public void put(String key, T value)
    {
        int index = seekSlot(key);
        if (index >= 0) {
            slots[index] = key;
            values[index] = value;
        }
    }

    public T get(String key)
    {
        int index = find(key);
        if (index >= 0) {
            return values[index];
        }
        return null;
    }

    private int find(String key)
    {
        int indexByHash = hashFun(key);
        if (key.equals(slots[indexByHash])) {
            return indexByHash;
        }
        for (int i = (indexByHash + 1) % size; i != indexByHash; i = (i + 1) % size) {
            if (key.equals(slots[i])) {
                return i;
            }
        }
        return -1;
    }

    private int seekSlot(String key)
    {
        int indexByHash = hashFun(key);
        if (slots[indexByHash] == null || key.equals(slots[indexByHash])) {
            return indexByHash;
        }
        for (int i = (indexByHash + 1) % size; i != indexByHash; i = (i + 1) % size) {
            if (slots[i] == null || key.equals(slots[i])) {
                return i;
            }
        }
        return -1;
    }
}