package dataStructures;

import java.io.Serializable;

class FibQueue<K extends Comparable<? super K>, V>
    implements Serializable
{

    static final long serialVersionUID = 0L;


    public static final double GOLDEN_RATIO = 1.618;


    // (Pointer to) a tree with the smallest key.
    protected FibNode<K,V> min; 

    // Number of entries in the priority queue.
    protected int currentSize;

	public FibQueue( )
    {
        min = null;
        currentSize = 0;
    }


    // Returns true iff the priority queue contains no entries.
    public boolean isEmpty( )
    {
        return min == null;
    }


    // Returns the number of entries in the priority queue.
    public int size( ) 
    {
        return currentSize;
    }


    // Returns an entry with the smallest key in the queue.
    // Pre-condition: the queue is not empty.
    public Entry<K,V> minEntry( )
    { 
        return min.getEntry();
    }


    // Inserts the entry (key, value) in the queue and
    // returns the node which contains that entry.
    public FibNode<K,V> insert( K key, V value )
    {
        FibNode<K,V> newTree = new FibNode<K,V>(key, value);
        if ( this.isEmpty() )
            min = newTree;
        else 
        {
            this.insertTree(min, newTree);
            // Update min.
            if ( key.compareTo( min.getKey() ) < 0 )
                min = newTree;
        }
        currentSize++;
        return newTree;
    }


    // Inserts the specified tree in the list,
    // to the left of the specified head.
    // Pre-condition: the list is not empty.
    protected void insertTree( FibNode<K,V> head, FibNode<K,V> newTree )
    {
        FibNode<K,V> leftTree = head.getLeftSibling();
        newTree.setLeftSibling(leftTree);
        newTree.setRightSibling(head);
        leftTree.setRightSibling(newTree);
        head.setLeftSibling(newTree);
    }


    // Removes an entry with the smallest key from the queue
    // and returns that entry.
    // Pre-condition: the queue is not empty.
    public Entry<K,V> removeMin( )
    {
        Entry<K,V> entry = min.getEntry();
        this.insertChildren( min.getChild() );
        this.removeMinTree();
        if ( min != null )
            this.consolidate();
        currentSize--;
        return entry;
    }


    // Inserts every element of the specified list in the queue.
    // Pre-condition: the queue is not empty.
    protected void insertChildren( FibNode<K,V> firstChild )
    {
        if ( firstChild != null )
        {
            FibNode<K,V> tree = firstChild;
            do 
            {
                FibNode<K,V> nextTree = tree.getRightSibling();
                tree.setParent(null);
                tree.unmark();
                this.insertTree(min, tree);
                tree = nextTree;
            }
            while ( tree != firstChild );
        }
    }


    // Removes the head of the queue and updates min.
    // If the queue becomes empty, min is set to null;
    // otherwise, min points to one of the remaining trees.
    // Pre-condition: the queue is not empty.
    protected void removeMinTree( )
    {	
    	//Se min for irmao de si proprio, so ha uma arvore
    	if(min.getLeftSibling().equals(min)){
    		min = null;
    	}
    	//Senao, liga os irmaos do min um ao outro, e poe o direito como min
    	else{
    		FibNode<K, V> rightSibling = min.getRightSibling();
    		
    		min.getLeftSibling().setRightSibling(min.getRightSibling());
			min.getRightSibling().setLeftSibling(min.getLeftSibling());

    		min = rightSibling;
    	}
    	
    }

    // Rebuilds the queue in such a way that  
    // every resulting tree has a distinct degree.
    // Pre-condition: the queue is not empty.
    protected void consolidate( )
    {
        FibNode<K,V>[] trees = this.buildTrees();
        this.rebuildQueue(trees);
    }


    // Returns an array with the new queue trees (indexed by degree).
    // Pre-condition: the queue is not empty.
    @SuppressWarnings("unchecked")
    protected FibNode<K,V>[] buildTrees( )
    {
        int capacity = this.maxDegree() + 1;
        // Compiler would give a warning.
        FibNode<K,V>[] trees = (FibNode<K,V>[]) new FibNode[capacity];
        
        FibNode<K,V> currTree = min;
        do 
        {
            int currDegree = currTree.getDegree();
            FibNode<K,V> nextTree = currTree.getRightSibling();
            while ( trees[currDegree] != null )
            {
                currTree = this.linkTrees(currTree, trees[currDegree]);
                trees[currDegree] = null;
                currDegree++;
            }
            trees[currDegree] = currTree;
            currTree = nextTree;
        }
        while ( currTree != min );

        return trees;
    }


    // Rebuilds the queue with the trees in the specified array.
    // Pre-condition: the queue is not empty.
    protected void rebuildQueue( FibNode<K,V>[] trees )
    {
    	min = null;
    	//TODO min = null -> make singleton?!?!
    	
        for(int i = 0; i < trees.length; i++){
        	//Verifca se ha arvore com esse grau
        	if(trees[i] != null){
        		//Se min estiver a null, esta na primeira arvore e inicializa
        		if(min == null){
        			min = trees[i];
        			min.makeSingleton();
        		}
        		//Senao ja ta pelo menos na segunda arvore, e actualiza irmaos
        		//e talvez min
        		else{
        			insertTree(min, trees[i]);
        			
        			//Verifica se nova arvore e min
        			if(trees[i].getKey().compareTo(min.getKey()) < 0){
        				min = trees[i];
        			}
        		}
        	}
        }
    }


    // Returns the maximum possible degree of a tree in the queue.
    protected int maxDegree( )
    {
        return (int) ( Math.log(currentSize) / Math.log(GOLDEN_RATIO) );
    }

    // Links two trees of degree k and
    // returns the resulting tree of degree k + 1.
    protected FibNode<K,V> linkTrees( FibNode<K,V> tree1, FibNode<K,V> tree2 )
    {
    	
    	//TODO é preciso tirar a árvore (o primeiro argumento) da fila. 
    	//A árvore que estava no vector (2º argumento) já deveria ter saído da fila
    	//???
    	
    	tree1.makeSingleton();
    	tree2.makeSingleton();
    	
    	FibNode<K,V> root;
    	FibNode<K,V> child;
    	
    	//Define a raiz da nova arvore e o filho a adiciona-la
        if(tree1.getKey().compareTo(tree2.getKey()) < 0){
        	root = tree1;
        	child = tree2;
        }
        else{
        	root = tree2;
        	child = tree1;
        }
        
        
        
        child.setParent(root);
        //TODO pai e filho unico?
        
        //Se raiz tiver grau um, aponta para a outra arvore e arvore
        //passa a ser filho unico
        if(root.getDegree() == 0){
        	root.setChild(child);
        	child.makeSingleton();
        }
        else{
        	insertTree(root.getChild(), child);
        }
        	
        root.incrementDegree();
        return root; 
    }


    // Replaces the key in the specified node by the specified key.
    // Pre-condition: the specified key is less than the one in the node.
    public void decreaseKey( FibNode<K,V> node, K newKey ) 
    {
        node.setKey(newKey);
        FibNode<K,V> parent = node.getParent();
        if ( parent != null && parent.getKey().compareTo(newKey) > 0 )
        {
            this.cut(node, parent);
            this.cascadingCut(parent);
        }
        // Update min.
        if ( newKey.compareTo( min.getKey() ) < 0 )
            min = node;
    } 


    // Cuts the link between the specified node and its parent
    // and makes the specified node a queue root.
    // The parent's degree is decreased but its mark is not changed.
    protected void cut( FibNode<K,V> node, FibNode<K,V> parent )
    {
        this.removeChild(parent, node);
        node.setParent(null);
        node.unmark();
        this.insertTree(min, node);
    }


    // Recursively cuts all marked ancestors of the specified node
    // (starting with the specified node) until an unmarked node is found.
    // Marks that unmarked node, unless it is the root.
    protected void cascadingCut( FibNode<K,V> node )
    {
        FibNode<K,V> parent = node.getParent();
        if ( parent != null )
            if ( node.isMarked() )
            {
                this.cut(node, parent);
                this.cascadingCut(parent);
            }
            else
                node.mark();
    }


    // Removes the specified child from the list of children
    // of the specified parent.
    protected void removeChild( FibNode<K,V> parent, FibNode<K,V> child )
    {
    	//Verifica se child e filho de parent
    	if(child.getParent().equals(parent)){
    		//Verifica se pai aponta para esse filho
    		if(parent.getChild().equals(child)){
    			//Se irmao direito do filho for igual a si proprio e filho unico
    			//e pai passa a ter child como null
    			if(child.getRightSibling().equals(child)){
    				parent.setChild(null);
    			}
    			//Senao, coloca pai a apontar para irmao direito, e actualiza 
    			//irmaos do no a remover
    			else{
    				parent.setChild(child.getRightSibling());
    				
    				child.getLeftSibling().setRightSibling(child.getRightSibling());
    				child.getRightSibling().setLeftSibling(child.getLeftSibling());
    			}
    		}
    		//Senao, tem pelo menos um irmao, e poe os irmaos a apontar um para
    		//o outro
    		else{
    			child.getLeftSibling().setRightSibling(child.getRightSibling());
				child.getRightSibling().setLeftSibling(child.getLeftSibling());
    		}
    		child.setParent(null);
    		parent.decrementDegree();
    	}  
    }

    // Removes the entry in the specified node.
    public void remove( FibNode<K,V> node )
    {
        FibNode<K,V> parent = node.getParent();
        if ( parent != null )
        {
            this.cut(node, parent);
            this.cascadingCut(parent);
        }
        // Update min to remove the node.
        min = node;
        this.removeMin();
    }

 
}
