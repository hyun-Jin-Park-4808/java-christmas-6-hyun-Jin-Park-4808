package christmas.main.type;

public enum BadgeType {
    STAR("별"),
    TREE("트리"),
    SANTA("산타");

    private String badgeName;

    private BadgeType(String eventName) {
        this.badgeName = eventName;
    }

    public String getBadgeName() {
        return this.badgeName;
    }
}
