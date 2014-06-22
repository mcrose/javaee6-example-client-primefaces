/**
 * 
 */
package py.org.icarusdb.example.rest.client;

import java.io.IOException;
import java.net.ConnectException;

import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;

import py.org.icarusdb.commons.exception.ActiveUserException;
import py.org.icarusdb.commons.exception.ConfigException;
import py.org.icarusdb.commons.exception.LoginFailedException;
import py.org.icarusdb.commons.exception.RegisteredUserException;
import py.org.icarusdb.commons.util.GlobalParameters;
import py.org.icarusdb.commons.util.UriBuilder;

/**
 * @author rgamarra
 * 
 */
public abstract class ServiceHelper
{
    protected static ClientRequest request = null;
    protected static ClientResponse<?> response = null;

    protected void createConnection(String connectUri)
    {
        request = new ClientRequest(UriBuilder.buildUri(connectUri));
        request.accept(MediaType.APPLICATION_JSON);
    }

    protected void readResponse() throws LoginFailedException, RegisteredUserException, ActiveUserException
                                       , ConfigException, JsonParseException, JsonMappingException, IOException
    {
        switch (response.getStatus())
        {
            case GlobalParameters.RESPONSE_ERROR_LOGIN_FAILED:
                throw new LoginFailedException();
            case GlobalParameters.RESPONSE_ERROR_REGISTERED_USER:
                throw new RegisteredUserException();
            case GlobalParameters.RESPONSE_ERROR_ACTIVE_USER:
                throw new ActiveUserException();
            case GlobalParameters.RESPONSE_ERROR_CONNECTION:
                throw new ConnectException();
            case GlobalParameters.RESPONSE_ERROR_CONFIGURATION:
                throw new ConfigException();

            default:
                // TODO add REST codes
                break;
        }

        if (response.getStatus() != 200) // TODO add to global parameters or response enums/parameters
        {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
        }
    }

}
