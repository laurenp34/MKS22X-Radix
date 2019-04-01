public class Radix {

  public void radixsort(int[] data){
    int passes = maxDigits(data);

    for (int i=0;i<passes;i++) {
      MyLinkedList[] digits = new MyLinkedList[10];
      for (int i2=0;i2<10;i2++) {
        digits[i2] = new MyLinkedList();
      }
    }
  }

  private static int maxDigits(int[] data) {
    int max = 0;
    for (int i: data) {
      if (Math.abs(i) > max) max = Math.abs(i);
    }
    String maxS = ""+max;
    return maxS.length();
  }


  public static void main(String[] args) {
    int[] data = {1,5,88,99,-1000,599999,-6000000};
    System.out.println(maxDigits(data));
  }

}
