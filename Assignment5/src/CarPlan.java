import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

class CarPlan extends Plan {
    static final String inputTag = "CAR_PLAN";
    RangeCriterion mileageCriterion = new RangeCriterion();

    // Change String, Tag to String, List<Tag> in HashMap
    // and add .get(0) to each tag to refer to the list
    CarPlan(HashMap<String, List<Tag>> tags) {
        super(tags);
        if (tags.get("CAR.MILEAGE") != null) {
            // for RangeCriterion, start a loop less than the size of the list
            // that's associated with the key
            // then get every value and add it to the respective RangeCriterion
            for (int i = 0; i < tags.get("CAR.MILEAGE").size(); i ++){
                mileageCriterion.addCriterion(tags.get("CAR.MILEAGE").get(i));
            }
        }
    }

    @Override
    boolean isEligible(Insurable insurable, Date date) {
        if (!(insurable instanceof Car))
            return false;
        Car car = (Car) insurable;
        return mileageCriterion.isInRange(car.getMileage());
    }

    @Override
    ArrayList<? extends Insurable> getInsuredItems(Customer customer, Database database) {
        return database.getCarsByOwnerName(customer.getName());
    }

    @Override
    Insurable getInsuredItem(String insurableID, Database database) {
        return database.getCarByPlateNumber(insurableID);
    }

}
