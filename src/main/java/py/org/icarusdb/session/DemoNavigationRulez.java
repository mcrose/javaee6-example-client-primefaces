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

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import py.org.icarusdb.util.NavigationRulez;
import py.org.icarusdb.util.NavigationRulezHelper;

/**
 * @author Betto McRose [icarus]
 *         mcrose@icarusdb.com.py
 *         mcrose.dev@gmail.com
 *
 */
@Named(value="navigationRulez")
@RequestScoped
public class DemoNavigationRulez extends NavigationRulez
{
    
    public String goContinents()
    {
        return "/admin/continents.xhtml"+NavigationRulezHelper.FACES_REDIRECT;
    }
    
    public String goCountries()
    {
        return "/admin/countries.xhtml"+NavigationRulezHelper.FACES_REDIRECT;
    }
    
    public String goStates()
    {
        return "/admin/states.xhtml"+NavigationRulezHelper.FACES_REDIRECT;
    }
    
    public String goCities()
    {
        return "/admin/cities.xhtml"+NavigationRulezHelper.FACES_REDIRECT;
    }
    
    public String goCityEdit(String version)
    {
        if("v1".equals(version)) {
            return "/admin/city.xhtml"+NavigationRulezHelper.FACES_REDIRECT;
        }
        else if ("v2".equalsIgnoreCase(version)) {
            return "/admin/city2.xhtml"+NavigationRulezHelper.FACES_REDIRECT;
        }
        return null;
    }
    
    
}
