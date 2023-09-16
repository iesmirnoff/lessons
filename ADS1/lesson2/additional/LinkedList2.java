package com.iesmirnoff;

import java.util.ArrayList;

public class LinkedList2
{
    public Node head;
    public Node tail;

    public LinkedList2()
    {
        head = new Node(0);
        tail = new Node(0);
        head.next = tail;
        tail.prev = head;
    }

    public void addInTail(Node _item)
    {
        _item.prev = tail.prev;
        _item.next = tail;
        tail.prev.next = _item;
        tail.prev = _item;
    }

    public Node find(int _value)
    {
        // здесь будет ваш код поиска
        Node node = head.next;
        while (node != tail) {
            if (node.value == _value) {
                return node;
            }
            node = node.next;
        }
        return null;
    }

    public ArrayList<Node> findAll(int _value)
    {
        ArrayList<Node> nodes = new ArrayList<>();
        // здесь будет ваш код поиска всех узлов по заданному значению
        Node node = head.next;
        while (node != tail) {
            if (node.value == _value) {
                nodes.add(node);
            }
            node = node.next;
        }
        return nodes;
    }

    public boolean remove(int _value)
    {
        // здесь будет ваш код удаления одного узла по заданному значению
        Node node = find(_value);
        // empty list or no matching nodes
        if (node == null) {
            return false;
        }
        node.prev.next = node.next;
        node.next.prev = node.prev;
        return true; // если узел был удалён
    }

    public void removeAll(int _value)
    {
        // здесь будет ваш код удаления всех узлов по заданному значению
        boolean removed = remove(_value);
        while (removed) {
            removed = remove(_value);
        }
    }

    public void clear()
    {
        // здесь будет ваш код очистки всего списка
        head.next = tail;
        tail.prev = head;
    }

    public int count()
    {
        if (head.next == tail) {
            return 0;
        }
        Node node = head.next;
        int counter = 1;
        while (node.next != tail) {
            counter++;
            node = node.next;
        }
        return counter; // здесь будет ваш код подсчёта количества элементов в списке
    }

    public void insertAfter(Node _nodeAfter, Node _nodeToInsert)
    {
        // здесь будет ваш код вставки узла после заданного узла

        // если _nodeAfter = null
        // добавьте новый элемент первым в списке
        if (_nodeAfter == null) {
            _nodeAfter = head;
        }
        _nodeToInsert.prev = _nodeAfter;
        _nodeToInsert.next = _nodeAfter.next;
        _nodeAfter.next.prev = _nodeToInsert;
        _nodeAfter.next = _nodeToInsert;
    }
}

class Node
{
    public int value;
    public Node next;
    public Node prev;

    public Node(int _value)
    {
        value = _value;
        next = null;
        prev = null;
    }
}