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
package py.org.icarusdb.example.controller.util;

import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;


/**
 * @author Betto McRose [icarus]
 *         mcrose@icarusdb.com.py
 *         mcrose.dev@gmail.com
 *
 */
public abstract class PanelHelper 
{
    public abstract void search();
    public abstract void clear();
    public abstract boolean isSelected();
    public abstract void handleClose(CloseEvent event);
//    public abstract List<EntityInterface> getResultList(); // FIXME implement!
    

    
    /**
     * example: :searchform:supplier or :editform:supplier
     */
    protected String tagId2update = null;
    
    protected String clientId = null;

    public String getTagId2update()
    {
        return tagId2update;
    }
    
    public void setTagId2update(String tagId2update)
    {
        this.tagId2update = tagId2update;
    }
    
    
    public void processUpdateClientId()
    {
        if(clientId != null)
        {
            RequestContext.getCurrentInstance().update(clientId);
        }
    }


}
