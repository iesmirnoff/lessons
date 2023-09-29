package com.iesmirnoff;

public class PowerSet
{

    private final int size = 20000;

    private int quantity = 0;

    public final String [] slots;

    public PowerSet()
    {
        // ваша реализация хранилища
        slots = new String[size];
    }

    public int size()
    {
        return quantity;
    }


    public void put(String value)
    {
        int index = seekValueIndex(value);
        if (index < 0) {
            index = hashFun(value);
        }
        slots[index] = value;
        quantity++;
    }

    public boolean get(String value)
    {
        return seekValueIndex(value) >= 0;
    }

    public boolean remove(String value)
    {
        int index = seekValueIndex(value);
        if (index >= 0) {
            slots[index] = null;
            quantity--;
            return true;
        }
        return false;
    }

    public PowerSet intersection(PowerSet set2)
    {
        PowerSet newSet = new PowerSet();
        for (int i = 0; i < size; i++) {
            String value = slots[i];
            if (value != null && set2.get(value)) {
                newSet.put(value);
            }
        }
        return newSet;
    }

    public PowerSet union(PowerSet set2)
    {
        PowerSet newSet = new PowerSet();
        for (int i = 0; i < size; i++) {
            String value = slots[i];
            if (value != null) {
                newSet.put(value);
            }
        }
        for (int i = 0; i < set2.size; i++) {
            String value = set2.slots[i];
            if (value != null) {
                newSet.put(value);
            }
        }
        return newSet;
    }

    public PowerSet difference(PowerSet set2)
    {
        PowerSet newSet = new PowerSet();
        for (int i = 0; i < size; i++) {
            String value = slots[i];
            if (value != null && !set2.get(value)) {
                newSet.put(value);
            }
        }
        return newSet;
    }

    public boolean isSubset(PowerSet set2)
    {
        // возвращает true, если set2 есть
        // подмножество текущего множества,
        // иначе false
        for (int i = 0; i < set2.size; i++) {
            String value = set2.slots[i];
            if (value != null && !get(value)) {
                return false;
            }
        }
        return true;
    }

    private int seekValueIndex(String value)
    {
        int indexByHash = hashFun(value);
        if (value.equals(slots[indexByHash])) {
            return indexByHash;
        }
        for (int i = (indexByHash + 1) % size; i != indexByHash; i = (i + 1) % size) {
            if (value.equals(slots[i])) {
                return i;
            }
        }
        return -1;
    }

    private int hashFun(String value)
    {
        int hashCode = value.hashCode();
        hashCode = hashCode >= 0 ? hashCode : hashCode * -1;
        return hashCode % size;
    }
}