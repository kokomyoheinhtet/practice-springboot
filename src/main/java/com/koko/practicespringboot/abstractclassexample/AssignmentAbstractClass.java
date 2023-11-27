package com.koko.practicespringboot.abstractclassexample;

abstract class ListItem {
    // write code here
    protected ListItem rightLink;
    protected ListItem leftLink;
    protected Object value;

    public ListItem(Object value) {
        this.value = value;
    }

    public abstract ListItem next();

    public abstract ListItem setNext(ListItem item);

    public abstract ListItem previous();

    public abstract ListItem setPrevious(ListItem item);

    public abstract void compareTo();

    private Object getValue() {
        return value;
    }

    private void setValue(Object value) {
        this.value = value;
    }
}

class Node extends ListItem {

    public Node(Object value) {
        super(value);
    }

    @Override
    public ListItem next() {
        return rightLink;
    }

    @Override
    public ListItem setNext(ListItem item) {
        rightLink = item;
        return rightLink;
    }

    @Override
    public ListItem previous() {
        return leftLink;
    }

    @Override
    public ListItem setPrevious(ListItem item) {
        leftLink = item;
        return leftLink;
    }

    @Override
    public void compareTo() {

    }
}

public class AssignmentAbstractClass {
}
