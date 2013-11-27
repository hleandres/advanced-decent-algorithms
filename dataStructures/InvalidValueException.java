package dataStructures;

public class InvalidValueException extends RuntimeException
{

    static final long serialVersionUID = 0L;


    public InvalidValueException( )
    {
        super();
    }

    public InvalidValueException( String message )
    {
        super(message);
    }

}

