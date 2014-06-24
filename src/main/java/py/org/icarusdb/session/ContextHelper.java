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
package py.org.icarusdb.session;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 * @author Betto McRose [icarus]
 *         mcrose@icarusdb.com.py
 *         mcrose.dev@gmail.com
 *
 */
@Named
@SessionScoped
public class ContextHelper implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = 1744702171402523184L;
    
    private String selectedMenu = null;
    private String selectedAction = null;
    private Serializable selectedEntityId = null;


    
    
    public String getSelectedMenu()
    {
        return selectedMenu;
    }
    
    public void setSelectedMenu(String selectedMenu)
    {
        this.selectedMenu = selectedMenu;
    }
    
    public String getSelectedAction()
    {
        return selectedAction;
    }
    
    public void setSelectedAction(String selectedAction)
    {
        this.selectedAction = selectedAction;
    }
    
    public Serializable getSelectedEntityId()
    {
        return selectedEntityId;
    }
    
    public void setSelectedEntityId(Serializable id)
    {
        this.selectedEntityId = id;
    }
    
    public boolean containsMenuAction(String key)
    {
        return key.equalsIgnoreCase(selectedMenu);
    }

    public void clearAction()
    {
        selectedMenu = null;
        selectedAction = null;
        selectedEntityId = null;
    }

    
    
}
