package com.icompete.service.facade;

import com.icompete.dto.SportDTO;
import com.icompete.entity.Sport;
import com.icompete.exception.EntityNotFoundException;
import com.icompete.facade.SportFacade;
import com.icompete.service.BeanMappingService;
import com.icompete.service.SportService;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

/**
 *
 * @author Xhulio
 */
@Service
@Transactional
public class SportFacadeImpl implements SportFacade{

    @Inject
    private SportService sportService;
    
    @Inject
    private BeanMappingService beanMappingService;
    
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

    @Override
    public void create(SportDTO sportDTO) {
        Sport sport = beanMappingService.mapTo(sportDTO, Sport.class);
        sportService.create(sport);
    }

    @Override
    public void update(SportDTO sportDTO) throws EntityNotFoundException {
        Sport sport = sportService.findById(sportDTO.getId());
        if (sport == null) {
            throw new EntityNotFoundException("Cannot find sport with id {" + sportDTO.getId() + "}");
        }
        beanMappingService.mapTo(sportDTO, sport);
        sportService.update(sport);
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException {
        Sport sport = sportService.findById(id);
        if (sport == null) {
            throw new EntityNotFoundException("Cannot find sport with id {" + id + "}");
        }
        sportService.delete(sport);
    }
}
