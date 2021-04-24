package com.stuinfo.service;


import com.stuinfo.dao.Dao;
import com.stuinfo.util.MD5;

import java.util.List;
import java.util.Map;

public class AdminServiceImpl implements AdminService {

    Dao dao = new Dao();


    @Override
    public Map login(String name, String pass) {
        List lst = dao.getAll("select * from `admin` where name=? and pass=?", new Object[]{name, MD5.MD5Encode(pass)});
        if (lst.size() > 0)
            return (Map) lst.get(0);
        return null;

    }

    @Override
    public List select(String name) {
        return dao.getAll("select * from `admin` where name like '%" + name + "%'");
    }

    @Override
    public boolean insert(String name, String pass, String privi) {
        String sql = "insert into admin (name,pass,privi)  values(?,?,?)";
        return dao.update(sql, new Object[]{name, MD5.MD5Encode(pass), privi});

    }

    @Override
    public boolean check(String name) {
        return dao.isExist("select * from admin where name='" + name + "'");
    }
}
