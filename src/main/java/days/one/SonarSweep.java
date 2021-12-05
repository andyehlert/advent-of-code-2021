package days.one;

import utils.FileReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class SonarSweep
{
   public static final String INPUT_FILE_NAME = "day_1.txt";

   public static void main(String[] args) throws IOException
   {
      countDepthIncreases(FileReader.getFileFromResourceAsBufferedReader(INPUT_FILE_NAME));
      countSlidingWindowSumIncreases(FileReader.getFileFromResourceAsBufferedReader(INPUT_FILE_NAME));
   }

   private static void countDepthIncreases(BufferedReader reader) throws IOException
   {
      String line;
      int previousDepth = 0;
      int numberOfIncreases = 0;
      boolean firstIteration = true;

      while ((line = reader.readLine()) != null)
      {
         int depth = Integer.parseInt(line);
         if (firstIteration)
         {
            firstIteration = false;
         }
         else
         {
            if (depth > previousDepth)
            {
               numberOfIncreases++;
            }
         }
         previousDepth = depth;
      }

      System.out.println("Part 1 - Number of Depth Increases: " + numberOfIncreases);
   }

   private static void countSlidingWindowSumIncreases(BufferedReader reader) throws IOException
   {
      String line;
      Queue<Integer> currentWindow = new LinkedList<>();

      int maxWindowSize = 3;
      int currentWindowIndex = 0;
      int previousWindowSum = 0;
      int numberOfIncreases = 0;

      while ((line = reader.readLine()) != null)
      {
         int depth = Integer.parseInt(line);
         if (currentWindowIndex < maxWindowSize)
         {
            currentWindow.add(depth);
            previousWindowSum += depth;
            currentWindowIndex++;
         }
         else
         {
            int depthToRemove = currentWindow.remove();
            currentWindow.add(depth);
            int currentWindowSum = previousWindowSum - depthToRemove + depth;
            if (currentWindowSum > previousWindowSum)
            {
               numberOfIncreases++;
            }
            previousWindowSum = currentWindowSum;
         }
      }

      System.out.println("Part 2 - Number of Sliding Window Sum Increases: " + numberOfIncreases);
   }
}
