package cn.young.autumn.data.jpa.filter.impl;


import java.util.Collections;
import java.util.List;

import cn.young.autumn.data.jpa.filter.IFilter;
import cn.young.autumn.data.jpa.filter.exception.InvalidFilterException;
import cn.young.autumn.data.jpa.filter.operator.ConditionOperator;
import cn.young.autumn.data.jpa.query.QueryCondition;


class VariableFilter extends BaseFilter {
    /**
     * the alias.
     */
    private String alias = QueryCondition.DEFAULT_ALIAS;

    /**
     * the variable.
     */
    private String variable;

    public VariableFilter(String variable) {
        this.variable = variable;
    }

    @Override
    public IFilter append(IFilter filter, ConditionOperator operator) throws InvalidFilterException {
        throw new InvalidFilterException("filter variable cannot append filter");
    }

    @Override
    public String getString() {
        return alias + "." + variable;
    }

    @Override
    public List<Object> getValues() {
        return Collections.emptyList();
    }

    @Override
    public void setTableAlias(String alias) {
        this.alias = alias;
    }
}
