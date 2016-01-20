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
package py.org.icarusdb.example.model;


/**
 * @author Betto McRose [icarus]
 *         mcrose@icarusdb.com.py
 *         mcrose.dev@gmail.com
 *
 */
public class CountryDTO
{
    
    private Long id;
    private ContinentDTO continentDTO;
    private String name;
    private Boolean active;
//    private Set<State> states = new HashSet<State>(0);

    public Long getId()
    {
        return this.id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public ContinentDTO getContinentDTO()
    {
        return this.continentDTO;
    }

    public void setContinentDTO(ContinentDTO continent)
    {
        this.continentDTO = continent;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Boolean getActive()
    {
        return this.active;
    }

    public void setActive(Boolean active)
    {
        this.active = active;
    }

//    public Set<State> getStates()
//    {
//        return this.states;
//    }
//
//    public void setStates(Set<State> stateses)
//    {
//        this.states = stateses;
//    }

}
