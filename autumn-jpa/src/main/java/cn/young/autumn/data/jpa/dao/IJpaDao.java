package cn.young.autumn.data.jpa.dao;

import java.io.Serializable;
import java.util.List;

import cn.young.autumn.data.jpa.filter.IFilter;
import cn.young.autumn.data.jpa.pagination.Pagination;
import cn.young.autumn.data.jpa.query.QueryCondition;
import cn.young.autumn.data.jpa.sort.ISort;

public interface IJpaDao {
	/**
	 * persist the entity.
	 *
	 * @param entity
	 *            the entity
	 */
	public <T> void save(T entity);

	/**
	 * persist the entities.
	 *
	 * @param entities
	 *            the entity List
	 */
	public <T> void saveList(List<T> entities);

	/**
	 * update the entity.
	 *
	 * @param entity
	 *            the entity
	 */
	public <T> void update(T entity);

	/**
	 * get entity by id.
	 *
	 * @param clazz
	 *            the entity class.
	 * @param id
	 *            the id.
	 * @return the entity.
	 */
	public <T> T getById(Class<T> clazz, Serializable id);

	/**
	 * get entity by query conditions.
	 *
	 * @param clazz
	 *            the entity class.
	 * @param condition
	 *            the conditions.
	 * @return the list of entity.
	 */
	public <T> List<T> get(Class<T> clazz, QueryCondition<T> condition);

	/**
	 * get entity by filter.
	 *
	 * @param clazz
	 *            the entity class.
	 * @param filter
	 *            the filter.
	 * @return the list of entity.
	 */
	public <T> List<T> get(Class<T> clazz, IFilter filter);

	/**
	 * get entity by filter.
	 *
	 * @param clazz
	 *            the entity class.
	 * @param filter
	 *            the filter.
	 * @return the list of entity.
	 */
	public <T> T getUnique(Class<T> clazz, IFilter filter);

	/**
	 * get entity by filter and then sort the entity.
	 *
	 * @param clazz
	 *            the entity class.
	 * @param filter
	 *            the filter.
	 * @param sort
	 *            the sort info.
	 * @return the list of entity after sorted.
	 */
	public <T> List<T> get(Class<T> clazz, IFilter filter, ISort sort);

	/**
	 * get all entity.
	 *
	 * @param clazz
	 *            the entity class.
	 * @return all of the entity.
	 */
	public <T> List<T> getAll(Class<T> clazz);

	/**
	 * the pagination query.
	 *
	 * @param clazz
	 *            the entity class.
	 * @param condition
	 *            the condition
	 * @return the pagination object.
	 */
	public <T> Pagination<T> getByPageination(Class<T> clazz, QueryCondition<T> condition);

	/**
	 * the pagination query.get total record size.
	 *
	 * @param clazz
	 *            the entity class.
	 * @param condition
	 *            the condition
	 * @return the pagination object.
	 */
	public <T> Long getRecordSize(Class<T> clazz, QueryCondition<T> condition);

	/**
	 * delete entity.
	 *
	 * @param entity
	 *            the entity.
	 */
	public <T> void delete(T entity);

	/**
	 * get list by jpql
	 *
	 * @param jpql
	 *            the jpql
	 * @param objects
	 *            the parameters
	 * @return the list of object T
	 */
	public <T> List<T> getByJpql(String jpql, Object... objects);

	/**
	 * get list by sql
	 *
	 * @param sql
	 *            the sql
	 * @param objects
	 *            the parameters
	 * @return the list of object T
	 */
	public <T> List<T> getBySQL(String sql, Object... objects);

	/**
	 * execute up date by jpql.
	 *
	 * @param jpql
	 *            the jpql
	 * @param params
	 *            the parameters
	 * @return the code returned.
	 */
	public int executeByJpql(String jpql, Object... params);

	/**
	 * execute up date by sql.
	 *
	 * @param sql
	 *            the sql
	 * @param params
	 *            the parameters
	 * @return the code returned.
	 */
	public int executeBySQL(String sql, Object... params);
}
