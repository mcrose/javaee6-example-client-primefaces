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

import java.io.Serializable;

/**
 * @author Betto McRose [icarus]
 *         mcrose@icarusdb.com.py
 *         mcrose.dev@gmail.com
 *
 */
public abstract class BaseController implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = -6926252853009751615L;
    
    protected String actionSubTitle = null;
    protected String action = null;


    protected String serverUri = null; 
    protected String summary = null;

    
    public String getActionSubTitle()
    {
        return actionSubTitle;
    }

    public boolean isView()
    {
        return (action != null && action.contains("view"));
    }
    
}
