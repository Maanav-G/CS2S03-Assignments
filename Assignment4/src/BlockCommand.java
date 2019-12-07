import java.text.ParseException;
import java.util.HashMap;

class BlockCommand extends Command {
    // initialize String "blockType"
    private String blockType;
    // initialize a HashMap with String and type Tag called "tags"
    private HashMap<String, Tag> tags = new HashMap<>();

    BlockCommand(String blockType) {
        this.blockType = blockType;
    }

    void addTag(Tag tag) {
        tags.put(tag.getName(), tag);
    }

    String getBlockType() {
        return blockType;
    }

    // overrides inherited method of "run" from Command
    @Override
    void run(Database database) throws ParseException {
        // if statements to check for what block type is
        // insert to database accordingly
        if (blockType.equals(Customer.inputTag)) {
            database.insertCustomer(new Customer(tags));
        } if (blockType.equals(Home.inputTag)) {
            database.insertHome(new Home(tags));
        } if (blockType.equals(Car.inputTag)) {
            database.insertCar(new Car(tags));
        } if (blockType.equals(Claim.inputTag)) {
            Claim claim = new Claim(tags);
            database.insertClaim(claim);
            // if the claim processed through the database is successful
            if (processClaim(claim, database)) {
                // then set the claim to be successful with true
                claim.setSuccessful(true);
                // and out put a line saying the claim for the contract was successful
                System.out.println("Claim on " + Utils.formattedDate(claim.getDate())
                        + " for contract " + claim.getContractName() + " was successful.");
            } else {
                // otherwise set claim to be unsuccessful with false
                claim.setSuccessful(false);
                // and output a line saying that it was not successful
                System.out.println("Claim on " + Utils.formattedDate(claim.getDate())
                        + " for contract " + claim.getContractName() + " was not successful.");
            }
        } if (blockType.equals(Contract.inputTag)) {
            database.insertContract(new Contract(tags));
        } if (blockType.equals(HomePlan.inputTag)) {
            database.insertPlan(new HomePlan(tags));

//            System.out.println("IM OVER HERE" + blockType);


        } if (blockType.equals(CarPlan.inputTag)) {
            database.insertPlan(new CarPlan(tags));
        }
    }

    private boolean processClaim(Claim claim, Database database) {
        Contract contract = database.getContract(claim.getContractName());
        Customer customer = database.getCustomer(contract.getCustomerName());
        Plan plan = database.getPlan(contract.getPlanName());
        Insurable insurable = plan.getInsuredItem(customer, database);

        // If the claimed amount is higher than covered by the plan.
        if (plan.getMaxCoveragePerClaim() < claim.getAmount())
            return false;

        // If the claim date is not in the contract period.
        if (claim.getDate().after(contract.getEndDate()) || claim.getDate().before(contract.getStartDate()))
            return false;

        // If the customer was not eligible.
        if (!plan.isEligible(customer, claim.getDate()))
            return false;

        return plan.isEligible(insurable, claim.getDate());
    }
}
