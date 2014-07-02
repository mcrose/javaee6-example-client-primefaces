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
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import org.primefaces.component.celleditor.CellEditor;
import org.primefaces.event.RowEditEvent;

import py.org.icarusdb.commons.util.IDBProperties;
import py.org.icarusdb.commons.util.UriBuilder;
import py.org.icarusdb.example.model.ContinentDTO;
import py.org.icarusdb.example.model.CountryDTO;
import py.org.icarusdb.example.rest.client.CountryClientService;
import py.org.icarusdb.example.util.CollectionHelper;
import py.org.icarusdb.example.util.quialifiers.ComboBoxActiveContinents;
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
public class CountryController extends BaseController implements Serializable
{
    //TODO implement logging
//    private static final Logger LOGGER = Logger.getLogger(CountryController.class);
    
    
//    @Inject 
//    private ContextHelper contextHelper;
    
    @Inject
    @ComboBoxActiveContinents
    List<ContinentDTO> activeContinents;
    

    private CountryClientService service = null;

    private List<CountryDTO> resultList = null;
    private CountryDTO selectedRow = null;
    private ContinentDTO selectedContinent = null;
    
    private String name = null;

//    private String serviceLookupByName = null;
    private String serviceNameSave = null;

    private boolean showActivationButtons = true;
    
    
    @PostConstruct
    public void init()
    {
        // TODO add navigation control

        service = new CountryClientService();
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
        
        serverUri = UriBuilder.buildUri(service.getConnInfo(), "serviceNameCountries");
        resultList = service.getCountries(serverUri);
    }
    
    private void initVarz()
    {
        resultList = null;
        selectedRow = new CountryDTO();
        selectedContinent = null;
        
        summary = null; 
        name = null;
    }
    
    public CountryDTO getSelectedRow()
    {
        return selectedRow ;
    }

    public void setSelectedRow(CountryDTO selectedRow)
    {
        this.selectedRow = selectedRow;
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
    
    public List<CountryDTO> getResultList()
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
    
    

    public boolean isShowActivationButtons()
    {
        return showActivationButtons;
    }
    
    public boolean isPrintable()
    {
        return (resultList != null) && (!resultList.isEmpty());
    }

    
    
    public void search(ActionEvent actionEvent)
    {
        if(selectedContinent == null && (name == null || name.isEmpty()))  
        {
            resultList = service.getCountries(serverUri);
        }
        else
        {
            Properties parameters = new IDBProperties();
            parameters.put("name", name);
            parameters.put("continent", selectedContinent.getId());
            
            resultList = service.getCountries(serverUri + "/search", parameters);
        }
        
    }

    public String save()
    {
        String result = null;
        
        try
        {
            selectedRow.setContinentDTO(selectedContinent);
            
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
            
            showActivationButtons = true;
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
        selectedRow = new CountryDTO();
        selectedRow.setActive(true);
        
        if (resultList == null) {
            resultList = new LinkedList<CountryDTO>();
        }
        
        resultList.add(selectedRow);
        
        showActivationButtons = false;
    }
    
    public void updateContinentInfo()
    {
        selectedContinent = CollectionHelper.getContinent(activeContinents, selectedRow.getContinentDTO());
    }
    
    public void onCellEdit(CellEditor editor)
    {
        showActivationButtons = !showActivationButtons;
    }
    
//    public void onRowEdit(RowEditEvent event)
//    {
//        selectedRow = (CountryDTO) event.getObject();
//        selectedRow.setContinentDTO(selectedContinent);
//        save();
//    }
    
//    public void onRowCancel(RowEditEvent event)
//    {
//        showActivationButtons = true;
//        selectedRow = (CountryDTO) event.getObject();
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

            showActivationButtons = true;
            search(null);
        }
    }
    
    // TODO implement report 
    public void print()
    {
//        reportController.init();
//        
//        reportController.setReportPath("/reports");
//        reportController.setReportTemplateName("Countries");
//
//        reportController.setReportName("Countries");
//        reportController.addDataSourceEntityCollection(resultList);
//        
//        reportController.addParameter("name" , name);
//        
//        reportController.print();
//        
    }
    

}
