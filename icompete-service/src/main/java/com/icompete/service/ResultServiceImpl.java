package com.icompete.service;

import com.icompete.dao.ResultDao;
import com.icompete.entity.Result;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

/**
 *
 * @author Bohumel
 */
@Service
public class ResultServiceImpl implements ResultService {
    
    @Inject
    ResultDao result;

    @Override
    public List<Result> findAll() {
        return this.result.findAll();
    }

    @Override
    public Result findById(Long id) {
        return this.result.findById(id);
    }

    @Override
    public void create(Result result) {
        this.result.create(result);
    }

    @Override
    public void update(Result result) {
        this.result.update(result);
    }

    @Override
    public void delete(Result result) {
        this.result.delete(result);
    }

    @Override
    public List<Result> findResultsByPosition(Integer position) {
        List<Result> results = new ArrayList<>();
        if(position == null){
            return results;
        }
        List<Result> allResults = this.result.findAll();
        for(Result item : allResults){
            if(item.getPosition() == position){
                results.add(item);
            }
        }
        return results; 
    }
    
}
