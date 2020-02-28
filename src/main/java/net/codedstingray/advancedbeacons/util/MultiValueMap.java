/*
 * AdvancedBeacons, a Minecraft plugin for a better beacon experience
 * Copyright (C) CodedStingray <http://codedstingray.net>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package net.codedstingray.advancedbeacons.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * In general, boolean methods return true if the map was modified and false if it wasn't modified.
 * @param <K>
 * @param <V>
 */
public class MultiValueMap<K, V> {

    private HashMap<K, HashSet<V>> internalMap = new HashMap<>();

    public int size() {
        return internalMap.size();
    }

    public boolean isEmpty() {
        return internalMap.isEmpty();
    }

    public boolean containsKey(K key) {
        return internalMap.containsKey(key);
    }

    public boolean containsValueForKey(K key, V value) {
        HashSet<V> values = internalMap.get(key);
        if(values == null)
            return false;

        return values.contains(value);
    }

    /**
     * NOTE: slow-ish, runs in O(n) with n = size()
     */
    public HashSet<K> getKeysForValue(V value) {
        HashSet<K> ret = new HashSet<>();

        for(Map.Entry<K, HashSet<V>> entry: internalMap.entrySet()) {
            HashSet<V> values = entry.getValue();
            if(values != null && values.contains(value)) {
                ret.add(entry.getKey());
            }
        }

        return ret;
    }

    public HashSet<V> get(K key) {
        return internalMap.get(key);
    }

    /**
     * @return true if the value has been added to the map, false if the value was already mapped to this key
     */
    public boolean put(K key, V value) {
        HashSet<V> values = internalMap.get(key);

        if(values == null) {
            values = new HashSet<V>();
            values.add(value);
            internalMap.put(key, values);
            return true;
        }

        return values.add(value);
    }

    public boolean removeValue(K key, V value) {
        HashSet<V> values = internalMap.get(key);

        if(values == null) {
            return false;
        }

        return values.remove(value);
    }

    /**
     * Clears the set for the given key, but doesn't remove hte key or set
     */
    public void clearForKey(K key) {
        HashSet<V> values = internalMap.get(key);
        if(values != null) {
            values.clear();
        }
    }

    public void clearAllKeys() {
        for(Map.Entry<K, HashSet<V>> entry: internalMap.entrySet()) {
            HashSet<V> values = entry.getValue();
            if(values != null) {
                values.clear();
            }
        }
    }

    public HashSet<V> removeKey(K key) {
        return internalMap.remove(key);
    }

    public void clear() {
        internalMap.clear();
    }
}
