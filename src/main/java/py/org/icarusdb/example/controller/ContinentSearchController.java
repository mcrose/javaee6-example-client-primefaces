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
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.jboss.logging.Logger;
import org.primefaces.event.CloseEvent;

import py.org.icarusdb.commons.util.IDBProperties;
import py.org.icarusdb.example.model.Continent;
import py.org.icarusdb.example.rest.client.ContinentClientService;
import py.org.icarusdb.util.AppHelper;
import py.org.icarusdb.util.MessageUtil;

/**
 * @author Betto McRose [icarus]
 *         mcrose@icarusdb.com.py
 *         mcrose.dev@gmail.com
 *
 */
@ManagedBean
@ViewScoped
public class ContinentSearchController implements Serializable
{
    private static final Logger LOGGER = Logger.getLogger(ContinentSearchController.class);
    
    private String name = null;
    private ContinentClientService service = null;
    private String serverUri = null; 
    private List<Continent> continents = null;
    private Continent continent = null;
    private String summary = null; 
    
    @PostConstruct
    public void init()
    {
        try
        {
            serverUri = AppHelper.getRESTfullConfig("example-rest.cfg.properties");
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
            String err = "No se pudo encontrar el archivo de configuracion de los servicios RESTful de AgenDA";
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, err , "");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            
            LOGGER.error(err);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            String err = "No se pudo leer el archivo de configuracion de los servicios RESTful de AgenDA";
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, err , "");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            
            LOGGER.error(err);
        }
        
        service = new ContinentClientService();
        continents  = service.getContinents(serverUri);
    }
    
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public List<Continent> getContinents()
    {
        return continents;
    }
    
    public void search(AjaxBehaviorEvent event)
    {
        if(name == null || name.isEmpty()) 
        {
            continents = service.getContinents(serverUri);
        }
        else
        {
            Properties parameters = new IDBProperties();
            parameters.put("nombreApellido", name);
            
            continents = service.getContinents(serverUri + "/name", parameters);
        }
        
        
    }

    public void handleClose(CloseEvent event)
    {
        if(summary != null) 
        {
            LOGGER.info(summary);
            
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
            summary = null;
        }
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
    
}
