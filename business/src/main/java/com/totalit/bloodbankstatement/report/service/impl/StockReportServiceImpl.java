package com.totalit.bloodbankstatement.report.service.impl;

import com.totalit.bloodbankstatement.domain.config.Admin.NoDaysRequiremets;
import com.totalit.bloodbankstatement.domain.config.Branch;
import com.totalit.bloodbankstatement.domain.config.StockAvailable;
import com.totalit.bloodbankstatement.domain.config.StockQuarantined;
import com.totalit.bloodbankstatement.domain.util.DateUtil;
import com.totalit.bloodbankstatement.repo.admin.NoDaysRequiremetsRepo;
import com.totalit.bloodbankstatement.report.api.GenericReportModel;
import com.totalit.bloodbankstatement.report.service.StockReportService;
import com.totalit.bloodbankstatement.service.BranchService;
import com.totalit.bloodbankstatement.service.StockAvailableService;
import com.totalit.bloodbankstatement.service.StockQuarantinedService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class StockReportServiceImpl implements StockReportService {

    @Resource
    private BranchService branchService;
    @Resource
    private StockAvailableService service;
    @Resource
    private StockQuarantinedService qService;
    @Resource
    private NoDaysRequiremetsRepo requirementsRepo;

    @Override
    public List<GenericReportModel> getDefaultReport() {
        List<GenericReportModel> list = new ArrayList<>();
        List<String> months = new ArrayList<>();
        for(int i = 5; i >= 1; i--) {
            months.add(DateUtil.getFrindlyName(DateUtil.getDateDiffMonth(-i)));
        }
        months.add(DateUtil.getFrindlyName(new Date()));
        list.add(new GenericReportModel(months));
        branchService.getAll().forEach(branch -> {
            GenericReportModel model = new GenericReportModel();
            List<String> row = new ArrayList<>();
            row.add(branch.getBranchName());
            model.setRow(getRow(branch, row));
            list.add(model);
        });
        return list;
    }

    @Override
    public List<GenericReportModel> GetLastMonthStockReport() {
        List<GenericReportModel> list = new ArrayList<>();
        String colors [] = {
                "Green", "Orange", "Red"
        };
        for(String item : colors) {
            List<String> row = new ArrayList<>();
            GenericReportModel model = new GenericReportModel();
            row.add(item);
            model.setRow(getMonthlyRow(item, row));
            list.add(model);
        }
        return list;
    }

    private List<String> getMonthlyRow(String color,  List<String> row) {
        Date date = DateUtil.getDateDiffMonth(-1);
        if(color.equals("Green")){
        branchService.getAll().forEach(branch -> row.add(calculateMonthlyValueGreen(date, DateUtil.getLastDayOfMonth((date)), branch).toString()));
        return row;}
        if(color.equals("Orange")){
            branchService.getAll().forEach(branch -> row.add(calculateMonthlyValueOrange(date, DateUtil.getLastDayOfMonth((date)), branch).toString()));
            return row;}
        if(color.equals("Red")){
            branchService.getAll().forEach(branch -> row.add(calculateMonthlyValueRed(date, DateUtil.getLastDayOfMonth((date)), branch).toString()));
            return row;}
        else return null;
    }

    private Integer calculateMonthlyValueGreen(Date start, Date end, Branch branch) {
        Double bsms = 0.0;
        List<GenericReportModel> model = new ArrayList<>();
        Integer green = 0;
        Integer orange = 0;
        Integer red = 0;
        Integer total = 0;

        List <StockAvailable> availableByDateAndBranch= service.getAvailableByDateAndBranch(branch, start, end);
        for(StockAvailable item : availableByDateAndBranch){

            Integer supplies = 0;
            Integer orders = 0;
            Double demandVsSupply = 0.0;
            Integer quarantineStock = 0;
            Integer stockedOplus = 0;
            Integer stockedOminus = 0;
            Integer stockedAplus = 0;
            Integer stockedBplus = 0;
            Integer dailyReqOplus = 0;
            Integer dailyReqOminus = 0;
            Integer dailyReqAplus = 0;
            Integer dailyReqBplus = 0;
            if (item != null) {
                total++;
                supplies = checkNull(item.getHospitals()) + checkNull(item.getCompatsIssues());
                orders = checkNull(item.getCompatsOrders()) + checkNull(item.getTotalHospitalOrders());
                stockedOplus = checkNull(item.getRhPositivePcO()) + checkNull(item.getRhPositiveWbO())
                        + checkNull(item.getRhPositivePaedPcO()) + checkNull(item.getRhPositivePaedWbO())
                        + checkNull(item.getRhPositivePcOcompatibility()) + checkNull(item.getRhPositiveWbOcompatibility())
                        + checkNull(item.getRhPositivePaedPcOcompatibility()) + checkNull(item.getRhPositivePaedWbOcompatibility());
                stockedOminus = checkNull(item.getRhNegativePcO()) + checkNull(item.getRhNegativeWbO())
                        + checkNull(item.getRhNegativePaedPcO()) + checkNull(item.getRhNegativePaedWbO())
                        +checkNull(item.getRhNegativePcOcompatibility()) + checkNull(item.getRhNegativeWbOcompatibility())
                        + checkNull(item.getRhNegativePaedPcOcompatibility()) + checkNull(item.getRhNegativePaedWbOcompatibility());
                stockedAplus = checkNull(item.getRhPositivePcA()) + checkNull(item.getRhPositiveWbA())
                        + checkNull(item.getRhPositivePaedPcA()) + checkNull(item.getRhPositivePaedWbA())
                        + checkNull(item.getRhPositivePcAcompatibility()) + checkNull(item.getRhPositiveWbAcompatibility())
                        + checkNull(item.getRhPositivePaedPcAcompatibility()) + checkNull(item.getRhPositivePaedWbAcompatibility());
                stockedBplus = checkNull(item.getRhPositivePcB()) + checkNull(item.getRhPositiveWbB())
                        + checkNull(item.getRhPositivePaedPcB()) + checkNull(item.getRhPositivePaedWbB())
                        + checkNull(item.getRhPositivePcBcompatibility()) + checkNull(item.getRhPositiveWbBcompatibility())
                        + checkNull(item.getRhPositivePaedPcBcompatibility()) + checkNull(item.getRhPositivePaedWbBcompatibility());

                demandVsSupply = supplies.doubleValue()/orders.doubleValue();
                NoDaysRequiremets dailyRequirements = requirementsRepo.findFirstByOrderById();
                if (branch.getBranchName().equals("HARARE")) {
                    dailyReqOplus = dailyRequirements.getHarareOplus();
                    dailyReqOminus = dailyRequirements.getHarareOminus();
                    dailyReqAplus = dailyRequirements.getHarareAplus();
                    dailyReqBplus = dailyRequirements.getHarareBplus();
                }
                if (branch.getBranchName().equals("BULAWAYO")) {
                    dailyReqOplus = dailyRequirements.getBulawayoOplus();
                    dailyReqOminus= dailyRequirements.getBulawayoOminus();
                    dailyReqAplus = dailyRequirements.getBulawayoAplus();
                    dailyReqBplus = dailyRequirements.getBulawayoBplus();
                }
                if (branch.getBranchName().equals("GWERU")) {
                    dailyReqOplus = dailyRequirements.getGweruOplus();
                    dailyReqOminus = dailyRequirements.getGweruOminus();
                    dailyReqAplus = dailyRequirements.getGweruAplus();
                    dailyReqBplus = dailyRequirements.getGweruBplus();
                }
                if (branch.getBranchName().equals("MUTARE")) {
                    dailyReqOplus = dailyRequirements.getMutareOplus();
                    dailyReqOminus = dailyRequirements.getMutareOminus();
                    dailyReqAplus = dailyRequirements.getMutareAplus();
                    dailyReqBplus = dailyRequirements.getMutareBplus();
                }
                if (branch.getBranchName().equals("MASVINGO")) {
                    dailyReqOplus = dailyRequirements.getMasvingoOplus();
                    dailyReqOminus = dailyRequirements.getMasvingoOminus();
                    dailyReqAplus = dailyRequirements.getMasvingoAplus();
                    dailyReqBplus = dailyRequirements.getMasvingoBplus();
                }

                if ( qService.getByBranchAndDateAndActive(branch, item.getDateCreated(), Boolean.FALSE)!= null) {
                    quarantineStock = checkNull(qService.getByBranchAndDateAndActive(item.getBranch(), item.getDateCreated(), Boolean.FALSE).getCurrentStockInQuarantine());
                }


                double availableStockedUnitsTotal = ((stockedOplus + stockedOminus) * 0.6) + ((stockedAplus + stockedBplus) * 0.2);
                double availableRequiremetnsTotal = ((dailyReqOplus + dailyReqOminus) * 0.6) + ((dailyReqAplus + dailyReqBplus) * 0.2);
                double quarantineRequiremetnsTotalRaw = (dailyReqOplus + dailyReqOminus + dailyReqAplus + dailyReqBplus)*0.435;

                bsms += (((availableStockedUnitsTotal/availableRequiremetnsTotal)/5) + (((quarantineStock*0.42)/quarantineRequiremetnsTotalRaw)/5) + (demandVsSupply))/3;
                if (!bsms.isNaN()) {
                    if (bsms >=0.5) {
                        green++;
                    } else if(bsms >=0.25 && bsms <=0.5) {
                        orange++;
                    } else{
                        red++;
                    }

                }
            }


        }
        return (green/checkZero(total))*100;
    }
    private Integer calculateMonthlyValueRed(Date start, Date end, Branch branch) {
        Double bsms = 0.0;
        List<GenericReportModel> model = new ArrayList<>();
        Integer green = 0;
        Integer orange = 0;
        Integer red = 0;
        Integer total = 0;

        List <StockAvailable> availableByDateAndBranch= service.getAvailableByDateAndBranch(branch, start, end);
        for(StockAvailable item : availableByDateAndBranch){

            Integer supplies = 0;
            Integer orders = 0;
            Double demandVsSupply = 0.0;
            Integer quarantineStock = 0;
            Integer stockedOplus = 0;
            Integer stockedOminus = 0;
            Integer stockedAplus = 0;
            Integer stockedBplus = 0;
            Integer dailyReqOplus = 0;
            Integer dailyReqOminus = 0;
            Integer dailyReqAplus = 0;
            Integer dailyReqBplus = 0;
            if (item != null) {
                total++;
                supplies = checkNull(item.getHospitals()) + checkNull(item.getCompatsIssues());
                orders = checkNull(item.getCompatsOrders()) + checkNull(item.getTotalHospitalOrders());
                stockedOplus = checkNull(item.getRhPositivePcO()) + checkNull(item.getRhPositiveWbO())
                        + checkNull(item.getRhPositivePaedPcO()) + checkNull(item.getRhPositivePaedWbO())
                        + checkNull(item.getRhPositivePcOcompatibility()) + checkNull(item.getRhPositiveWbOcompatibility())
                        + checkNull(item.getRhPositivePaedPcOcompatibility()) + checkNull(item.getRhPositivePaedWbOcompatibility());
                stockedOminus = checkNull(item.getRhNegativePcO()) + checkNull(item.getRhNegativeWbO())
                        + checkNull(item.getRhNegativePaedPcO()) + checkNull(item.getRhNegativePaedWbO())
                        +checkNull(item.getRhNegativePcOcompatibility()) + checkNull(item.getRhNegativeWbOcompatibility())
                        + checkNull(item.getRhNegativePaedPcOcompatibility()) + checkNull(item.getRhNegativePaedWbOcompatibility());
                stockedAplus = checkNull(item.getRhPositivePcA()) + checkNull(item.getRhPositiveWbA())
                        + checkNull(item.getRhPositivePaedPcA()) + checkNull(item.getRhPositivePaedWbA())
                        + checkNull(item.getRhPositivePcAcompatibility()) + checkNull(item.getRhPositiveWbAcompatibility())
                        + checkNull(item.getRhPositivePaedPcAcompatibility()) + checkNull(item.getRhPositivePaedWbAcompatibility());
                stockedBplus = checkNull(item.getRhPositivePcB()) + checkNull(item.getRhPositiveWbB())
                        + checkNull(item.getRhPositivePaedPcB()) + checkNull(item.getRhPositivePaedWbB())
                        + checkNull(item.getRhPositivePcBcompatibility()) + checkNull(item.getRhPositiveWbBcompatibility())
                        + checkNull(item.getRhPositivePaedPcBcompatibility()) + checkNull(item.getRhPositivePaedWbBcompatibility());

                demandVsSupply = supplies.doubleValue()/orders.doubleValue();
                NoDaysRequiremets dailyRequirements = requirementsRepo.findFirstByOrderById();
                if (branch.getBranchName().equals("HARARE")) {
                    dailyReqOplus = dailyRequirements.getHarareOplus();
                    dailyReqOminus = dailyRequirements.getHarareOminus();
                    dailyReqAplus = dailyRequirements.getHarareAplus();
                    dailyReqBplus = dailyRequirements.getHarareBplus();
                }
                if (branch.getBranchName().equals("BULAWAYO")) {
                    dailyReqOplus = dailyRequirements.getBulawayoOplus();
                    dailyReqOminus= dailyRequirements.getBulawayoOminus();
                    dailyReqAplus = dailyRequirements.getBulawayoAplus();
                    dailyReqBplus = dailyRequirements.getBulawayoBplus();
                }
                if (branch.getBranchName().equals("GWERU")) {
                    dailyReqOplus = dailyRequirements.getGweruOplus();
                    dailyReqOminus = dailyRequirements.getGweruOminus();
                    dailyReqAplus = dailyRequirements.getGweruAplus();
                    dailyReqBplus = dailyRequirements.getGweruBplus();
                }
                if (branch.getBranchName().equals("MUTARE")) {
                    dailyReqOplus = dailyRequirements.getMutareOplus();
                    dailyReqOminus = dailyRequirements.getMutareOminus();
                    dailyReqAplus = dailyRequirements.getMutareAplus();
                    dailyReqBplus = dailyRequirements.getMutareBplus();
                }
                if (branch.getBranchName().equals("MASVINGO")) {
                    dailyReqOplus = dailyRequirements.getMasvingoOplus();
                    dailyReqOminus = dailyRequirements.getMasvingoOminus();
                    dailyReqAplus = dailyRequirements.getMasvingoAplus();
                    dailyReqBplus = dailyRequirements.getMasvingoBplus();
                }

                if ( qService.getByBranchAndDateAndActive(branch, item.getDateCreated(), Boolean.FALSE)!= null) {
                    quarantineStock = checkNull(qService.getByBranchAndDateAndActive(item.getBranch(), item.getDateCreated(), Boolean.FALSE).getCurrentStockInQuarantine());
                }


                double availableStockedUnitsTotal = ((stockedOplus + stockedOminus) * 0.6) + ((stockedAplus + stockedBplus) * 0.2);
                double availableRequiremetnsTotal = ((dailyReqOplus + dailyReqOminus) * 0.6) + ((dailyReqAplus + dailyReqBplus) * 0.2);
                double quarantineRequiremetnsTotalRaw = (dailyReqOplus + dailyReqOminus + dailyReqAplus + dailyReqBplus)*0.435;

                bsms += (((availableStockedUnitsTotal/availableRequiremetnsTotal)/5) + (((quarantineStock*0.42)/quarantineRequiremetnsTotalRaw)/5) + (demandVsSupply))/3;
                if (!bsms.isNaN()) {
                    if (bsms >=0.5) {
                        green++;
                    } else if(bsms >=0.25 && bsms <=0.5) {
                        orange++;
                    } else{
                        red++;
                    }

                }
            }


        }
        return (red/checkZero(total))*100;
    }
    private Integer calculateMonthlyValueOrange(Date start, Date end, Branch branch) {
        Double bsms = 0.0;
        List<GenericReportModel> model = new ArrayList<>();
        Integer green = 0;
        Integer orange = 0;
        Integer red = 0;
        Integer total = 0;

        List <StockAvailable> availableByDateAndBranch= service.getAvailableByDateAndBranch(branch, start, end);
        for(StockAvailable item : availableByDateAndBranch){

            Integer supplies = 0;
            Integer orders = 0;
            Double demandVsSupply = 0.0;
            Integer quarantineStock = 0;
            Integer stockedOplus = 0;
            Integer stockedOminus = 0;
            Integer stockedAplus = 0;
            Integer stockedBplus = 0;
            Integer dailyReqOplus = 0;
            Integer dailyReqOminus = 0;
            Integer dailyReqAplus = 0;
            Integer dailyReqBplus = 0;
            if (item != null) {
                total++;
                supplies = checkNull(item.getHospitals()) + checkNull(item.getCompatsIssues());
                orders = checkNull(item.getCompatsOrders()) + checkNull(item.getTotalHospitalOrders());
                stockedOplus = checkNull(item.getRhPositivePcO()) + checkNull(item.getRhPositiveWbO())
                        + checkNull(item.getRhPositivePaedPcO()) + checkNull(item.getRhPositivePaedWbO())
                        + checkNull(item.getRhPositivePcOcompatibility()) + checkNull(item.getRhPositiveWbOcompatibility())
                        + checkNull(item.getRhPositivePaedPcOcompatibility()) + checkNull(item.getRhPositivePaedWbOcompatibility());
                stockedOminus = checkNull(item.getRhNegativePcO()) + checkNull(item.getRhNegativeWbO())
                        + checkNull(item.getRhNegativePaedPcO()) + checkNull(item.getRhNegativePaedWbO())
                        +checkNull(item.getRhNegativePcOcompatibility()) + checkNull(item.getRhNegativeWbOcompatibility())
                        + checkNull(item.getRhNegativePaedPcOcompatibility()) + checkNull(item.getRhNegativePaedWbOcompatibility());
                stockedAplus = checkNull(item.getRhPositivePcA()) + checkNull(item.getRhPositiveWbA())
                        + checkNull(item.getRhPositivePaedPcA()) + checkNull(item.getRhPositivePaedWbA())
                        + checkNull(item.getRhPositivePcAcompatibility()) + checkNull(item.getRhPositiveWbAcompatibility())
                        + checkNull(item.getRhPositivePaedPcAcompatibility()) + checkNull(item.getRhPositivePaedWbAcompatibility());
                stockedBplus = checkNull(item.getRhPositivePcB()) + checkNull(item.getRhPositiveWbB())
                        + checkNull(item.getRhPositivePaedPcB()) + checkNull(item.getRhPositivePaedWbB())
                        + checkNull(item.getRhPositivePcBcompatibility()) + checkNull(item.getRhPositiveWbBcompatibility())
                        + checkNull(item.getRhPositivePaedPcBcompatibility()) + checkNull(item.getRhPositivePaedWbBcompatibility());

                demandVsSupply = supplies.doubleValue()/orders.doubleValue();
                NoDaysRequiremets dailyRequirements = requirementsRepo.findFirstByOrderById();
                if (branch.getBranchName().equals("HARARE")) {
                    dailyReqOplus = dailyRequirements.getHarareOplus();
                    dailyReqOminus = dailyRequirements.getHarareOminus();
                    dailyReqAplus = dailyRequirements.getHarareAplus();
                    dailyReqBplus = dailyRequirements.getHarareBplus();
                }
                if (branch.getBranchName().equals("BULAWAYO")) {
                    dailyReqOplus = dailyRequirements.getBulawayoOplus();
                    dailyReqOminus= dailyRequirements.getBulawayoOminus();
                    dailyReqAplus = dailyRequirements.getBulawayoAplus();
                    dailyReqBplus = dailyRequirements.getBulawayoBplus();
                }
                if (branch.getBranchName().equals("GWERU")) {
                    dailyReqOplus = dailyRequirements.getGweruOplus();
                    dailyReqOminus = dailyRequirements.getGweruOminus();
                    dailyReqAplus = dailyRequirements.getGweruAplus();
                    dailyReqBplus = dailyRequirements.getGweruBplus();
                }
                if (branch.getBranchName().equals("MUTARE")) {
                    dailyReqOplus = dailyRequirements.getMutareOplus();
                    dailyReqOminus = dailyRequirements.getMutareOminus();
                    dailyReqAplus = dailyRequirements.getMutareAplus();
                    dailyReqBplus = dailyRequirements.getMutareBplus();
                }
                if (branch.getBranchName().equals("MASVINGO")) {
                    dailyReqOplus = dailyRequirements.getMasvingoOplus();
                    dailyReqOminus = dailyRequirements.getMasvingoOminus();
                    dailyReqAplus = dailyRequirements.getMasvingoAplus();
                    dailyReqBplus = dailyRequirements.getMasvingoBplus();
                }

                if ( qService.getByBranchAndDateAndActive(branch, item.getDateCreated(), Boolean.FALSE)!= null) {
                    quarantineStock = checkNull(qService.getByBranchAndDateAndActive(item.getBranch(), item.getDateCreated(), Boolean.FALSE).getCurrentStockInQuarantine());
                }


                double availableStockedUnitsTotal = ((stockedOplus + stockedOminus) * 0.6) + ((stockedAplus + stockedBplus) * 0.2);
                double availableRequiremetnsTotal = ((dailyReqOplus + dailyReqOminus) * 0.6) + ((dailyReqAplus + dailyReqBplus) * 0.2);
                double quarantineRequiremetnsTotalRaw = (dailyReqOplus + dailyReqOminus + dailyReqAplus + dailyReqBplus)*0.435;

                bsms += (((availableStockedUnitsTotal/availableRequiremetnsTotal)/5) + (((quarantineStock*0.42)/quarantineRequiremetnsTotalRaw)/5) + (demandVsSupply))/3;
                if (!bsms.isNaN()) {
                    if (bsms >=0.5) {
                        green++;
                    } else if(bsms >=0.25 && bsms <=0.5) {
                        orange++;
                    } else{
                        red++;
                    }

                }
            }


        }
        return (orange/checkZero(total))*100;
    }

    private List<String> getRow(Branch branch, List<String> row) {
        for(int i = 5; i >= 1; i--) {
            Date date = DateUtil.getDateDiffMonth(-i);
            row.add(calcValue(date, DateUtil.getLastDayOfMonth(date), branch).toString());
        }
        row.add(calcValue(DateUtil.getDateDiffMonth(0), DateUtil.getLastDayOfMonth(new Date()), branch).toString());
        return row;
    }

    private Double calcValue(Date start, Date end, Branch branch) {

        Double bsms = 0.0;

        List <StockAvailable> availableByDateAndBranch= service.getAvailableByDateAndBranch(branch, start, end);
        for(StockAvailable item : availableByDateAndBranch){
            Integer supplies = 0;
            Integer orders = 0;
            Double demandVsSupply = 0.0;
            Integer quarantineStock = 0;
            Integer stockedOplus = 0;
            Integer stockedOminus = 0;
            Integer stockedAplus = 0;
            Integer stockedBplus = 0;
            Integer dailyReqOplus = 0;
            Integer dailyReqOminus = 0;
            Integer dailyReqAplus = 0;
            Integer dailyReqBplus = 0;
            if (item != null) {
                supplies = checkNull(item.getHospitals()) + checkNull(item.getCompatsIssues());
                orders = checkNull(item.getCompatsOrders()) + checkNull(item.getTotalHospitalOrders());
                stockedOplus = checkNull(item.getRhPositivePcO()) + checkNull(item.getRhPositiveWbO())
                        + checkNull(item.getRhPositivePaedPcO()) + checkNull(item.getRhPositivePaedWbO())
                        + checkNull(item.getRhPositivePcOcompatibility()) + checkNull(item.getRhPositiveWbOcompatibility())
                        + checkNull(item.getRhPositivePaedPcOcompatibility()) + checkNull(item.getRhPositivePaedWbOcompatibility());
                stockedOminus = checkNull(item.getRhNegativePcO()) + checkNull(item.getRhNegativeWbO())
                        + checkNull(item.getRhNegativePaedPcO()) + checkNull(item.getRhNegativePaedWbO())
                        +checkNull(item.getRhNegativePcOcompatibility()) + checkNull(item.getRhNegativeWbOcompatibility())
                        + checkNull(item.getRhNegativePaedPcOcompatibility()) + checkNull(item.getRhNegativePaedWbOcompatibility());
                stockedAplus = checkNull(item.getRhPositivePcA()) + checkNull(item.getRhPositiveWbA())
                        + checkNull(item.getRhPositivePaedPcA()) + checkNull(item.getRhPositivePaedWbA())
                        + checkNull(item.getRhPositivePcAcompatibility()) + checkNull(item.getRhPositiveWbAcompatibility())
                        + checkNull(item.getRhPositivePaedPcAcompatibility()) + checkNull(item.getRhPositivePaedWbAcompatibility());
                stockedBplus = checkNull(item.getRhPositivePcB()) + checkNull(item.getRhPositiveWbB())
                        + checkNull(item.getRhPositivePaedPcB()) + checkNull(item.getRhPositivePaedWbB())
                        + checkNull(item.getRhPositivePcBcompatibility()) + checkNull(item.getRhPositiveWbBcompatibility())
                        + checkNull(item.getRhPositivePaedPcBcompatibility()) + checkNull(item.getRhPositivePaedWbBcompatibility());

                demandVsSupply = supplies.doubleValue()/orders.doubleValue();
                NoDaysRequiremets dailyRequirements = requirementsRepo.findFirstByOrderById();
                if (branch.getBranchName().equals("HARARE")) {
                    dailyReqOplus = dailyRequirements.getHarareOplus();
                    dailyReqOminus = dailyRequirements.getHarareOminus();
                    dailyReqAplus = dailyRequirements.getHarareAplus();
                    dailyReqBplus = dailyRequirements.getHarareBplus();
                }
                if (branch.getBranchName().equals("BULAWAYO")) {
                    dailyReqOplus = dailyRequirements.getBulawayoOplus();
                    dailyReqOminus= dailyRequirements.getBulawayoOminus();
                    dailyReqAplus = dailyRequirements.getBulawayoAplus();
                    dailyReqBplus = dailyRequirements.getBulawayoBplus();
                }
                if (branch.getBranchName().equals("GWERU")) {
                    dailyReqOplus = dailyRequirements.getGweruOplus();
                    dailyReqOminus = dailyRequirements.getGweruOminus();
                    dailyReqAplus = dailyRequirements.getGweruAplus();
                    dailyReqBplus = dailyRequirements.getGweruBplus();
                }
                if (branch.getBranchName().equals("MUTARE")) {
                    dailyReqOplus = dailyRequirements.getMutareOplus();
                    dailyReqOminus = dailyRequirements.getMutareOminus();
                    dailyReqAplus = dailyRequirements.getMutareAplus();
                    dailyReqBplus = dailyRequirements.getMutareBplus();
                }
                if (branch.getBranchName().equals("MASVINGO")) {
                    dailyReqOplus = dailyRequirements.getMasvingoOplus();
                    dailyReqOminus = dailyRequirements.getMasvingoOminus();
                    dailyReqAplus = dailyRequirements.getMasvingoAplus();
                    dailyReqBplus = dailyRequirements.getMasvingoBplus();
                }

                if ( qService.getByBranchAndDateAndActive(branch, item.getDateCreated(), Boolean.FALSE)!= null) {
                quarantineStock = checkNull(qService.getByBranchAndDateAndActive(item.getBranch(), item.getDateCreated(), Boolean.FALSE).getCurrentStockInQuarantine());
                }


                double availableStockedUnitsTotal = ((stockedOplus + stockedOminus) * 0.6) + ((stockedAplus + stockedBplus) * 0.2);
                double availableRequiremetnsTotal = ((dailyReqOplus + dailyReqOminus) * 0.6) + ((dailyReqAplus + dailyReqBplus) * 0.2);
                double quarantineRequiremetnsTotalRaw = (dailyReqOplus + dailyReqOminus + dailyReqAplus + dailyReqBplus)*0.435;

                bsms += (((availableStockedUnitsTotal/availableRequiremetnsTotal)/5) + (((quarantineStock*0.42)/quarantineRequiremetnsTotalRaw)/5) + (demandVsSupply))/3;
                  }

        }

        return bsms.isNaN() ? 0.0 : bsms/checkZero(availableByDateAndBranch.size());
    }

    public static Integer checkNull ( Integer input){
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
}
