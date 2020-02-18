package matrixmultiplication;

import java.io.BufferedReader;

import java.io.FileReader;
import java.util.Scanner;

public class Main {

    public static int matris1[][];
    public static int matris2[][];
    public static int sonuc[][];
    public static int rows1;
    public static int columns1;
    public static int rows2;
    public static int columns2;
    public static MultiplyThread[][] threads;

    public static void main(String args[]) throws Exception {
        Scanner sc1 = new Scanner(new BufferedReader(new FileReader("C:\\Users\\ozgen\\Documents\\NetBeansProjects\\MatrixMultiplication\\src\\matrixmultiplication\\Matris1.txt")));
        int rows1 = 0;
        int columns1 = 0;
        while (sc1.hasNextLine()) {
            columns1 = sc1.nextLine().trim().split(" ").length;
            rows1++;
        }
        int matris1[][] = new int[rows1][columns1];
        
        Scanner sc2 = new Scanner(new BufferedReader(new FileReader("C:\\Users\\ozgen\\Documents\\NetBeansProjects\\MatrixMultiplication\\src\\matrixmultiplication\\Matris1.txt")));
        while (sc2.hasNextLine()) {
            for (int i = 0; i < rows1; i++) {
                String[] line = sc2.nextLine().trim().split(" ");
                for (int j = 0; j < columns1; j++) {
                    
                    matris1[i][j] = Integer.parseInt(line[j]);
                }
            }
        }
        
        
        Scanner sc3 = new Scanner(new BufferedReader(new FileReader("C:\\Users\\ozgen\\Documents\\NetBeansProjects\\MatrixMultiplication\\src\\matrixmultiplication\\Matris2.txt")));
        int rows2 = 0;
        int columns2 = 0;
        while (sc3.hasNextLine()) {
            columns2 = sc3.nextLine().trim().split(" ").length;
            rows2++;
        }
        int matris2[][] = new int[rows2][columns2];
        Scanner sc4 = new Scanner(new BufferedReader(new FileReader("C:\\Users\\ozgen\\Documents\\NetBeansProjects\\MatrixMultiplication\\src\\matrixmultiplication\\Matris2.txt")));
        while (sc4.hasNextLine()) {
            for (int i = 0; i < rows2; i++) {
                String[] line = sc4.nextLine().trim().split(" ");
                for (int j = 0; j < columns2; j++) {
                    matris2[i][j] = Integer.parseInt(line[j]);
                }
            }
        }
        
        
        sonuc = new int[rows1][columns2];
        threads = new MultiplyThread[rows1][columns2];
        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < columns2; j++) {               
                MultiplyThread thread = new MultiplyThread(i, j, matris1,matris2,sonuc);             
                threads[i][j] = thread;
                threads[i][j].start();
                
            }
        }

        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < columns2; j++) {
                threads[i][j].join();
                
            }
        }

        System.out.println("Result: ");
        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < columns2; j++) {
                System.out.print(sonuc[i][j] + " ");
            }
            System.out.println();
        }
        
    }

    private static class MultiplyThread extends Thread {

        private final int row;
        private final int col;
        private int[][] sonuc;
        private int[][] matris1;
        private int[][] matris2;
        
        

        private MultiplyThread(int row, int col, int[][] matris1, int[][] matris2, int[][] sonuc) {
            this.row = row;
            this.col = col;
            this.matris1 = matris1;
            this.matris2 = matris2;
            this.sonuc = sonuc;
        }
        
        @Override
        public void run() {
           
            int sum=0;
            
            for (int i = 0; i < matris2.length; i++) {
                sum = sum+(matris1[row][i] * matris2[i][col]);
            }
            sonuc[row][col] = sum;    
             
        }
    }
}
