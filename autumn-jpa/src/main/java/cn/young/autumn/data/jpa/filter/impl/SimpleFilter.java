package cn.young.autumn.data.jpa.filter.impl;

import java.util.ArrayList;
import java.util.List;

import cn.young.autumn.data.jpa.filter.IFilter;
import cn.young.autumn.data.jpa.filter.exception.InvalidFilterException;
import cn.young.autumn.data.jpa.filter.operator.ConditionOperator;

public class SimpleFilter extends BaseFilter {

	private IFilter left;

	private ConditionOperator operator;

	private IFilter right;

	public SimpleFilter(IFilter left, IFilter right, ConditionOperator operator) {
		this.left = left;
		this.right = right;
		this.operator = operator;
	}

	public SimpleFilter(String variable, Object value) {
		this.left = new VariableFilter(variable);
		this.right = new ConstantFilter(value);
		this.operator = ConditionOperator.EQUAL;
	}

	@Override
	public String getString() {
		if (left instanceof IFilter && right instanceof IFilter) {
			return operator.getValue().replace("%0", ((IFilter) left).getString()).replace("%1",
					((IFilter) right).getString());
		} else {
			throw new InvalidFilterException("unsupport filter.the filter must be implements of IJpaFilter");
		}
	}

	@Override
	public List<Object> getValues() {
		if (left instanceof IFilter && right instanceof IFilter) {
			List<Object> ret = new ArrayList<>();
			ret.addAll(((IFilter) left).getValues());
			ret.addAll(((IFilter) right).getValues());
			return ret;
		} else {
			throw new InvalidFilterException("unsupport filter.the filter must be implements of IJpaFilter");
		}
	}

	@Override
	public void setTableAlias(String alias) {
	}
}
