/**
 * 
 */
package py.org.icarusdb.exepction;

import java.io.IOException;

/**
 * @author Betto McRose [icarus]
           mcrose@icarusdb.com.py
 *
 */
public class IDBException extends Exception
{
    public IDBException(String message)
    {
        super(message);
    }


    public IDBException(IOException e)
    {
        super(e);
    }

}
