package com.battle;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class Main {

    public static void main(String[] args) {

        var playerCards = IntStream.range(0,12).boxed().collect(toList());
        var botCards = new ArrayList<>(playerCards);

        int cardOne, cardTwo, i = 12, cardValFirst, cardValSec;
        int endSumFirstPl = 0, endSumSecPl = 0;
        boolean isPlayerMove = true;

        Scanner in = new Scanner(System.in);

        do {
            if (isPlayerMove ) {
                System.out.print("Первый игрок, введите номинал карты из оставшихся карт: ");
                playerCards.forEach(a -> System.out.print(a + " "));
                System.out.println();

                cardOne = in.nextInt();
                cardValFirst = playerCards.get(playerCards.indexOf(cardOne));

                cardTwo = 1 + (int) (Math.random() * (botCards.size() - 1));
                cardValSec = botCards.get(cardTwo - 1);
                System.out.println("Игрок 2 положил карту");

                if (cardValFirst > cardValSec)
                    endSumSecPl = endSumSecPl + (cardValFirst - cardValSec);

                System.out.println("\nВскрываем карты:");
                System.out.println("Первый игрок вытянул карту: " + cardValFirst);
                System.out.println("Второй игрок вытянул карту: " + cardValSec);

                System.out.println("\n" + "-- Переход кода --" + "\n");

                isPlayerMove = false;
            } else {
                cardTwo = 1 + (int) (Math.random() * (botCards.size() - 1));
                cardValSec = botCards.get(cardTwo - 1);
                System.out.println("Игрок 2 положил карту");

                System.out.print("Первый игрок, введите номинал карты из оставшихся карт: ");
                playerCards.forEach(a -> System.out.print(a + " "));
                System.out.println();

                cardOne = in.nextInt();
                cardValFirst = playerCards.get(playerCards.indexOf(cardOne));

                if (cardValSec > cardValFirst)
                    endSumFirstPl = endSumFirstPl + (cardValSec - cardValFirst);

                System.out.println("\nВскрываем карты:");
                System.out.println("Второй игрок вытянул карту: " + cardValSec);
                System.out.println("Первый игрок вытянул карту: " + cardValFirst);

                System.out.println("\n" + "-- Переход кода --" + "\n");

                isPlayerMove = true;
            }

            playerCards.remove(playerCards.indexOf(cardOne));
            botCards.remove(cardTwo - 1);

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