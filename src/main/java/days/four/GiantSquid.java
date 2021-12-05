package days.four;

import com.sun.deploy.util.StringUtils;
import utils.FileReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class GiantSquid
{
   public static final String INPUT_FILE_NAME = "day_4.txt";

   public static void main(String[] args) throws IOException
   {
      firstWinningBingoCardScore(FileReader.getFileFromResourceAsStream(INPUT_FILE_NAME));
      lastWinningBingoCardScore(FileReader.getFileFromResourceAsStream(INPUT_FILE_NAME));
   }

   private static void firstWinningBingoCardScore(InputStream is) throws IOException
   {
      InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
      BufferedReader reader = new BufferedReader(streamReader);

      List<String> drawingNumbers = readDrawingNumbers(reader);
      List<List<List<String>>> boards = readBoards(reader);

      long winningScore = 0;

      for (String number : drawingNumbers)
      {
         for (List<List<String>> board : boards)
         {
            markCalledNumberOnBoard(board, number);

            if (winningBoard(board))
            {
               winningScore = calculateWinningScore(board, number);
               break;
            }
         }
         if (winningScore > 0)
         {
            break;
         }
      }

      System.out.println("Part 1 - First Winning Board Score: " + winningScore);
   }

   private static void lastWinningBingoCardScore(InputStream is) throws IOException
   {
      InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
      BufferedReader reader = new BufferedReader(streamReader);

      List<String> drawingNumbers = readDrawingNumbers(reader);
      List<List<List<String>>> boards = readBoards(reader);

      long winningScore;
      List<Long> listOfWinningScores = new ArrayList<>();

      for (String calledNumber : drawingNumbers)
      {
         int numBoards = boards.size();
         List<Integer> indexOfBoardsToRemove = new ArrayList<>();

         for (int boardsIndex = 0; boardsIndex < numBoards; boardsIndex++)
         {
            List<List<String>> board = boards.get(boardsIndex);

            markCalledNumberOnBoard(board, calledNumber);

            if (winningBoard(board))
            {
               winningScore = calculateWinningScore(board, calledNumber);
               listOfWinningScores.add(winningScore);
               indexOfBoardsToRemove.add(boardsIndex);
            }
         }

         if (indexOfBoardsToRemove.size() > 0)
         {
            int boardsRemoved = 0;
            for (Integer boardIndex : indexOfBoardsToRemove.stream().sorted().collect(Collectors.toList()))
            {
               boards.remove(boardIndex - boardsRemoved);
               boardsRemoved++;
            }
         }
      }

      System.out.println("Part 2 - Last Winning Board Score: " + listOfWinningScores.get(listOfWinningScores.size() - 1));
   }

   private static List<String> readDrawingNumbers(BufferedReader reader) throws IOException
   {
      String firstLine = reader.readLine();
      reader.readLine();
      return Arrays.stream(StringUtils.splitString(firstLine, ","))
            .collect(Collectors.toList());
   }

   private static List<List<List<String>>> readBoards(BufferedReader reader) throws IOException
   {
      String line;
      List<List<List<String>>> boards = new ArrayList<>();

      while ((line = reader.readLine()) != null)
      {
         List<List<String>> individualBoard = new ArrayList<>();
         individualBoard.add(Arrays.stream(line.split("\\s+"))
               .filter(entry -> !entry.equals(""))
               .collect(Collectors.toList()));

         while (!Objects.equals(line = reader.readLine(), "") && line != null)
         {
            individualBoard.add(Arrays.stream(line.split("\\s+"))
                  .filter(entry -> !entry.equals(""))
                  .collect(Collectors.toList()));
         }
         boards.add(individualBoard);
      }
      return boards;
   }

   private static boolean winningBoard(List<List<String>> board)
   {
      return containsWinningRow(board) || containsWinningColumn(board);
   }

   private static boolean containsWinningRow(List<List<String>> board)
   {
      int numColumns = board.get(0).size();
      for (List<String> row : board)
      {
         boolean rowWon = true;
         for (int columnIndex = 0; columnIndex < numColumns; columnIndex++)
         {
            if (!Objects.equals(row.get(columnIndex), "X"))
            {
               rowWon = false;
               break;
            }
         }
         if (rowWon)
         {
            return true;
         }
      }
      return false;
   }

   private static boolean containsWinningColumn(List<List<String>> board)
   {
      int numColumns = board.get(0).size();
      for (int columnIndex = 0; columnIndex < numColumns; columnIndex++)
      {
         boolean columnWon = true;
         for (List<String> strings : board)
         {
            if (!Objects.equals(strings.get(columnIndex), "X"))
            {
               columnWon = false;
               break;
            }
         }
         if (columnWon)
         {
            return true;
         }
      }
      return false;
   }

   private static void markCalledNumberOnBoard(List<List<String>> board, String numberCalled)
   {
      for (List<String> row : board)
      {
         if (row.contains(numberCalled))
         {
            row.set(row.indexOf(numberCalled), "X");
         }
      }
   }

   private static long calculateWinningScore(List<List<String>> board, String numberCalled)
   {
      long runningSum = 0;
      for (List<String> strings : board)
      {
         for (int columnIndex = 0; columnIndex < board.get(0).size(); columnIndex++)
         {
            String currentElement = strings.get(columnIndex);
            if (!Objects.equals(currentElement, "X"))
            {
               runningSum += Long.parseLong(currentElement);
            }
         }
      }
      return runningSum * Long.parseLong(numberCalled);
   }
}
