
package dividing;

public class DivideMethod {
    private static final String FORMATING_OF="%";
    private static final String STRING="s";
    private static final String LINE_FEED="\n";
    private static final Character LINE_FEED_CHAR='\n';
    private static final String EMPTY = "";
    private static final String DIGIT="d";

    private final StringBuilder result = new StringBuilder();
    private final StringBuilder quotient = new StringBuilder();//частное
    private final StringBuilder partOfDividend = new StringBuilder();//делимое

    public String division(int dividend, int divisor) {

        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);

        if (divisor == 0) {
            throw new IllegalArgumentException("Divisor can't be 0");
        }
        if (dividend < divisor) {
            throw new IllegalArgumentException("Dividend can't be less than divisor");
        }

        String[] digits = String.valueOf(dividend).split(EMPTY);
        int partOfDividendNumber;//делимое(часть от большого числа)
        int resultOfDividing;//число после итерации части делимого/делитель
        int mod;//остаток от деления  части делимого на делитель

        for (int indexOfDigitInArray = 0; indexOfDigitInArray < digits.length; indexOfDigitInArray++) {

            partOfDividend.append(digits[indexOfDigitInArray]);
            partOfDividendNumber = Integer.parseInt(partOfDividend.toString());

            if (partOfDividendNumber >= divisor) {

                mod = partOfDividendNumber % divisor;
                resultOfDividing = partOfDividendNumber;

                String lastReminder = String.format(FORMATING_OF + (indexOfDigitInArray + 2) + STRING, "_" + partOfDividendNumber);
                result.append(lastReminder).append(LINE_FEED);

                String multiply = String.format(FORMATING_OF+ (indexOfDigitInArray + 2) + DIGIT, resultOfDividing);
                result.append(multiply).append(LINE_FEED);

                Integer tab = lastReminder.length() - calculateDigit(resultOfDividing);
                result.append(createDivisor(resultOfDividing, tab)).append(LINE_FEED);

                quotient.append(partOfDividendNumber / divisor);

                partOfDividend.replace(0, partOfDividend.length(), Integer.toString(mod));
                partOfDividendNumber = Integer.parseInt(partOfDividend.toString());

            }
            if (indexOfDigitInArray == digits.length - 1) {
                result.append(String.format(FORMATING_OF + (indexOfDigitInArray + 2) + STRING, partOfDividendNumber)).append(LINE_FEED);
            }
        }
        modifyResultView(dividend, divisor);
        return result.toString();
    }

    private String createDivisor(Integer multiplyResult, Integer tab) {
        return assemblyString(tab, ' ') + assemblyString(calculateDigit(multiplyResult), '-');
    }

    private int calculateDigit(int i) {
        return (int) Math.log10(i) + 1;
    }

    private String assemblyString(int numberOfSymbols, char symbol) {
        return String.valueOf(symbol).repeat(Math.max(0, numberOfSymbols));
    }

    private void modifyResultView(Integer dividend, Integer divisor) {
        int[] index = new int[3];
        for (int i = 0, j = 0; i < result.length(); i++) {
            if (result.charAt(i) == LINE_FEED_CHAR) {
                index[j] = i;
                j++;
            }
            if (j == 3) {
                break;
            }

        }

        int tab = calculateDigit(dividend) + 1 - index[0];
        result.insert(index[2], assemblyString(tab, ' ') + "│" + quotient);
        result.insert(index[1], assemblyString(tab, ' ') + "│" + assemblyString(quotient.length(), '-'));
        result.insert(index[0], "│" + divisor);
        result.replace(1, index[0], dividend.toString());

    }
}
