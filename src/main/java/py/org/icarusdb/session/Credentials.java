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
import java.util.LinkedList;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import py.org.icarusdb.commons.session.BaseCredentials;

/**
 * @author Betto McRose [icarus]
 *         mcrose@icarusdb.com.py
 *         mcrose.dev@gmail.com
 *
 */
@SessionScoped
@Named
public class Credentials extends BaseCredentials implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = 5579793022747171305L;

    @PostConstruct
    public void init()
    {
        ipAddr = null;
        username = null;
        password = null;
        parameters = new Properties();
        rols = new LinkedList<String>();
    }

    public String getIpAddr()
    {
        return ipAddr;
    }
    
    public void setIpAddr(String ipAddr)
    {
        this.ipAddr = ipAddr;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
    
    public Properties getParameters()
    {
        return parameters;
    }
    
    public void setParameters(Properties parameters)
    {
        this.parameters = parameters;
    }
    
    public void addParamenter(String key, Object value)
    {
        parameters.put(key, value);
    }

    public void addRole(String rol)
    {
        rols.add(rol);
    }
    
    public boolean hasRol(String rol)
    {
        return rols.contains(rol);
    }

}