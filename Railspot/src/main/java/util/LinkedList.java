package util;

import java.io.Serializable;

public class LinkedList<T extends Comparable<T>> implements Serializable {
    public int len = 0;
    private Node<T> head = null;
    private Node<T> tail = null;

    //Add an element to the end of the list.
    public void add(T data) {
        Node<T> newElement = new Node<>(data);
        if (this.head == null) {
            this.head = this.tail = newElement;
        } else {
            this.tail.setNext(newElement);
            this.tail = newElement;
        }
        this.len++;
    }

    public void addFirst(T data) {
        Node<T> newElement = new Node<>(data);
        if (this.head == null) {
            this.head = this.tail = newElement;
        } else {
            newElement.setNext(this.head);
            this.head = newElement;
        }
        this.len++;
    }

    @Override
    public String toString() {
        return "LinkedList{" +
                "len=" + len +
                ", head=" + head +

                '}';
    }

    //get an element by index
    public T getElement(int i) {
        if (i > (this.len - 1)) {
            throw new IllegalArgumentException("Index out of range, max: " + (this.len - 1) + "given :" + i);

        } else if (i == (len - 1)) {
            return this.tail.getData();
        }
        Node<T> temp = this.head;
        int counter = 0;
        while (counter < i) {
            temp = temp.getNext();
            counter++;
        }
        return temp.getData();
    }

    //get an Node given its content
    private Node<T> getNode(T data) {
        Node<T> result = null;
        if (this.head == null) return result;

        Node<T> temp = this.head;
        int counter = 0;
        while (counter < this.len) {
            if (temp.getData().compareTo(data) == 0) {
                result = temp;
                break;
            }
            temp = temp.getNext();
            counter++;
        }
        return result;
    }

    //get an Node given its content
    public T getElement(T data) {
        T result = null;
        if (this.head == null) return result;

        Node<T> temp = this.head;
        int counter = 0;
        while (counter < this.len) {
            if (temp.getData().compareTo(data) == 0) {
                result = temp.data;
                break;
            }
            temp = temp.getNext();
            counter++;
        }
        return result;
    }

    //delete an node
    public void delete(T data) {
        //only head
        if (this.len == 1) {
            this.head = this.tail = null;
            this.len = 0;
        }
        //delete head
        Node<T> temp = this.head;
        if (this.head.getData().compareTo(data) == 0) {
            this.head = this.head.next;
            temp.setNext(null);
            this.len--;
        } else {
            int counter = 0;
            while (counter < this.len) {
                if (temp.getNext().getData().compareTo(data) == 0) {
                    temp.setNext(temp.getNext().getNext());
                    this.len--;
                    break;
                }
                temp = temp.getNext();
                counter++;
            }
        }
    }
}

class Node<T extends Comparable<T>> implements Serializable {
    Node<T> prev = null;
    Node<T> next = null;
    T data = null;

    public Node(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Node{" +
                "next=" + next +
                ", data=" + data +
                '}';
    }

    public Node<T> getPrev() {
        return prev;
    }

    public void setPrev(Node<T> prev) {
        this.prev = prev;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void delete() {
        this.next = this.prev = null;
    }
}



