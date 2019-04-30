package cn.young.autumn.data.jpa.filter;


import java.util.List;

import cn.young.autumn.data.jpa.filter.operator.ConditionOperator;


public interface IFilter {
    /**
     * append and another filter.
     *
     * @param filter the and-appending filter.
     * @return filter
     */
    public IFilter appendAnd(IFilter filter);

    /**
     * append or another filter.
     *
     * @param filter the or-appending filter.
     * @return filter
     */
    public IFilter appendOr(IFilter filter);

    /**
     * append another filter
     *
     * @param filter   the appending filter
     * @param operator the append operator,and/or
     * @return filter
     */
    public IFilter append(IFilter filter, ConditionOperator operator);

    /**
     * set table alias
     *
     * @param alias
     */
    public void setTableAlias(String alias);

    /**
     * get filter string
     *
     * @return the string .
     */
    public String getString();

    /**
     * get filter values
     *
     * @return the values.
     */
    public List<Object> getValues();

}
