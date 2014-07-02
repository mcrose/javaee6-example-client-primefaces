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
import py.org.icarusdb.example.model.CountryDTO;
import py.org.icarusdb.example.model.StateDTO;
import py.org.icarusdb.example.rest.client.StateClientService;
import py.org.icarusdb.example.util.CollectionHelper;
import py.org.icarusdb.example.util.quialifiers.ComboBoxActiveCountries;
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
public class StateController extends BaseController implements Serializable
{
    //TODO implement logging
//    private static final Logger LOGGER = Logger.getLogger(StateController.class);
    
    
//    @Inject 
//    private ContextHelper contextHelper;
    
    @Inject
    @ComboBoxActiveCountries
    List<CountryDTO> activeCountries;
    

    private StateClientService service = null;

    private List<StateDTO> resultList = null;
    private StateDTO selectedRow = null;
    private CountryDTO selectedCountry = null;
    
    private String name = null;

//    private String serviceLookupByName = null;
    private String serviceNameSave = null;

    
    
    @PostConstruct
    public void init()
    {
        // TODO add navigation control

        service = new StateClientService();
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
        
        serverUri = UriBuilder.buildUri(service.getConnInfo(), "serviceNameStates");
        resultList = service.getStates(serverUri);
    }
    
    private void initVarz()
    {
        resultList = null;
        selectedRow = new StateDTO();
        selectedCountry = null;
        
        summary = null; 
        name = null;
    }
    
    public StateDTO getSelectedRow()
    {
        return selectedRow ;
    }

    public void setSelectedRow(StateDTO selectedRow)
    {
        this.selectedRow = selectedRow;
    }
    
    public CountryDTO getSelectedCountry()
    {
        return selectedCountry;
    }
    
    public void setSelectedCountry(CountryDTO selectedCountry)
    {
        this.selectedCountry = selectedCountry;
    }

    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public List<StateDTO> getResultList()
    {
        return resultList;
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
        if(selectedCountry == null && (name == null || name.isEmpty()))  
        {
            resultList = service.getStates(serverUri);
        }
        else
        {
            Properties parameters = new IDBProperties();
            parameters.put("name", name);
            parameters.put("country", selectedCountry.getId());
            
            resultList = service.getStates(serverUri + "/search", parameters);
        }
        
    }

    public String save()
    {
        String result = null;
        
        try
        {
            selectedRow.setCountryDTO(selectedCountry);
            
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
        selectedRow = new StateDTO();
        selectedRow.setActive(true);
    }
    
    public void updateCountryInfo()
    {
        selectedCountry = CollectionHelper.getCountry(activeCountries, selectedRow.getCountryDTO());
    }
    
//    public void onCellEdit(CellEditor editor)
//    {
//        showActivationButtons = !showActivationButtons;
//    }
    
//    public void onRowEdit(RowEditEvent event)
//    {
//        selectedRow = (StateDTO) event.getObject();
//        selectedRow.setCountryDTO(selectedCountry);
//        save();
//    }
    
//    public void onRowCancel(RowEditEvent event)
//    {
//        showActivationButtons = true;
//        selectedRow = (StateDTO) event.getObject();
//        
//        String message = AppHelper.getBundleMessage("action.result.cancelledEdition");
//        MessageUtil.addFacesMessageWarm(message, selectedRow.getName());
//    }

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

//            showActivationButtons = true;
            search(null);
        }
    }
    
    // TODO implement report 
    public void print()
    {
//        reportController.init();
//        
//        reportController.setReportPath("/reports");
//        reportController.setReportTemplateName("States");
//
//        reportController.setReportName("States");
//        reportController.addDataSourceEntityCollection(resultList);
//        
//        reportController.addParameter("name" , name);
//        
//        reportController.print();
//        
    }
    

}
