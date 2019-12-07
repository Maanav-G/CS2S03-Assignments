import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;

class Contract {
    // variables for the contract name, customer name, plan name,
    // start date (type date), end date (type date) and discount percentage
    private String contractName;
    private String customerName;
    private String planName;
    private Date startDate;
    private Date endDate;
    private int discountPercentage;

    static final String inputTag = "CONTRACT";

    // object contract
    Contract(HashMap<String, Tag> tags) throws ParseException {
        contractName = tags.get("CONTRACT_NAME").getValue();
        customerName = tags.get("CUSTOMER_NAME").getValue();
        planName = tags.get("PLAN_NAME").getValue();
        startDate = Utils.convertDate(tags.get("START_DATE").getValue());
        endDate = Utils.convertDate(tags.get("END_DATE").getValue());
        discountPercentage = Integer.parseInt(tags.get("DISCOUNT_PERCENTAGE").getValue());
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getPlanName() {
        return planName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() { return endDate; }


    public int getDiscountPercentage() {
        return discountPercentage;
    }

    public static String getInputTag() {
        return inputTag;
    }

    public String getContractName() {
        return contractName;
    }
}
