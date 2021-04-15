package org.kosiuk.webApp.dto;

import org.springframework.stereotype.Component;

@Component
public class CarSearchAndSortCritereaDto {

    private String searchQualClass;
    private String searchBrandName;
    private String sortingParameter;

    public CarSearchAndSortCritereaDto(String searchQualClass, String searchBrandName, String sortingParameter) {
        this.searchQualClass = searchQualClass;
        this.searchBrandName = searchBrandName;
        this.sortingParameter = sortingParameter;
    }

    public CarSearchAndSortCritereaDto() {

    }

    public String getSearchQualClass() {
        return searchQualClass;
    }

    public void setSearchQualClass(String searchQualClass) {
        this.searchQualClass = searchQualClass;
    }

    public String getSearchBrandName() {
        return searchBrandName;
    }

    public void setSearchBrandName(String searchBrandName) {
        this.searchBrandName = searchBrandName;
    }

    public String getSortingParameter() {
        return sortingParameter;
    }

    public void setSortingParameter(String sortingParameter) {
        this.sortingParameter = sortingParameter;
    }

}
