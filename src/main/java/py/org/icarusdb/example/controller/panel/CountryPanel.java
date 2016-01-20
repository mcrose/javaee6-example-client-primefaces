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
package py.org.icarusdb.example.controller.panel;

import java.io.Serializable;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.CloseEvent;

import py.org.icarusdb.commons.util.IDBProperties;
import py.org.icarusdb.example.controller.util.PanelHelper;
import py.org.icarusdb.example.model.ContinentDTO;
import py.org.icarusdb.example.model.CountryDTO;
import py.org.icarusdb.example.repository.CountryListProducer;

/**
 * @author Betto McRose [icarus]
 *         mcrose@icarusdb.com.py
 *         mcrose.dev@gmail.com
 *
 */
@Named
@SessionScoped
public class CountryPanel extends PanelHelper implements Serializable
{

    @Inject
    CountryListProducer producer;
    
    
    private List<CountryDTO> resultList = null;

    private CountryDTO selectedEntity = null;
    private ContinentDTO selectedContinent = null;
    
    private String name = null;


    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public CountryDTO getSelectedEntity()
    {
        return selectedEntity;
    }
    
    public void setSelectedEntity(CountryDTO selectedEntity)
    {
        this.selectedEntity = selectedEntity;
    }
    
    public ContinentDTO getSelectedContinent()
    {
        return selectedContinent;
    }
    
    public void setSelectedContinent(ContinentDTO selectedContinent)
    {
        this.selectedContinent = selectedContinent;
    }
    
    public List<CountryDTO> getResultList()
    {
        return resultList;
    }
    
    public void search()
    {
        if(selectedContinent == null && (name == null || name.isEmpty()))  
        {
            resultList = producer.getActiveCountries();
        }
        else
        {
            Properties parameters = new IDBProperties();
            parameters.put("name", name);

            if(selectedContinent != null) {
                parameters.put("continent", selectedContinent.getId());
            }
            
            resultList = producer.search("/search", parameters);
        }
                
    }

    @Override
    public void clear()
    {
        name = null;
        selectedEntity = null;
        resultList = null;
    }
    
    @Override
    public boolean isSelected()
    {
        return selectedEntity != null;
    }
    
    @Override
    public void handleClose(CloseEvent event)
    {
        // nothing by now...
        System.out.println("closing " + this.getName()); //TODO remove line
    }
    
    @PostConstruct
    public void init()
    {
        System.out.println("init... " + this.getClass().getSimpleName()); //TODO remove line
    }


}
