package cn.young.autumn.data.jpa.filter.impl;

import java.util.ArrayList;
import java.util.List;

import cn.young.autumn.data.jpa.filter.operator.ConditionOperator;

public class BetweenFilter extends BaseFilter {

	private VariableFilter variable;

	private List<Object> values = new ArrayList<Object>(2);

	private BetweenFilter(VariableFilter variable, List<Object> values) {
		if (values.size() != 2) {
			throw new RuntimeException("the operator of between need two params");
		}
		this.variable = variable;
		this.values = values;
	}

	public BetweenFilter(String variable, List<Object> values) {
		this(new VariableFilter(variable), values);
	}

	@Override
	public String getString() {
		return ConditionOperator.BETWEEN.getValue().replace("%0", variable.getString()).replace("%1", "?").replace("%2",
				"?");
	}

	@Override
	public List<Object> getValues() {
		return values;
	}

	@Override
	public void setTableAlias(String alias) {
		variable.setTableAlias(alias);
	}

}
