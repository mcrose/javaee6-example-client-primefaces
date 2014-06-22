/**
 * 
 */
package py.org.icarusdb.example.rest.client;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import py.org.icarusdb.commons.exception.ActiveUserException;
import py.org.icarusdb.commons.exception.ConfigException;
import py.org.icarusdb.commons.exception.LoginFailedException;
import py.org.icarusdb.commons.exception.RegisteredUserException;
import py.org.icarusdb.example.model.Continent;

/**
 * @author rgamarra
 *
 */
public class ContinentClientService extends ServiceHelper
{
    private List<Continent> continents = null;
    
    
    
    /**
     * connect to RESTful services via GET
     * 
     * @param connectUri
     * @return
     */
    public List<Continent> getContinents(String connectUri)
    {
        try
        {
            createConnection(connectUri);
            
            response = request.get(String.class);
            
            retrieveInfo();
            
        }
        catch (JsonGenerationException e1)
        {
            e1.printStackTrace();
        }
        catch (JsonMappingException e1)
        {
            e1.printStackTrace();
        }
        catch (IOException e1)
        {
            e1.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return continents;
    }


    /**
     * connect to RESTful services via POST
     * 
     * @param connectUri
     * @param parameters
     * @return
     */
    public List<Continent> getContinents(String connectUri, Properties parameters)
    {
        try
        {
            createConnection(connectUri);
            
            if(parameters == null || parameters.isEmpty()) return null;
            
            request.body(MediaType.APPLICATION_JSON, new ObjectMapper().writeValueAsString(parameters));

            response = request.post(String.class);
            
            retrieveInfo();
            
        }
        catch (JsonGenerationException e1)
        {
            e1.printStackTrace();
        }
        catch (JsonMappingException e1)
        {
            e1.printStackTrace();
        }
        catch (IOException e1)
        {
            e1.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return continents;
    }


    private void retrieveInfo() throws LoginFailedException, RegisteredUserException
                                     , ActiveUserException, ConfigException, JsonParseException
                                     , JsonMappingException, IOException
    {
        readResponse();

        continents = new ObjectMapper().readValue(response.getEntity().toString(), new TypeReference<List<Continent>>() { });
    }


    public String update(String uri, Continent continent)
    {
        try
        {
            createConnection(uri);
            
            request.body(MediaType.APPLICATION_JSON, new ObjectMapper().writeValueAsString(continent));
            
            response = request.post(String.class);
            
            readResponse();
            
            return "updated";
            
        }
        catch (JsonGenerationException e)
        {
            e.printStackTrace();
        }
        catch (JsonMappingException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        
        return null;
    }
    
}
