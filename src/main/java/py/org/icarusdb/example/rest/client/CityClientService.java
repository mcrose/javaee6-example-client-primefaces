/**
 * Copyright 2014 Roberto Gamarra (Betto McRose [icarus]
           mcrose@icarusdb.com.py@icarusdb.com.py)
 * Icarus
 * Betto McRose
 * Roberto Gamarra
 * 
 * as you wish... at your service
 * 
 * IcarusDB
 * 
 *            
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package py.org.icarusdb.example.rest.client;

import java.io.IOException;
import java.io.Serializable;
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
import py.org.icarusdb.example.model.CityDTO;


/**
 * @author Betto McRose [icarus]
 *         mcrose@icarusdb.com.py
 *         mcrose.dev@gmail.com
 *
 */
public class CityClientService extends ExampleClientService
{

    public CityClientService()
    {
        super();
    }


    private CityDTO state = null;
    private List<CityDTO> states = null;
    
    
    /**
     * connect to RESTful services via GET
     * 
     * @param connectUri
     * @return
     */
    public List<CityDTO> getCities(String connectUri)
    {
        return getCities(connectUri, null);
    }


    /**
     * connects to RESTful services via POST when @param parameters have values
     * otherwise connects via GET
     * 
     * @param connectUri valid URI, http://servername:port/modulename/serviceuri/servicename[/action]
     * @param parameters
     * 
     * @return {@link City} or empty list 
     */
    public List<CityDTO> getCities(String connectUri, Properties parameters)
    {
        try
        {
            createConnection(connectUri);
            
            if(parameters == null || parameters.isEmpty())
            {
                response = request.get(String.class);
            }
            else
            {
                request.body(MediaType.APPLICATION_JSON, new ObjectMapper().writeValueAsString(parameters));
                
                response = request.post(String.class);
            }
            
            retrieveInfo();
            
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

        return states;
    }


    private void retrieveInfo() throws LoginFailedException, RegisteredUserException
                                     , ActiveUserException, ConfigException, JsonParseException
                                     , JsonMappingException, IOException
    {
        readResponse();
        
        if(response.getEntity().toString().startsWith("["))
        {
            states = new ObjectMapper().readValue(response.getEntity().toString(), new TypeReference<List<CityDTO>>() { });
        }
        else
        {
            state = new ObjectMapper().readValue(response.getEntity().toString(), new TypeReference<CityDTO>() { });
        }
        
    }


    public String execute(String uri, CityDTO entity)
    {
        try
        {
            createConnection(uri);
            
            request.body(MediaType.APPLICATION_JSON, new ObjectMapper().writeValueAsString(entity));
            
            response = request.post(String.class);
            
            readResponse();
            
            return "OK";
            
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

    public CityDTO getCity(String uri, Serializable entityId)
    {
        try
        {
            createConnection(uri + "/id"+entityId);
            
//            request.body(MediaType.APPLICATION_JSON, new ObjectMapper().writeValueAsString(entity));
            
            response = request.get(String.class);
            
            retrieveInfo();
            
            return state;
            
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
