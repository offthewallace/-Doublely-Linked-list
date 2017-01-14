

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class is to create a data structure of doubly linked list that each Node
 * has two references to previous and subsequent Node,and this doubly linked
 * list data structure ObjectList class would implement ObjectListInterface.
 *
 * @author Wallace He
 *
 */
public class ObjectList implements ObjectListInterface {

    private Node myHeadNode;
    private int myNumberOfEntries;
    private IteratorForDoublelyLinkedList myIterator;

    /**
     *
     * Default constructor contains no data and no links to other Nodes
     *
     */
    public ObjectList() {
        myNumberOfEntries = 0;
        myHeadNode = null;
        myIterator = new IteratorForDoublelyLinkedList();

    }

    /**
     * Returns the length of the list.
     *
     * @return The number of data entered into the list.
     *
     */
    @Override
    public int getLength() {
        return myNumberOfEntries;
    }

    /**
     * Returns the position of the Current Node in the list.
     *
     * @return the position of the Current Node in the list. Returns 0 if there
     * is an empty list.
     *
     */
    public int getCurrentPosition() {
        return myIterator.myIndex;
    }

    /**
     * Returns the data stored in the current node, after calling, returns null
     * if there is a empty list or returns the object stored in the current
     * Node.
     *
     * @return Object The current Node. Returns null if there is nothing in the
     * list.
     *
     */
    public Object getCurrent() {
        if (myNumberOfEntries == 0) {
            return null;
            //empty list situation 
        }
        return myIterator.myCurrentNode.getData();
    }

    /**
     * Returns the Object stored in the Node in the list after CurrentNode, If
     * at the end of the list, returns the object at the end of the list,
     * returns null if there is a empty list.
     *
     * @return Object in the next node in the list or the last thing stored in
     * the list if in the end. Returns null if there is a empty list
     *
     */
    public Object getNext() {
        if (myIterator.hasNext() && myNumberOfEntries > 0) {

            return myIterator.next();

        } else if (myNumberOfEntries == 0) {
            return null;
            //empty list situation 

        } else {
            return this.getCurrent();
            //last element of list situation
        }
    }

    /**
     * Returns the Object stored in the Node in the list before CurrentNode, if
     * at the first of the list, returns the object at the first of the list,
     * returns null if there is a empty list.
     *
     * @return Object in the next node in the list or the last thing stored in
     * the list if in the end, return null if there is a empty list
     *
     */
    public Object getPrevious() {
        if (myIterator.hasPrevious() && myNumberOfEntries > 0) {

            return myIterator.previous();

        } else if (myNumberOfEntries == 0) {
            return null;
            //empty list situation

        } else {
            return this.getCurrent();
            //first element of list situation
        }
    }

    /**
     * Returns the first object in the list of first Node.
     *
     * @return Object, object stored in the first Node . Return null if there is
     * a empty list.
     *
     */
    public Object getFirst() {
        if (myNumberOfEntries > 0) {
            Object tmp = getCurrent();

            while (myIterator.hasPrevious()) {
                tmp = getPrevious();
            }
            return tmp;

        } else {
            return null;
            //empty list situation
        }
    }

    /**
     * Returns the last object in the list.
     *
     * @return Object stored in the last Node. Returns null if there is a empty
     * list.
     *
     */
    public Object getLast() {
        if (myNumberOfEntries > 0) {
            Object tmp = this.getCurrent();

            while (myIterator.hasNext()) {
                tmp = this.getNext();
            }
            return tmp;

        } else {
            return null;
            //empty list situation
        }
    }

    /**
     * Returns true if add a new Object at the end of the list, If there is an
     * empty list, add the object as the first item.
     *
     * @param newObject The object will be added at the end of the list.
     *
     * @return True when adding the object in the end of list.
     *
     */
    public boolean append(Object newObject) {
        Node tmp = new Node(newObject);

        if (myNumberOfEntries == 0) {
            myHeadNode = tmp;
            myNumberOfEntries = 1;
            myIterator.myCurrentNode = tmp;
            myIterator.myIndex = 1;
            tmp.setNextNode(null);
            return true;
            //empty list situation

        } else {
            getLast();
            myIterator.myCurrentNode.setNextNode(tmp);
            tmp.setPrevNode(myIterator.myCurrentNode);
            myNumberOfEntries++;
            myIterator.next();
            tmp.setNextNode(null);
            return true;
        }
    }

    /**
     * Returns true after adding a new Object in a new node before the current
     * node in the list, or at the start of the list when there is an empty list
     * or the current node is the first element of list.
     *
     * @param newObject The Object being added into the list.
     *
     * @return True when inserting the object into the list.
     *
     */
    public boolean insert(Object newObject) {
        Node tmp = new Node(newObject);
        
        if (getCurrentPosition() == 0) {
            append(newObject);
            //empty list situation

        } else if (getCurrentPosition() == 1) {
            tmp.setNextNode(myIterator.myCurrentNode);
            myIterator.myCurrentNode.setPrevNode(tmp);
            myIterator.myIndex = 2;
            myNumberOfEntries++;
            myIterator.previous();
            myHeadNode = tmp;
            //single element list situation

        } else {
            Node tmp2 = myIterator.myCurrentNode.getPrevNode();
            Node tmp3 = myIterator.myCurrentNode;
            tmp2.setNextNode(tmp);
            tmp.setPrevNode(tmp2);
            tmp.setNextNode(tmp3);
            tmp3.setPrevNode(tmp);
            myIterator.myCurrentNode = tmp;
            myNumberOfEntries++;
        }
        return true;
    }

    /**
     * Returns ture after emptying the list.
     *
     * @return True when the list is empty, false if not.
     *
     */
    public boolean clear() {
        myHeadNode = null;
        myNumberOfEntries = 0;
        myIterator.myIndex = 0;
        return true;
    }

    /**
     * Returns ture if replacing the object in the current node with the
     * parameter newObject
     *
     * returns false if it is an empty list.
     *
     * @param newObject is the object going to replace the object stored in the
     * current node.
     *
     * @return Returns true when replace. Returns false when there is an empty
     * list;
     *
     */
    public boolean replace(Object newObject) {

        if (myNumberOfEntries != 0) {
            myIterator.myCurrentNode.setData(newObject);
            return true;

        } else {
            return false;
            //empty list situation
        }
    }

    /**
     * Returns the removed the current Node in the list, and makes the previous
     * Node into the current Node
     *
     * Returns null if it is empty list.
     *
     * @return The removed object; Returns null if there is empty list;
     *
     */
    public Object remove() {
        Object result = getCurrent();
        
        if (myNumberOfEntries == 0) {
            return null;
            //empty list situation 

        } else if (myNumberOfEntries == 1) {
            clear();
            //single element list situation

        } else if (getCurrentPosition() == 1 && myNumberOfEntries > 1) {
            myHeadNode = myIterator.myCurrentNode.getNextNode();
            myIterator.next();
            myHeadNode.setPrevNode(null);
            myIterator.myIndex = 1;
            myNumberOfEntries--;
            // when the current Node at the first of list 

        } else if (myNumberOfEntries == getCurrentPosition() && myNumberOfEntries > 1) {
            myIterator.previous();
            myIterator.myCurrentNode.setNextNode(null);
            myNumberOfEntries--;
            // when the curent Node at the end of list 

        } else {
            Node tmp1 = myIterator.myCurrentNode.getNextNode();
            Node tmp2 = myIterator.myCurrentNode.getPrevNode();
            myIterator.previous();
            tmp1.setPrevNode(tmp2);
            tmp2.setNextNode(tmp1);
            myNumberOfEntries--;
            //common situation
        }

        return result;
    }

    /**
     * This subclass is used to store the data and the references to the next
     * and previous Node in the chain of Nodes.
     *
     */
    private class Node {

        private Object myData;
        private Node myNextNode, myPreviousNode;

        /**
         * Default constructor contains no data and no links to other Nodes.
         */
        private Node() {
            myData = null;
            myNextNode = null;
            myPreviousNode = null;
        }

        /**
         * Constructor of creating a new Node containing the specified Object.
         *
         * @param T Object to be stored in Node.
         *
         */
        private Node(Object T) {
            myData = T;
            myNextNode = null;
            myPreviousNode = null;
        }

        /**
         * Returns to the next Node pointed to by current Node.
         *
         * @return Returns the next Node
         *
         */
        private Node getNextNode() {
            return myNextNode;
        }

        /**
         * Returns to the previous Node pointed to by current Node.
         *
         * @return Returns the previous Node.
         *
         */
        private Node getPrevNode() {
            return myPreviousNode;
        }

        /**
         * Sets current Node link to the given Node as the next Node.
         *
         * @param nextNode Node to point to the new next Node
         *
         */
        private void setNextNode(Node nextNode) {
            this.myNextNode = nextNode;
        }

        /**
         * Sets current node link to the given node as previous Node.
         *
         * @param Node prevNode to link before the current Node.
         *
         */
        private void setPrevNode(Node prevNode) {
            this.myPreviousNode = prevNode;
        }

        /**
         * Returns the Object stored in the Node
         *
         * @return Object stored in the Node, null if no Object stored in Node.
         *
         */
        private Object getData() {
            return myData;
        }

        /**
         * Sets the Object stored in the Node to the given Object.
         *
         * @param Object newdata to be stored
         *
         */
        private void setData(Object newdata) {
            this.myData = newdata;
        }

    }

    /**
     * Creating an private inner class to keep track of the current position in
     * the List.
     *
     */
    private class IteratorForDoublelyLinkedList {

        private Node myCurrentNode;
        private int myIndex;

        /**
         * Default constructor initializes the value in the myCurrentNode and
         * gives myIndex the value of 0.
         */
        private IteratorForDoublelyLinkedList() {

            myCurrentNode = myHeadNode;
            myIndex = 0;
        }

        /**
         * Check if there is anything in the list after the CurrentNode. Returns
         * true if there is, false if not.
         *
         * @return True if there is something in the list after the current one,
         * false if not.
         *
         */
        private boolean hasNext() {
            return myIndex < myNumberOfEntries;
        }

        /**
         * Check if there is anything in the list before the CurrentNode.
         * Returns true if there is, false if not.
         *
         * @return True if there is something in the list before the current
         * one, false if not.
         *
         */
        private boolean hasPrevious() {
            return myIndex > 1;
        }

        /**
         * Returns the next Object in the list, Iterator move forward, throws an
         * error if there is nothing after the list.
         *
         * @return The data stored in the Node after the current one.
         *
         * @throws NoSuchElementException if there is nothing after the list.
         *
         */
        private Object next() {
            if (hasNext()) {

                myCurrentNode = myCurrentNode.getNextNode();
                Object result = myCurrentNode.getData();

                myIndex++;
                return result;

            } else {
                throw new NoSuchElementException("Nothing in the next");
            }

        }

        /**
         * Returns the previous Object in the list, Iterator move to previous
         * throws an error if there is nothing before the list.
         *
         * @return The data stored in the Node before the current one.
         *
         * @throws NoSuchElementException if there is nothing before the list.
         */
        private Object previous() {
            if (hasPrevious()) {
                myCurrentNode = myCurrentNode.getPrevNode();
                Object result = myCurrentNode.getData();
                myIndex--;
                return result;
            } else {
                throw new NoSuchElementException("Nothing in the previous");
            }

        }

    }
}

