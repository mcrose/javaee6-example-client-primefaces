/**
 * 
 */
package py.org.icarusdb.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.jboss.logging.Logger;
import org.jboss.logging.Logger.Level;

import py.org.icarusdb.exepction.IDBException;

/**
 * @author Betto McRose [icarus]
           mcrose@icarusdb.com.py
 * 
 */
public class FileHelper
{
    private static Logger logger = Logger.getLogger(FileHelper.class);
    
    /**
     * @author Pedro Flores [neowinx]
     *         pflores@codelab.com.py
     * @param is
     * @param filename
     * @param path
     * @return
     * @throws IDBException
     */
    public static boolean writeFile(InputStream is, String filename, String path) throws IDBException
    {

        File fileDir = new File(path);
        if (!fileDir.exists())
        {
            fileDir.mkdir();
        }

        String file = fileDir + File.separator + filename;

        try
        {
            FileOutputStream out = new FileOutputStream(new File(file));

            int c;
            while ((c = is.read()) != -1)
            {
                out.write(c);
            }
            is.close();
            out.close();
        }
        catch (IOException e)
        {
            logger.log(Level.DEBUG, FileHelper.class.getName(), e);
            throw new IDBException(e);
        }

        return true;
    }

    public static boolean writeFile(byte[] data, String filename, String path) throws IDBException
    {
        return writeFile(new ByteArrayInputStream(data), filename, path);
    }

    
    public static IDBProperties loadConfigParams(String cfgFileName, String projectCfgPath) throws FileNotFoundException, IOException
    {
        String filepath = System.getProperty(ServerConfiguracionHelper.JBOSS7_JBOSS_SERVER_CONFIG_DIR) + 
                            File.separator + projectCfgPath + File.separator + cfgFileName;
        
        IDBProperties parameteres = new IDBProperties();
        parameteres.load(new FileInputStream(filepath));
        
        return parameteres;
    }
    
//    public static IDBProperties loadConfigParams(String cfgFileName) throws FileNotFoundException, IOException
//    {
//        String filepath = System.getProperty(ServerConfiguracionHelper.JBOSS7_JBOSS_SERVER_CONFIG_DIR) + 
//                            File.separator + ServerConfiguracionHelper.JBOSS7_JBOSSSERVER_IDB_CONN_CONFIG_DIR +
//                            File.separator + cfgFileName;
//        
//        IDBProperties parameteres = new IDBProperties();
//        parameteres.load(new FileInputStream(filepath));
//        
//        return parameteres;
//    }
        
    
    
}
