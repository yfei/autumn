package cn.young.autumn.data.jpa.service;

import java.io.Serializable;
import java.util.List;

import cn.young.autumn.data.jpa.filter.IFilter;
import cn.young.autumn.data.jpa.pagination.Pagination;
import cn.young.autumn.data.jpa.query.QueryCondition;
import cn.young.autumn.data.jpa.sort.ISort;

public interface IJpaService extends IService {

	public <T> void save(T entity);

	public <T> void saveList(List<T> entities);

	public <T> void update(T entity);

	public <T> T getById(Class<T> clazz, Serializable id);

	public <T> List<T> getAll(Class<T> clazz);

	public <T> T getUnique(Class<T> clazz, IFilter filter);

	public <T> void delete(T entity);

	public <T> List<T> get(Class<T> clazz, QueryCondition<T> condition);

	public <T> List<T> get(Class<T> clazz, IFilter filter);

	public <T> List<T> get(Class<T> clazz, IFilter filter, ISort sort);

	public <T> Pagination<T> getByPageination(Class<T> clazz, QueryCondition<T> condition);
}
