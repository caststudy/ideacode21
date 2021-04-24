package com.fldmvc.service;



import com.fldmvc.mapper.FldMapper;
import com.fldmvc.model.Fld;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Hashtable;
import java.util.List;

@Service
public class FldServiceImpl implements FldService{

//    Dao dao = new Dao();
    @Autowired
    FldMapper fldMapper;
    @Override
    public List getFld(String fldb){
        //  return dao.getFld("select * from fld where fldb like '%" + fldb + "%'");
        return  fldMapper.getAll(fldb);
    }

    @Override
    public boolean insertFld(Fld fld) {
//        String sql = "insert into fld(flda,fldb,fldc,fldd) values(?,?,?,?)";
//        return dao.update(sql, new Object[]{fld.getFlda(), fld.getFldb(),
//                fld.getFldc(), fld.getFldd()});
         return  fldMapper.insert(fld)>0;
    }



    @Override
    public boolean checkFlda(int flda) {

        Hashtable a;
//        return dao.isExist("select * from fld where flda='" + flda + "'");
        return  fldMapper.getObject(new Fld(flda))!=null;
    }

    @Override
    public boolean deleteFld(int flda) {

//        return dao.update("delete from fld where flda =?",new Object[]{flda});
        return  fldMapper.delete(flda)>0;
    }
}
