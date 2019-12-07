import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

public class Company extends Insurable {

    private String companyName;


    static final String inputTag = "COMPANY";

    // Change String, Tag to String, List<Tag> in HashMap
    // and add .get(0) to each tag to refer to the list
    Company(HashMap<String, List<Tag>> tags) throws ParseException {
        super(tags);
        companyName = tags.get("COMPANY_NAME").get(0).getValue();
    }

    public String getCompanyName() {
        return companyName;
    }

    public static String getInputTag() {
        return inputTag;
    }

}
