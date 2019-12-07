import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

class HomePlan extends Plan {
    static final String inputTag = "HOME_PLAN";
    private RangeCriterion homeValueCriterion = new RangeCriterion();
    private RangeCriterion homeAgeCriterion = new RangeCriterion();

    // Change String, Tag to String, List<Tag> in HashMap
    // and add .get(0) to each tag to refer to the list
    HomePlan(HashMap<String, List<Tag>> tags) {
        super(tags);

        // for RangeCriterion, start a loop less than the size of the list
        // that's associated with the key
        // then get every value and add it to the respective RangeCriterion

        if (tags.get("HOME.VALUE") != null) {
            for (int i = 0; i < tags.get("HOME.VALUE").size(); i ++){
                homeValueCriterion.addCriterion(tags.get("HOME.VALUE").get(i));
            }
        }
        if (tags.get("HOME.AGE") != null) {
            for (int i = 0; i < tags.get("HOME.AGE").size(); i ++){
                homeAgeCriterion.addCriterion(tags.get("HOME.AGE").get(i));
            }
        }



    }

    @Override
    boolean isEligible(Insurable insurable, Date date) {
        if (!(insurable instanceof Home))
            return false;
        Home home = (Home) insurable;
        if (!homeValueCriterion.isInRange(home.getValue()))
            return false;

        // Extracting the age of the home
        LocalDate localCurrentDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localBuiltDate = home.getBuildDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        // get a rough estimate of the age of home
        long age = localCurrentDate.getYear() - localBuiltDate.getYear();
        // get the month for the current date and the build date
        long monthNow = localCurrentDate.getMonthValue();
        long monthBuiltDate = localBuiltDate.getMonthValue();
        // if month of the build day is bigger than the month right now
        if (monthBuiltDate > monthNow) {
            // then decrement age by 1
            // because build date hasn't happened yet
            age--;
        }
        // otherwise if the current month is also the build date month
        else if (monthBuiltDate == monthNow) {
            // get the current day, and day of build date
            int dayNow = localCurrentDate.getDayOfMonth();
            int dayBuiltDate = localBuiltDate.getDayOfMonth();
            // if day of build date is bigger than the current day
            if (dayBuiltDate > dayNow) {
                // then decrement age by 1
                // because build date hasn't happened yet
                age--;
            }
        }


        // Checking if the age is in the range.
        return homeAgeCriterion.isInRange(age);
    }

    @Override
    ArrayList<? extends Insurable> getInsuredItems(Customer customer, Database database) {
        return database.getHomesByOwnerName(customer.getName());
    }

    @Override
    Insurable getInsuredItem(String insurableID, Database database) {
        return database.getHomeByPostalCode(insurableID);
    }
}
