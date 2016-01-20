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
package py.org.icarusdb.util.ui;

import javax.faces.bean.RequestScoped;
import javax.inject.Named;

import py.org.icarusdb.commons.util.iu.primefaces.DialogEffect;

/**
 * @author Betto McRose [icarus]
 *         mcrose@icarusdb.com.py
 *         mcrose.dev@gmail.com
 *
 */
@Named @RequestScoped
public class DialogEffectHelper 
{

    public String getBlindEffect()
    {
        return DialogEffect.BLIND.toString();
    }
    
    public String getBounceEffect()
    {
        return DialogEffect.BOUNCE.toString();
    }
    
    public String getClipEffect()
    {
        return DialogEffect.CLIP.toString();
    }
    
    public String getDropEffect()
    {
        return DialogEffect.DROP.toString();
    }
    
    public String getExplodeEffect()
    {
        return DialogEffect.EXPLODE.toString();
    }
    
    public String getFadeEffect()
    {
        return DialogEffect.FADE.toString();
    }
    
    public String getFoldEffect()
    {
        return DialogEffect.FOLD.toString();
    }
    
    public String getHighlightEffect()
    {
        return DialogEffect.HIGHLIGHT.toString();
    }
    
    public String getPuffEffect()
    {
        return DialogEffect.PUFF.toString();
    }
    
    public String getPulsateEffect()
    {
        return DialogEffect.PULSATE.toString();
    }
    
    public String getScaleEffect()
    {
        return DialogEffect.SCALE.toString();
    }
    
    public String getShakeEffect()
    {
        return DialogEffect.SHAKE.toString();
    }
    
    public String getSizeEffect()
    {
        return DialogEffect.SIZE.toString();
    }
    
    public String getSlideEffect()
    {
        return DialogEffect.SLIDE.toString();
    }
    
    public String getTransferEffect()
    {
        return DialogEffect.TRANSFER.toString();
    }
    
    
    
}
