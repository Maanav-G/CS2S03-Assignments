import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;

class Customer {
    // variables for name, date of birth (type date), and income
    private String name;
    private Date dateOfBirth;
    private long income;

    static final String inputTag = "CUSTOMER";

    // object customer
    Customer(HashMap<String, Tag> tags) throws ParseException {
        // get value after "Name"
        name = tags.get("NAME").getValue();
        // get value after "DATE_OF_BIRTH"
        dateOfBirth = Utils.convertDate(tags.get("DATE_OF_BIRTH").getValue());
        // get value after "INCOME"
        income = Long.parseLong(tags.get("INCOME").getValue());
    }

    public String getName() {
        return name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public long getIncome() {
        return income;
    }

    public static String getInputTag() {
        return inputTag;
    }
}
