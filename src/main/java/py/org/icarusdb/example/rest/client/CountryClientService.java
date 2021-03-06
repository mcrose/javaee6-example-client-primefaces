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
import java.util.List;
import java.util.Properties;

import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import py.org.icarusdb.commons.exception.ActiveUserException;
import py.org.icarusdb.commons.exception.ConfigException;
import py.org.icarusdb.commons.exception.LoginFailedException;
import py.org.icarusdb.commons.exception.RegisteredUserException;
import py.org.icarusdb.example.model.CountryDTO;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

/**
 * @author Betto McRose [icarus]
 *         mcrose@icarusdb.com.py
 *         mcrose.dev@gmail.com
 *
 */
public class CountryClientService extends ExampleClientService
{

    public CountryClientService()
    {
        super();
    }


    private List<CountryDTO> countries = null;
    
    
    /**
     * connect to RESTful services via GET
     * 
     * @param connectUri
     * @return
     */
    public List<CountryDTO> getCountries(String connectUri)
    {
        return getCountries(connectUri, null);
    }


    /**
     * connects to RESTful services via POST when @param parameters have values
     * otherwise connects via GET
     * 
     * @param connectUri valid URI, http://servername:port/modulename/serviceuri/servicename[/action]
     * @param parameters
     * 
     * @return {@link Country} or empty list 
     */
    public List<CountryDTO> getCountries(String connectUri, Properties parameters)
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

        return countries;
    }


    private void retrieveInfo() throws LoginFailedException, RegisteredUserException
                                     , ActiveUserException, ConfigException, JsonParseException
                                     , JsonMappingException, IOException
    {
        readResponse();

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new Hibernate4Module());
        
        countries = (List<CountryDTO>) mapper.readValue(response.getEntity().toString(), new TypeReference<List<CountryDTO>>() { });
        
    }


    public String execute(String uri, CountryDTO country)
    {
        try
        {
            createConnection(uri);
            
            request.body(MediaType.APPLICATION_JSON, new ObjectMapper().writeValueAsString(country));
            
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


}
