/**
 * 
 */
package py.org.icarusdb.exepction;

/**
 * @author Betto McRose [icarus]
           mcrose@icarusdb.com.py
 * 
 */
public class ActiveUserException extends Exception
{

    public ActiveUserException()
    {
        super();
    }

    public ActiveUserException(String message)
    {
        super(message);
    }
}
