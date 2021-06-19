package com.battle;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class Main {

    public static void main(String[] args) {

        var playerCards = IntStream.range(0, 12).boxed().collect(toList());
        var botCards = new ArrayList<>(playerCards);

        int cardOne;
        int cardTwo;
        int cardValSec;
        int endSumFirstPl = 0;
        int endSumSecPl = 0;
        boolean isPlayerMove = true;

        try (Scanner in = new Scanner(System.in)) {
            while (playerCards.size() != 0) {
                cardTwo = 1 + (int) (Math.random() * (botCards.size() - 1));
                cardValSec = botCards.get(cardTwo - 1);

                if (isPlayerMove) {
                    System.out.println("Первый игрок, введите номинал карты из оставшихся карт: " +
                            playerCards.stream().map(String::valueOf).collect(Collectors.joining(", ")));

                    cardOne = getConsoleValue(playerCards, in);

                    System.out.println("Игрок 2 положил карту");

                    endSumSecPl += compareValues(cardOne, cardValSec);

                    openCards(cardOne, cardValSec);

                    isPlayerMove = false;
                } else {
                    System.out.println("Игрок 2 положил карту");

                    System.out.println("Первый игрок, введите номинал карты из оставшихся карт: " +
                            playerCards.stream().map(String::valueOf).collect(Collectors.joining(", ")));

                    cardOne = getConsoleValue(playerCards, in);

                    endSumFirstPl += compareValues(cardValSec, cardOne);

                    openCards(cardOne, cardValSec);

                    isPlayerMove = true;
                }

                playerCards.remove((Integer) cardOne);
                botCards.remove(cardTwo - 1);
            }

            checkResult(endSumFirstPl, endSumSecPl);
        }
    }

    private static int compareValues(int firstVal, int secondVal) {
        if (firstVal > secondVal)
            return firstVal - secondVal;
        else
            return 0;
    }

    private static void openCards(int cardOne, int cardValSec) {
        System.out.println("\nВскрываем карты:");
        System.out.println("Первый игрок вытянул карту: " + cardOne);
        System.out.println("Второй игрок вытянул карту: " + cardValSec);

        System.out.println("\n" + "-- Переход кода --" + "\n");
    }

    private static int getConsoleValue(List<Integer> playerCards, Scanner in) {

        int inputValue;

        do {
            while (!in.hasNextInt()) {
                System.out.println("Введите число из списка оставшихся!");
                in.next();
            }
            System.out.println("Введите число из списка оставшихся!");
            inputValue = in.nextInt();
        } while (inputValue < 0 || !playerCards.contains(inputValue));

        return inputValue;
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