import java.util.HashMap;
import java.util.List;

class Insurable {
    protected String ownerName;
    protected long value;

    // Change String, Tag to String, List<Tag> in HashMap
    // and add .get(0) to each tag to refer to the list
    Insurable(HashMap<String, List<Tag>> tags) {
        ownerName = tags.get("OWNER_NAME").get(0).getValue();
        value = Long.parseLong(tags.get("VALUE").get(0).getValue());
    }

    public String getOwnerName() {
        return ownerName;
    }

    public long getValue() {
        return value;
    }
}
