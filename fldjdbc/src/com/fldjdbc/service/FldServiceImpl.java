package com.fldjdbc.service;


import com.fldjdbc.model.Fld;
import com.fldjdbc.dao.Dao;

import java.util.List;

public class FldServiceImpl implements FldService{

    Dao dao = new Dao();

    @Override
    public boolean insertFld(Fld fld) {


        String sql = "insert into fld(flda,fldb,fldc,fldd) values(?,?,?,?)";
        return dao.update(sql, new Object[]{fld.getFlda(), fld.getFldb(),
                fld.getFldc(), fld.getFldd()});
    }

    @Override
    public List<Fld> getFld(String fldb) {
        return dao.getFld("select * from fld where fldb like '%" + fldb + "%'");
    }

    @Override
    public boolean checkFlda(int flda) {
        return dao.isExist("select * from fld where flda='" + flda + "'");
    }

    @Override
    public boolean deleteFld(int flda) {
        return dao.update("delete from fld where flda =?",new Object[]{flda});
    }
}
