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
package py.org.icarusdb.example.rest.client;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import py.org.icarusdb.commons.rest.client.ServiceHelper;
import py.org.icarusdb.commons.util.FileHelper;
import py.org.icarusdb.example.util.SessionParameters;

/**
 * @author Betto McRose [icarus]
 *         mcrose@icarusdb.com.py
 *         mcrose.dev@gmail.com
 *
 */
public class ExampleClientService extends ServiceHelper
{

    public ExampleClientService() throws FileNotFoundException, IOException
    {
        super();
        addConnConfigParamater(
                FileHelper.loadConfigParams(
                        SessionParameters.EXAMPLE_SERVER_PROJECT_CFG_FILE_NAME, 
                        SessionParameters.JBOSS7_JBOSSSERVER_EXAMPLE_SERVER_CONN_CONFIG_DIR
                )
        );
    }
    
    @Override
    public String getConnInfo(String key)
    {
        return super.connInfo.get(key);
    }

    @Override
    public Map<String, String> getConnInfo()
    {
        return super.connInfo;
    }


}
