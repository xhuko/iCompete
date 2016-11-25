package com.icompete.service;

import com.icompete.dao.RuleDao;
import com.icompete.entity.Rule;

import javax.inject.Inject;
import java.util.Collection;

/**
 * @author Peter Sekan, peter.sekan@mail.muni.cz
 * @version 25/11/2016
 */
public class RuleServiceImpl implements RuleService {

    @Inject
    private RuleDao ruleDao;

    @Override
    public Collection<Rule> findAll() {
        return ruleDao.findAll();
    }

    @Override
    public Rule findById(Long id) {
        return ruleDao.findById(id);
    }

    @Override
    public Long create(Rule rule) {
        if (rule == null) throw new IllegalArgumentException("Argument rule is null.");
        ruleDao.create(rule);
        return rule.getId();
    }

    @Override
    public void update(Rule rule) {
        if (rule == null) throw new IllegalArgumentException("Argument rule is null.");
        ruleDao.update(rule);
    }

    @Override
    public void delete(Rule rule) {
        if (rule == null) throw new IllegalArgumentException("Argument rule is null.");
        ruleDao.delete(rule);
    }
}
