package cn.young.autumn.data.jpa.query;

import cn.young.autumn.data.jpa.filter.IFilter;
import cn.young.autumn.data.jpa.pagination.Pagination;
import cn.young.autumn.data.jpa.sort.ISort;

public class QueryCondition<T> {
    /**
     * default alias
     */
    public static final String DEFAULT_ALIAS = "o";

    /**
     * the filter
     */
    private IFilter filter;
    /**
     * the sort
     */
    private ISort sort;

    /**
     * the pagination
     */
    private Pagination<T> pagination;

    public IFilter getFilter() {
        return filter;
    }

    public void setFilter(IFilter filter) {
        this.filter = filter;
    }

    public ISort getSort() {
        return sort;
    }

    public void setSort(ISort sort) {
        this.sort = sort;
    }

    public Pagination<T> getPagination() {
        return pagination;
    }

    public void setPagination(Pagination<T> pagination) {
        this.pagination = pagination;
    }
}
