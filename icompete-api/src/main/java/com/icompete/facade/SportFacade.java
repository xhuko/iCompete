package com.icompete.facade;

import com.icompete.dto.SportDTO;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Xhulio
 */
@Service
public interface SportFacade {
    public SportDTO getById(Long id);
    public List<SportDTO> getAll();
}
