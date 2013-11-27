package dataStructures;

public class InvalidKeyException extends RuntimeException
{

    static final long serialVersionUID = 0L;


    public InvalidKeyException( )
    {
        super();
    }

    public InvalidKeyException( String message )
    {
        super(message);
    }

}

