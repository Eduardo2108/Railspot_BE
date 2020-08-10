package util;

public class LinkedList<T extends Comparable<T>> {

    public int len = 0;
    private Node<T> head = null;
    private Node<T> tail = null;

    //Add an element to the end of the list.
    public void add(T data) {
        Node<T> newElement = new Node<>(data);
        if (this.head == null) {
            this.head = this.tail = newElement;
            this.len++;
        } else {
            this.tail.setNext(newElement);
            newElement.setPrev(this.tail);
            this.tail = newElement;
            this.len++;
        }
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        Node<T> temp = this.head;
        int counter = 0;
        string.append("[ ");
        while (counter < this.len) {
            string.append(temp.getData() + " ");
            temp = temp.getNext();
            counter++;
        }
        string.append("]");
        return string.toString();
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
        if (this.head.getData().compareTo(data) == 0) {
            this.head = this.head.next;
            this.head.getPrev().setNext(null);
            this.head.setPrev(null);
            this.len--;
        } else if (this.tail.getData().compareTo(data) == 0) {
            this.tail = this.tail.getPrev();
            this.tail.setNext(null);
            this.len--;
        } else {
            Node<T> toDelete = this.getNode(data);
            if (toDelete == null) {
                return;
            }
            Node<T> prev = toDelete.getPrev();
            Node<T> next = toDelete.getNext();
            prev.setNext(next);
            next.setPrev(prev);
            toDelete.delete();
            this.len--;
        }
    }

    class Node<T extends Comparable<T>> {
        Node<T> prev = null;
        Node<T> next = null;
        T data = null;

        @Override
        public String toString() {
            return data.toString();
        }

        public Node(T data) {
            this.data = data;
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
}


