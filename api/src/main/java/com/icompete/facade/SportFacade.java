package com.icompete.facade;

import com.icompete.dto.SportDTO;
import java.util.List;

import com.icompete.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Xhulio
 */
@Service
public interface SportFacade {
    SportDTO getById(Long id);
    List<SportDTO> getAll();
    void create(SportDTO sportDTO);
    void update(SportDTO sportDTO) throws EntityNotFoundException;
    void delete(Long id) throws EntityNotFoundException;
}
