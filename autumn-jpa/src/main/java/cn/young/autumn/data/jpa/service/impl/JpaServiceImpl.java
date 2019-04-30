package cn.young.autumn.data.jpa.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.young.autumn.data.exception.DataAccessException;
import cn.young.autumn.data.jpa.dao.IJpaDao;
import cn.young.autumn.data.jpa.filter.IFilter;
import cn.young.autumn.data.jpa.pagination.Pagination;
import cn.young.autumn.data.jpa.query.QueryCondition;
import cn.young.autumn.data.jpa.service.IJpaService;
import cn.young.autumn.data.jpa.sort.ISort;

@Service(value = "jpaService")
public class JpaServiceImpl implements IJpaService, ApplicationContextAware {
	/**
	 * the logger
	 */
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource(name = "jpaDao")
	protected IJpaDao dao;

	protected ApplicationContext applicationContext;

	public IJpaDao getDao() {
		return dao;
	}

	@Override
	public void setDao(IJpaDao dao) {
		if (dao instanceof IJpaDao) {
			this.dao = (IJpaDao) dao;
		} else {
			throw new DataAccessException("unsupport dao type");
		}
	}

	@Transactional
	@Override
	public <T> void save(T entity) {
		dao.save(entity);
	}

	@Transactional
	@Override
	public <T> void saveList(List<T> entities) {
		dao.saveList(entities);
	}

	@Transactional
	@Override
	public <T> void update(T entity) {
		dao.update(entity);
	}

	@Override
	public <T> T getById(Class<T> clazz, Serializable id) {
		return dao.getById(clazz, id);
	}

	@Override
	public <T> List<T> getAll(Class<T> clazz) {
		return dao.getAll(clazz);
	}

	@Transactional
	@Override
	public <T> void delete(T entity) {
		dao.delete(entity);
	}

	@Override
	public <T> List<T> get(Class<T> clazz, QueryCondition<T> condition) {
		return dao.get(clazz, condition);
	}

	@Override
	public <T> List<T> get(Class<T> clazz, IFilter filter) {
		return dao.get(clazz, filter);
	}

	@Override
	public <T> List<T> get(Class<T> clazz, IFilter filter, ISort sort) {
		return dao.get(clazz, filter, sort);
	}

	@Override
	public <T> Pagination<T> getByPageination(Class<T> clazz, QueryCondition<T> condition) {
		return dao.getByPageination(clazz, condition);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Override
	public <T> T getUnique(Class<T> clazz, IFilter filter) {
		return dao.getUnique(clazz, filter);
	}
}
