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
package py.org.icarusdb.util;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * @author Betto McRose [icarus]
 *         mcrose@icarusdb.com.py
 *         mcrose.dev@gmail.com
 *
 */
public class MessageUtil
{
    
    
    public static String retrieveMessage(String message)
    {
        String msg =  AppHelper.getBundleMessage(message);
        if(msg == null) {
            msg = message;
        }
        return msg;
    }

    /**
     * <p>add a message to {@link FacesContext}'s message with the given message</p>
     * <p>if the message represents a key stored in bundle property, will show the corresponding
     *    value store in the file. 
     * </p>
     * @param message
     * 
     */
    public static void addFacesMessage(String message)
    {
        String msg = retrieveMessage(message);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));
    }

    public static void addFacesMessageInfo(String message)
    {
        addFacesMessageInfo(message, null);
    }
    
    public static void addFacesMessageInfo(String message, String detail)
    {
        String msg = retrieveMessage(message);
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, detail);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }

    public static void addFacesMessageWarm(String message)
    {
        addFacesMessageWarm(message, null);
    }
    
    public static void addFacesMessageWarm(String message, String detail)
    {
        String msg = retrieveMessage(message);
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_WARN, msg, detail);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }

    public static void addFacesMessageError(String message)
    {
        addFacesMessageError(message, null);
    }
    
    public static void addFacesMessageError(String message, String detail)
    {
        String msg = retrieveMessage(message);
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, detail);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }

    public static void showResults(List<?> collection)
    {
        if(collection.isEmpty()) {
            addFacesMessageWarm("search.label.noRecordsFound");
        } else {
            String message = AppHelper.getBundleMessage("search.label.recordsFound");
            addFacesMessageInfo(message + ": " + collection.size());
        }
    }
    
    
}
