import java.text.ParseException;

class PrintCommand extends Command {

    // variables for entity type, query type and value
    private String entityType;
    private String queryType;
    private String queryValue;

    // object PrintCommand, with variables from Command
    // and new field variables
    PrintCommand(String[] tokens) {
        super();
        entityType = tokens[1];
        queryType = tokens[2];
        queryValue = tokens[3];
    }

    // override run from Command
    @Override
    void run(Database database) {
        // if entity "CUSTOMER" then call runPrintCustomer
        if (entityType.equals("CUSTOMER"))
            runPrintCustomer(database);
        // if entity "PLAN" then cal; runPrintPlan
        else if (entityType.equals("PLAN"))
            runPrintPlan(database);
        // otherwise throw a bad print command exception
        else {
            throw new BadCommandException("Bad print command.");
        }
    }

    private void runPrintCustomer(Database database) {
        // if "TOTAL_CLAIMED"
        if (queryType.equals("TOTAL_CLAIMED")) {
            // output the following
            System.out.println("Total amount claimed by " + database.getCustomer(queryValue).getName() +
                    " is " + database.totalClaimAmountByCustomer(queryValue));
        }
        // if "TOTAL_RECEIVED"
        else if (queryType.equals("TOTAL_RECEIVED")) {
            // output the following
            System.out.println("Total amount received by " + database.getCustomer(queryValue).getName() +
                            " is " + database.totalReceivedAmountByCustomer(queryValue));
        }
    }

    private void runPrintPlan(Database database) {
        // if "NUM_CUSTOMERS"
        if (queryType.equals("NUM_CUSTOMERS")){
            // output the following
            System.out.println("Number of customers under " + database.getPlan(queryValue).getName() + " is " + database.getAmountOfCustomersForPlan(queryValue)
            );

        }
        // if "TOTAL_PAYED_TO_CUSTOMERS"
        else if (queryType.equals("TOTAL_PAYED_TO_CUSTOMERS")){
            // output the following
            System.out.println("Total amount payed under " + database.getPlan(queryValue).getName() + " is " + database.getTotalAmountPayedUnderPlan(queryValue)
            );
        }

    }
}
