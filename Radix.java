public class Radix {

  public void radixsort(int[] data){
    int passes = maxDigits(data);

    for (int i=0;i<passes;i++) {
      //create array of linkedlists.
      MyLinkedList[] digits = new MyLinkedList[10];
      //initialize eachbucket.
      for (int i2=0;i2<10;i2++) {
        digits[i2] = new MyLinkedList();
      }

      //loop through data.
      for (int idx=0;idx<data.length;idx++) {
        //get specific digit of current int.
        int digit = Integer.parseInt( ""+ (""+Math.abs(data[idx])).charAt(i));
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


  //gets nth digit, where one's digit is n=0 (modified to work better with other methods)
  //so getting 3rd digit of 432 would be 0 and 2nd digit would be 4.
  private static int getNthDigit(int num, int n) {
    String s = ""+Math.abs(num);
    //if nth digit doesn't exist (it's 0), add zeroes to s.

    if (s.length() < n) return 0;

    int zeroes = (n-s.length())+1; // represents zeroes to add.

      //loop to add zeroes to start of new string number
      String s2 = "";
      for (int i=0;i<zeroes;i++) {
        s2 += "0";
      }
      s2 += s;

    int idx = s2.length() - n - 1;
    return Integer.parseInt(""+s2.charAt(idx));
  }

  public static void main(String[] args) {
    int[] data = {1,5,88,99,-1000,599999,-6000000};
    System.out.println(maxDigits(data));
    System.out.println(getNthDigit(423,1));
    System.out.println(getNthDigit(4233,3));
    System.out.println(getNthDigit(423,2));
    System.out.println(getNthDigit(444423,4));
  }

}
