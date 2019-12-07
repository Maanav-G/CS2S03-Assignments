class Tag {
    // enum of relations
    public enum Relation {
        SMALLER, LARGER, EQUAL
    }
    // variables of relation (of type Enum Relation), name and value
    private Relation relation;
    private String name;
    private String value;

    Tag(String[] tokens) {
        // name is 1st toke in tokens
        name = tokens[0];

        // switch with 2nd token, first character
        switch (tokens[1].charAt(0)) {
            // if variable '<' then smaller
            case '<':
                relation = Relation.SMALLER;
                break;
            // if variable '>' then larger
            case '>':
                relation = Relation.LARGER;
                break;
            // if variable '=' then equal
            case '=':
                relation = Relation.EQUAL;
                break;
            // otherwise throw bad command exception of invalid tag
            default:
                throw new BadCommandException("Invalid tag: ill-defined bad relation.");
        }
        // value is 3rd token in tokens
        value = tokens[2];
    }

    public Relation getRelation() {
        return relation;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
