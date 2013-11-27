package dataStructures;                                         

import java.io.Serializable; 

class FibNode<K,V> implements Serializable
{                                                                   

    static final long serialVersionUID = 0L;


    // Entry stored in the node.
    private EntryClass<K,V> entry;                                      

    // The degree of the node.
    private int degree;

    // (Pointer to) a child.
    private FibNode<K,V> child;

    // (Pointer to) the left sibling.
    private FibNode<K,V> leftSibling;

    // (Pointer to) the right sibling.
    private FibNode<K,V> rightSibling;

    // (Pointer to) the parent.
    private FibNode<K,V> parent;

    // The node is marked if it has lost a child since
    // it has the current parent. Roots are unmarked.
    private boolean mark;


    public FibNode( K key, V value )
    {    
        entry = new EntryClass<K,V>(key, value);
        degree = 0; 
        child = null;
        leftSibling = this;
        rightSibling = this;
        parent = null;
        mark = false;
    }


    public EntryClass<K,V> getEntry( )                           
    {
        return entry;
    }


    public K getKey( )                           
    {
        return entry.getKey();
    }


    public V getValue( )                           
    {
        return entry.getValue();
    }


    public int getDegree( )                                     
    {    
        return degree;
    }


    public FibNode<K,V> getChild( )                                    
    {    
        return child;
    }


    public FibNode<K,V> getLeftSibling( )                                    
    {    
        return leftSibling;
    }


    public FibNode<K,V> getRightSibling( )                                    
    {    
        return rightSibling;
    }


    public FibNode<K,V> getParent( )                                    
    {    
        return parent;
    }


    public boolean isMarked( )                           
    {
        return mark;
    }


    public void setEntry( EntryClass<K,V> newEntry )
    {    
        entry = newEntry;
    }


    public void setEntry( K newKey, V newValue )
    {    
        entry = new EntryClass<K,V>(newKey, newValue);
    }


    public void setKey( K newKey )
    {    
        entry.setKey(newKey);
    }


    public void setValue( V newValue )
    {    
        entry.setValue(newValue);
    }


    public void setDegree( int newDegree )
    {    
        degree = newDegree;
    }


    public void incrementDegree( )                                
    {    
        degree++;
    }                                                                  


    public void decrementDegree( )                                
    {    
        degree--;
    }                                                                  


    public void setChild( FibNode<K,V> newChild )                   
    {    
        child = newChild;
    }


    public void setLeftSibling( FibNode<K,V> newLeftSibling ) 
    {    
        leftSibling = newLeftSibling;
    }


    public void setRightSibling( FibNode<K,V> newRightSibling )
    {    
        rightSibling = newRightSibling;
    }


    public void makeSingleton( )
    {
        leftSibling = this;
        rightSibling = this;
    }


    public void setParent( FibNode<K,V> newParent )                   
    {    
        parent = newParent;
    }


    public void mark( )                                     
    {    
        mark = true;
    }


    public void unmark( )                                     
    {    
        mark = false;
    }


}
