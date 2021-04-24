package com.onlyboot.mapper;

import java.util.List;
import java.util.Map;

public interface Mapper {
	List getAll(Map map);

	Map getObject(Map map);

	int delete(int id);

	int insert(Map map);

}
