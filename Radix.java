import java.io.*;
import java.util.*;

public class Radix {

  public static void radixsort(int[] data){
    int passes = maxDigits(data);

      //create array of linkedlists.
      MyLinkedList<Integer>[] digits = new MyLinkedList[10];
      //initialize eachbucket.
      for (int i2=0;i2<10;i2++) {
        digits[i2] = new MyLinkedList<Integer>();
      }

      //loop through data.
      for (int idx=0;idx<data.length;idx++) {
        int element = data[idx];
        //get specific digit of current int.
        int digit = getNthDigit(element,0);
        //if positive, add to end of bucket.
        if (element > 0) digits[digit].add(element);
        //if negative, add to beginning of bucket.
        else digits[digit].add(0,element);

        //System.out.println(digits[digit]);
      }

      /*
      // PRINTING BUCKETS
      for (int i=0;i<10;i++) {
        System.out.println(i+": "+digits[i]);
      }*/


      //now add elements back to data in new order
      MyLinkedList<Integer>  out = new MyLinkedList<Integer>();
      for (int idx=0;idx<10;idx++) {
        if (! digits[idx].equals(null)) out.extend(digits[idx]);
      }
      //System.out.println(digits[0]);

      if (passes > 1) data = radix(out,1,passes);

  }


  //helper method, where i is the pass #.
  private static int[] radix(MyLinkedList<Integer> data, int i, int passes) {
    //System.out.println(data);
    //base case: i == passes, then copy LL to int[] to return
    if (i==passes) {
      int[] out = new int[data.size()];
      data.resetCur();
      for (int l=0;l<data.size();l++) {
        out[l] = data.getNext();
      }
      return out;
     }

    //create array of linkedlists. (buckets)
    MyLinkedList<Integer>[] digits = new MyLinkedList[10];
    //initialize eachbucket.
    for (int i2=0;i2<10;i2++) {
      digits[i2] = new MyLinkedList<Integer>();
    }

    //loop through data.
    data.resetCur();
    for (int idx=0;idx<data.size();idx++) {

      int element = data.getNext();
      //get specific digit of current int.
      int digit = getNthDigit(element,i);

      //if positive, add to end of bucket.
      if (element > 0) digits[digit].add(element);
      //if negative, add to beginning of bucket.
      else digits[digit].add(0,element);
    }
    /*
    for (int ii=0;ii<10;ii++) {
      System.out.println(ii+": "+digits[ii]);
    }*/



      //now add elements back to data in new order
      MyLinkedList<Integer> out = new MyLinkedList<Integer>();

      //if this is the last pass, start at sIdx and move any negatives to front.
      if (i+ 1==passes) {

        if (! digits[0].equals(null)) out.extend(digits[0]);
        for (int idx=1;idx<10;idx++) {
            //loop through each bucket to add to 0
            while (digits[idx].size() > 0) {
              int element = digits[idx].remove(0);
              if (element > 0) out.add(element);
              else out.add(0,element);
            }

        }
      } else {
        //link the lists together
        for (int idx=0;idx<10;idx++) {
          //System.out.println(digits[idx]);
          if (! digits[idx].equals(null)) out.extend(digits[idx]);
        }
      }

      //regardless, call recursion
      return radix(out,i+1,passes);

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
    radixsort(data);
    System.out.println(Arrays.toString(data));
    /*
    System.out.println(maxDigits(data));
    System.out.println(getNthDigit(423,1));
    System.out.println(getNthDigit(4233,3));
    System.out.println(getNthDigit(423,2));
    System.out.println(getNthDigit(444423,4));*/
  }

}
