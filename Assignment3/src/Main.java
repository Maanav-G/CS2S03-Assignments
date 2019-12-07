import java.awt.*;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        // initialize Scanner to get input
        Scanner keyboard = new Scanner(System.in);
        // read user's input
        String input = keyboard.nextLine();
        // call function "eval(input)" to evaluate user's input
        eval(input);
    }
    private static void eval(String s) {
        // test if user input has "Invalid Characters"
        if (!validityTestCharacter(s)){
            // call validityTestCharacter(s) to check if true or false
            // if function returns false, then print "INVALID CHARACTERS"
            System.out.println("INVALID CHARACTERS");
        }
        // test if user input is an "Invalid Expression
        else if (!validityTestExpression(s)){
            // call validityTestExpression(s) to check if true or false
            // if function returns false, then print "INVALID EXPRESSION"
            System.out.println("INVALID EXPRESSION");
        }
        else {
            // if expression is valid
            // then evaluate the expression by calling "evaluateExpression(s)"
            evaluateExpression(s);
        }

    }
    private static void evaluateExpression(String expression) {
        // initialize a try and catch
        // in-case function throws an error
        try {
            // initialize a char array called currChar
            char[] currChar = expression.toCharArray();
            // initialize a stack called values (for the numbers)
            Stack<Integer> values = new Stack<Integer>();
            // initialize a stack called ops (for the operations)
            Stack<Character> ops = new Stack<Character>();

            // iterate through expression
            for (int i = 0; i < currChar.length; i++) {
                // if current character is a number
                if (currChar[i] >= '0' && currChar[i] <= '9') {
                    // initialize a StringBuffer called x to build a string
                    StringBuffer x = new StringBuffer();
                    // add current character to the StringBuffer
                    x.append(currChar[i]);
                    // initialize boolean value y for while loop
                    boolean y = true;
                    // run while loop if number is multiple digits long
                    while (y) {
                        // initialize Integer j to equal index of next character
                        int j = i + 1;
                        // if j is equal to the length of the array
                        if (j == currChar.length) {
                            // then no next character and
                            // exit and stop the while loop
                            y = false;
                            break;
                        }
                        // otherwise, if the next character is also a number
                        if (currChar[j] >= '0' && currChar[j] <= '9') {
                            // add that character to the StringBuffer x
                            x.append(currChar[j]);
                            // add one to the index so we don't iterate through it again
                            i++;
                        }
                        else{
                            // if the next character is anything else
                            // then y is false, to end loop
                            y = false;
                        }
                    }
                    // convert StringBuffer to a String
                    String xToString = x.toString();
                    // convert that String to an Integer
                    Integer stringToInt = Integer.parseInt(xToString);
                    // push that Integer value to the 'values' Stack
                    values.push(stringToInt);
                }
                // otherwise if the current character is '('
                else if (currChar[i] == '(') {
                    // then push the character to the 'ops' Stack
                    ops.push(currChar[i]);
                }
                // otherwise if the current character is ')'
                else if (currChar[i] == ')') {
                    // then while the top element of the ops stack
                    // is not '('
                    while (ops.peek() != '(') {
                        // initialize Character variable op, with
                        // the top element of the Stack 'ops' and
                        // remove that element as well from the stack
                        char op = ops.pop();
                        // initialize Character variables a and b, with
                        // the top 2 elements of the Stack 'values' and
                        // remove those elements as well from the stack
                        Integer a = values.pop();
                        Integer b = values.pop();
                        // initialize an Integer to push to the 'values' Stack, with
                        // the value returned from calling the function 'applyOperations(op, a, b)'
                        // with op as the operation, and a and b as the values to evalute
                        Integer toPush = applyOperations(op, a, b);
                        // push that Integer value to the 'values' Stack
                        values.push(toPush);
                    }
                    // remove the top element of the Stack 'ops'
                    ops.pop();
                }
                // otherwise if the current character is '@' or '&'
                else if (currChar[i] == '@' || currChar[i] == '&') {
                    // then while the Stack 'ops' isn't empty
                    // and the function precedence with operation1 as current character
                    // and operation2 as the top element of the Stack 'ops' is true
                    while (!ops.empty() && precedence(currChar[i], ops.peek())) {
                        // then push the value return from
                        // 'applyOperations(ops.pop(), values.pop(), values.pop())'
                        // to the Stack 'values'
                        values.push(applyOperations(ops.pop(), values.pop(), values.pop()));
                    }
                    // then push the current character
                    // to the Stack 'ops'
                    ops.push(currChar[i]);
                }
            }
            // if after iterating through array
            // the 'ops' still has elements
            while (!ops.empty()){
                // then evaluate the top two (numbers) elements of the Stack 'values'
                // using the top element of the Stack 'ops' as the operation
                // and push that value to the Stack 'values'
                values.push(applyOperations(ops.pop(), values.pop(), values.pop()));
            }
            // at the end output the top value of
            // the Stack 'values' - which is the answer
            System.out.println(values.peek());

        }
        // if function throws error
        catch (Exception e) {
            // output "INVALID EXPRESSION"
            System.out.println("INVALID EXPRESSION");
        }
    }
    private static boolean validityTestCharacter(String expression) {
        // initialize boolean value to return validity
        boolean valid = true;
        // iterate through expression
        for (int i = 0; i < expression.length(); i++){
            // initialize current character
            // with type character, to variable "c"
            char c = expression.charAt(i);
            // if current character is not '@', '&', '(' or ')'
            if (!(c == '@' || c == '&' || c == '(' || c == ')')) {
                // and if current character is not a number
                if (!(c >= '0' && c <= '9')){
                    // then current character is invalid
                    // and makes valid equal false
                    valid = false;
                    // break out of loop,
                    // because one invalid character is enough
                    break;
                }
            }
            // if none of the above conditions met
            // then set valid to true
            else valid = true;
        }
        // function returns boolean value
        // that determines if expression is valid or not
        return valid;
        }
    private static boolean validityTestExpression(String expression) {
        // initialize boolean value to return validity
        boolean valid = true;
        // initialize a Stack called "stack"
        Stack<Character> stack = new Stack<Character>();
        // iterate through expression
        for (int i = 0; i < expression.length(); i++){
            // initialize current character
            // with type character, to variable "c"
            char c = expression.charAt(i);
            // if current index is not last index
            // if current character is not the last character in expression
            if (i < expression.length() - 1){
                // then initialize a character
                // which is the next character after 'c'
                char c2 = expression.charAt(i+1);
                // if c is '(' and c2 is ')'
                // where '(' and ')' are back to back in expression
                if (c == '(' && c2 == ')' ){
                    // then makes the entire expression invalid
                    valid = false;
                    // break out of loop,
                    // because one invalid sub-expression is enough
                    break;
                }
            }

            // if current character is '(' or ')'
            if (c == ')' || c == '('){
                // if current character is ')'
                if (c == ')') {
                    // if the stack is empty
                    // then set the character called top element
                    // with a dummy variable '?'
                    // else, if stack has elements
                    // then set 'topElement' with the top element of the stack
                    // and remove the top element from stack as well
                    char topElement = stack.empty() ? '?' : stack.pop();
                    // if the top element doesn't equal '('
                    if (topElement != '('){
                        // implies that there's no opening parenthesis
                        // for current closing parenthesis
                        // then makes the entire expression invalid
                        valid = false;
                        // break out of loop,
                        // because one invalid sub-expression is enough
                        break;
                    }
                }
                // otherwise, if current character is '('
                else if (c == '('){
                    // then push the opening brace to the stack
                    stack.push(c);
                }
                // set valid to equal if stack is empty or not
                // if empty - then true, hence expression valid
                // if not empty - then false, because parenthesis aren't paired up
                valid = stack.isEmpty();
            }
        }
        // function returns boolean value
        // that determines if expression is valid or not
        return valid;
    }
    private static boolean precedence(char operation1, char operation2){
        // judges precedence for expression
        // if operation 2 is '(' or ')'
        if(operation2 == '(' || operation2 == ')' ){
            // has top precedence, and return false
            return false;
        }
        // if operation 1 is '@' and operation 2 is '&' or vice versa
        if ((operation1 == '@' || operation2 == '&') && (operation2 == '@' || operation1 == '&') )
            // has second precedence, and return false
            return false;
        else
            // if any other combination, then return true
            return true;
    }
    private static int applyOperations(char operation, Integer a, Integer b){
        // switch, where operation is the operation to calculate
        switch (operation){
            // if operation '@'
            case '@':
                // then find the minimum value between a and b
                return Math.min(a, b);
            // if operation '&'
            case '&':
                // then find the maximum value between a and b
                return Math.max(a, b);
        }
        // else return 0
        return 0;
    }
}
