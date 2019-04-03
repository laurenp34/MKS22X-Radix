public class Node<E> {
  private E data;
  private Node next,prev;

@SuppressWarnings("unchecked")
  public Node(E yum) { // should the next node be part of the constructor?
    data = yum;
    //next = setNext;
    //prev = setPrev;
  }

@SuppressWarnings("unchecked")
  public Node() {

  }

  //NOT SURE IF WE WERE SUPPOSED TO DO THIS
  //this method retroactively sets the next node.
  public void setNext(Node setNext) {
    next = setNext;
  }

  public void setPrev(Node setPrev) {
    prev = setPrev;
  }

  public E getData() {
    return data;
  }

  public E setData(E newData) {
    E oldData = data;

    data = newData;
    return oldData;
  }

  public Node next() {
    return next;
  }

  public Node prev() {
    return prev;
  }

  //
  public boolean hasNext() {
    if (next != null) {
      return true;
    } else {
      return false;
    }
  }

  public String toString() {
    return "" + getData();
  }

  public static void main(String[] args) {
    Node test = new Node(4);
    Node test2 = new Node(2);
    System.out.println(test2.setData(3)); // should print 2
  }
}
