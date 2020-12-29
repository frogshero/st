package com.tools.st.mapper.base;


public interface GenericMapper<T, PK> {

	int deleteByPrimaryKey(PK id);

	int insert(T record);

	T selectByPrimaryKey(PK id);

	int updateByPrimaryKey(T record);
}
