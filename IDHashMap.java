import java.util.HashMap;

public class IDHashMap {
    private HashMap<String, Object> ids = new HashMap<String, Object>();
    public IDHashMap() {
        size = 0;
    }

    public int size;

    

    /// Used for creating a copy with preset data
    private IDHashMap(HashMap<String, Object> id) {
        this.ids = id;
        this.size = id.size();
    }

    public void setToken(String id, Object reference) {
        ids.put(id, reference);
        this.size++;
    }

    
    public Object getToken(String id) {
        return ids.get(id);
    }

    /// Use this for when going up the call stack or something
    public IDHashMap getCopy() {
        return new IDHashMap(this.ids);
    }

    public void print() {
        System.out.println(this.ids);
    }
}
