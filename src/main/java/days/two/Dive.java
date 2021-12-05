package days.two;

import com.sun.deploy.util.StringUtils;
import utils.FileReader;

import java.io.BufferedReader;
import java.io.IOException;

public class Dive
{
   public static final String INPUT_FILE_NAME = "day_2.txt";

   public static void main(String[] args) throws IOException
   {
      multiplyPositionByDepth(FileReader.getFileFromResourceAsBufferedReader(INPUT_FILE_NAME));
      multiplyPositionByDepthWithAim(FileReader.getFileFromResourceAsBufferedReader(INPUT_FILE_NAME));
   }

   public static void multiplyPositionByDepth(BufferedReader reader) throws IOException
   {
      String line;
      int horizontalPosition = 0;
      int verticalPosition = 0;

      while ((line = reader.readLine()) != null)
      {
         String[] command = StringUtils.splitString(line, " ");
         switch (command[0])
         {
            case "up":
               verticalPosition -= Integer.parseInt(command[1]);
               break;
            case "down":
               verticalPosition += Integer.parseInt(command[1]);
               break;
            case "forward":
               horizontalPosition += Integer.parseInt(command[1]);
               break;
            default:
               break;
         }
      }

      System.out.println("Part 1 - Product of Horizontal Position and Depth: " + verticalPosition * horizontalPosition);
   }

   public static void multiplyPositionByDepthWithAim(BufferedReader reader) throws IOException
   {
      String line;
      int horizontalPosition = 0;
      int verticalPosition = 0;
      int aim = 0;

      while ((line = reader.readLine()) != null)
      {
         String[] command = StringUtils.splitString(line, " ");
         switch (command[0])
         {
            case "up":
               aim -= Integer.parseInt(command[1]);
               break;
            case "down":
               aim += Integer.parseInt(command[1]);
               break;
            case "forward":
               horizontalPosition += Integer.parseInt(command[1]);
               if (aim != 0)
               {
                  verticalPosition += aim * Integer.parseInt(command[1]);
               }
               break;
            default:
               break;
         }
      }

      System.out.println("Part 2 - Product of Horizontal Position and Depth: " + verticalPosition * horizontalPosition);
   }

}
