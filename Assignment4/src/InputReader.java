import java.util.ArrayList;
import java.util.Scanner;

class InputReader {

    // initialize Scanner with Variable keyboard
    private Scanner keyboard;
    // initialize an InputReader object called instance
    private static InputReader instance = null;
    // initialize an int called lineNumber
    private int lineNumber = 0;


    private InputReader() {
        keyboard = new Scanner(System.in);
    }

    static InputReader getInstance() {
        if (instance == null) {
            instance = new InputReader();
        }
        return instance;
    }

    ArrayList<Command> getCommands() {
        ArrayList<Command> commands = new ArrayList<>();
        String line = "";
        lineNumber = 0;
        // try the following, if there is a BadCommand Exception then catch
        try {
            while (keyboard.hasNext()) {
                // increment line number
                lineNumber++;
                // input next line
                line = keyboard.nextLine();
                // if inputted line starts with "PRINT"
                if (line.startsWith("PRINT ")) {
                    // then push entire line to commands list as a print command
                    commands.add(makePrintCommand(line));
                }
                // if inputted line starts with "BEGIN_"
                else if (line.startsWith("BEGIN_")) {
                    // then push entire line to commands list as a block command
                    commands.add(makeBlockCommand(line));
                }
                // if inputted line is "FINISH"
                else if (line.equals("FINISH")) {
                    // end taking input
                    break;
                }
                // if input is  nothing
                else if (!line.equals("")) {
                    // print out line
                    System.out.println(line);
                    // and throw exception
                    throw new BadCommandException("Invalid command.");
                }
            }
        }
        // if a BadCommand Exception is thrown
        catch (BadCommandException e) {
            // then throw Bad Command Exception, with line number and message
            throw new BadCommandException("Line " + lineNumber + " : " + e.getMessage());
        }
        // return commands
        return commands;
    }

    private Command makeBlockCommand(String line) {
        // Removes "BEGIN_" from the current line to get the command type;
        BlockCommand command = new BlockCommand(line.substring(6));
        // wile current input has more tokens
        while (keyboard.hasNext()) {
            // increment line number
            lineNumber ++;
            // line equals the rest of the tokens
            line = keyboard.nextLine();
            // if line starts and
            if (line.equals("END_" + command.getBlockType())) {
                return command;
            } else if (line.equals("")) {
            }
            else {
                String [] tokens = line.split(" ", 3);
                if (tokens.length != 3 || tokens[1].length() != 1)
                    throw new BadCommandException("Invalid tag.");
                command.addTag(new Tag(tokens));
            }
        }
        return command;
    }

    private Command makePrintCommand(String line) {
        // divide the line by the space, and store in String[] called tokens
        String[] tokens = line.split(" ", 5);
        // if too many tokens (more than 4)
        if (tokens.length > 4) {
            // throw exception
            throw new BadCommandException("Invalid print command; too many tokens.");
        }
        // if not enough tokens (less than 4)
        else if (tokens.length < 4) {
            // throw exception
            throw new BadCommandException("Invalid print command; too few tokens.");
        }
        return new PrintCommand(tokens);
    }
}