import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;




// This program uses the user's decision to continue with the program
// If user agrees to continue the program with the preset values, the file which was created will be displayed
// If user agrees to continue the program with their own values, a new file will be created with their values and will be displayed
    public class BasketballTeamRecordSheet {
        public static void main(String[] args)
            // Throwing away the exception to file not found.
            // Allows the program to use the files which are in the program
                throws FileNotFoundException {

            // Asks the user to either view the preset table created, or make their own table with their own values
            System.out.println("This is a program to either enter your own values or see the preset values of a basketball game.");
            System.out.print("Do you want to create your own values? (yes or no): ");
            // Creating a scanner
            Scanner scanner = new Scanner(System.in);
            String userSelection = scanner.next();
            // This is to count how many lines there are in the present file to make sure the correct amount
            // of lines is being used for the array
            int noOfRows = 0;
            Scanner inputFile = new Scanner(new File("atlantahawkscoring.txt"));
            while (inputFile.hasNextLine()) {
                inputFile.nextLine();
                noOfRows++;
            }

            String continueProgram = "yes";
            // If the user says yes, this code will be prompted so the user can input the data
            if ("yes".equalsIgnoreCase(userSelection)) {
                noOfRows = 1;
                // This is the syntax to making a new file in which the user can write to the file
                PrintStream output = new PrintStream(new File("GameScores.txt"));

                // As the answer is yes, the set of code to input their own values will be prompted
                // Used while loop with two cases so if the user wants to add more players, this loop will run again
                while (continueProgram.equalsIgnoreCase("yes")) {

                    System.out.print("\nPlease type in the name: ");
                    String firstName = scanner.next();
                    output.print(firstName);

                    // asks the user for the last name of the player
                    System.out.print("Please type in the last name: ");
                    String lastName = scanner.next();
                    output.print("\t" + lastName);
                    // Asks the user for the position they play

                    System.out.print("Please type in the position: ");
                    String position = scanner.next();
                    output.print("\t" + position);

                    // Asks the user how many minutes they've played during the game
                    System.out.print("Please type in the minutes played in point form: ");
                    double minsPlayed = scanner.nextDouble();
                    output.print("\t" + minsPlayed);

                    // Asks the user how many points that certain player has scored
                    System.out.print("Please type in the points scored: ");
                    double pointsScored = scanner.nextDouble();
                    output.print("\t" + pointsScored);

                    // Asks the user the number of assists made by the player
                    System.out.print("Please type in the assists: ");
                    double assists = scanner.nextDouble();
                    output.print("\t" + assists);

                    // Asks the player the number of rebounds attained
                    System.out.print("Please type in the rebounds: ");
                    double rebounds = scanner.nextDouble();
                    output.print("\t" + rebounds);

                    // Asks the user the goal percentage between the shots made and shyots missed by the player
                    System.out.print("Please type in the field goal percentage as an integer: ");
                    double fieldGoalPercentage = scanner.nextDouble();
                    output.println("\t" + fieldGoalPercentage);

                    // The second condition for this while loop, if the user says yes then the loop will run again
                    System.out.print("would you like to add more players? ");
                    continueProgram = scanner.next();
                    if (continueProgram.equalsIgnoreCase("yes")) {
                        noOfRows++;
                    }

                }
            }

            // If user says "No", the program will create arrays using the preset file
            // Else it will create arrays using the file created by the user
            Scanner inputOutputFile;
            if (userSelection.equalsIgnoreCase("No")) {
                inputOutputFile = new Scanner(new File("atlantahawkscoring.txt"));
            } else {
                inputOutputFile = new Scanner(new File("GameScores.txt"));
            }

            // Creating the array "game" as an object so it can hold all data types
            Object[][] game = new Object[noOfRows][];
            for (int i = 0; i < noOfRows; i++) {
                game[i] = new Object[8];
            }

            int lineCount = 0;

            // Used while statement to see if the file actually has a next line to read
            while (inputOutputFile.hasNextLine()) {
                String line = inputOutputFile.nextLine();
                Scanner lineScan = new Scanner(line);

                // The data from the files is read and put into the array which have 8 columns or 8 different
                // storages to hold the data
                while (lineScan.hasNext()) {
                    game[lineCount][0] = lineScan.next();
                    game[lineCount][1] = lineScan.next();
                    game[lineCount][2] = lineScan.next();
                    game[lineCount][3] = lineScan.nextDouble();
                    game[lineCount][4] = lineScan.nextDouble();
                    game[lineCount][5] = lineScan.nextDouble();
                    game[lineCount][6] = lineScan.nextDouble();
                    game[lineCount][7] = lineScan.nextDouble();
                    lineCount++;
                }
            }
            // This asks the user to which type of sorting they would like to do
            // If user does not say 4, 5, or 6, then the program is stuck in the loop
            System.out.print("Would you like to sort by Points, Assists, or Rebounds? Please select the number" +
                    " respectively (4, 5, or 6): ");
            int sortColumn = scanner.nextInt();
            while (sortColumn != 4 && sortColumn != 5 && sortColumn != 6) {
                System.out.print("You didn't enter right number, please enter again: ");
                sortColumn = scanner.nextInt();
            }
            // After the number of column has been sorted, it is then put into a final variable because in comparator
            // a final variable has to be used, so it cannot be changed
            final int sortCol = sortColumn;
            final Comparator<Object[]> arrayComparator = new Comparator<Object[]>() {
                @Override
                public int compare(Object[] array1, Object[] array2) {
                    return (((Double) array1[sortCol]).compareTo((Double) array2[sortCol]));
                }
            };

            Arrays.sort(game, arrayComparator);
            // Headings of the table. Used %- to perfectly adjust the widths between each heading
            System.out.format("%-20s %-15s", "Name", "Position");
            System.out.format("%-15s %-15s %-15s", "MinsPlayed", "#Points", "Rebounds");
            System.out.format("%-12s %-12s", "AST", "FG%");

            // Created a loop to make sure all the data in the file is being converted from an array to the console,
            // so it can be made into a table
            for (int rows = 0; rows < game.length; rows++) {

                System.out.format("\n" + "%-8s %-14s %-13s %-15s %-15s %-13s %-11s %-12s",
                        game[rows][0], game[rows][1], game[rows][2], game[rows][3], game[rows][4], game[rows][5],
                        game[rows][6], game[rows][7]);
            }
        }
    }
