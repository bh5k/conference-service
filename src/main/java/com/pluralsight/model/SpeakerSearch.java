package com.pluralsight.model;

import java.util.List;

public class SpeakerSearch {

    private List<String> companies;
    private int ageFrom;

    public SpeakerSearchType getSearchType() {
        return searchType;
    }

    public void setSearchType(SpeakerSearchType searchType) {
        this.searchType = searchType;
    }

    private SpeakerSearchType searchType;



    public List<String> getCompanies() {
        return companies;
    }

    public void setCompanies(List<String> companies) {
        this.companies = companies;
    }

    public int getAgeFrom() {
        return ageFrom;
    }

    public void setAgeFrom(int ageFrom) {
        this.ageFrom = ageFrom;
    }

    public int getAgeTo() {
        return ageTo;
    }

    public void setAgeTo(int ageTo) {
        this.ageTo = ageTo;
    }

    private int ageTo;

}
