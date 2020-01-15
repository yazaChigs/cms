package com.totalit.bloodbankstatement.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.totalit.bloodbankstatement.domain.config.Branch;
import com.totalit.bloodbankstatement.domain.config.StockIssuedToQuarantine;
import com.totalit.bloodbankstatement.domain.config.StockQuarantined;
import com.totalit.bloodbankstatement.domain.config.StockReceivedFromQuarantined;
import com.totalit.bloodbankstatement.domain.dto.SearchDTO;
import com.totalit.bloodbankstatement.repo.StockAvailableRepo;
import com.totalit.bloodbankstatement.repo.StockIssuedToQuarantineRepo;
import com.totalit.bloodbankstatement.repo.StockQuarantinedRepo;
import com.totalit.bloodbankstatement.repo.StockReceivedFromQuarantineRepo;
import com.totalit.bloodbankstatement.service.StockQuarantinedService;
import com.totalit.bloodbankstatement.service.UserService;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class StockQuarantinedServiceImpl implements StockQuarantinedService {

    @Resource
    private StockQuarantinedRepo repo;
    @Resource
    private StockReceivedFromQuarantineRepo receivedFromQuarantineRepo;
    @Resource
    private StockIssuedToQuarantineRepo issuedToQuarantineRepo;
    @Resource
    private UserService userService;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public StockQuarantined getByName(String name) {
        return null;
    }

    @Override
    public List<StockQuarantined> getAll() {
            return repo.findByActive(Boolean.TRUE);
    }

    @Override
    public StockQuarantined get(Long id) {
        return repo.findById(id).get();
    }

    @Override
    public void delete(StockQuarantined stockQuarantined) {

    }

    @Override
    public List<StockQuarantined> getPageable() {
        return null;
    }

    @Override
    public StockQuarantined save(StockQuarantined t) {
        if (t.getId() == null) {
            t.setCreatedBy(userService.getCurrentUser());
            t.setDateCreated(new Date());
            return repo.save(t);
        }
        if(t.getCreatedById()!=null){
            t.setCreatedBy(userService.get(t.getCreatedById()));
        }
        t.setModifiedBy(userService.getCurrentUser());
        t.setDateModified(new Date());
        return repo.save(t);
    }

    @Override
    public StockQuarantined findByUuid(String uuid) {
        return null;
    }

    @Override
    public Boolean checkDuplicate(StockQuarantined current, StockQuarantined old) {
        return null;
    }

    @Override
    public StockQuarantined getByBranchAndActive(Branch branch, boolean active) {
        return repo.findByBranchAndActive(branch, active);
    }

    @Override
    public StockQuarantined getByBranchAndDateAndActive(Branch branch, Date date, Boolean active) {
        return repo.findByBranchAndDateCreatedAndActive(branch, date, active);
    }

    @Override
    public Long getQuarantineByDateAndBranch(Branch branch, Date startDate, Date endDate, String column) {
        StringBuilder builder = new StringBuilder("Select sum(p." + column + ") from StockQuarantined p");
        int position = 0;
        if(branch != null) {
            if(position == 0) {
                builder.append(" where p.branch=:branch");
                position++;
            }else{
                builder.append(" and p.branch=:branch");
            }
        }
        if(startDate != null && endDate != null) {
            if(position == 0) {
                builder.append(" where p.todaysDate between :startDate and :endDate");
                position++;
            }else{
                builder.append(" and p.todaysDate between :startDate and :endDate");
            }
        }
        if(position == 0) {
            builder.append(" where p.active=:active");
            position++;
        }else{
            builder.append(" and p.active=:active");
        }
        TypedQuery query = entityManager.createQuery(builder.toString(), Long.class);
        if(branch != null){
            query.setParameter("branch", branch);
        }
        if(startDate != null && endDate != null) {
            query.setParameter("startDate", startDate);
            query.setParameter("endDate", endDate);
        }
        query.setParameter("active", Boolean.FALSE);
        Long result = (Long) query.getSingleResult();
        return result != null ? result : 0L;
    }


    public StockQuarantined getQuarantineByDate(SearchDTO dto, Branch branch) {
        StockQuarantined wantedQuarantine;
        List<StockIssuedToQuarantine> stockIssuedToQuarantines = new ArrayList<>();
        List<StockReceivedFromQuarantined> stockReceivedFromQuarantineds = new ArrayList<>();
        try{
            StringBuilder builder = new StringBuilder("from StockQuarantined p");
            int position = 0;
            if(branch != null) {
                if(position == 0) {
                    builder.append(" where p.branch=:branch");
                    position++;
                }else{
                    builder.append(" and p.branch=:branch");
                }
            }
            if(dto.getDate() != null) {
                if(position == 0) {
                    builder.append("where p.todaysDate=:date");
                    position++;
                }else{
                    builder.append(" and p.todaysDate=:date");
                }
            }
//            if(position == 0) {
//                builder.append(" where p.active=:active");
//                position++;
//            }else{
//                builder.append(" and p.active=:active");
//            }
            TypedQuery query = entityManager.createQuery(builder.toString(), StockQuarantined.class);
            if(branch != null){
                query.setParameter("branch", branch);
            }
            if(dto.getDate() != null) {
                query.setParameter("date", dto.getDate());
            }
//            query.setParameter("active", Boolean.FALSE);

            wantedQuarantine = (StockQuarantined) query.getSingleResult();
            issuedToQuarantineRepo.findAllByDateCreatedAndStockQuarantined(wantedQuarantine.getDateCreated(), wantedQuarantine).stream().forEach(item-> {
                stockIssuedToQuarantines.add(item);
            });
            receivedFromQuarantineRepo.findAllByDateCreatedAndStockQuarantined(wantedQuarantine.getDateCreated(), wantedQuarantine).stream().forEach(item-> {
                stockReceivedFromQuarantineds.add(item);
            });
            wantedQuarantine.setIssuedToQuarantines(stockIssuedToQuarantines);
            wantedQuarantine.setReceivedFromQuarantineds(stockReceivedFromQuarantineds);
            return wantedQuarantine;
        }catch (NoResultException ex) {
            return null;
        }

    }

    @Override
    public List<StockQuarantined> getAllByActive(boolean active) {
        return repo.findByActive(active);
    }

    public StockQuarantined getByBranchAndActiveAndDateCreatedBetween(Branch branch, Boolean active, Date startDate, Date endDate) {
        return repo.findByBranchAndActiveAndDateCreatedBetween(branch, active, startDate, endDate);
    }
}
