package util;

import com.google.gson.annotations.Expose;
import main.Settings;

import java.io.Serializable;
import java.util.logging.Level;

public class LinkedList<T extends Comparable<T>> implements Serializable {
    public int len = 0;
    @Expose
    private Node<T> head = null;

    //Add an element to the end of the list.
    public void add(T data) {
        Node<T> newElement = new Node<>(data);
        if (this.head == null) {
            this.head = newElement;
        } else {
            Node<T> temp = this.head;
            while (temp.getNext() != null) {
                temp = temp.next;
            }
            temp.setNext(newElement);
        }
        this.len++;
    }

    public void addFirst(T data) {
        Node<T> newElement = new Node<>(data);
        if (this.head == null) {
            this.head = newElement;
        } else {
            newElement.setNext(this.head);
            this.head = newElement;
        }
        this.len++;
    }

    @Override
    public String toString() {
        if (this.head == null) {
            return "[]";
        }
        StringBuilder string = new StringBuilder().append("[");
        for (int i = 0; i < this.len; i++) {
            string.append(this.getElement(i)).append(", ");
        }
        return string.toString();
    }

    //get an element by index
    public T getElement(int i) {
        if (this.len == 0) {
            Settings.Loggers.LIST.log(Level.INFO, "The list its empty;");
            return null;
        }
        if (i == 0) {
            return this.head.getData();
        }
        if (i > (this.len - 1)) {
            throw new IllegalArgumentException("Index out of range, max: " + (this.len - 1) + "given :" + i);
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
            this.head =  null;
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

    Node<T> next = null;
    T data = null;

    public Node(T data) {
        this.data = data;
        this.next = null;
    }

    @Override
    public String toString() {

        return data.toString();
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
        this.next = null;
    }
}



