import java.util.ArrayList;

class Database {
    // Array Lists for Customers, Homes, Cars, Plans, Contracts and Claims
    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<Home> homes = new ArrayList<>();
    private ArrayList<Car> cars = new ArrayList<>();
    private ArrayList<Plan> plans = new ArrayList<>();
    private ArrayList<Contract> contracts = new ArrayList<>();
    private ArrayList<Claim> claims = new ArrayList<>();

    // inset commands for each variable
    void insertHome(Home home) {
        homes.add(home);
    }

    void insertCar(Car car) {
        cars.add(car);
    }

    void insertCustomer(Customer customer) {
        customers.add(customer);
    }

    void insertPlan(Plan plan) {
        plans.add(plan);
    }

    void insertClaim(Claim claim) {
        claims.add(claim);
    }

    void insertContract(Contract contract) {
        contracts.add(contract);
    }

    // get plan specific to the name
    Plan getPlan(String name) {
        // iterate through each plan in array list plans
        for (Plan plan : plans) {
            // and if plan is equal to the name
            if (plan.name.equals(name))
                // then return it
                return plan;
        }
        // otherwise null
        return null;
    }

    // get customer specific to the name
    Customer getCustomer(String name) {
        // iterate through each customer in array list customers
        for (Customer customer : customers) {
            // and if customer is equal to the name
            if (customer.getName().equals(name))
                // then return it
                return customer;
        }
        // otherwise null
        return null;
    }

    // get contract specific to the name
    Contract getContract(String name) {
        // iterate through each contract in array list contracts
        for (Contract contract : contracts) {
            // and if contract name is equal to the name
            if (contract.getContractName().equals(name))
                // then return it
                return contract;
        }
        // otherwise null
        return null;
    }

    // get amount of customers specific to plan (name)
    int getAmountOfCustomersForPlan(String name){
        // initialize variable amount with 0
        int amount = 0;
        // iterate through each contract in array list contracts
        for (Contract contract : contracts){
            // and if contract name is equal to specified plan
            if (contract.getPlanName().equals(name)){
                // increment amount by 1
                amount++;
            }
        }
        // return amount
        return amount;
    }

    // get amount payed under each plan
    int getTotalAmountPayedUnderPlan(String name){
        // initialize variable total with 0
        int total = 0;
        // iterate through each claim in array list claims
        for (Claim claim : claims) {
            // get contract of claim
            Contract contract = getContract(claim.getContractName());
            // if contract name, is specified name
            if (contract.getPlanName().equals(name)) {
                // and if the claim is successful
                if (claim.wasSuccessful()) {
                    // get deductible of claim
                    long deductible = getPlan(contract.getPlanName()).getDeductible();
                    // total is total plus
                    // the max of 0 (so a minimum 0 is add) and the claim amount minus the deductible
                    total += Math.max(0, claim.getAmount() - deductible);
                }
            }
        }
        // return total
        return total;
    }


    /**
     * There is at most one home owned by each person.
     */
    // get home specific to name
    Home getHome(String ownnerName) {
        // iterate through each home in array list homes
        for (Home home : homes) {
            // and if home's owner name is equal to the name
            if (home.getOwnerName().equals(ownnerName))
                // then return it
                return home;
        }
        // otherwise null
        return null;
    }

    /**
     * There is at most one car owned by each person.
     */
    // get car specific to name
    Car getCar(String ownnerName) {
        // iterate through each car in array list cars
        for (Car car : cars) {
            // and if car's owner name is equal to the name
            if (car.getOwnerName().equals(ownnerName))
                // then return it
                return car;
        }
        // otherwise null
        return null;
    }

    // get total claimed by customer
    long totalClaimAmountByCustomer(String customerName) {
        // initialize variable totalClaimed with 0
        long totalClaimed = 0;
        // iterate through each claim in array list claims
        for (Claim claim : claims) {
            // if contract's customer name of current claim's contract
            // is equal to the customer's name
            if (getContract(claim.getContractName()).getCustomerName().equals(customerName))
                // then add claim amount to total claimed
                totalClaimed += claim.getAmount();
        }
        // return total claim
        return totalClaimed;
    }

    // total amount received by specific customer
    long totalReceivedAmountByCustomer(String customerName) {
        // initialize variable totalReceived with 0
        long totalReceived = 0;
        // iterate through each claim in array list claims
        for (Claim claim : claims) {
            // get contract of claim
            Contract contract = getContract(claim.getContractName());
            // if contract's customer's name is equal to the customer's name
            if (contract.getCustomerName().equals(customerName)) {
                // and if the claim is successful
                if (claim.wasSuccessful()) {
                    // get deductible of claim
                    long deductible = getPlan(contract.getPlanName()).getDeductible();
                    // totalReceived is totalReceived plus
                    // the max of 0 (so a minimum 0 is add) and the claim amount minus the deductible
                    totalReceived += Math.max(0, claim.getAmount() - deductible);
                }
            }
        }
        // return totalReceived
        return totalReceived;
    }

}
