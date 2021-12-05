package days.two;

import com.sun.deploy.util.StringUtils;
import utils.FileReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Dive
{
   public static final String INPUT_FILE_NAME = "day_2.txt";

   public static void main(String[] args) throws IOException
   {
      multiplyPositionByDepth(FileReader.getFileFromResourceAsStream(INPUT_FILE_NAME));
      multiplyPositionByDepthWithAim(FileReader.getFileFromResourceAsStream(INPUT_FILE_NAME));
   }

   public static void multiplyPositionByDepth(InputStream is) throws IOException
   {
      InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
      BufferedReader reader = new BufferedReader(streamReader);

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

   public static void multiplyPositionByDepthWithAim(InputStream is) throws IOException
   {
      InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
      BufferedReader reader = new BufferedReader(streamReader);

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
