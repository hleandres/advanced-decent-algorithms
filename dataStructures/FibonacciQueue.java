package dataStructures;

import java.util.Map;
import java.util.HashMap;

public class FibonacciQueue<K extends Comparable<? super K>, V>
    implements AdaptMinPriQueue<K,V>
{

    static final long serialVersionUID = 0L;

    // Default size of the dictionary.
    protected static final int DEFAULT_CAPACITY = 100;

    // The Fibonacci queue.
    protected FibQueue<K,V> queue; 

    // The dictionary.
    protected Map<V, FibNode<K,V>> dict;


    public FibonacciQueue( int capacity )
    {
        queue = new FibQueue<K,V>();
        dict = new HashMap<V, FibNode<K,V>>(capacity);
    }


    public FibonacciQueue( )
    {
        this(DEFAULT_CAPACITY);
    }


    // Returns true iff the priority queue contains no entries.
    public boolean isEmpty( )
    {
        return queue.isEmpty();
    }


    // Returns the number of entries in the priority queue.
    public int size( )
    {
        return queue.size();
    }


    // Returns an entry with the smallest key in the priority queue.      
    public Entry<K,V> minEntry( ) throws EmptyQueueException
    { 
        if ( this.isEmpty() )
            throw new EmptyQueueException();

        return queue.minEntry();
    }


    // Inserts the entry (key, value) in the priority queue.
    public void insert( K key, V value ) throws InvalidValueException
    {
        if ( dict.containsKey(value) )
            throw new InvalidValueException(
                "The queue already has an entry with the value.");

        FibNode<K,V> node = queue.insert(key, value);
        dict.put(value, node);
    }


    // Removes an entry with the smallest key from the priority queue
    // and returns that entry.
    public Entry<K,V> removeMin( ) throws EmptyQueueException
    {
        if ( this.isEmpty() )
            throw new EmptyQueueException();

        Entry<K,V> entry = queue.removeMin();
        dict.remove( entry.getValue() );
        return entry;
    }


    // If the priority queue contains an entry with the specified value,
    // returns the associated key and replaces it by the specified key
    // (which is less than the old one). Otherwise, returns null.
    public K decreaseKey( V value, K newKey ) throws InvalidKeyException
    {
        FibNode<K,V> node = dict.get(value);
        if ( node == null )
            return null;

        K oldKey = node.getKey();
        if ( oldKey.compareTo(newKey) <= 0 )
            throw new InvalidKeyException(
                "The specified key is not less than the existent one.");

        queue.decreaseKey(node, newKey);
        return oldKey;
    } 


    // If the priority queue contains an entry with the specified value,
    // removes and returns that entry. Otherwise, returns null.
    public Entry<K,V> remove( V value )
    {
        FibNode<K,V> node = dict.remove(value);
        if ( node == null )
            return null;

        queue.remove(node);
        return node.getEntry();

    }


}
