package days.three;

import utils.FileReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BinaryDiagnostic
{
   public static final String INPUT_FILE_NAME = "day_3.txt";

   public static void main(String[] args) throws IOException
   {
      List<String> numbers = getNumbers(FileReader.getFileFromResourceAsBufferedReader(INPUT_FILE_NAME));
      multiplyGammaRateAndEpsilonRate(numbers);
      multiplyOxygenGeneratorRatingByCO2ScrubberRating(numbers);
   }

   private static void multiplyGammaRateAndEpsilonRate(List<String> numbers)
   {
      int numberOfBits = numbers.get(0).length();
      StringBuilder gammaRate = new StringBuilder();
      StringBuilder epsilonRate = new StringBuilder();

      for (int bitIndex = 0; bitIndex < numberOfBits; bitIndex++)
      {
         char mostFrequentBit = determineMostFrequentBitAtIndex(numbers, bitIndex);
         if (mostFrequentBit == '0')
         {
            gammaRate.append('0');
            epsilonRate.append('1');
         }
         else
         {
            gammaRate.append('1');
            epsilonRate.append('0');
         }
      }

      System.out.println("Part 1 - Product of Gamma Rate and Epsilon Rate: "
            + Integer.parseInt(gammaRate.toString(), 2) * Integer.parseInt(epsilonRate.toString(), 2));
   }

   private static void multiplyOxygenGeneratorRatingByCO2ScrubberRating(List<String> numbers) throws IOException
   {
      String oxygenGeneratorRating = findOxygenGeneratorRating();
      String CO2ScrubberRating = findCO2ScrubberRating();
      System.out.println("Part 2 - Product of Oxygen Generator Rating and CO2 Scrubber Rating: "
            + Integer.parseInt(oxygenGeneratorRating, 2) * Integer.parseInt(CO2ScrubberRating, 2));
   }

   private static String findOxygenGeneratorRating() throws IOException
   {
      List<String> numbers = getNumbers(FileReader.getFileFromResourceAsBufferedReader(INPUT_FILE_NAME));
      int numberOfBits = numbers.get(0).length();
      int bitIndex = 0;

      while (numbers.size() > 1 && bitIndex < numberOfBits)
      {
         int finalBitIndex = bitIndex;
         List<String> numbersToRemove = new ArrayList<>();
         char mostFrequentBit = determineMostFrequentBitAtIndex(numbers, bitIndex);

         numbers.stream()
               .filter(number -> number.charAt(finalBitIndex) != mostFrequentBit)
               .forEach(numbersToRemove::add);
         numbersToRemove.forEach(numbers::remove);

         bitIndex++;
      }
      return numbers.get(0);
   }

   private static String findCO2ScrubberRating() throws IOException
   {
      List<String> numbers = getNumbers(FileReader.getFileFromResourceAsBufferedReader(INPUT_FILE_NAME));
      int numberOfBits = numbers.get(0).length();
      int bitIndex = 0;

      while (numbers.size() > 1 && bitIndex < numberOfBits)
      {
         int finalBitIndex = bitIndex;
         List<String> numbersToRemove = new ArrayList<>();
         char mostFrequentBit = determineMostFrequentBitAtIndex(numbers, bitIndex);

         numbers.stream()
               .filter(number -> number.charAt(finalBitIndex) == mostFrequentBit)
               .forEach(numbersToRemove::add);
         numbersToRemove.forEach(numbers::remove);

         bitIndex++;
      }
      return numbers.get(0);
   }

   private static char determineMostFrequentBitAtIndex(List<String> numbers, int bitIndex)
   {
      int zeroCount = 0;
      int oneCount = 0;
      for (String number : numbers)
      {
         char currentBit = number.charAt(bitIndex);
         switch (currentBit)
         {
            case '0':
               zeroCount++;
               break;
            case '1':
               oneCount++;
               break;
            default:
               break;
         }
      }
      return zeroCount > oneCount ? '0' : '1';
   }

   private static List<String> getNumbers(BufferedReader reader) throws IOException
   {
      List<String> numbers = new ArrayList<>();
      String line;
      while ((line = reader.readLine()) != null)
      {
         numbers.add(line);
      }
      return numbers;
   }
}
