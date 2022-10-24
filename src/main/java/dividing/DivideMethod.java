package dividing;

public class DivideMethod {
  private static final String LINE_FEED = "\n";
  private static final Character LINE_FEED_CHAR = '\n';
  private static final String EMPTY = "";
  private static final int ADDITIONAL_SPACE_IN_LINE = 2;
  private static final char CHAR_SPACE = ' ';
  private static final char CHAR_DASH = '-';
  private static final String UNDERSCORE = "_";
  private static final String VERTICAL_LINE = "│";
  private static final int ONE = 1;
  private static final int indexPositionOfDivisor = 0;
  private static final int indexPositionOfLineSpliter = 1;
  private static final int indexPositionOfQuotient = 2;

  private final StringBuilder result = new StringBuilder();
  private final StringBuilder quotient = new StringBuilder();
  private final StringBuilder partOfDividend = new StringBuilder();

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
    int partOfDividendNumber;
    int resultOfDividing; // partOfDividendNumber/divisor
    int mod; // remainder of the partOfDividendNumber

    for (int indexOfDigitInArray = 0; indexOfDigitInArray < digits.length; indexOfDigitInArray++) {

      partOfDividend.append(digits[indexOfDigitInArray]);
      partOfDividendNumber = Integer.parseInt(partOfDividend.toString());

      if (partOfDividendNumber >= divisor) {

        mod = partOfDividendNumber % divisor;
        resultOfDividing = partOfDividendNumber;
// Formating a String result in output if partOfDividendNumber >= divisor
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

        quotient.append(partOfDividendNumber / divisor);

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
    modifyResultView(dividend, divisor);
    return result.toString();
  }

  public String createDivisor(Integer multiplyResult, Integer tab) {
    return assemblyString(tab, CHAR_SPACE)
        + assemblyString(calculateDigits(multiplyResult), CHAR_DASH);
  }

  private int calculateDigits(int number) {
    return (int) Math.log10(number) + ONE;
  }

  private String assemblyString(int numberOfSymbols, char symbol) {
    return String.valueOf(symbol).repeat(Math.max(0, numberOfSymbols));
  }

  private void modifyResultView(Integer dividend, Integer divisor) {
    Positions positions = getPositions();

    int tab = calculateDigits(dividend) + ONE - positions.getPositionOfDivisor();
    result.insert(
        positions.getPositionOfQuotient(),
        assemblyString(tab, CHAR_SPACE) + VERTICAL_LINE + quotient);
    result.insert(
        positions.getPositionOfLineSpliter(),
        assemblyString(tab, CHAR_SPACE)
            + VERTICAL_LINE
            + assemblyString(quotient.length(), CHAR_DASH));
    result.insert(positions.getPositionOfDivisor(), VERTICAL_LINE + divisor);
    result.replace(1, positions.getPositionOfDivisor(), dividend.toString());
  }

  private Positions getPositions() {
    int[] index = new int[3];
    for (int indexOfDigitInArray = 0, numberOfDigitInArray = 0;
        indexOfDigitInArray < result.length();
        indexOfDigitInArray++) {
      if (result.charAt(indexOfDigitInArray) == LINE_FEED_CHAR) {
        index[numberOfDigitInArray] = indexOfDigitInArray;
        numberOfDigitInArray++;
      }
      if (numberOfDigitInArray == 3) {
        break;
      }
    }
    return new Positions(
        index[indexPositionOfDivisor],
        index[indexPositionOfLineSpliter],
        index[indexPositionOfQuotient]);
  }
}
