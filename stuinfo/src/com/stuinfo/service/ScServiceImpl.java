package com.stuinfo.service;


import com.stuinfo.dao.Dao;

import java.util.List;

public class ScServiceImpl implements ScService {

    Dao dao = new Dao();


    @Override
    public List select(String sname) {
        return dao.getAll("select a.sid,sname,cname,score from student a,sc,course b where a.sid=sc.sid and sc.cid=b.cid and  sname like '%" + sname + "%'");
    }

}
