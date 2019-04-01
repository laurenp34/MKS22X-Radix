public class Radix {

  public void radixsort(int[] data){

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
