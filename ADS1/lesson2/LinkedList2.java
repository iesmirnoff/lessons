package com.iesmirnoff;

import java.util.*;

public class LinkedList2
{
    public Node head;
    public Node tail;

    public LinkedList2()
    {
        head = null;
        tail = null;
    }

    public void addInTail(Node _item)
    {
        if (head == null) {
            this.head = _item;
            this.head.next = null;
            this.head.prev = null;
        } else {
            this.tail.next = _item;
            _item.prev = tail;
        }
        this.tail = _item;
    }

    public Node find(int _value)
    {
        // здесь будет ваш код поиска
        Node node = head;
        while (node != null) {
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
        Node node = head;
        while (node != null) {
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
        // only one node in the list and it matches
        if (head == tail) {
            clear();
            return true;
        }
        // many nodes in the list, head matches
        if (node == head) {
            head = head.next;
            head.prev = null;
            return true;
        }
        if (node == tail) {
            tail = tail.prev;
            tail.next = null;
            return true;
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
        head = null;
        tail = null;
    }

    public int count()
    {
        if (head == null) {
            return 0;
        }
        Node node = head;
        int counter = 1;
        while (node.next != null) {
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
            head.prev = _nodeToInsert;
            _nodeToInsert.next = head;
            head = _nodeToInsert;
        } else {
            _nodeToInsert.next = _nodeAfter.next;
            _nodeToInsert.prev = _nodeAfter;
            _nodeAfter.next = _nodeToInsert;
            if (_nodeAfter != tail) {
                _nodeToInsert.next.prev = _nodeToInsert;
            } else {
                tail = _nodeToInsert;
            }
        }
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