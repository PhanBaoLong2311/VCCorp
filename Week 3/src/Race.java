public class Race {
    private int sum1 = 0;
    private int sum2 = 0;

    public void add1(int val1) {
        this.sum1 += val1;
    }

    public void add2(int val2) {
        synchronized(this) {
            this.sum2 += val2;
        }
    }

    public int getSum1() {
        return sum1;
    }

    public int getSum2() {
        return sum2;
    }

    public static void main(String[] args){
        Race race = new Race();

        Thread[] threads1 = new Thread[100];
        for (int i = 0; i < threads1.length; i++) {
            threads1[i] = new Thread(() -> {
                for (int j = 0; j < 10; j++) {
                    race.add1(1);
                }
            });
        }

        Thread[] threads2 = new Thread[100];
        for (int i = 0; i < threads2.length; i++) {
            threads2[i] = new Thread(() -> {
                for (int j = 0; j < 10; j++) {
                    race.add2(1);
                }
            });
        }

        for (Thread thread : threads1) {
            thread.start();
        }
        for (Thread thread : threads2) {
            thread.start();
        }

        for (Thread thread : threads1) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (Thread thread : threads2) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Sum1: " + race.getSum1());
        System.out.println("Sum2: " + race.getSum2());
    }
}
