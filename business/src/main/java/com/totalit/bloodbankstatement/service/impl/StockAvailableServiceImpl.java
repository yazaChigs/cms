package com.totalit.bloodbankstatement.service.impl;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.totalit.bloodbankstatement.domain.config.Branch;
import com.totalit.bloodbankstatement.domain.config.StockAvailable;
import com.totalit.bloodbankstatement.domain.config.StockQuarantined;
import com.totalit.bloodbankstatement.domain.dto.SearchDTO;
import com.totalit.bloodbankstatement.domain.dto.StockInfoDTO;
import com.totalit.bloodbankstatement.repo.StockAvailableRepo;
import com.totalit.bloodbankstatement.service.StockAvailableService;
import com.totalit.bloodbankstatement.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.StringUtil;
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

@Repository
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class StockAvailableServiceImpl implements StockAvailableService {

    @Resource
    private StockAvailableRepo repo;
    @Resource
    private UserService userService;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public StockAvailable getByName(String name) {
        return null;
    }

    @Override
    public List<StockAvailable> getAll() {
        return repo.findByActive(Boolean.TRUE);
    }

    @Override
    public StockAvailable get(Long id) {
        return repo.findById(id).get();
    }

    @Override
    public void delete(StockAvailable stockAvailable) {

    }

    @Override
    public List<StockAvailable> getPageable() {
        return null;
    }

    @Override
    public StockAvailable save(StockAvailable t) {
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
    public StockAvailable findByUuid(String uuid) {
        return null;
    }

    @Override
    public Boolean checkDuplicate(StockAvailable current, StockAvailable old) {
        if (current.getId() != null) {
            /**
             * @param current is in existence
             */
            if (!current.getId().equals(old.getId())) {
                if (get(current.getId()) != null) {
                    return true;
                }
            }

        } else if (current.getId() == null) {
            /**
             * @param current is new
             */
            if (get(current.getId())  != null) {
                return true;
            }
        }
        return false;
    }

    @Override
    public StockAvailable getByBranchAndActive(Branch branch, Boolean active) {
        return repo.getByBranchAndActive(branch, active);
    }

    public StockAvailable getAvailable(SearchDTO dto, Branch branch) {
        try{
            StringBuilder builder = new StringBuilder("from StockAvailable p");
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
                builder.append("where p.dateCreated=:date");
                position++;
            }else{
                builder.append(" and p.dateCreated=:date");
            }
        }
            if(position == 0) {
                builder.append(" where p.active=:active");
                position++;
            }else{
                builder.append(" and p.active=:active");
            }
            TypedQuery query = entityManager.createQuery(builder.toString(), StockAvailable.class);
            if(branch != null){
                query.setParameter("branch", branch);
            }
            if(dto.getDate() != null) {
                query.setParameter("date", dto.getDate());
            }
            query.setParameter("active", Boolean.TRUE);

            return (StockAvailable) query.getSingleResult();
        }catch (NoResultException ex) {
            return null;
        }

    }

    public StockQuarantined getQuarantine(SearchDTO dto, Branch branch) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        System.err.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(dto));

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
            System.err.println("****************");
            System.err.println("finding by date");
            System.err.println("****************");
            if(position == 0) {
                builder.append("where p.dateCreated=:date");
                position++;
            }else{
                builder.append(" and p.dateCreated=:date");
            }
        }
            if(position == 0) {
                builder.append(" where p.active=:active");
                position++;
            }else{
                builder.append(" and p.active=:active");
            }
            TypedQuery query = entityManager.createQuery(builder.toString(), StockQuarantined.class);
            if(branch != null){
                query.setParameter("branch", branch);
            }
            if(dto.getDate() != null) {
                query.setParameter("date", dto.getDate());
            }
            query.setParameter("active", Boolean.TRUE);

            return (StockQuarantined) query.getSingleResult();
        }catch (NoResultException ex) {
            return null;
        }

    }

    public StockInfoDTO getResult(SearchDTO dto) {
        List<StockAvailable> availableList = new ArrayList<>();
        List<StockQuarantined> quarantinedList = new ArrayList<>();
        dto.getBranches().forEach(item -> {
            if (item != null) {
                availableList.add(getAvailable(dto, item));
                try {
                    quarantinedList.add(getQuarantine(dto, item));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        });
        Integer availableStock = 0;
        Integer supplies = 0;
        Integer orders = 0;
        Integer demandVsSupply = 0;
        Integer collections = 0;
        Integer quarantineStock = 0;
        Integer opening = 0;
        Integer receipts = 0;
        Integer issues = 0;
        Integer discards = 0;

        StockInfoDTO availableDTO = new StockInfoDTO();

        for(StockAvailable item : availableList){
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                System.err.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(item));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            if (item != null) {
                availableStock += checkNull(item.getTotalTotal()) + checkNull(item.getTotalTotalcompatibility());
                supplies += checkNull(item.getHospitals()) + checkNull(item.getCompatsIssues());
                orders += checkNull(item.getCompatsOrders()) + checkNull(item.getTotalHospitalOrders());
                if (orders != 0)
                    demandVsSupply += checkNull(supplies/orders);
            }
        }
        availableDTO.setStockAvailable(availableStock);
        availableDTO.setSupplies(supplies);
        availableDTO.setOrders(orders);
        availableDTO.setDemandVsSupply(demandVsSupply);

        for(StockQuarantined item : quarantinedList){
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                System.err.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(item));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            if (item != null) {

                opening += checkNull(item.getOpeningStock());
                receipts += checkNull(item.getTotalReceiptsFromBranches());
                issues += checkNull(item.getTotalIssues());
                discards += checkNull(item.getTotalIssuesDiscards());
                quarantineStock += checkNull(item.getTotalCollections()) + checkNull(item.getTotalReceiptsFromBranches()) - checkNull(item.getTotalIssuesDiscards()) - checkNull(item.getTotalIssues());
                collections += checkNull(item.getTotalCollections()) - checkNull(item.getOpeningStock()); // divided by branchDailyMinimalCapacity.harareTotalMinCapacity kuFront end
            }
        }
        availableDTO.setCollections(collections);
        availableDTO.setStockAvailable(availableStock);
        availableDTO.setOpening(opening);
        availableDTO.setReceipts(receipts);
        availableDTO.setIssues(issues);
        availableDTO.setDiscards(discards);
        availableDTO.setQuarantineStock(quarantineStock);
        return availableDTO;
    }

    public static Integer checkNull (Integer input){
        if(input == null){
            input = 0;
        }
        return input;
    }
}
