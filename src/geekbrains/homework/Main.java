package geekbrains.homework;


public class Main {
    static final int size = 10000000;
    static final int HALF = size / 2;
    static float[] arr = new float[size];
    static float[] arr1 = new float[HALF];
    static float[] arr2 = new float[HALF];

    public static void main(String[] args) {
        System.out.println("First Method: \n");
        method1();
        System.out.println("Second Method: \n");
        method2();
    }

    public static void method1() {
        for (int i = 0; i < size; i++) {
            arr[i] = 1;
        }

        long a = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println(System.currentTimeMillis() - a);
    }

    public static void method2() {
        long a = System.currentTimeMillis();


        new Thread(new thread1()).start();
        new Thread(new thread2()).start();

        System.arraycopy(arr1, 0, arr, 0, HALF);
        System.arraycopy(arr2, 0, arr, HALF, HALF);

        System.out.println(System.currentTimeMillis() - a);
    }

    static class thread1 implements Runnable {
        @Override
        public void run() {

            System.arraycopy(arr, 0, arr1, 0, HALF);

            for (int i = 0; i < HALF; i++) {
                arr1[i] = (float) (arr1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        }
    }

    static class thread2 implements Runnable {
        @Override
        public void run() {

            System.arraycopy(arr, HALF, arr2, 0, HALF);

            for (int i = 0; i < HALF; i++) {
                arr2[i] = (float) (arr2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        }
    }
}



