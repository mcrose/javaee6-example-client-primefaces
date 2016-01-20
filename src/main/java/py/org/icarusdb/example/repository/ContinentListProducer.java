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

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import py.org.icarusdb.commons.util.UriBuilder;
import py.org.icarusdb.example.model.ContinentDTO;
import py.org.icarusdb.example.rest.client.ContinentClientService;
import py.org.icarusdb.example.util.quialifiers.ComboBoxActiveContinents;

/**
 * @author Betto McRose [icarus]
 *         mcrose@icarusdb.com.py
 *         mcrose.dev@gmail.com
 *
 */
@ApplicationScoped
public class ContinentListProducer 
{
    private String serverUri = null;
    private ContinentClientService service = null;

    private List<ContinentDTO> continents = null;
    private List<ContinentDTO> activeContinents = null;


    @PostConstruct
    private void init()
    {
        service = new ContinentClientService();
        try
        {
            service.loadConfig();
            serverUri = UriBuilder.buildUri(service.getConnInfo(), "serviceNameContinents");
        }
        catch (Exception e)
        {
            // errors already in context
            // TODO test if they are displayed
        }
        
        
    }
    
    
    
    @Produces
    @Named
    public List<ContinentDTO> getContinentDTOs()
    {
        if(continents == null) {
            retrieveAllContinentsOrderedByName();
        }
        return continents;
    }

    @Produces
    @Named
    public List<ContinentDTO> getActiveContinents()
    {
        if(activeContinents == null) {
            retrieveAcTiveContinentsOrderedByName();
        }
        return activeContinents;
    }

    @ApplicationScoped
    @Produces
    @Named
    @ComboBoxActiveContinents
    public List<ContinentDTO> getComboBoxActiveContinents()
    {
        List<ContinentDTO> combobox = new LinkedList<ContinentDTO>();
//        ContinentDTO selectElement = new ContinentDTO();
//        selectElement.setName(DialogEffectHelper.getBundleMessage("label.selection.select"));
//        combobox.add(selectElement);
        combobox.addAll(getActiveContinents());
        
        return combobox;
    }
    
    public void onContinentDTOListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final ContinentDTO ContinentDTO)
    {
        retrieveAllContinentsOrderedByName();
    }

    public void retrieveAllContinentsOrderedByName()
    {
        continents = service.getContinents(serverUri);        
    }
    
    public void retrieveAcTiveContinentsOrderedByName()
    {
        activeContinents = service.getContinents(serverUri + "/actives");        
    }
    
}
