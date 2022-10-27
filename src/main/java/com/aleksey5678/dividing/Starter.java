package com.aleksey5678.dividing;

import java.util.Scanner;

public class Starter {
    public static void main(String[] args) {
        LongDivision dividing = new LongDivision();
        System.out.println("Please press numbers for division :");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press dividend  for division :");
        int dividend = scanner.nextInt();
        System.out.println("Press divider for division :");
        int divider = scanner.nextInt();
        System.out.println(dividing.divide(dividend, divider));
    }
}
