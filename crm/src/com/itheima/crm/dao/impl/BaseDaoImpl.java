package com.itheima.crm.dao.impl;

import com.itheima.crm.dao.BaseDao;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author 若风
 * @version 1.0
 */
public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {

    private Class clazz;

    //无参构造,在无参构造中获得具体类型的class,具体类型的class是参数类型中的实际类型参数
    public BaseDaoImpl(){
        //反射：第一步获得class
        Class clazz = this.getClass();//正在被调用哪个类的class

        Type type = clazz.getGenericSuperclass();
        //将type强转为参数化类型、
        ParameterizedType pType = (ParameterizedType) type;
        //通过参数化类型获得实际类型:得到一个实际类型参数的数据
        Type[] types = pType.getActualTypeArguments();
        //获得实际类型的集合
        this.clazz = (Class) types[0];
    }



    public BaseDaoImpl(Class clazz){
        this.clazz = clazz;
    }

    @Override
    public void save(T t) {
        this.getHibernateTemplate().save(t);
    }

    @Override
    public void update(T t) {
        this.getHibernateTemplate().update(t);
    }

    @Override
    public void delete(T t) {
        this.getHibernateTemplate().delete(t);
    }

    @Override
    public T findById(Serializable id) {
        return (T) this.getHibernateTemplate().get(clazz,id);
    }

    @Override
    public Integer findCount(DetachedCriteria detachedCriteria) {
        detachedCriteria.setProjection(Projections.rowCount());
        List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
        if(list.size()>0){
            return list.get(0).intValue();
        }
        return null;
    }

    @Override
    public List<T> findAll() {
        return (List<T>) this.getHibernateTemplate().find("from"+clazz.getSimpleName());
    }

    @Override
    public List<T> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize) {
        detachedCriteria.setProjection(null);
        return (List<T>) this.getHibernateTemplate().findByCriteria(detachedCriteria,begin,pageSize);
    }
}
