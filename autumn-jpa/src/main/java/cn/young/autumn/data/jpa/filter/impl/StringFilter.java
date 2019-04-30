package cn.young.autumn.data.jpa.filter.impl;

import java.util.ArrayList;
import java.util.List;

public class StringFilter extends BaseFilter {

    private String condition;

    private List<Object> params = new ArrayList<Object>();

    public StringFilter(String condition) {
        this.condition = condition;
    }

    public StringFilter(String condition, List<Object> params) {
        this.condition = condition;
        this.params = params;
    }

    public StringFilter(String condition, Object... params) {
        this.condition = condition;
        this.params = new ArrayList<Object>();
        for (Object param : params) {
            this.params.add(param);
        }
    }

    @Override
    public String getString() {
        return condition;
    }

    @Override
    public List<Object> getValues() {
        return params;
    }

}
