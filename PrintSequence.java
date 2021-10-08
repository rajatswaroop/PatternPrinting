public class PrintSequence {
    /*

Thread t1 -> Print multiple of 1
Thread t2 -> Print multiple of 2
Thread t3 -> Print multiple of 3
     */
    private int number = 1;
    private int numberOfThreads;
    private int totalNumbersInSequence;

    public PrintSequence(int numberOfThreads, int totalNumbersInSequence) {
        super();
        this.numberOfThreads = numberOfThreads;
        this.totalNumbersInSequence = totalNumbersInSequence;
    }

    public void sequence(int result) {
        synchronized (this) {
            while (number < totalNumbersInSequence-1) {
                while (number % numberOfThreads != result) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println(Thread.currentThread().getName()+" "+number);
                number++;
                notifyAll();
            }
        }
    }
}


 class PrintSequenceService implements Runnable{

     private PrintSequence printSequence;
     private int result;

     public PrintSequenceService(PrintSequence printSequence, int result) {
         super();
         this.printSequence = printSequence;
         this.result = result;
     }

     @Override
     public void run() {
         printSequence.sequence(result);
     }
 }

class SequenceNumberGeneratorTest {

    private static final int TOTAL_NUMBER_IN_SEQUENCE = 10;
    private static final int TOTAL_NUMBER_OF_THREADS = 3;

    public static void main(String[] args) {

        PrintSequence numbersGenerator = new PrintSequence(TOTAL_NUMBER_OF_THREADS, TOTAL_NUMBER_IN_SEQUENCE);

        //Created three Threads
        //So in the Thread constructor , we do pass the object of the (class which implements the Runnable interface).
        //So when we do thread.start() -> automatically executes the run method.
        //we can also pass the Thread name as a 2nd parameter in Thread constructor.


        Thread t1 = new Thread(new PrintSequenceService(numbersGenerator, 1), "Thread-1");
        Thread t2 = new Thread(new PrintSequenceService(numbersGenerator, 2), "Thread-2");
        Thread t3 = new Thread(new PrintSequenceService(numbersGenerator, 0), "Thread-3");

        //Start all three threads
        t1.start();
        t2.start();
        t3.start();

    }

}
