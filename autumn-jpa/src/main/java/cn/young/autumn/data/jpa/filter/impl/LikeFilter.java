package cn.young.autumn.data.jpa.filter.impl;

import java.util.Arrays;
import java.util.List;

import cn.young.autumn.data.jpa.filter.operator.ConditionOperator;

public class LikeFilter extends BaseFilter {

	private VariableFilter variable;

	private ConstantFilter constant;

	private LikeFilter(VariableFilter variable, ConstantFilter constant) {
		this.variable = variable;
		this.constant = constant;
	}

	public LikeFilter(String variable, Object constant) {
		this(new VariableFilter(variable), new ConstantFilter(constant));
	}

	@Override
	public String getString() {
		return ConditionOperator.LIKE.getValue().replace("%0", variable.getString()).replace("%1", "?");
	}

	@Override
	public List<Object> getValues() {
		return Arrays.asList((Object) ("%" + constant.getValues().get(0) + "%"));
	}

	@Override
	public void setTableAlias(String alias) {
		variable.setTableAlias(alias);
	}
}
