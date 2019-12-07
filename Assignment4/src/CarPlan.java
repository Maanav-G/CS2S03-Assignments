import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;

class CarPlan extends Plan {
    static final String inputTag = "CAR_PLAN";
    // set mileage criterion of type RangeCriterion
    RangeCriterion mileageCriterion = new RangeCriterion();

    // object CarPlan, with variables from Plan
    // and new field variables
    CarPlan(HashMap<String, Tag> tags) {

        super(tags);
        // if tag "CAR.MILEAGE" exists, add criterion to mileageCriterion
        if (tags.get("CAR.MILEAGE") != null) {
            mileageCriterion.addCriterion(tags.get("CAR.MILEAGE"));
        }
    }

    // override isEligible from Plan
    @Override
    boolean isEligible(Insurable insurable, Date date) {
        // if insurance instance of Car is false
        if (!(insurable instanceof Car))
            // then return false
            return false;
        Car car = (Car) insurable;
        return mileageCriterion.isInRange(car.getMileague());
    }

    // override getInsuredItem from Plan
    @Override
    Insurable getInsuredItem(Customer customer, Database database) {
        return database.getCar(customer.getName());
    }
}
