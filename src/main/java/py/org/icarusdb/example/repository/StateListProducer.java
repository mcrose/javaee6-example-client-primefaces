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
import py.org.icarusdb.example.model.StateDTO;
import py.org.icarusdb.example.rest.client.StateClientService;
import py.org.icarusdb.example.util.quialifiers.ComboBoxActiveStates;

/**
 * @author Betto McRose [icarus]
 *         mcrose@icarusdb.com.py
 *         mcrose.dev@gmail.com
 *
 */
@ApplicationScoped
public class StateListProducer 
{
    private String serverUri = null;
    private StateClientService service = null;

    private List<StateDTO> states = null;
    private List<StateDTO> activeStates = null;


    @PostConstruct
    private void init()
    {
        service = new StateClientService();
        try
        {
            service.loadConfig();
            serverUri = UriBuilder.buildUri(service.getConnInfo(), "serviceNameStates");
        }
        catch (Exception e)
        {
            // errors already in context
            // TODO test if they are displayed
        }
        
        
    }
    
    
    
    @Produces
    @Named
    public List<StateDTO> getStateDTOs()
    {
        if(states == null) {
            retrieveAllStatesOrderedByName();
        }
        return states;
    }

    @Produces
    @Named
    public List<StateDTO> getActiveStates()
    {
        if(activeStates == null) {
            retrieveAcTiveStatesOrderedByName();
        }
        return activeStates;
    }

    // TODO check values on session context for errors
//    @ApplicationScoped
    @Produces
    @Named
    @ComboBoxActiveStates
    public List<StateDTO> getComboBoxActiveStates()
    {
        return new LinkedList<StateDTO>(getActiveStates());
    }
    
    public void onStateDTOListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final StateDTO StateDTO)
    {
        retrieveAllStatesOrderedByName();
    }

    public void retrieveAllStatesOrderedByName()
    {
        states = service.getStates(serverUri);        
    }
    
    public void retrieveAcTiveStatesOrderedByName()
    {
        activeStates = service.getStates(serverUri + "/actives");        
    }
    
}
