/**
 * Copyright 2014 Roberto Gamarra (Betto McRose [icarus]
           mcrose@icarusdb.com.py@icarusdb.com.py)
 * Icarus
 * Betto McRose
 * Roberto Gamarra
 * 
 * as you wish... at your service
 * 
 * IcarusDB
 * 
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
package py.org.icarusdb.example.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import py.org.icarusdb.commons.converter.EntityConverter;
import py.org.icarusdb.example.model.CountryDTO;
import py.org.icarusdb.example.util.SessionParameters;

/**
 * @author Betto McRose [icarus]
 *         mcrose@icarusdb.com.py
 *         mcrose.dev@gmail.com
 *
 */
@FacesConverter(forClass=CountryDTO.class)
public class CountryConverter extends EntityConverter implements Converter
{
    
    public Object getAsObject(FacesContext context, UIComponent component, String value)
    {
        if(value == null || value.isEmpty())
        {
            return null;
        }
        
        return getViewMap(context).get(value+CountryDTO.class.getName());
    }

    public String getAsString(FacesContext context, UIComponent component, Object object)
    {
        if(object == null) return null;
        
        CountryDTO entity = null;
        try
        {
            entity = (CountryDTO) object;
            Long id = entity.getId();
            if (id != null)
            {
                getViewMap(context).put(id.toString()+CountryDTO.class.getName(), object);
                
                return id.toString();
            }
        }
        catch (ClassCastException cce)
        {
            FacesMessage errMsg = new FacesMessage(SessionParameters.CONVERSION_ERROR_MESSAGE_ID);
            FacesContext.getCurrentInstance().addMessage(null, errMsg);
            throw new ConverterException(errMsg.getSummary());
        }        
        
        return null;
        
    }

}
