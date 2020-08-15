package util;

import com.google.gson.annotations.Expose;
import main.Settings;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;

public class LinkedList<T extends Comparable<T>> implements Serializable {
    private int len = 0;
    @Expose
    private Node<T> head = null;

    //Add an element to the end of the list.
    public void add(T data) {
        Node<T> newElement = new Node<>(data);
        if (this.getHead() == null) {
            this.setHead(newElement);
        } else {
            Node<T> temp = this.getHead();
            while (temp.getNext() != null) {
                temp = temp.next;
            }
            temp.setNext(newElement);
        }
        this.len = this.getLen() + 1;
    }

    public void addFirst(T data) {
        Node<T> newElement = new Node<>(data);
        if (this.getHead() == null) {
            this.setHead(newElement);
        } else {
            newElement.setNext(this.getHead());
            this.setHead(newElement);
        }
        this.len = this.getLen() + 1;
    }

    @Override
    public String toString() {
        if (this.getHead() == null) {
            return "[]";
        }
        StringBuilder string = new StringBuilder().append("[");
        for (int i = 0; i < this.getLen(); i++) {
            string.append(this.getElement(i)).append(", ");
        }
        return string.toString();
    }

    //get an element by index
    public T getElement(int i) {
        if (this.getLen() == 0) {
            Settings.Loggers.LIST.log(Level.INFO, "The list its empty;");
            return null;
        }
        if (i == 0) {
            return this.getHead().getData();
        }
        if (i > (this.getLen() - 1)) {
            throw new IllegalArgumentException("Index out of range, max: " + (this.getLen() - 1) + "given :" + i);
        }

        Node<T> temp = this.getHead();
        int counter = 0;
        while (counter < i) {
            temp = temp.getNext();
            counter++;
        }
        return temp.getData();
    }

    //get an Node given its content
    public T getElement(T data) {
        T result = null;
        if (this.getHead() == null) return null;
        Node<T> temp = this.getHead();
        int counter = 0;
        while (counter < this.getLen()) {
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
    public void delete(T data) throws IOException {
        //only head
        if (this.getLen() == 1) {
            this.setHead(null);
            this.len = 0;
        }
        if (this.getHead() == null)
            return;
        //delete head
        Node<T> temp = this.getHead();
        if (this.getHead().getData().compareTo(data) == 0) {
            this.setHead(this.getHead().next);
            temp.setNext(null);
            this.len = this.getLen() - 1;
        } else {
            int counter = 0;
            while (counter < this.getLen()) {
                if (temp.getNext().getData().compareTo(data) == 0) {
                    temp.setNext(temp.getNext().getNext());
                    this.len = this.getLen() - 1;
                    break;
                }
                temp = temp.getNext();
                counter++;
            }
            if (counter == getLen()) {
                throw new IOException("The element was not found.");
            }
        }
    }

    public int getLen() {
        return len;
    }

    public Node<T> getHead() {
        return head;
    }

    public void setHead(Node<T> head) {
        this.head = head;
    }
}

class Node<T extends Comparable<T>> implements Serializable {

    Node<T> next;
    T data;

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

}



