import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

class Customer {
    private String name;
    private Date dateOfBirth;
    private long income;

    static final String inputTag = "CUSTOMER";

    // Change String, Tag to String, List<Tag> in HashMap
    // and add .get(0) to each tag to refer to the list
    Customer(HashMap<String, List<Tag>> tags) throws ParseException {
        name = tags.get("NAME").get(0).getValue();
        dateOfBirth = Utils.convertDate(tags.get("DATE_OF_BIRTH").get(0).getValue());
        income = Long.parseLong(tags.get("INCOME").get(0).getValue());
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

    // gets wealth of customer,
    // by calling the function from the database
    public long getWealth(Database database){
        long wealth = database.getWealthOfCustomer(name);
        return wealth;
    }

    public static String getInputTag() {
        return inputTag;
    }
}
