import java.util.HashMap;

class Insurable {
    // create protected variables for owner name and value of insurable
    protected String ownerName;
    protected long value;

    // object insurable
    Insurable(HashMap<String, Tag> tags) {
        ownerName = tags.get("OWNER_NAME").getValue();
        value = Long.parseLong(tags.get("VALUE").getValue());
    }

    public String getOwnerName() {
        return ownerName;
    }

    public long getValue() {
        return value;
    }
}
