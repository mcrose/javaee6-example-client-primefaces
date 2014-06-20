/**
 * 
 */
package py.org.icarusdb.exepction;

/**
 * @author Betto McRose [icarus]
 *         mcrose@icarusdb.com.py
 *
 */
public class LoginFailedException extends Exception
{
    public LoginFailedException()
    {
        super();
    }
    
    public LoginFailedException(String msg)
    {
        super(msg);
    }
}
