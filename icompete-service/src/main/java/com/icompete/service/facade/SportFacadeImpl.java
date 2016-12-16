package com.icompete.service.facade;

import com.icompete.dto.SportDTO;
import com.icompete.entity.Sport;
import com.icompete.facade.SportFacade;
import com.icompete.service.BeanMappingService;
import com.icompete.service.SportService;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

/**
 *
 * @author Xhulio
 */
@Service
@Transactional
public class SportFacadeImpl implements SportFacade{

    @Inject
    SportService sportService;
    
    @Inject
    BeanMappingService beanMappingService;
    
    @Override
    public SportDTO getById(Long l) {
        Sport sport = sportService.findById(l);
        SportDTO mappedSport = beanMappingService.mapTo(sport, SportDTO.class);
        mappedSport.setId(sport.getId());
        return mappedSport;
    }

    @Override
    public List<SportDTO> getAll() {
        List<Sport> allSports = sportService.findAll();
        List<SportDTO> allMappedSports = new ArrayList<>();
        
        for(Sport sport : allSports){
            SportDTO mappedSport = beanMappingService.mapTo(sport, SportDTO.class);
            mappedSport.setId(sport.getId());
            allMappedSports.add(mappedSport);
        }
        
        return allMappedSports;
    }
    
}
