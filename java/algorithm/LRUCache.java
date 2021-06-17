class LRUCache {

    Map<Integer, Integer> cache;

    public LRUCache(int capacity) {
        cache = new LinkedHashMap<Integer, Integer>(capacity, 1, true){
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size() > capacity;
            }
        };
    }
    
    public int get(int key) {
        if (cache.get(key) == null) return -1;
        return cache.get(key);
    }
    
    public void put(int key, int value) {
        cache.put(key, value);
    }
}
