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

        if (v1 instanceof Byte val1 && v2 instanceof Byte val2) {
            return val1.compareTo(val2);
        }

        if (v1 instanceof Short val1 && v2 instanceof Short val2) {
            return val1.compareTo(val2);
        }

        if (v1 instanceof Integer val1 && v2 instanceof Integer val2) {
            return val1.compareTo(val2);
        }

        if (v1 instanceof Long val1 && v2 instanceof Long val2) {
            return val1.compareTo(val2);
        }

        if (v1 instanceof Float val1 && v2 instanceof Float val2) {
            return val1.compareTo(val2);
        }

        if (v1 instanceof Double val1 && v2 instanceof Double val2) {
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
        ArrayList<Node<T>> r = new ArrayList<>();
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