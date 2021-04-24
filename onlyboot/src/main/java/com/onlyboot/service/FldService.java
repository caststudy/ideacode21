package com.onlyboot.service;

import java.util.List;
import java.util.Map;


public interface FldService {

	List getAll(Map map);

	boolean  isExist(int flda);
	boolean  insertFld(Map map);
	boolean  deleteFld(int  flda);
}
