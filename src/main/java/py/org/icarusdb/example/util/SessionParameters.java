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
package py.org.icarusdb.example.util;

import java.util.Locale;

import javax.enterprise.inject.Produces;
import javax.inject.Named;

import py.org.icarusdb.commons.parameter.GlobalParameters;
import py.org.icarusdb.util.AppHelper;

/**
 * @author Betto McRose [icarus]
 *         mcrose@icarusdb.com.py
 *         mcrose.dev@gmail.com
 *
 */
public class SessionParameters extends GlobalParameters
{
    
    /** custom jboss-as-7.1.1.Final configuration folders */
    public static final String JBOSS7_JBOSSSERVER_EXAMPLE_SERVER_CONN_CONFIG_DIR = "example-server/connection";
    
    /** server-side module project */
    public static final String EXAMPLE_SERVER_PROJECT_CFG_FILE_NAME = "example-rest.cfg.properties";
    
    
    
    public static final String BUNDLE_URL = "py.com.icarusdb.demo.resources.Messages";

    @Produces @Named final int getSearchMaxResutl()
    {
        return SEARCH_MAX_RESULT;
    }
    
    @Produces @Named final String getDatePattern()
    {
        return "dd/MM/yyyy";
    }
    
    @Produces @Named final String getDateTimePattern()
    {
        return "dd/MM/yyyy HH:mm:ss";
    }
    
    @Produces @Named final Locale getAppLocale()
    {
        return AppHelper.getLocale();
    }
    
    // User Setting
    public final static String ACTION_MENU_USERSETTING = "UserSettingAction";
    
    @Produces @Named final String getActionUserSetting()
    {
        return ACTION_MENU_USERSETTING;
    }
    
    // Invoice menu
    public final static String ACTION_MENU_INVOICE = "ACTION_MENU_INVOICE";
    public final static String ACTION_ADD_INVOICE = "ACTION_ADD_INVOICE";
    
    @Produces @Named final String getActionMenuInvoice()
    {
        return ACTION_MENU_INVOICE;
    }
    
    @Produces @Named final String getActionAddInvoice()
    {
        return ACTION_ADD_INVOICE;
    }
    
}
