package days.five;

import com.sun.deploy.util.StringUtils;
import utils.FileReader;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HydrothermalVenture
{
   public static final String INPUT_FILE_NAME = "day_5.txt";

   public static void main(String[] args) throws IOException
   {
      Map<Point, Integer> coordinateMapWithoutDiagonal =
            createCoordinateMap(FileReader.getFileFromResourceAsBufferedReader(INPUT_FILE_NAME), false);
      System.out.println("Part 1 - Points to Avoid: " + calculateTotalPointsToAvoid(coordinateMapWithoutDiagonal));

      Map<Point, Integer> coordinateMapWithDiagonal =
            createCoordinateMap(FileReader.getFileFromResourceAsBufferedReader(INPUT_FILE_NAME), true);
      System.out.println("Part 2 - Points to Avoid: " + calculateTotalPointsToAvoid(coordinateMapWithDiagonal));
   }

   private static long calculateTotalPointsToAvoid(Map<Point, Integer> coordinateMap)
   {
      return coordinateMap.values().stream()
            .filter(coordinate -> coordinate > 1)
            .count();
   }

   private static Map<Point, Integer> createCoordinateMap(BufferedReader reader, boolean includeDiagonal) throws IOException
   {
      Map<Point, Integer> coordinateMap = new HashMap<>();

      String line;
      while ((line = reader.readLine()) != null)
      {
         String[] coordinates = StringUtils.splitString(line, " -> ");
         Point startCoordinate = convertStringToPoint(coordinates[0]);
         Point endCoordinate = convertStringToPoint(coordinates[1]);

         if (startCoordinate.x == endCoordinate.x)
         {
            addVerticalLineToCoordinateMap(coordinateMap, startCoordinate, endCoordinate);
         }
         else if (startCoordinate.y == endCoordinate.y)
         {
            addHorizontalLineToCoordinateMap(coordinateMap, startCoordinate, endCoordinate);
         }
         else if (includeDiagonal)
         {
            addDiagonalLineToCoordinateMap(coordinateMap, startCoordinate, endCoordinate);
         }
      }

      return coordinateMap;
   }

   private static void addVerticalLineToCoordinateMap(Map<Point, Integer> coordinateMap, Point startCoordinate, Point endCoordinate)
   {
      int xValue = startCoordinate.x;
      if (startCoordinate.y > endCoordinate.y)
      {
         for (int yValue = startCoordinate.y; yValue >= endCoordinate.y; yValue--)
         {
            Point currentCoordinate = new Point(xValue, yValue);
            coordinateMap.merge(currentCoordinate, 1, Integer::sum);
         }
      }
      else if (startCoordinate.y < endCoordinate.y)
      {
         for (int yValue = startCoordinate.y; yValue <= endCoordinate.y; yValue++)
         {
            Point currentCoordinate = new Point(xValue, yValue);
            coordinateMap.merge(currentCoordinate, 1, Integer::sum);
         }
      }
   }

   private static void addHorizontalLineToCoordinateMap(Map<Point, Integer> coordinateMap, Point startCoordinate, Point endCoordinate)
   {
      int yValue = startCoordinate.y;
      if (startCoordinate.x > endCoordinate.x)
      {
         for (int xValue = startCoordinate.x; xValue >= endCoordinate.x; xValue--)
         {
            Point currentCoordinate = new Point(xValue, yValue);
            coordinateMap.merge(currentCoordinate, 1, Integer::sum);
         }
      }
      else if (startCoordinate.x < endCoordinate.x)
      {
         for (int xValue = startCoordinate.x; xValue <= endCoordinate.x; xValue++)
         {
            Point currentCoordinate = new Point(xValue, yValue);
            coordinateMap.merge(currentCoordinate, 1, Integer::sum);
         }
      }
   }

   private static void addDiagonalLineToCoordinateMap(Map<Point, Integer> coordinateMap, Point startCoordinate, Point endCoordinate)
   {
      float slope = calculateSlope(startCoordinate, endCoordinate);
      if (slope == 1)
      {
         addPositiveSlopeLineToCoordinateMap(coordinateMap, startCoordinate, endCoordinate);
      }
      else if (slope == -1)
      {
         addNegativeSlopeLineToCoordinateMap(coordinateMap, startCoordinate, endCoordinate);
      }
   }

   private static void addNegativeSlopeLineToCoordinateMap(Map<Point, Integer> coordinateMap, Point startCoordinate, Point endCoordinate)
   {
      int xValue = startCoordinate.x, yValue = startCoordinate.y;
      if (startCoordinate.x < endCoordinate.x)
      {
         while (xValue <= endCoordinate.x && yValue >= endCoordinate.y)
         {
            Point currentCoordinate = new Point(xValue, yValue);
            coordinateMap.merge(currentCoordinate, 1, Integer::sum);
            xValue++;
            yValue--;
         }
      }
      else
      {
         while (xValue >= endCoordinate.x && yValue <= endCoordinate.y)
         {
            Point currentCoordinate = new Point(xValue, yValue);
            coordinateMap.merge(currentCoordinate, 1, Integer::sum);
            xValue--;
            yValue++;
         }
      }
   }

   private static void addPositiveSlopeLineToCoordinateMap(Map<Point, Integer> coordinateMap, Point startCoordinate, Point endCoordinate)
   {
      int xValue = startCoordinate.x, yValue = startCoordinate.y;
      if (startCoordinate.x < endCoordinate.x)
      {
         while (xValue <= endCoordinate.x && yValue <= endCoordinate.y)
         {
            Point currentCoordinate = new Point(xValue, yValue);
            coordinateMap.merge(currentCoordinate, 1, Integer::sum);
            xValue++;
            yValue++;
         }
      }
      else
      {
         while (xValue >= endCoordinate.x && yValue >= endCoordinate.y)
         {
            Point currentCoordinate = new Point(xValue, yValue);
            coordinateMap.merge(currentCoordinate, 1, Integer::sum);
            xValue--;
            yValue--;
         }
      }
   }

   private static float calculateSlope(Point startCoordinate, Point endCoordinate)
   {
      return ((float) (endCoordinate.y - startCoordinate.y)) / ((float) (endCoordinate.x - startCoordinate.x));
   }

   private static Point convertStringToPoint(String coordinateAsString)
   {
      List<Integer> coordinates = Arrays.stream(StringUtils.splitString(coordinateAsString, ","))
            .map(Integer::parseInt).collect(Collectors.toList());
      return new Point(coordinates.get(0), coordinates.get(1));
   }
}
