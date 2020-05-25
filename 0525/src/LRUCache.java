import java.util.LinkedHashMap;
import java.util.Map;

class LRUCache {

    private LinkedHashMap lru;

    public LRUCache(int capacity) {
        lru = new LinkedHashMap(capacity,0.75f,true){
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return lru.size() > capacity;
            }
        };
    }

    public int get(int key) {
        if(lru.containsKey(key)) {
            return (int) lru.get(key);
        }else {
            return -1;
        }
    }

    public void put(int key, int value) {
        lru.put(key,value);
    }
}