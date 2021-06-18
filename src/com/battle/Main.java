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

        int cardOne;
        int cardTwo;
        int cardValSec;
        int endSumFirstPl = 0;
        int endSumSecPl = 0;
        boolean isPlayerMove = true;

        Scanner in = new Scanner(System.in);

        while (playerCards.size() != 0) {
            if (isPlayerMove) {
                System.out.print("Первый игрок, введите номинал карты из оставшихся карт: ");
                System.out.println(playerCards);

                cardOne = getConsoleValue(playerCards, in);

                cardTwo = 1 + (int) (Math.random() * (botCards.size() - 1));
                cardValSec = botCards.get(cardTwo - 1);
                System.out.println("Игрок 2 положил карту");

                if (cardOne > cardValSec)
                    endSumSecPl = endSumSecPl + (cardOne - cardValSec);

                openCards(cardOne, cardValSec);

                isPlayerMove = false;
            } else {
                cardTwo = 1 + (int) (Math.random() * (botCards.size() - 1));
                cardValSec = botCards.get(cardTwo - 1);
                System.out.println("Игрок 2 положил карту");

                System.out.print("Первый игрок, введите номинал карты из оставшихся карт: ");
                System.out.println(playerCards);

                cardOne = getConsoleValue(playerCards, in);

                if (cardValSec > cardOne)
                    endSumFirstPl = endSumFirstPl + (cardValSec - cardOne);

                openCards(cardOne, cardValSec);

                isPlayerMove = true;
            }

            playerCards.remove((Integer) cardOne);
            botCards.remove(cardTwo - 1);
        }

        checkResult(endSumFirstPl, endSumSecPl);

        in.close();
    }

    private static void openCards(int cardOne, int cardValSec) {
        System.out.println("\nВскрываем карты:");
        System.out.println("Первый игрок вытянул карту: " + cardOne);
        System.out.println("Второй игрок вытянул карту: " + cardValSec);

        System.out.println("\n" + "-- Переход кода --" + "\n");
    }

    private static int getConsoleValue(List<Integer> playerCards, Scanner in) {

        int inputValue = 0;
        boolean errorInput = true;

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