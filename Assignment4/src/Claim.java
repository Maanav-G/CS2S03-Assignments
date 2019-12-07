import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;

class Claim {
    // variables for the name of contract, the amount,
    // the date (With type Date), and if successful or not
    private String contractName;
    private long amount;
    private Date date;
    private boolean successful;

    static final String inputTag = "CLAIM";

    // object claim with contract name, date and amount
    Claim(HashMap<String, Tag> tags) throws ParseException {
        contractName = tags.get("CONTRACT_NAME").getValue();
        date = Utils.convertDate(tags.get("DATE").getValue());
        amount = Long.parseLong(tags.get("AMOUNT").getValue());
    }

    public String getContractName() {
        return contractName;
    }

    public long getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }

    public boolean wasSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }
}
