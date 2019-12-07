import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

class Home extends Insurable {
    private String postalCode;
    private Date buildDate;

    static final String inputTag = "HOME";

    // Change String, Tag to String, List<Tag> in HashMap
    // and add .get(0) to each tag to refer to the list
    Home(HashMap<String, List<Tag>> tags) throws ParseException {
        super(tags);
        postalCode = tags.get("POSTAL_CODE").get(0).getValue();
        buildDate = Utils.convertDate(tags.get("BUILD_DATE").get(0).getValue());
    }

    public String getPostalCode() {
        return postalCode;
    }

    public Date getBuildDate() {
        return buildDate;
    }

    public static String getInputTag() {
        return inputTag;
    }
}
