package org.com.br.Core.Domain.Rules;

import java.util.Hashtable;
import java.util.List;
import java.util.function.Function;

public class ListToHashtableAdapter<K, V> {

    public Hashtable<K, V> listToHashtable(List<V> list, Function<V, K> keyMapper) {
        Hashtable<K, V> hashtable = new Hashtable<>();
        for (V item : list) {
            K key = keyMapper.apply(item);
            hashtable.put(key, item);
        }
        return hashtable;
    }

    public List<V> hashtableToList(Hashtable<K, V> hashtable) {
        return List.copyOf(hashtable.values());
    }
}
