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
package py.org.icarusdb.example.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import py.org.icarusdb.commons.util.UriBuilder;
import py.org.icarusdb.example.controller.panel.CountryPanel;
import py.org.icarusdb.example.controller.util.BaseController;
import py.org.icarusdb.example.model.CityDTO;
import py.org.icarusdb.example.model.ContinentDTO;
import py.org.icarusdb.example.model.CountryDTO;
import py.org.icarusdb.example.model.StateDTO;
import py.org.icarusdb.example.rest.client.CityClientService;
import py.org.icarusdb.example.util.CollectionHelper;
import py.org.icarusdb.example.util.SessionParameters;
import py.org.icarusdb.example.util.quialifiers.ComboBoxActiveContinents;
import py.org.icarusdb.example.util.quialifiers.ComboBoxActiveCountries;
import py.org.icarusdb.example.util.quialifiers.ComboBoxActiveStates;
import py.org.icarusdb.session.SessionContextHelper;
import py.org.icarusdb.session.DemoNavigationRulez;
import py.org.icarusdb.util.AppHelper;
import py.org.icarusdb.util.MessageUtil;
import py.org.icarusdb.util.NavigationRulezHelper;

/**
 * @author Betto McRose [icarus]
 *         mcrose@icarusdb.com.py
 *         mcrose.dev@gmail.com
 *
 */
@ManagedBean
@ViewScoped
//TODO add roles
public class CityEditController extends BaseController implements Serializable
{
    //TODO implement logging
//    private static final Logger LOGGER = Logger.getLogger(CityController.class);
    
    @Inject
    @ComboBoxActiveContinents
    List<ContinentDTO> activeContinents;

    @Inject
    @ComboBoxActiveCountries
    List<CountryDTO> activeCountries;
    
    @Inject
    @ComboBoxActiveStates
    List<StateDTO> activeStates;

    @Inject
    DemoNavigationRulez navigationRulez;

    @Inject 
    SessionContextHelper sessionContextHelper;
    
    @Inject
    CountryPanel countryPanel;
    
    
    
    
    private CityClientService service = null;

    private CityDTO selectedRow = null;
    private StateDTO selectedState = null;
    private CountryDTO selectedCountry = null;
    private ContinentDTO selectedContinent = null;
    
    private List<StateDTO> filteredStates = null;
    private List<CountryDTO> filteredCountries = null;
    
    private String name = null;

    private String serviceNameSave = null;

    
    
    @PostConstruct
    public void init()
    {
        if(!sessionContextHelper.containsMenuAction(SessionParameters.ACTION_MENU_CITY))
        {
            MessageUtil.addFacesMessageError("error.action.noActionDefined");
            NavigationRulezHelper.redirect(AppHelper.getDomainUrl() + "/home.jsf");
            return;
        }
        
        initVarz();
        
        service = new CityClientService();
        try
        {
            service.loadConfig();
        }
        catch (Exception e)
        {
            // errors already in context
            // TODO test if they are displayed
        }
        
        serverUri = UriBuilder.buildUri(service.getConnInfo(), "serviceNameCities");
        
        action = sessionContextHelper.getSelectedAction();
        if(action.equalsIgnoreCase(SessionParameters.ACTION_NEW_CITY)) 
        {
            selectedRow = new CityDTO();
            actionSubTitle = AppHelper.getBundleMessage("label.add");
        }
        else
        {
            Serializable entityId = (Serializable) sessionContextHelper.getSelectedEntityId();
            selectedRow = service.getCity(serverUri, entityId);
            actionSubTitle = AppHelper.getBundleMessage("label.update");
            
            updateCollectionsInfo();
            
        }
        
        //sessionContextHelper.clearAction(); TODO uncomment when finish debug

    }
    
    private void initVarz()
    {
        selectedState = null;
        selectedCountry = null;
        selectedContinent = null;
        
        filteredCountries = null;
        filteredStates = null;
        
        summary = null; 
        name = null;
        
        countryPanel.clear();
    }
    
    public CityDTO getSelectedRow()
    {
        return selectedRow ;
    }

    public void setSelectedRow(CityDTO selectedRow)
    {
        this.selectedRow = selectedRow;
    }
    
    public StateDTO getSelectedState()
    {
        return selectedState;
    }
    
    public void setSelectedState(StateDTO selectedState)
    {
        this.selectedState = selectedState;
    }
    
    public CountryDTO getSelectedCountry()
    {
        return selectedCountry;
    }
    
    public void setSelectedCountry(CountryDTO selectedCountry)
    {
        this.selectedCountry = selectedCountry;
    }
    
    public ContinentDTO getSelectedContinent()
    {
        return selectedContinent;
    }
    
    public void setSelectedContinent(ContinentDTO selectedContinent)
    {
        this.selectedContinent = selectedContinent;
    }

    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public List<CountryDTO> getFilteredCountries()
    {
        return filteredCountries;
    }
    
    public List<StateDTO> getFilteredStates()
    {
        return filteredStates;
    }
    
    private String getServiceSave()
    {
        if (serviceNameSave == null)
        {
            serviceNameSave = service.getConnInfo("serviceNameSave"); 
        }
        return serviceNameSave ;
    }

    
    public String save()
    {
        String result = null;
        
        try
        {
            selectedRow.setStateDTO(selectedState);
            
            result = service.execute(serverUri + getServiceSave(), selectedRow);
            
        }
        catch (Exception e) 
        {
            AppHelper.printException(e);
            summary = "Error !";
        }
        finally
        {
            if(result != null)
            {
                summary = MessageUtil.retrieveMessage("action.result.removed");
                
                return navigationRulez.goCities();
            }
        }
        
        return null;
    }

    public void clear()
    {
        initVarz();
    }
    
    public void add()
    {
        selectedRow = new CityDTO();
        selectedRow.setActive(true);
    }
    
    public void updateCollectionsInfo()
    {
        selectedState = CollectionHelper.getState(activeStates, selectedRow.getStateDTO());
        selectedCountry = CollectionHelper.getCountry(activeCountries, selectedState.getCountryDTO());
        selectedContinent = CollectionHelper.getContinent(activeContinents, selectedCountry.getContinentDTO());
        updateCountries();
        updateStates();
    }
    
    public void updateCountries()
    {
        filteredCountries = CollectionHelper.getCountriesByContinent(activeCountries, selectedContinent);
    }
    
    public void updateStates()
    {
        filteredStates = CollectionHelper.getStatesByCountry(activeStates, selectedCountry);
    }
    
    public void activate()
    {
        selectedRow.setActive(true);
        save();
    }
    
    public void inactivate()
    {
        selectedRow.setActive(false);
        save();
    }
    
    public void remove()
    {
        String result = null;

        try
        {
            result = service.execute(serverUri + "/remove", selectedRow);
        }
        catch (Exception e)
        {
            AppHelper.printException(e);
            summary = "Error !";
        }
        finally
        {
            if (result != null)
            {
                summary = MessageUtil.retrieveMessage("action.result.updated");
                selectedRow = null;
            }

        }
    }

    public void preparePanel(String panelname)
    {
        if("country".equalsIgnoreCase(panelname)) {
            countryPanel.setTagId2update(":editform");
            countryPanel.clear();
        }
    }
    
    
}
