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
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import py.org.icarusdb.commons.util.IDBProperties;
import py.org.icarusdb.commons.util.UriBuilder;
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
import py.org.icarusdb.session.ContextHelper;
import py.org.icarusdb.util.AppHelper;
import py.org.icarusdb.util.BaseController;
import py.org.icarusdb.util.MessageUtil;

/**
 * @author Betto McRose [icarus]
 *         mcrose@icarusdb.com.py
 *         mcrose.dev@gmail.com
 *
 */
@ManagedBean
@ViewScoped
//TODO add roles
public class CityController extends BaseController implements Serializable
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
    ContextHelper contextHelper;
    

    
    private CityClientService service = null;

    private List<CityDTO> resultList = null;
    private CityDTO selectedRow = null;
    private StateDTO selectedState = null;
    private CountryDTO selectedCountry = null;
    private ContinentDTO selectedContinent = null;
    
    private List<StateDTO> filteredStates = null;
    private List<CountryDTO> filteredCountries = null;
    
    private String name = null;

//    private String serviceLookupByName = null;
    private String serviceNameSave = null;

    
    
    @PostConstruct
    public void init()
    {
        // TODO add navigation control

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
        
        initVarz();
        
        serverUri = UriBuilder.buildUri(service.getConnInfo(), "serviceNameCities");
        resultList = service.getCities(serverUri);
    }
    
    private void initVarz()
    {
        resultList = null;
        selectedRow = new CityDTO();
        selectedState = null;
        selectedCountry = null;
        selectedContinent = null;
        
        summary = null; 
        name = null;
    }
    
    
    public void selectAction(String action)
    {
        selectAction(action, null);
    }
    
    public void selectAction(String action, Integer id)
    {
        contextHelper.setSelectedMenu(SessionParameters.ACTION_MENU_CITY);
        contextHelper.setSelectedAction(action);
        contextHelper.setSelectedEntityId(id);
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
    
    public List<CityDTO> getResultList()
    {
        return resultList;
    }
    
    public List<CountryDTO> getFilteredCountries()
    {
        return filteredCountries;
    }
    
    public List<StateDTO> getFilteredStates()
    {
        return filteredStates;
    }
    
//    private String getServiceLookupByName()
//    {
//        if (serviceLookupByName == null)
//        {
//            serviceLookupByName = service.getConnInfo("serviceNameFindByName"); 
//        }
//        return serviceLookupByName ;
//    }
    
    private String getServiceSave()
    {
        if (serviceNameSave == null)
        {
            serviceNameSave = service.getConnInfo("serviceNameSave"); 
        }
        return serviceNameSave ;
    }

//    private String getServiceLookupByParams()
//    {
//        if (serviceLookupByName == null)
//        {
//            serviceLookupByName = service.getConnInfo("serviceNameFindByParams"); 
//        }
//        return serviceLookupByName ;
//    }
    
    

    public boolean isPrintable()
    {
        return (resultList != null) && (!resultList.isEmpty());
    }

    
    
    public void search(ActionEvent actionEvent)
    {
        if((selectedState == null) && (selectedContinent == null) && (selectedCountry == null) 
                && (name == null || name.isEmpty()) )  
        {
            resultList = service.getCities(serverUri);
        }
        else
        {
            Properties parameters = new IDBProperties();
            
            if (selectedState != null) {
                parameters.put("state", selectedState.getId());
            }
            if (selectedCountry != null) {
                parameters.put("country", selectedCountry.getId());
            }
            if (selectedContinent != null) {
                parameters.put("continent", selectedContinent.getId());
            }
            
            parameters.put("name", name);
            
            resultList = service.getCities(serverUri + "/search", parameters);
        }
        
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
                selectedRow = null;
            }
            
            search(null);
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

            search(null);
        }
    }
    
    // TODO implement report 
    public void print()
    {
//        reportController.init();
//        
//        reportController.setReportPath("/reports");
//        reportController.setReportTemplateName("Cities");
//
//        reportController.setReportName("Cities");
//        reportController.addDataSourceEntityCollection(resultList);
//        
//        reportController.addParameter("name" , name);
//        
//        reportController.print();
//        
    }
    

}
