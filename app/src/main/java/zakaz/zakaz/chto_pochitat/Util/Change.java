package zakaz.zakaz.chto_pochitat.Util;

public class Change {

    private static boolean changes = false;

    private static Change instance;
    private Change(){}

    public static Change getInstance() {
        if (instance == null) {
            instance = new Change();
        }
        return instance;
    }

    public static boolean isChanges() {
        return changes;
    }

    public static void setChanges(boolean changes) {
        Change.changes = changes;
    }
}
