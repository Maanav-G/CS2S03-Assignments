import java.text.ParseException;

class CommandHandler {
    Database database;
    // object CommandHandler that holds the database
    CommandHandler(Database database) {
        this.database = database;
    }

    void run(Command command) throws ParseException {
        command.run(database);
    }
}
