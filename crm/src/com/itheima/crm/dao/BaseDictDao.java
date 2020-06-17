package com.itheima.crm.dao;

import com.itheima.crm.domain.BaseDict;

import java.util.List;

/**
 * @author 若风
 * @version 1.0
 */
public interface BaseDictDao {
    List<BaseDict> findByTypeCode(String dict_type_code);
}
