package com.totalit.bloodbankstatement.service.impl;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.totalit.bloodbankstatement.domain.config.Admin.BranchDailyMinimalCapacity;
import com.totalit.bloodbankstatement.domain.config.Admin.NoDaysRequiremets;
import com.totalit.bloodbankstatement.domain.config.Admin.UnadjustedDailyRequirements;
import com.totalit.bloodbankstatement.domain.config.Branch;
import com.totalit.bloodbankstatement.domain.config.StockAvailable;
import com.totalit.bloodbankstatement.domain.config.StockQuarantined;
import com.totalit.bloodbankstatement.domain.dto.SearchDTO;
import com.totalit.bloodbankstatement.domain.dto.StockInfoDTO;
import com.totalit.bloodbankstatement.repo.StockAvailableRepo;
import com.totalit.bloodbankstatement.repo.admin.BranchDailyMinimalCapacityRepo;
import com.totalit.bloodbankstatement.repo.admin.NoDaysRequiremetsRepo;
import com.totalit.bloodbankstatement.repo.admin.UnadjustedDailyRequirementsRepo;
import com.totalit.bloodbankstatement.service.StockAvailableService;
import com.totalit.bloodbankstatement.service.UserService;
import com.totalit.bloodbankstatement.service.admin.UnadjustedDailyRequirementsService;
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
    @Resource
    private NoDaysRequiremetsRepo requirementsRepo;
    @Resource
    private BranchDailyMinimalCapacityRepo branchDailyMinimalCapacityRepo;
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
//        return repo.getByBranchAndActive(branch, active) != null ? true : false
        return  repo.getByBranchAndActive(branch, active);
    }

    public StockAvailable getAvailableByDate(SearchDTO dto, Branch branch) {
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
            TypedQuery query = entityManager.createQuery(builder.toString(), StockAvailable.class);
            if(branch != null){
                query.setParameter("branch", branch);
            }
            if(dto.getDate() != null) {
                query.setParameter("date", dto.getDate());
            }
//            query.setParameter("active", Boolean.FALSE);

            return (StockAvailable) query.getSingleResult();
        }catch (NoResultException ex) {
            return null;
        }

    }

    @Override
    public List<StockAvailable> getAvailableByDateAndBranch(Branch branch, Date startDate, Date endDate) {
//        StringBuilder builder = new StringBuilder("Select sum(p." + column + ") from StockAvailable p");
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
        TypedQuery query = entityManager.createQuery(builder.toString(), StockAvailable.class);
        if(branch != null){
            query.setParameter("branch", branch);
        }
        if(startDate != null && endDate != null) {
            query.setParameter("startDate", startDate);
            query.setParameter("endDate", endDate);
        }
        query.setParameter("active", Boolean.FALSE);
        List<StockAvailable> result =  query.getResultList();
        return result;
    }

    @Override
    public List<StockAvailable> getAllByActive(Boolean active) {
        return repo.findByActive(active);
    }

    public List<StockAvailable> getAvailable(SearchDTO dto, Branch branch) {
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
                    builder.append("where p.todaysDate=:date");
                    position++;
                }else{
                    builder.append(" and p.todaysDate=:date");
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
            query.setParameter("active", Boolean.FALSE);

            return (List<StockAvailable>) query.getResultList();
        }catch (NoResultException ex) {
            return null;
        }

    }

    public List<StockQuarantined> getQuarantine(SearchDTO dto, Branch branch) {

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
            query.setParameter("active", Boolean.FALSE);

            return (List<StockQuarantined>) query.getResultList();
        }catch (NoResultException ex) {
            return null;
        }

    }

    public StockInfoDTO getResult(SearchDTO dto) {
        Integer availableStock = 0;
        Double supplies = 0.0;
        Double orders = 0.0;
        Double demandVsSupply = 0.0;
        Integer collections = 0;
        Integer collectionsHarare = 0;
        Integer collectionsBulawayo = 0;
        Integer collectionsGweru = 0;
        Integer collectionsMutare = 0;
        Integer collectionsMasvingo = 0;
        Integer quarantineStock = 0;
        Integer opening = 0;
        Integer receipts = 0;
        Integer issues = 0;
        Integer discards = 0, expired = 0, serum = 0, samplesOnly = 0;
        Integer harareTotalMinCap = 0;
        Integer bulawayoTotalMinCap = 0;
        Integer masvingoTotalMinCap = 0;
        Integer mutareTotalMinCap = 0;
        Integer gweruTotalMinCap = 0;
        Integer bmcNumber = 0;
        Integer branchNumberAvailable = 0;
        Integer branchNumberQuarantine = 0;
        Integer stockedOplus = 0;
        Integer stockedOminus = 0;
        Integer stockedAplus = 0;
        Integer stockedBplus = 0;
        Integer dailyNumber = 0;
        Integer dailyReqOplus = 0;
        Integer dailyReqOminus = 0;
        Integer dailyReqAplus = 0;
        Integer dailyReqBplus = 0;
        double bsms = 0;

        int harareDone = 0, bulawayoDone=0, gweruDone=0, masvingoDone=0, mutareDone = 0;
        List<StockAvailable> availableList = new ArrayList<>();
        List<StockQuarantined> quarantinedList = new ArrayList<>();
        dto.getBranches().forEach(item -> {
            if (item != null) {

                availableList.addAll(getAvailable(dto, item));
                quarantinedList.addAll(getQuarantine(dto, item));
            }
        });

        StockInfoDTO availableDTO = new StockInfoDTO();


        for(StockAvailable item : availableList){
            if (item != null) {
                availableStock += checkNull(item.getTotalTotal()) + checkNull(item.getTotalTotalcompatibility());
                supplies += checkNull(item.getHospitals()) + checkNull(item.getCompatsIssues());
                orders += checkNull(item.getCompatsOrders()) + checkNull(item.getTotalHospitalOrders());
                expired += checkNull(item.getExpired());
                stockedOplus += checkNull(item.getRhPositivePcO()) + checkNull(item.getRhPositiveWbO())
                        + checkNull(item.getRhPositivePaedPcO()) + checkNull(item.getRhPositivePaedWbO())
                        + checkNull(item.getRhPositivePcOcompatibility()) + checkNull(item.getRhPositiveWbOcompatibility())
                        + checkNull(item.getRhPositivePaedPcOcompatibility()) + checkNull(item.getRhPositivePaedWbOcompatibility());
                stockedOminus += checkNull(item.getRhNegativePcO()) + checkNull(item.getRhNegativeWbO())
                        + checkNull(item.getRhNegativePaedPcO()) + checkNull(item.getRhNegativePaedWbO())
                        +checkNull(item.getRhNegativePcOcompatibility()) + checkNull(item.getRhNegativeWbOcompatibility())
                        + checkNull(item.getRhNegativePaedPcOcompatibility()) + checkNull(item.getRhNegativePaedWbOcompatibility());
                stockedAplus += checkNull(item.getRhPositivePcA()) + checkNull(item.getRhPositiveWbA())
                        + checkNull(item.getRhPositivePaedPcA()) + checkNull(item.getRhPositivePaedWbA())
                        + checkNull(item.getRhPositivePcAcompatibility()) + checkNull(item.getRhPositiveWbAcompatibility())
                        + checkNull(item.getRhPositivePaedPcAcompatibility()) + checkNull(item.getRhPositivePaedWbAcompatibility());
                stockedBplus += checkNull(item.getRhPositivePcB()) + checkNull(item.getRhPositiveWbB())
                        + checkNull(item.getRhPositivePaedPcB()) + checkNull(item.getRhPositivePaedWbB())
                        + checkNull(item.getRhPositivePcBcompatibility()) + checkNull(item.getRhPositiveWbBcompatibility())
                        + checkNull(item.getRhPositivePaedPcBcompatibility()) + checkNull(item.getRhPositivePaedWbBcompatibility());
                demandVsSupply = supplies/orders;
                branchNumberAvailable ++;
            }
        }

        availableDTO.setExpired(expired);
        availableDTO.setStockedOplus(stockedOplus);
        availableDTO.setStockedOminus(stockedOminus);
        availableDTO.setStockedAplus(stockedAplus );
        availableDTO.setStockedBplus(stockedBplus);
        availableDTO.setStockAvailable(availableStock );
        availableDTO.setSupplies(supplies);
        availableDTO.setOrders(orders);
        availableDTO.setDemandVsSupply(demandVsSupply );

        for(StockQuarantined item : quarantinedList){

            NoDaysRequiremets dailyRequirements = requirementsRepo.findFirstByOrderById();
            BranchDailyMinimalCapacity dailyMinimalCapacity = branchDailyMinimalCapacityRepo.findFirstByOrderById();

            if (item != null && dailyMinimalCapacity != null && dailyRequirements != null) {

                if (item.getBranch().getBranchName().equals("HARARE")){
                harareTotalMinCap += checkNull(dailyMinimalCapacity.getHarareTotalMinCapacity());
                    collectionsHarare = checkNull(item.getTotalCollections());}
                if (item.getBranch().getBranchName().equals("BULAWAYO")){
                bulawayoTotalMinCap += checkNull(dailyMinimalCapacity.getBulawayoTotalMinCapacity());
                    collectionsBulawayo = checkNull(item.getTotalCollections());}
                if (item.getBranch().getBranchName().equals("GWERU")){
                gweruTotalMinCap += checkNull(dailyMinimalCapacity.getGweruTotalMinCapacity());
                    collectionsGweru = checkNull(item.getTotalCollections());}
                if (item.getBranch().getBranchName().equals("MUTARE")){
                mutareTotalMinCap += checkNull(dailyMinimalCapacity.getMutareTotalMinCapacity());
                    collectionsMutare = checkNull(item.getTotalCollections());}
                if (item.getBranch().getBranchName().equals("MASVINGO")){
                masvingoTotalMinCap += checkNull(dailyMinimalCapacity.getMasvingoTotalMinCapacity());
                    collectionsMasvingo = checkNull(item.getTotalCollections());}

//                if (item != null) {
                    if (item.getBranch().getBranchName().equals("HARARE") && harareDone==0) {
                        dailyReqOplus += checkNull(dailyRequirements.getHarareOplus());
                        dailyReqOminus += checkNull(dailyRequirements.getHarareOminus());
                        dailyReqAplus += checkNull(dailyRequirements.getHarareAplus());
                        dailyReqBplus += checkNull(dailyRequirements.getHarareBplus());
                        harareDone++;
                    }
                    if (item.getBranch().getBranchName().equals("BULAWAYO") && bulawayoDone==0) {
                        dailyReqOplus += checkNull(dailyRequirements.getBulawayoOplus());
                        dailyReqOminus += checkNull(dailyRequirements.getBulawayoOminus());
                        dailyReqAplus += checkNull(dailyRequirements.getBulawayoAplus());
                        dailyReqBplus += checkNull(dailyRequirements.getBulawayoBplus());
                        bulawayoDone++;
                    }
                    if (item.getBranch().getBranchName().equals("GWERU") && gweruDone==0) {
                        dailyReqOplus += checkNull(dailyRequirements.getGweruOplus());
                        dailyReqOminus += checkNull(dailyRequirements.getGweruOminus());
                        dailyReqAplus += checkNull(dailyRequirements.getGweruAplus());
                        dailyReqBplus += checkNull(dailyRequirements.getGweruBplus());
                        gweruDone++;
                    }
                    if (item.getBranch().getBranchName().equals("MUTARE") && mutareDone==0) {
                        dailyReqOplus += checkNull(dailyRequirements.getMutareOplus());
                        dailyReqOminus += checkNull(dailyRequirements.getMutareOminus());
                        dailyReqAplus += checkNull(dailyRequirements.getMutareAplus());
                        dailyReqBplus += checkNull(dailyRequirements.getMutareBplus());
                        mutareDone++;
                    }
                    if (item.getBranch().getBranchName().equals("MASVINGO") && masvingoDone==0) {
                        dailyReqOplus += checkNull(dailyRequirements.getMasvingoOplus());
                        dailyReqOminus += checkNull(dailyRequirements.getMasvingoOminus());
                        dailyReqAplus += checkNull(dailyRequirements.getMasvingoAplus());
                        dailyReqBplus += checkNull(dailyRequirements.getMasvingoBplus());
                        masvingoDone++;
                    }

                opening += checkNull(item.getOpeningStock());
                receipts += checkNull(item.getTotalReceiptsFromBranches());
                issues += checkNull(item.getTotalIssues())+ checkNull(item.getAvailableStock());
                discards += checkNull(item.getTotalIssuesDiscards());
                serum += checkNull(item.getDryPacksD1()) + checkNull(item.getDryPacksD3D4());
                samplesOnly += checkNull((item.getSamplesOnly()));
                quarantineStock += checkNull(item.getTotalReceiptsFromBranches())
                        - checkNull(item.getTotalIssuesDiscards()) - checkNull(item.getTotalIssues());
                collections += checkNull(item.getTotalCollections()) ; // divided by branchDailyMinimalCapacity.harareTotalMinCapacity kuFront end
                branchNumberQuarantine ++;
            }
        }

        double availableStockedUnitsTotal = ((stockedOplus + stockedOminus) * 0.6) + ((stockedAplus + stockedBplus) * 0.2);
        double availableRequiremetnsTotal = ((dailyReqOplus + dailyReqOminus) * 0.6) + ((dailyReqAplus + dailyReqBplus) * 0.2);
        double quarantineRequiremetnsTotalRaw = (dailyReqOplus + dailyReqOminus + dailyReqAplus + dailyReqBplus)*0.435;

        bsms = (((availableStockedUnitsTotal/availableRequiremetnsTotal)/5) + (((quarantineStock*0.42)/quarantineRequiremetnsTotalRaw)/5) + (demandVsSupply))/3;

        availableDTO.setHarareTotalMinCap(harareTotalMinCap / checkZero(bmcNumber));
        availableDTO.setBulawayoTotalMinCap(bulawayoTotalMinCap / checkZero(bmcNumber));
        availableDTO.setGweruTotalMinCap(gweruTotalMinCap / checkZero(bmcNumber));
        availableDTO.setMutareTotalMinCap(mutareTotalMinCap / checkZero(bmcNumber));
        availableDTO.setMasvingoTotalMinCap(masvingoTotalMinCap / checkZero(bmcNumber));
        availableDTO.setDailyReqOplus(dailyReqOplus / checkZero(dailyNumber));
        availableDTO.setDailyReqOminus(dailyReqOminus / checkZero(dailyNumber));
        availableDTO.setDailyReqAplus(dailyReqAplus / checkZero(dailyNumber));
        availableDTO.setDailyReqBplus(dailyReqBplus / checkZero(dailyNumber));
        availableDTO.setCollectionsHarare(collectionsHarare);
        availableDTO.setCollectionsBulawayo(collectionsBulawayo);
        availableDTO.setCollectionsGweru(collectionsGweru);
        availableDTO.setCollectionsMasvingo(collectionsMasvingo);
        availableDTO.setCollectionsMutare(collectionsMutare);
        availableDTO.setCollections(collections);
        availableDTO.setStockAvailable(availableStock / checkZero(branchNumberQuarantine));
        availableDTO.setOpening(opening);
        availableDTO.setReceipts(receipts);
        availableDTO.setIssues(issues);
        availableDTO.setDiscards(discards);
        availableDTO.setQuarantineStock(quarantineStock);
        availableDTO.setSerum(serum);
        availableDTO.setSamplesOnly(samplesOnly);
        availableDTO.setBsms(bsms);

        return availableDTO;
    }

    public static Integer checkNull (Integer input){
        if(input == null){
            input = 0;
        }
        return input;
    }
    public static Integer checkZero (Integer input){
        if(input == 0){
            input = 1;
        }
        return input;
    }

    @Override
    public StockAvailable getByBranchAndActiveAndDateCreatedBetween(Branch branch, Boolean active, Date startDate, Date endDate) {
        return repo.findByBranchAndActiveAndDateCreatedBetween(branch, active, startDate, endDate);
    }
}
