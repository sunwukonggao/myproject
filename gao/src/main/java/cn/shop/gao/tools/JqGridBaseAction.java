package cn.shop.gao.tools;

import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository("jqGridBaseAction")
public class JqGridBaseAction<T> {
    // 和jqGrid组件相关的参数属性
    private List<T> allrow = Collections.emptyList();
    private Integer rows = 0;
    private Integer page = 0;
    private Integer total = 0;
    private Integer record = 0;
    private String sord;
    private String sidx;
    private boolean search;
    private String searchField;
    private String searchString;
    private String searchOper;
    private String filters;

    public List<T> getAllrow() {
        return allrow;
    }

    public void setAllrow(List<T> allrow) {
        this.allrow = allrow;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getRecord() {
        return record;
    }

    public void setRecord(Integer record) {
        this.record = record;
    }

    public String getSord() {
        return sord;
    }

    public void setSord(String sord) {
        this.sord = sord;
    }

    public String getSidx() {
        return sidx;
    }

    public void setSidx(String sidx) {
        this.sidx = sidx;
    }

    public boolean isSearch() {
        return search;
    }

    public void setSearch(boolean search) {
        this.search = search;
    }

    public String getSearchField() {
        return searchField;
    }

    public void setSearchField(String searchField) {
        this.searchField = searchField;
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public String getFilters() {
        return filters;
    }

    public void setFilters(String filters) {
        this.filters = filters;
    }

    public String getSearchOper() {
        return searchOper;
    }

    public void setSearchOper(String searchOper) {
        this.searchOper = searchOper;
    }
}
