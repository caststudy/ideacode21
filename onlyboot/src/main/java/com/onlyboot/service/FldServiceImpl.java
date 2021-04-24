package com.onlyboot.service;

import com.onlyboot.mapper.FldMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FldServiceImpl  implements FldService {

	@Autowired
	FldMapper fldMapper;
	@Override
	public List getAll(Map map) {
		return fldMapper.getAll(map);
	}
	
	@Override
	public boolean isExist(int flda) {
		Map map=new HashMap<String,Integer>();
		map.put("flda", flda);
		Map m=fldMapper.getObject(map);		
		return m!=null;
	}

	@Override
	public boolean insertFld(Map map) {
		return fldMapper.insert(map)>0;
	}

	@Override
	public boolean deleteFld(int flda) {
		return fldMapper.delete(flda)>0;
	}

	

}
