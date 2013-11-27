package dataStructures;

public interface AdaptMinPriQueue<K extends Comparable<? super K>, V>
    extends MinPriorityQueue<K,V>
{

    // If the priority queue contains an entry with the specified value,
    // returns the associated key and replaces it by the specified key
    // (which is less than the old one). Otherwise, returns null.
    K decreaseKey( V value, K newKey ) throws InvalidKeyException; 

    // If the priority queue contains an entry with the specified value,
    // removes and returns that entry. Otherwise, returns null.
    Entry<K,V> remove( V value );

}
