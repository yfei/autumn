package cn.young.autumn.data.jpa.filter.impl;

import java.util.Arrays;
import java.util.List;

import cn.young.autumn.data.jpa.filter.IFilter;
import cn.young.autumn.data.jpa.filter.exception.InvalidFilterException;
import cn.young.autumn.data.jpa.filter.operator.ConditionOperator;

class ConstantFilter extends BaseFilter {
	/**
	 * 变量值
	 */
	private Object value;

	public ConstantFilter(Object value) {
		this.value = value;
	}

	@Override
	public IFilter append(IFilter filter, ConditionOperator operator) throws InvalidFilterException {
		throw new InvalidFilterException("filter constant cannot append filter");
	}

	/**
	 * use"?" instead of real value, and then set real value by
	 * Query.setParameter().
	 */
	@Override
	public String getString() {
		return "?";
	}

	/**
	 * get value list.
	 */
	@Override
	public List<Object> getValues() {
		return Arrays.asList(value);
	}
}
