public class PrintPattern {
    /*
    1
    2 6
    3 7 10
    4 8 11 13
    5 9 12 14 15

The above pattern has differences between the first and 2nd in this format.
    4
    4 3
    4 3 2
    4 3 2 1
     */

    public static void main(String [] args)
    {
        for(int i=0; i<5; i++){
            int value = i+1;
            int decr = 4;
            for(int j=1; j<=i+1; j++){
                if(j!=1){
                    value = value + decr;
                    System.out.print(" "+value);
                    decr--;
                }
                else{
                    System.out.print(" "+value);
                }
            }
            System.out.println(" ");
        }
    }
}
