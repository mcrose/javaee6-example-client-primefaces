/**
 * Copyright 2014 Roberto Gamarra [icarus] ** ( Betto McRose  )
 *                mcrose@icarusdb.com.py | mcrose.dev@gmail.com
 *                
 * as you wish... at your service ;-P
 * 
 * IcarusDB.com.py
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
package py.org.icarusdb.example.repository;

import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import py.org.icarusdb.commons.util.UriBuilder;
import py.org.icarusdb.example.model.CountryDTO;
import py.org.icarusdb.example.rest.client.CountryClientService;
import py.org.icarusdb.example.util.quialifiers.ComboBoxActiveCountries;

/**
 * @author Betto McRose [icarus]
 *         mcrose@icarusdb.com.py
 *         mcrose.dev@gmail.com
 *
 */
@ApplicationScoped
public class CountryListProducer 
{
    private String serverUri = null;
    private CountryClientService service = null;

    private List<CountryDTO> countries = null;
    private List<CountryDTO> activeCountries = null;


    @PostConstruct
    private void init()
    {
        service = new CountryClientService();
        try
        {
            service.loadConfig();
            serverUri = UriBuilder.buildUri(service.getConnInfo(), "serviceNameCountries");
        }
        catch (Exception e)
        {
            // errors already in context
            // TODO test if they are displayed
        }
        
        
    }
    
    
    
    @Produces
    @Named
    public List<CountryDTO> getCountryDTOs()
    {
        if(countries == null) {
            retrieveAllCountriesOrderedByName();
        }
        return countries;
    }

    @Produces
    @Named
    public List<CountryDTO> getActiveCountries()
    {
        if(activeCountries == null) {
            retrieveAcTiveCountriesOrderedByName();
        }
        return activeCountries;
    }

    // TODO check values on session context for errors
//    @ApplicationScoped
    @Produces
    @Named
    @ComboBoxActiveCountries
    public List<CountryDTO> getComboBoxActiveCountries()
    {
        return new LinkedList<CountryDTO>(getActiveCountries());
    }
    
    public void onCountryDTOListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final CountryDTO CountryDTO)
    {
        retrieveAllCountriesOrderedByName();
    }

    private void retrieveAllCountriesOrderedByName()
    {
        countries = service.getCountries(serverUri);        
    }
    
    private void retrieveAcTiveCountriesOrderedByName()
    {
        activeCountries = service.getCountries(serverUri + "/actives");        
    }



    public List<CountryDTO> search(String path, Properties parameters)
    {
        String fullpath = path;
        if(!fullpath.startsWith(serverUri)) {
            fullpath = serverUri + path;
        }
        return service.getCountries(fullpath, parameters);
    }

}
