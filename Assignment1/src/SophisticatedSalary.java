/*
    Write a Java class called SophisticatedSalary whose main function does the exact same thing as problem 1, except that the input and output formats are different.
    In particular, t, d, D, and T are still given in a single line, but in arbitrary order.
    In order to avoid ambiguity, the name of each variable is given, e.g. d=10.

    Also, the output is given in three lines: the middle line starts by the salary, a space, and then the word "Dollars".
    The  first and the last line are filled with * symbol; the number of stars is equal to the number of characters in the second line.
 */


import java.util.Scanner;

public class SophisticatedSalary {
    public static void main(String[] args){

        // initialize variable t, d, D and T
        int t = 0, d = 0, D = 0, T = 0;
        // initialize Scanner to get input
        Scanner keyboard = new Scanner(System.in);
        // get input for t, d, D and T
        String input = keyboard.nextLine();


        @parm weeklySalary

        int weeklySalary = getWeeklySalary(t, d, D, T, input);

        // adding the word "Dollars" to the output
        String weeklySalaryOutput = weeklySalary + " Dollars";

        // determine the length of the weeklySalaryOutput string
        int weeklySalaryStringLength = weeklySalaryOutput.length();
        // using the length, return a string of stars, by replacing all the "\0"s (null characters) with "*"
        String stringOfStars = new String(new char[weeklySalaryStringLength]).replace('\0', '*');

        // print the string of stars, the salary, then the string of stars again
        System.out.println(stringOfStars);
        System.out.println(weeklySalaryOutput);
        System.out.println(stringOfStars);
    }

    private static int getWeeklySalary(int t, int d, int d2, int t2, String input) {
        // split input by " " and add to an array of Strings
        String[] wordsList = input.split("\\s+");
        // set length of array
        int wordsListLength = wordsList.length;

        // for loop - for each word in array
        for ( int i = 0; i < wordsListLength; i++) {

            // initialize a new string builder
            StringBuilder build = new StringBuilder();
            // for loop = for every character of each word after the "=" (the 1st index)
            for (int j = 2; j < wordsList[i].length(); j++) {
                // append character to the build
                build.append(wordsList[i].charAt(j));
            }
            // convert build to string, and then string to int
            int num = Integer.parseInt(build.toString());

            // get the first character, which is the variable t, d, D or T
            char firstChar = wordsList[i].charAt(0);
            // convert the character from Char to String
            String var = Character.toString(firstChar);

            // switch - where var is the first character, aka the variable
            // each case, makes its respective variable equal the number
            switch (var) {
                case "t":
                    t = num;
                    break;
                case "T":
                    t2 = num;
                    break;
                case "d":
                    d = num;
                    break;
                case "D":
                    d2 = num;
                    break;
                default:
                    System.out.println("Invalid Input");
            }
        }

        // weekly salary determined be calling getWeeklySalary(t, d, D, T), with all the variables
        return calculateWeeklySalary(t, d, d2, t2);
    }

    private static int calculateWeeklySalary(int t, int d, int d2, int t2) {
        // declared required integer variables
        // extraHours determines how many how many extra hours beyond t have been worked
        // regularHours determines how many hours shes worked within t
        // weeklySalary determines how much salary she earns after working T hours
        int extraHours, regularHours, weeklySalary;

        // check if hours worked are beyond normal working hours, the same, or below
        // if beyond
        if(t2 > t){
            // extraHours is total working hours subtracted by normal working
            extraHours = t2 - t;
            // regularHours will then be normal working hours
            regularHours = t;
        }
        // if the same or below
        else {
            // extraHours will be 0, because there will be no extra hours worked
            extraHours = 0;
            // regularHours will then be total hours worked
            regularHours = t2;
        }

        // weeklySalary then computes the extraHours worked multiplied by D, and regularHours worked multiplied by d
        weeklySalary = extraHours* d2 + regularHours*d;
        return weeklySalary;
    }
}
