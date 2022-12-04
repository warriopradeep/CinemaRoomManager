package cinema;

import java.util.Arrays;
import java.util.Scanner;

public class Cinema {

    static Scanner in = new Scanner(System.in);
    static int rows;
    static int cols;
    static char[][] cinema;

    static int soldTickets = 0;
    static int totalTickets;
    static int currentIncome = 0;

    static int totalIncome;

    public static void main(String[] args) {
        // Write your code here

        System.out.println("Enter the number of rows:");
        rows = in.nextInt();
        System.out.println("Enter the number of seats in each row:");
        cols = in.nextInt();
        totalTickets = rows * cols;
        totalIncome();

        cinema = new char[rows][cols];

        fillSeats(cinema);

        String msg = """
                    1. Show the seats
                    2. Buy a ticket
                    3. Statistics
                    0. Exit
                    """;

//        printGrid(rows, cols);
        boolean flag = true;

        while (flag) {
            System.out.println();
            System.out.println(msg);
            int choice = in.nextInt();

            switch (choice) {
                case 0 -> flag = false;
                case 1 -> printGrid2(cinema);
                case 2 -> selectSeats();
                case 3 -> statistics();
            }
        }
    }

    private static void markSelectedSeat(char[][] c, int rowN, int colN) {
        c[rowN - 1][colN - 1] = 'B';
//        printGrid2(c);
    }

    static void fillSeats(char[][] s) {

        for (char[] chars : s) {
            Arrays.fill(chars, 'S');
        }

    }

    static void printGrid2(char[][] s) {

        StringBuilder header = new StringBuilder(" ");
        for (int i = 1; i <= s[0].length; i++) {
            header.append(" ").append(i);
        }

        System.out.println("Cinema:");
        System.out.println(header);

        int i = 1;

        for (char[] e : s) {
            System.out.print(i);
            for (char f : e) {
                System.out.print(" " + f);
            }
            System.out.println();
            i++;
        }
    }

    static void selectSeats() {

        int[] rowAndCol = rowCol();
        int rowN = rowAndCol[0];
        int colN = rowAndCol[1];

        System.out.println();

        soldTickets++;

        int ticketPrice;

        if (totalTickets <= 60) {
            ticketPrice = 10;
        } else {
            if (rowN <= rows/2) {
                ticketPrice = 10;
            } else ticketPrice = 8;
        }

        currentIncome += ticketPrice;

        System.out.println();
        System.out.println("Ticket price: $" + ticketPrice);
//        System.out.println();
        markSelectedSeat(cinema, rowN, colN);
    }

    static int[] rowCol() {

        int rowN;
        int colN;
        while (true) {
            System.out.println("Enter a row number:");
            rowN = in.nextInt();
            System.out.println("Enter a seat number in that row:");
            colN = in.nextInt();

            if (rowN < 0 || rowN > rows || colN < 0 || colN > cols) {
                System.out.println("Wrong input!");
            } else if (checkBought(rowN, colN)) {
                System.out.println("That ticket has already been purchased!\n");
            } else break;
        }

        return new int[]{rowN, colN};
    }

    static void statistics() {
        System.out.println("Number of purchased tickets: " + soldTickets);
        float percent = (float) soldTickets / totalTickets * 100;
        System.out.printf("Percentage: %.2f%c", percent,'%');
        System.out.println();
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome);
    }

    static void totalIncome() {

        if (totalTickets <= 60) {
            totalIncome = totalTickets * 10;
        } else {
            totalIncome = (rows / 2) * cols * 10 + (rows - rows / 2) * cols * 8;
        }
    }

    static boolean checkBought(int rowN, int colN) {
        return cinema[rowN - 1][colN - 1] == 'B';
    }
}