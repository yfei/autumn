package cn.young.autumn.data.jpa.filter.impl;

import java.util.Collections;
import java.util.List;

import cn.young.autumn.data.jpa.filter.IFilter;
import cn.young.autumn.data.jpa.filter.exception.InvalidFilterException;
import cn.young.autumn.data.jpa.filter.operator.ConditionOperator;


public abstract class BaseFilter implements IFilter {

    @Override
    public IFilter appendAnd(IFilter filter) throws InvalidFilterException {
        return append(filter, ConditionOperator.AND);
    }

    @Override
    public IFilter appendOr(IFilter filter) throws InvalidFilterException {
        return append(filter, ConditionOperator.OR);
    }

    @Override
    public IFilter append(IFilter filter, ConditionOperator operator) throws InvalidFilterException {
        return new SimpleFilter(this, filter, operator);
    }

    @Override
    public List<Object> getValues() {
        return Collections.emptyList();
    }

    public void setTableAlias(String alias) {
    }
}
