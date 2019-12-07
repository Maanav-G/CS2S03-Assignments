import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;

class HomePlan extends Plan {

    static final String inputTag = "HOME_PLAN";
    // set home age and home value criterion of type RangeCriterion
    private RangeCriterion homeValueCriterion = new RangeCriterion();
    private RangeCriterion homeAgeCriterion = new RangeCriterion();

    // object home plan with variables from Plan
    // and new field variables
    HomePlan(HashMap<String, Tag> tags) {
        super(tags);
        // if tag "HOME.VALUE" exists, add criterion to homeValueCriterion
        if (tags.get("HOME.VALUE") != null) {
            homeValueCriterion.addCriterion(tags.get("HOME.VALUE"));
        }
        // if tag "HOME.AGE" exists, add criterion to homeAgeCriterion
        if (tags.get("HOME.AGE") != null) {
            homeAgeCriterion.addCriterion(tags.get("HOME.AGE"));
        }
    }

    // override isEligible from Plan
    @Override
    boolean isEligible(Insurable insurable, Date date) {
        // if insurable instanceof home is false
        if (!(insurable instanceof Home))
            // then return false
            return false;
        Home home = (Home) insurable;
        // check if homeValueCriterion is in the range of the value of home
        if (!homeValueCriterion.isInRange(home.getValue()))
            // then return false
            return false;

        // Extracting the age of the customer
        LocalDate localCurrentDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localBuiltDate = home.getBuildDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        long age = localCurrentDate.getYear() - localBuiltDate.getYear();;
        // Checking if the age is in the range.
        return homeAgeCriterion.isInRange(age);
    }

    // override getInsuredItem from Plan
    @Override
    Insurable getInsuredItem(Customer customer, Database database) {
        return database.getHome(customer.getName());
    }

}
