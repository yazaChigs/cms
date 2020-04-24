/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yaza.cms.domain.config.Admin;

import com.yaza.cms.domain.config.BaseName;

import javax.persistence.Entity;
import java.util.List;

/**
 *
 * @author machigere
 */
@Entity
public class QueryType extends BaseName {

    private CardQueries cardQueries;
    private MobileBanking mobileBanking;


    public CardQueries getCardQueries() {
        return cardQueries;
    }

    public void setCardQueries(CardQueries cardQueries) {
        this.cardQueries = cardQueries;
    }

    public MobileBanking getMobileBanking() {
        return mobileBanking;
    }

    public void setMobileBanking(MobileBanking mobileBanking) {
        this.mobileBanking = mobileBanking;
    }
}
