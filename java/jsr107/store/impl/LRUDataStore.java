package jsr107.store.impl;

import jsr107.PutStatus;
import jsr107.store.DataStore;
import jsr107.store.ValueHolder;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The type Lru data store.
 *
 * @param <K> the type parameter
 * @param <V> the type parameter
 * @author liufei
 * @date 11 /12/19 使用一个链表来记录并执行最近最久未使用淘汰算法 链表头部表示最近访问的元素,删除操作从链表尾部进行
 */
public class LRUDataStore<K, V> implements DataStore<K, V> {
    /**
     * The constant MAX_SIZE.
     */
    public static final int MAX_SIZE = 10;
    private ConcurrentHashMap<K, LRUEntity<K, ValueHolder<V>>> map = new ConcurrentHashMap<>();

    @Override
    public ValueHolder<V> get(K key) {
        LRUEntity<K, ValueHolder<V>> entity = getEntity(key);
        if (Objects.isNull(entity)) {
            return null;
        }
        moveToFirst(entity);
        return entity.getValue();
    }

    @Override
    public PutStatus put(K key, V value) {
        LRUEntity<K, ValueHolder<V>> entry = getEntity(key);
        PutStatus status = PutStatus.NOOP;
        if (entry == null) {
            if (map.size() >= MAX_SIZE) {
//                map.remove(last.getKey());
                removeLast();
            }
            entry = new LRUEntity<>(key, new BasicValueHolder<>(value));
            status = PutStatus.PUT;
        } else {
            entry.setValue(new BasicValueHolder<>(value));
            status = PutStatus.UPDATE;
        }
        moveToFirst(entry);
        map.put(key, entry);
        return status;
    }

    @Override
    public ValueHolder<V> remove(K key) {
        return null;
    }

    @Override
    public void clear() {

    }

    /**
     * Gets entity.
     *
     * @param key the key
     * @return the entity
     */
    public LRUEntity<K, ValueHolder<V>> getEntity(K key) {
        return null;
    }

    /**
     * Move to first.移动节点到链表的表头，表示数据最近被访问过
     *
     * @param entry the entity
     */
    public void moveToFirst(LRUEntity<K, ValueHolder<V>> entry) {
    }

    /**
     * Remove last.移除链表的末尾节点，表示删除最近最久未被访问的节点
     *
     * @return the lru entity
     */
    public LRUEntity<K, ValueHolder<V>> removeLast() {
        return null;

    }
}
