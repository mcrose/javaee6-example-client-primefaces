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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import org.jboss.logging.Logger;

import py.org.icarusdb.commons.util.IDBProperties;
import py.org.icarusdb.commons.util.UriBuilder;
import py.org.icarusdb.example.model.Continent;
import py.org.icarusdb.example.rest.client.ContinentClientService;
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
public class ContinentController extends BaseController implements Serializable
{
    private static final Logger LOGGER = Logger.getLogger(ContinentController.class);
    
    
    @Inject 
    private ContextHelper contextHelper;
    

    private ContinentClientService service = null;

    private List<Continent> resultList = null;
    private Continent continent = null;
    
    private String name = null;


    private String serviveLookupByName = null;
    
    @PostConstruct
    public void init()
    {
        // TODO add navigation control

        
        try
        {
            service = new ContinentClientService();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            
            MessageUtil.addFacesMessageError("error.not.found.config.file");;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            
            MessageUtil.addFacesMessageError("error.not.found.config.file");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            LOGGER.error(e);
            MessageUtil.addFacesMessageError("error.unknown");
        }
        
        initVarz();
        
        serverUri = UriBuilder.buildUri(service.getConnInfo(), "serviceNameContinents");
        resultList = service.getContinents(serverUri);
        
    }
    
    private void initVarz()
    {
        resultList = null;
        continent = null;
        summary = null; 
        name = null;
    }
    
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public List<Continent> getResultList()
    {
        return resultList;
    }
    
    public void search(ActionEvent actionEvent)
    {
        if(name == null || name.isEmpty()) 
        {
            resultList = service.getContinents(serverUri);
        }
        else
        {
            Properties parameters = new IDBProperties();
            parameters.put("name", name);
            
            resultList = service.getContinents(serverUri + getServiceLookupByName(), parameters);
        }
        
        
    }

    private String getServiceLookupByName()
    {
        if (serviveLookupByName == null)
        {
            serviveLookupByName = service.getConnInfo("serviceNameFindByName"); 
        }
        return serviveLookupByName ;
    }

    public Continent getContinent()
    {
        return continent ;
    }

    public void setContinent(Continent selectedRow)
    {
        this.continent = selectedRow;
    }

    public void save()
    {
        String result = null;
        
        try
        {
            result = service.update(serverUri + "/update", continent);
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
                summary = MessageUtil.retrieveMessage("label.record.updated");
            }
        }
    }

    public void clear()
    {
        // TODO check
        contextHelper.clearAction();
        
        initVarz();
    }

    public boolean isPrintable()
    {
        return (resultList != null) && (!resultList.isEmpty());
    }
    
    // TODO implement report 
    public void print()
    {
//        reportController.init();
//        
//        reportController.setReportPath("/reports");
//        reportController.setReportTemplateName("Continents");
//
//        reportController.setReportName("Continents");
//        reportController.addDataSourceEntityCollection(resultList);
//        
//        reportController.addParameter("name" , name);
//        
//        reportController.print();
//        
    }
    
}
