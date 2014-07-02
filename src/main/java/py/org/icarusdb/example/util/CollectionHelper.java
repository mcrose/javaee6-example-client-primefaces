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
package py.org.icarusdb.example.util;

import java.util.LinkedList;
import java.util.List;

import py.org.icarusdb.example.model.ContinentDTO;
import py.org.icarusdb.example.model.CountryDTO;
import py.org.icarusdb.example.model.StateDTO;

/**
 * @author Betto McRose [icarus]
 *         mcrose@icarusdb.com.py
 *         mcrose.dev@gmail.com
 *
 */
public class CollectionHelper
{

    // FIXME implement EntityInterface (will work for DTOs too) !!
    
    public static ContinentDTO getContinent(List<ContinentDTO> activeContinents, ContinentDTO continentDTO)
    {
        if (continentDTO == null) return null;
        
        for(ContinentDTO dto : activeContinents)
        {
            if (continentDTO.getId().longValue() == dto.getId().longValue())
            {
                return dto;
            }
        }
        return null;
    }

    public static CountryDTO getCountry(List<CountryDTO> activeCountries, CountryDTO countryDTO)
    {
        if (countryDTO == null) return null;
        
        for(CountryDTO dto : activeCountries)
        {
            if (countryDTO.getId().longValue() == dto.getId().longValue())
            {
                return dto;
            }
        }
        return null;
    }

    public static List<CountryDTO> getCountriesByContinent(List<CountryDTO> activeCountries, ContinentDTO continentDTO)
    {
        if (continentDTO == null) return null;
        
        List<CountryDTO> filtered = new LinkedList<CountryDTO>();
        
        for(CountryDTO dto : activeCountries)
        {
            if (continentDTO.getId().longValue() == dto.getContinentDTO().getId().longValue())
            {
                filtered.add(dto);
            }
        }
        
        return filtered;
    }

    public static StateDTO getState(List<StateDTO> activeStates, StateDTO stateDTO)
    {
        if (stateDTO == null) return null;
        
        for(StateDTO dto : activeStates)
        {
            if (stateDTO.getId().longValue() == dto.getId().longValue())
            {
                return dto;
            }
        }
        return null;
    }

    public static List<StateDTO> getStatesByCountry(List<StateDTO> activeStates, CountryDTO countryDTO)
    {
        if (countryDTO == null) return null;
        
        List<StateDTO> filtered = new LinkedList<StateDTO>();
        
        for(StateDTO dto : activeStates)
        {
            if (countryDTO.getId().longValue() == dto.getCountryDTO().getId().longValue())
            {
                filtered.add(dto);
            }
        }
        
        return filtered;
    }


}
