/**
 * 
 */
package py.org.icarusdb.example.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.PersistenceException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.RollbackException;

import py.org.icarusdb.util.BundleHelper;
import py.org.icarusdb.util.FileHelper;
import py.org.icarusdb.util.UriBuilder;


/**
 * @author RobertoGamarra
 *
 */
public class AppHelper
{
    
    public static String getDomainUrl()
    {
    	String host = getExternalContext().getRequestHeaderMap().get("host");
    	String appName = getExternalContext().getRequestContextPath();

    	return "http://" + host + appName;
    }

    public static ExternalContext getExternalContext()
    {
        return FacesContext.getCurrentInstance().getExternalContext();
    }
    
    public static ServletContext getServletContext()
    {
        return (ServletContext) getExternalContext().getContext();
    }
    
    public static HttpServletRequest getServletRequest()
    {
        return (HttpServletRequest) getExternalContext().getRequest();
    }
    
    public static HttpServletResponse getServletResponse()
    {
        return (HttpServletResponse) getExternalContext().getResponse();
    }
    
    public static Map<String, Object> getRequestContext()
    {
        return getExternalContext().getRequestMap();
    }
    
    public static Locale getLocale()
    {
        return FacesContext.getCurrentInstance().getViewRoot().getLocale();
    }

    public static String getBundleMessage(String key)
    {
        return BundleHelper.getBundleMessage(SessionParameters.BUNDLE_URL, key);
    }
    
    /**
     * FIXME add jar to facilitate exceptions' debug and return detailed errors
     * 
     * @param e
     */
    public static void printException(Exception e)
    {
        String message = e.getMessage();
        
        if(e instanceof RollbackException)
        {
            RollbackException rbe = (RollbackException) e;
            Throwable cause = rbe.getCause();

            if(cause instanceof PersistenceException)
            {
                PersistenceException pe = (PersistenceException) cause;
                if(pe.getCause().toString().contains("ConstraintViolationException"))
                {
//                    ConstraintViolationException cve = (ConstraintViolationException) pe.getCause();
                    message = pe.getCause().getLocalizedMessage();
//                    for(ConstraintViolation<?> ex : cve.getConstraintViolations())
//                    {
//                        ex.getConstraintDescriptor();
//                        ex.getInvalidValue();
//                        ex.getRootBean();
//                    }
//                    message = cve.getMessage();
                }
            }
            
            
//            for (InvalidValue invalidValue : ise.getInvalidValues()) 
//            {
//                String msg = invalidValue.getBeanClass().getSimpleName() +
//                             " has an invalid property: " + invalidValue.getPropertyName() +
//                             " with message: " + invalidValue.getMessage() + ".";
//                System.err.println(msg);
//            }
            
        }
        else
        {
            e.printStackTrace();
        }
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, "system.errors"));
    }

    /**
     * checks for the given String if it has any values
     * 
     * @param string
     * @return
     */
    public static boolean nothing(String string)
    {
        return string==null||string.isEmpty();
    }

    public static String getClientIpAddr()
    {
        return getServletRequest().getRemoteAddr();
    }

    public static String getRESTfullConfig(String cfgFileName) throws FileNotFoundException, IOException
    {
        return UriBuilder.buildLoginUri(FileHelper.loadConfigParams(cfgFileName, SessionParameters.JBOSS7_JBOSSSERVER_EXAMPLE_SERVER_CONN_CONFIG_DIR));
    }
    
}
