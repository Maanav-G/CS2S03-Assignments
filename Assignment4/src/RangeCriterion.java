class RangeCriterion {
    // variables for max and min values
    private long maxValue = Long.MAX_VALUE;
    private long minValue = Long.MIN_VALUE;

    void addCriterion(Tag tag) {
        if (tag.getRelation() == Tag.Relation.LARGER) {
            // then the new min value is the max value
            // between the current value, and the previous max value
            minValue = Math.max(minValue, Long.parseLong(tag.getValue()));
        }
        if (tag.getRelation() == Tag.Relation.SMALLER) {
            // then the new max value is the min value
            // between the current value, and the previous max value
            maxValue = Math.min(maxValue, Long.parseLong(tag.getValue()));
        }
    }

    boolean isInRange(long value) {
        // if value is less than max value and greater than min
        // hence in specified range
        // then return true, else return false
        if (value < maxValue && value > minValue)
            return true;
        return false;
    }
}
