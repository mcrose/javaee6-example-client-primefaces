/**
 * 
 */
package py.org.icarusdb.util;



/**
 * @author mcrose
 *
 */
public class UriBuilder
{
    /**
     * builds an URI with the given properties<br>
     * 
     * {@link GlobalParameters}.REST_AUTH_KEY_SERVERADDRESS<br>
     * {@link GlobalParameters}.REST_AUTH_KEY_SERVICENAME<br>
     * {@link GlobalParameters}.REST_AUTH_KEY_LOGINPATH<br>
     * must be present
     * 
     * @param properties
     * @return
     */
    public static String buildLoginUri(IDBProperties properties)
    {
        String server = (String) properties.get(GlobalParameters.REST_AUTH_KEY_SERVER);
        String port = (String) properties.get(GlobalParameters.REST_AUTH_KEY_PORT);
        String moduleName = (String) properties.get(GlobalParameters.REST_AUTH_KEY_MODULENAME);
        String serviceName = (String) properties.get(GlobalParameters.REST_AUTH_KEY_SERVICENAME);
        String loginPath = (String) properties.get(GlobalParameters.REST_AUTH_KEY_LOGINPATH);
        
        return "http://" + server + ":"+port + "/"+moduleName + serviceName + loginPath;
    }
    
    public static String buildUri(IDBProperties properties)
    {
        String server = (String) properties.get(GlobalParameters.REST_KEY_SERVER);
        String port = (String) properties.get(GlobalParameters.REST_KEY_PORT);
        String moduleName = (String) properties.get(GlobalParameters.REST_KEY_MODULENAME);
        String serviceName = (String) properties.get(GlobalParameters.REST_KEY_SERVICENAME);
        
        return "http://" + server + ":"+port + "/"+moduleName + serviceName;
    }
    
    public static String buildUri(String uri)
    {
        if(!uri.startsWith("http://")) 
        {
            uri = "http://" + uri;
        }
        
        return uri;
    }
    
    
}
