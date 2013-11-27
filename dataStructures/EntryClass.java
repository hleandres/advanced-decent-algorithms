package dataStructures;

class EntryClass<K,V> implements Entry<K,V>
{

    static final long serialVersionUID = 0L;


    // Key stored in the entry.
    private K key;

    // Value stored in the entry.
    private V value;


    public EntryClass( K theKey, V theValue )
    {
        key = theKey;
        value = theValue;
    }


    // Returns the key in the entry.
    public K getKey( )
    {
        return key;
    }


    // Returns the value in the entry.
    public V getValue( )
    {
        return value;
    }


    public void setKey( K newKey )
    {
        key = newKey;
    }


    public void setValue( V newValue )
    {
        value = newValue;
    }


}
