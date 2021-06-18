package com.battle;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class Main {

    public static void main(String[] args) {

        var playerCards = IntStream.range(0, 12).boxed().collect(toList());
        var botCards = new ArrayList<>(playerCards);

        int cardOne = 0, cardTwo, cardValSec;
        int endSumFirstPl = 0, endSumSecPl = 0;
        boolean isPlayerMove = true, errorInput;

        Scanner in = new Scanner(System.in);

        do {
            if (isPlayerMove) {
                System.out.print("Первый игрок, введите номинал карты из оставшихся карт: ");
                playerCards.forEach(a -> System.out.print(a + " "));
                System.out.println();

                cardOne = getConsoleValue(true, playerCards, in);

                cardTwo = 1 + (int) (Math.random() * (botCards.size() - 1));
                cardValSec = botCards.get(cardTwo - 1);
                System.out.println("Игрок 2 положил карту");

                if (cardOne > cardValSec)
                    endSumSecPl = endSumSecPl + (cardOne - cardValSec);

                System.out.println("\nВскрываем карты:");
                System.out.println("Первый игрок вытянул карту: " + cardOne);
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

                cardOne = getConsoleValue(true, playerCards, in);

                if (cardValSec > cardOne)
                    endSumFirstPl = endSumFirstPl + (cardValSec - cardOne);

                System.out.println("\nВскрываем карты:");
                System.out.println("Второй игрок вытянул карту: " + cardValSec);
                System.out.println("Первый игрок вытянул карту: " + cardOne);

                System.out.println("\n" + "-- Переход кода --" + "\n");

                isPlayerMove = true;
            }

            playerCards.remove((Integer) cardOne);
            botCards.remove(cardTwo - 1);

        } while (playerCards.size() != 0);

        checkResult(endSumFirstPl, endSumSecPl);

        in.close();
    }

    private static int getConsoleValue(boolean errorInput, List<Integer> playerCards, Scanner in) {

        int inputValue = 0;

        while (errorInput) {
            try {
                if (in.hasNextInt()) {
                    inputValue = in.nextInt();
                    if (inputValue < 0 || !playerCards.contains(inputValue)) {
                        System.out.println("Введите число из списка оставшихся!");
                        continue;
                    }
                } else {
                    in.next();
                    System.out.println("Введите число из списка оставшихся!");
                    continue;
                }
                errorInput = false;
            } catch (InputMismatchException ime) {
                System.out.println("Введите число из списка оставшихся!");
            }
        }
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