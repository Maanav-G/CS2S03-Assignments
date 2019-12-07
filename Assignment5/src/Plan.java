import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

abstract class Plan {
    String name;
    long premium;
    long maxCoveragePerClaim;
    long deductible;
    RangeCriterion customerAgeCriterion = new RangeCriterion();
    RangeCriterion customerIncomeCriterion = new RangeCriterion();
    RangeCriterion customerWealthCriterion = new RangeCriterion();

    // Change String, Tag to String, List<Tag> in HashMap
    // and add .get(0) to each tag to refer to the list
    Plan(HashMap<String, List<Tag>> tags) {
        name = tags.get("NAME").get(0).getValue();
        premium = Integer.parseInt(tags.get("PREMIUM").get(0).getValue());
        maxCoveragePerClaim = Integer.parseInt(tags.get("MAX_COVERAGE_PER_CLAIM").get(0).getValue());
        deductible = Integer.parseInt(tags.get("DEDUCTIBLE").get(0).getValue());

        // for RangeCriterion, start a loop less than the size of the list
        // that's associated with the key
        // then get every value and add it to the respective RangeCriterion
        if (tags.get("CUSTOMER.AGE") != null) {
            for (int i = 0; i < tags.get("CUSTOMER.AGE").size(); i ++){
                customerAgeCriterion.addCriterion(tags.get("CUSTOMER.AGE").get(i));
            }
        }
        if (tags.get("CUSTOMER.INCOME") != null) {
            for (int i = 0; i < tags.get("CUSTOMER.INCOME").size(); i ++){
                customerIncomeCriterion.addCriterion(tags.get("CUSTOMER.INCOME").get(i));
            }
        }
        if (tags.get("CUSTOMER.WEALTH") != null) {
            for (int i = 0; i < tags.get("CUSTOMER.WEALTH").size(); i ++){
                customerWealthCriterion.addCriterion(tags.get("CUSTOMER.WEALTH").get(i));
            }
        }
    }

    abstract boolean isEligible(Insurable insurable, Date date);

    abstract ArrayList<? extends Insurable> getInsuredItems(Customer customer, Database database);

    abstract Insurable getInsuredItem(String insurableID, Database database);

    boolean isEligible(Customer customer, Date currentDate, Database database) {
        // Extracting the age of the customer
        LocalDate localCurrentDate = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localBirthDate = customer.getDateOfBirth().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        // get a rough estimate of the age
        long age = localCurrentDate.getYear() - localBirthDate.getYear();
        // get the month for the current date and the birthday
        long monthNow = localCurrentDate.getMonthValue();
        long monthBirthDate = localBirthDate.getMonthValue();
        // if month of the birthday is bigger than the month right now
        if (monthBirthDate > monthNow) {
            // then decrement age by 1
            // because birthday hasn't happened yet
            age--;
        }
        // otherwise if the current month is also the birthday month
        else if (monthBirthDate == monthNow) {
            // get the current day, and day of birthday
            int dayNow = localCurrentDate.getDayOfMonth();
            int dayBirthDate = localBirthDate.getDayOfMonth();
            // if day of birthday is bigger than the current day
            if (dayBirthDate > dayNow) {
                // then decrement age by 1
                // because birthday hasn't happened yet
                age--;
            }
        }
        // Checking if the age is in the range.
        if (!customerAgeCriterion.isInRange(age))
            return false;
        // Checking if the income and wealth is in the range.
        if ((customerIncomeCriterion.isInRange(customer.getIncome()))
                && (customerWealthCriterion.isInRange(customer.getWealth(database)))){
            return true;
        } else {
            return false;
        }
    }

    String getName() {
        return name;
    }

    long getPremium() {
        return premium;
    }

    long getMaxCoveragePerClaim() {
        return maxCoveragePerClaim;
    }

    long getDeductible() {
        return deductible;
    }
}
