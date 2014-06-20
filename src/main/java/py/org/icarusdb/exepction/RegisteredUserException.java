/**
 * 
 */
package py.org.icarusdb.exepction;

/**
 * @author Betto McRose [icarus]
           mcrose@icarusdb.com.py
 *
 */
public class RegisteredUserException extends Exception
{
    /**
     * 
     */
    private static final long serialVersionUID = 7150338845615198733L;

    public RegisteredUserException()
    {
        super();
    }
    
    public RegisteredUserException(String msg)
    {
        super(msg);
    }
}
