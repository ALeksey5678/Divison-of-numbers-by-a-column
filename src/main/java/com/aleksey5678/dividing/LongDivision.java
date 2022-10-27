package com.aleksey5678.dividing;

public class LongDivision {
    public static final Character LINE_FEED = '\n';
    private static final String EMPTY = "";
    private static final int ADDITIONAL_SPACE_IN_LINE = 2;
    private static final char SPACE = ' ';
    private static final char DASH = '-';
    private static final String UNDERSCORE = "_";
    private static final String VERTICAL_LINE = "│";
    private static final int ONE = 1;
    private static final int ZERO=0;

    private final StringBuilder result = new StringBuilder();
    private final StringBuilder quotient = new StringBuilder();
    private final StringBuilder partOfDividend = new StringBuilder();

    public String divide(int dividend, int divisor) {
        int dividendAbsolute = Math.abs(dividend);
        int divisorAbsolute = Math.abs(divisor);

        if (divisorAbsolute == 0) {
            throw new IllegalArgumentException("Divisor can't be 0");
        }
        if (dividendAbsolute < divisorAbsolute) {
            throw new IllegalArgumentException("Dividend can't be less than divisor");
        }

        String[] digits = String.valueOf(dividendAbsolute).split(EMPTY);
        int partOfDividendNumber;
        int resultOfDividing; // partOfDividendNumber/divisor
        int mod; // remainder of the partOfDividendNumber

        for (int indexOfDigitInArray = 0; indexOfDigitInArray < digits.length; indexOfDigitInArray++) {

            partOfDividend.append(digits[indexOfDigitInArray]);
            partOfDividendNumber = Integer.parseInt(partOfDividend.toString());

            if (partOfDividendNumber >= divisorAbsolute) {

                mod = partOfDividendNumber % divisorAbsolute;
                resultOfDividing = partOfDividendNumber;
// Format a String result in output if partOfDividendNumber >= divisor
                String lastReminder =
                        String.format(
                                "%" + (indexOfDigitInArray + ADDITIONAL_SPACE_IN_LINE) + "s",
                                UNDERSCORE + partOfDividendNumber);
                result.append(lastReminder).append(LINE_FEED);

                String multiply =
                        String.format(
                                "%" + (indexOfDigitInArray + ADDITIONAL_SPACE_IN_LINE) + "d", resultOfDividing);
                result.append(multiply).append(LINE_FEED);

                Integer tab = lastReminder.length() - calculateDigits(resultOfDividing);
                result.append(createDivisor(resultOfDividing, tab)).append(LINE_FEED);

                quotient.append(partOfDividendNumber / divisorAbsolute);

                partOfDividend.replace(0, partOfDividend.length(), Integer.toString(mod));
                partOfDividendNumber = Integer.parseInt(partOfDividend.toString());
            }
            if (indexOfDigitInArray == digits.length - ONE) {
                result
                        .append(
                                String.format(
                                        "%" + (indexOfDigitInArray + ADDITIONAL_SPACE_IN_LINE) + "s",
                                        partOfDividendNumber))
                        .append(LINE_FEED);
            }
        }
        modifyResultView(dividendAbsolute, divisorAbsolute);
        return result.toString();
    }

    public String createDivisor(Integer multiplyResult, Integer tab) {
        return assembleString(tab, SPACE)
                + assembleString(calculateDigits(multiplyResult), DASH);
    }

    private int calculateDigits(int number) {
        return (int) Math.log10(number) + ONE;
    }

    private String assembleString(int numberOfSymbols, char symbol) {
        return String.valueOf(symbol).repeat(Math.max(ZERO, numberOfSymbols));
    }

    private void modifyResultView(Integer dividend, Integer divisor) {
        Positions positions = Positions.getPositions(result.toString());

        int tab = calculateDigits(dividend) + ONE - positions.getPositionOfDivisor();
        result.insert(
                positions.getPositionOfQuotient(),
                assembleString(tab, SPACE) + VERTICAL_LINE + quotient);
        result.insert(
                positions.getPositionOfLineSplitter(),
                assembleString(tab, SPACE)
                        + VERTICAL_LINE
                        + assembleString(quotient.length(), DASH));
        result.insert(positions.getPositionOfDivisor(), VERTICAL_LINE + divisor);
        result.replace(ONE, positions.getPositionOfDivisor(), dividend.toString());
    }


}
