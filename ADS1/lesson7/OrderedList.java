package com.iesmirnoff;

import java.util.*;


class Node<T>
{
    public T value;
    public Node<T> next, prev;

    public Node(T _value)
    {
        value = _value;
        next = null;
        prev = null;
    }
}

public class OrderedList<T>
{
    public Node<T> head, tail;
    private boolean _ascending;

    public OrderedList(boolean asc)
    {
        head = null;
        tail = null;
        _ascending = asc;
    }

    public int compare(T v1, T v2)
    {
        if (v1 instanceof String && v2 instanceof String) {
            return ((String) v1).trim().compareTo(((String) v2).trim());
        }

        if (v1 instanceof Byte && v2 instanceof Byte) {
            Byte val1 = (Byte) v1;
            Byte val2 = (Byte) v2;
            return val1.compareTo(val2);
        }

        if (v1 instanceof Short && v2 instanceof Short) {
            Short val1 = (Short) v1;
            Short val2 = (Short) v2;
            return val1.compareTo(val2);
        }

        if (v1 instanceof Integer && v2 instanceof Integer) {
            Integer val1 = (Integer) v1;
            Integer val2 = (Integer) v2;
            return val1.compareTo(val2);
        }

        if (v1 instanceof Long && v2 instanceof Long) {
            Long val1 = (Long) v1;
            Long val2 = (Long) v2;
            return val1.compareTo(val2);
        }

        if (v1 instanceof Float && v2 instanceof Float) {
            Float val1 = (Float) v1;
            Float val2 = (Float) v2;
            return val1.compareTo(val2);
        }

        if (v1 instanceof Double && v2 instanceof Double) {
            Double val1 = (Double) v1;
            Double val2 = (Double) v2;
            return val1.compareTo(val2);
        }
        return 0;
    }

    public void add(T value)
    {
        for (Node<T> n = head; n != null; n = n.next) {
            if (_ascending) {
                if (compare(value, n.value) < 1) {
                    insertBefore(n, new Node<>(value));
                    return;
                }
            } else {
                if (compare(n.value, value) < 1) {
                    insertBefore(n, new Node<>(value));
                    return;
                }
            }
        }
        insertBefore(null, new Node<>(value));
    }

    public Node<T> find(T val)
    {
        for (Node<T> n = head; n != null ; n = n.next) {
            if (val.equals(n.value)) {
                return n;
            }
            if (_ascending && compare(val, n.value) < 0 || !_ascending && compare(val, n.value) > 0) {
                return null;
            }
        }
        return null;
    }

    public void delete(T val)
    {
        Node<T> node = find(val);
        // empty list or no matching nodes
        if (node == null) {
            return;
        }
        // only one node in the list and it matches
        if (head == tail) {
            clear(_ascending);
            return;
        }
        // many nodes in the list, head matches
        if (node == head) {
            head = head.next;
            head.prev = null;
            return;
        }
        if (node == tail) {
            tail = tail.prev;
            tail.next = null;
            return;
        }
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public void clear(boolean asc)
    {
        _ascending = asc;
        head = null;
        tail = null;
    }

    public int count()
    {
        int counter = 0;
        for (Node<T> n = head; n != null; n = n.next) {
            counter++;
        }
        return counter;
    }

    ArrayList<Node<T>> getAll()
    {
        ArrayList<Node<T>> r = new ArrayList<Node<T>>();
        Node<T> node = head;
        while(node != null)
        {
            r.add(node);
            node = node.next;
        }
        return r;
    }

    private void insertBefore(Node<T> _nodeBefore, Node<T> _nodeToInsert)
    {
        if (tail == null) {
            head = _nodeToInsert;
            tail = _nodeToInsert;
            return;
        }
        if (_nodeBefore == null) {
            tail.next = _nodeToInsert;
            _nodeToInsert.prev = tail;
            tail = _nodeToInsert;
        } else {
            _nodeToInsert.next = _nodeBefore;
            _nodeToInsert.prev = _nodeBefore.prev;
            _nodeBefore.prev = _nodeToInsert;
            if (_nodeBefore != head) {
                _nodeToInsert.prev.next = _nodeToInsert;
            } else {
                head = _nodeToInsert;
            }
        }
    }
}