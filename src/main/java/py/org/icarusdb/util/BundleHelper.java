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
package py.org.icarusdb.util;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author mcrose
 *
 */
public class BundleHelper
{
    /**
     * <p>
     * default message bundle in IcarusDB's common-lib jar<br>
     * "py.com.icarusdb.messages.CommonMessages"<br>
     * </p>
     * @param key
     * @return message
     */
    public static String getBundleMessage(String key)
    {
        return getBundleMessage("py.com.icarusdb.common.CommonMessages", key);
    }
    
    public static String getBundleMessage(String bundleName, String key)
    {
        return getBundleMessage(bundleName, key, Locale.getDefault());
    }
    
    /**
     * returns the requested <b>key</b> message with the given <b>bundleName</b>
     *  
     * @param bundleName
     * @param key
     * @param locale 
     * @return message
     */
    public static String getBundleMessage(String bundleName, String key, Locale locale)
    {
        try
        {
            ResourceBundle bundle = ResourceBundle.getBundle(bundleName, locale);
            if(bundle.containsKey(key))
            {
                return bundle.getString(key);
                
            }
            /* 
             * may be is a "common" pre-defined property bundle 
             * look up for it in base common messages
             */
            if(!bundleName.contains("CommonMessages")) {
                return getBundleMessage(key);
            }
        }
        catch (Exception e)
        {
            /*nothing*/
        }
        return null;
    }
    

}
