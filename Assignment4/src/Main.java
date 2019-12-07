import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        // try main method, if throws a Parse Exception or Bad Command Exception then catch
        try {
            InputReader inputReader = InputReader.getInstance();
            ArrayList<Command> commands = inputReader.getCommands();
            Iterator<Command> currentCommand = commands.iterator();
            CommandHandler commandHandler = new CommandHandler(new Database());

            while (currentCommand.hasNext()) {
                commandHandler.run(currentCommand.next());
            }
        }
        // if a parse exception is thrown
        catch (ParseException e) {
            // then print message
            System.out.println(e.getMessage());
        }
        // if a bad command exception is thrown
        catch (BadCommandException e) {
            // then print message
            System.out.println(e.getMessage());
        }
    }
}
