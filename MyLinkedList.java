import java.util.*;
import java.io.*;

public class MyLinkedList {
 private int size;
 private Node start,end,current;

 public MyLinkedList(Node first) {
   start = first;
   end = first; //????
   size = 1;
   current = first;
  }

  public MyLinkedList(int value) {
    Node firstNode = new Node(value);
    start = firstNode;
    end = firstNode;
    current = firstNode;
  }

  public MyLinkedList() {
  }

  public int size() {
   return size;
  }

//THIS METHOD ADDS THE VALUE TO THE END.
  public boolean add(Integer value) {
    //need some way to connect the previous node to the new one.
    //i think we would need a method in the Node class that could alter the next node.
    Node toAdd = new Node(value);

  //special case: adding the value when the list is empty.
  //this block decides if it is empty.
    if (start == null && end == null) {
      start = toAdd;
      current = start;

    } else {

      toAdd.setPrev(end); // setting the element to add's prev to the last node.
      end.setNext(toAdd); // setting the last node's next element to the node to add.

    }

    end = toAdd;

    size ++;
    return true;
  }


  public String toString(){

    if (size() == 0) {
      return "[]";
    }

    Node current = start;
    String output = "[";

    //boolean cont = true;

    while (current.hasNext()) { // the loop works out perfectly to include everything but the last node.
      // then, this ensures that you won't have an extra comma at the end.
      output += current.getData();
      output += ", ";
      current = current.next();
    }
    //because the loop doesn't include the last node, we add it ourselves (w/o comma!)
    output += end.getData();
    return output += "]";
  }



  private Node getNthNode(int n) {

    if (n >= size() || n < 0) {
      throw new IndexOutOfBoundsException("Index " + n + " is out of bounds.");
    }

    Node result = start;
    int index = 1;

    while (index <= n) {
      result = result.next();
      index ++;
    }

    return result;

  }



  public Integer get(int index) {

    if (index >= size() || index < 0) {
      throw new IndexOutOfBoundsException("Index " + index + " is out of bounds.");
    }

    return getNthNode(index).getData();

  }


  public Integer set(int index,Integer value) {

    if (index >= size() || index < 0) {
      throw new IndexOutOfBoundsException("Index " + index + " is out of bounds.");
    }

    Node indexNode = getNthNode(index);

    Integer oldValue = indexNode.getData();

    indexNode.setData(value);

    return oldValue;

  }

  public boolean contains(Integer value) {

    for (int i=0;i<size;i++) {

      if (get(i).equals(value)) {
        return true;
      }

    }

    return false;
  }

  public int indexOf(Integer value) {

    for (int i=0;i<size;i++) {

      if (get(i).equals(value)) {
        return i;
      }
    }
    return -1;
  }

public void add(int index,Integer value) {


  if (index > size() || index < 0) {
    throw new IndexOutOfBoundsException("Index " + index + " is out of bounds.");
  }


  Node toAdd = new Node(value);

  if (index == 0) {
    if (size > 0) {
      Node atIndex = getNthNode(index);
      start = toAdd;
      start.setNext(atIndex);
    } else {
      start = toAdd;
    }
  } else if (index == size()) { // adding to last index.
    add(value);

  }  else { // inthis case there is a current value and a previous value at that index.

    Node atIndex = getNthNode(index);
    //Node previous = getNthNode(index-1);
    Node previous = atIndex.prev();

    previous.setNext(toAdd);
    toAdd.setNext(atIndex);

    toAdd.setPrev(previous);
    atIndex.setPrev(toAdd);

  }

  size ++;
}


public Integer remove(int index) {

  if (index >= size() || index < 0) {
    throw new IndexOutOfBoundsException("Index " + index + " is out of bounds.");
  }

  Node current = getNthNode(index);

  if (index == 0) {
    start = getNthNode(1);
    start.setPrev(null); // did this so it wouldn't mess up future loops.

  } else if (index + 1 == size()) {
    end = getNthNode(index-1);
    end.setNext(null);

  } else {

    //Node current = getNthNode(index);
    Node prevNode = current.prev();
    //System.out.println(prevNode == null);
    Node nextNode = current.next();
    //System.out.println(nextNode == null);

    prevNode.setNext(nextNode);
    nextNode.setPrev(prevNode);


  }

  size --;
  return current.getData();

}

public boolean remove(Integer value) {

  int index = indexOf(value);
  if (index == -1) {
    return false; // if value does not appear in list.
  }

  remove(index);
  return true;

}

public void extend(MyLinkedList other){
        //in O(1) runtime, move the elements from other onto the end of this
        //The size of other is reduced to 0
        //The size of this is now the combined sizes of both original lists
    if (other.size() > 0) { // you will only need to add if other is not empty.

      Node otherFirst = other.getNthNode(0);

      if (size() > 0) { // normal procedure.
        end.setNext(otherFirst);
      } else { // if adding to an empty list
        start = otherFirst;
      }

      //this happens no matter what.
      end = other.end;
      size += other.size();

    }

    other.size = 0;
    other.start = null;
    other.end = null;

}

  //this method uses instance variable current to iterate through list.
  public int getNext() {
    int out = current.getData();
    if (current == end) current = start;
    else current = current.next();
    return out;
  }

  public void resetCur() {
    current = start;
  }




  public static void main(String[] args) {

    MyLinkedList teeth = new MyLinkedList();
    System.out.println(teeth);
    teeth.add(1);
    teeth.add(3);
    teeth.add(6);
    System.out.println(teeth);
    //System.out.println(teeth.getNthNode(0));

    //Node n = teeth.getNthNode(0).next();
    //System.out.println(n.next());

    System.out.println(teeth.getNext());
    System.out.println(teeth.getNext());
    System.out.println(teeth.getNext());
    System.out.println(teeth.getNext());
    System.out.println(teeth.getNext());
    System.out.println(teeth.getNext());

  }

}
