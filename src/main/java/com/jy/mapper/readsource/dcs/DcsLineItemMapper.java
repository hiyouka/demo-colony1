package com.jy.mapper.readsource.dcs;

import com.jy.model.dcs.DcsLineItem;

public interface DcsLineItemMapper {
    int deleteByPrimaryKey(Long recId);

    int insert(DcsLineItem record);

    int insertSelective(DcsLineItem record);

    DcsLineItem selectByPrimaryKey(Long recId);

    int updateByPrimaryKeySelective(DcsLineItem record);

    int updateByPrimaryKey(DcsLineItem record);
}