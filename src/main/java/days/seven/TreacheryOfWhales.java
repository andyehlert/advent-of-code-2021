package days.seven;

import com.sun.deploy.util.StringUtils;
import utils.FileReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TreacheryOfWhales
{
   public static final String INPUT_FILE_NAME = "day_7.txt";

   public static void main(String[] args) throws IOException
   {
      System.out.println("Using data file " + INPUT_FILE_NAME);
      List<Integer> horizontalPositions = readHorizontalPositions(FileReader.getFileFromResourceAsBufferedReader(INPUT_FILE_NAME));
      System.out.println("Part 1 - Min Fuel Cost: " + findLowestFuelCost(horizontalPositions));
      System.out.println("Part 2 - Min Fuel Cost: " + findLowestFuelCostIncreasing(horizontalPositions));
   }

   private static long findLowestFuelCost(List<Integer> horizontalPositions)
   {
      long minFuelCost = Long.MAX_VALUE;
      List<Integer> sortedPositions = horizontalPositions.stream().sorted().collect(Collectors.toList());
      int minPosition = sortedPositions.get(0), maxPosition = sortedPositions.get(sortedPositions.size() - 1);
      for (int currentPosition = minPosition; currentPosition <= maxPosition; currentPosition++)
      {
         int finalCurrentPosition = currentPosition;
         long totalFuelCost = horizontalPositions.stream()
               .mapToLong(horizontalPosition -> Math.abs(horizontalPosition - finalCurrentPosition))
               .sum();
         minFuelCost = Math.min(totalFuelCost, minFuelCost);
      }
      return minFuelCost;
   }

   private static long findLowestFuelCostIncreasing(List<Integer> horizontalPositions)
   {
      long minFuelCost = Long.MAX_VALUE;
      List<Integer> sortedPositions = horizontalPositions.stream().sorted().collect(Collectors.toList());
      int minPosition = sortedPositions.get(0), maxPosition = sortedPositions.get(sortedPositions.size() - 1);
      for (int currentPosition = minPosition; currentPosition <= maxPosition; currentPosition++)
      {
         long totalFuelCost = 0;
         for (Integer horizontalPosition : horizontalPositions)
         {
            long horizontalDifference = Math.abs(horizontalPosition - currentPosition);
            long currentFuelCost = (horizontalDifference * horizontalDifference + horizontalDifference) / 2;
            totalFuelCost += currentFuelCost;
         }
         minFuelCost = Math.min(totalFuelCost, minFuelCost);
      }
      return minFuelCost;
   }

   private static List<Integer> readHorizontalPositions(BufferedReader reader) throws IOException
   {
      List<Integer> horizontalPositions = new ArrayList<>();
      String line;
      while ((line = reader.readLine()) != null)
      {
         List<Integer> horizontalPositionsOnLine = Arrays.stream(StringUtils.splitString(line, ","))
               .map(Integer::parseInt).collect(Collectors.toList());
         horizontalPositions.addAll(horizontalPositionsOnLine);
      }
      return horizontalPositions;
   }
}
