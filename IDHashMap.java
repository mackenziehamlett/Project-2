import java.util.HashMap;

public class IDHashMap {
    private HashMap<String, Object> ids = new HashMap<String, Object>();
    public IDHashMap() {}

    /// Used for creating a copy with preset data
    private IDHashMap(HashMap<String, Object> id) {
        this.ids = id;
    }

    public void setToken(String id, Object reference) {
        ids.put(id, reference);
    }

    public Object getToken(String id) {
        return ids.get(id);
    }

    /// Use this for when going up the call stack or something
    public IDHashMap getCopy() {
        return new IDHashMap(this.ids);
    }
}
