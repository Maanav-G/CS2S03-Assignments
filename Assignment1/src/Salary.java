/*
    Alice's normal working time is t hours per week.
    Each week, she receives d dollars for each hour of normal work;
    but if she works beyond t hours, then she would receive D dollars for each extra hour beyond t hours.

    Write a Java class named Salary whose main function computes how much Alice will earn in a week where she has worked T hours.
    Assume t, d, D, and T are all non-negative integers.
    The input to the program is a single line containing t, d, D and T respectively (separated by a space).
 */


import java.util.Scanner;

public class Salary {
    public static void main(String[] args){
        // initialize Scanner to get input
        Scanner keyboard = new Scanner(System.in);
        // get input for t, d, D and T
        int t = keyboard.nextInt();
        int d = keyboard.nextInt();
        int D = keyboard.nextInt();
        int T = keyboard.nextInt();
        int weeklySalary = getWeeklySalary(t, d, D, T);
        System.out.println(weeklySalary);
    }

    private static int getWeeklySalary(int t, int d, int d2, int t2) {
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

