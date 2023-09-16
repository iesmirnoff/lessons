package com.iesmirnoff;
import java.util.*;

public class LinkedList
{
    public Node head;
    public Node tail;

    public LinkedList()
    {
        head = null;
        tail = null;
    }

    public void addInTail(Node item) {
        if (this.head == null)
            this.head = item;
        else
            this.tail.next = item;
        this.tail = item;
    }

    public Node find(int value) {
        Node node = this.head;
        while (node != null) {
            if (node.value == value)
                return node;
            node = node.next;
        }
        return null;
    }

    public ArrayList<Node> findAll(int _value) {
        ArrayList<Node> nodes = new ArrayList<>();
        // здесь будет ваш код поиска всех узлов
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
            return true;
        }
        Node nodeBefore = findNodeBeforeGiven(node);
        // many nodes in the list, tail matches
        if (node == tail) {
            nodeBefore.next = null;
            tail = nodeBefore;
            return true;
        }
        // many nodes in the list, node in the middle matches
        nodeBefore.next = node.next;
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
        // здесь будет ваш код вставки узла после заданного

        // если _nodeAfter = null ,
        // добавьте новый элемент первым в списке
        if (_nodeAfter == null) {
            _nodeToInsert.next = head;
            head = _nodeToInsert;
        } else {
            _nodeToInsert.next = _nodeAfter.next;
            _nodeAfter.next = _nodeToInsert;
        }
        if (_nodeAfter == tail) {
            tail = _nodeToInsert;
        }
    }

    private Node findNodeBeforeGiven(Node given) {
        if (given == null || given == head) {
            return null;
        }
        Node node = head;
        while (node != tail) {
            if (node.next == given) {
                return node;
            }
            node = node.next;
        }
        return null;
    }

}

class Node
{
    public int value;
    public Node next;
    public Node(int _value)
    {
        value = _value;
        next = null;
    }
}
