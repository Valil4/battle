package com.battle;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        ArrayList<Integer> arrFirstPlCards = new ArrayList<>();
        arrFirstPlCards.add(0, 5);
        arrFirstPlCards.add(1, 0);
        arrFirstPlCards.add(2, 11);
        arrFirstPlCards.add(3, 4);
        arrFirstPlCards.add(4, 7);
        arrFirstPlCards.add(5, 9);
        arrFirstPlCards.add(6, 2);
        arrFirstPlCards.add(7, 1);
        arrFirstPlCards.add(8, 6);
        arrFirstPlCards.add(9, 3);
        arrFirstPlCards.add(10, 8);
        arrFirstPlCards.add(11, 10);

        ArrayList<Integer> arrSecondPlCards = new ArrayList<>();
        arrSecondPlCards.add(0, 9);
        arrSecondPlCards.add(1, 2);
        arrSecondPlCards.add(2, 0);
        arrSecondPlCards.add(3, 11);
        arrSecondPlCards.add(4, 4);
        arrSecondPlCards.add(5, 5);
        arrSecondPlCards.add(6, 10);
        arrSecondPlCards.add(7, 7);
        arrSecondPlCards.add(8, 1);
        arrSecondPlCards.add(9, 6);
        arrSecondPlCards.add(10, 3);
        arrSecondPlCards.add(11, 8);

        int cardOne, cardTwo, i = 12, currentPlayer = 1, cardValFirst, cardValSec;
        int endSumFirstPl = 0, endSumSecPl = 0;

        Scanner in = new Scanner(System.in);

        do {
            if (currentPlayer == 1) {
                System.out.println("Первый игрок, вытяните карту");
                cardOne = in.nextInt();
                cardValFirst = arrSecondPlCards.get(cardOne - 1);

                cardTwo = 1 + (int) (Math.random() * (arrFirstPlCards.size() - 1));
                cardValSec = arrFirstPlCards.get(cardTwo - 1);
                System.out.println("Игрок 2 вытянул карту");

                if (cardValFirst > cardValSec)
                    endSumSecPl = endSumSecPl + (cardValFirst - cardValSec);

                System.out.println("\nВскрываем карты:");
                System.out.println("Первый игрок вытянул карту: " + cardValFirst);
                System.out.println("Второй игрок вытянул карту: " + cardValSec);

                System.out.println("\n" + "-- Переход кода --" + "\n");

                currentPlayer = 2;
            } else {
                cardTwo = 1 + (int) (Math.random() * (arrFirstPlCards.size() - 1));
                cardValSec = arrFirstPlCards.get(cardTwo - 1);
                System.out.println("Игрок 2 вытянул карту");

                System.out.println("Первый игрок, вытяните карту");
                cardOne = in.nextInt();
                cardValFirst = arrSecondPlCards.get(cardOne - 1);

                if (cardValSec > cardValFirst)
                    endSumFirstPl = endSumFirstPl + (cardValSec - cardValFirst);

                System.out.println("\nВскрываем карты:");
                System.out.println("Второй игрок вытянул карту: " + cardValSec);
                System.out.println("Первый игрок вытянул карту: " + cardValFirst);

                System.out.println("\n" + "-- Переход кода --" + "\n");

                currentPlayer = 1;
            }

            
            arrFirstPlCards.remove(cardTwo - 1);
            arrSecondPlCards.remove(cardOne - 1);

            i--;
        } while (i != 0);

        checkResult(endSumFirstPl, endSumSecPl);

        in.close();
    }

    private static void checkResult(int endSumFirstPl, int endSumSecPl) {
        if (endSumFirstPl > endSumSecPl)
            System.out.println("Победил второй игрок с " + endSumSecPl + " баллами");
        else if (endSumSecPl > endSumFirstPl)
            System.out.println("Победил первый игрок с " + endSumFirstPl + " баллами");
        else
            System.out.println("Ничья");
    }
}
