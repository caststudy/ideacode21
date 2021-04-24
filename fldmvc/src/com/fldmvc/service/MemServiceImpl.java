package com.fldmvc.service;



import com.fldmvc.mapper.MemberMapper;
import com.fldmvc.model.Member;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemServiceImpl implements MemService{
    Logger log= Logger.getLogger(MemServiceImpl.class);
//    Dao dao = new Dao();
    @Autowired
    MemberMapper  memberMapper;

    @Override
    public boolean login(Member member) {
        log.debug(member);
        return memberMapper.getObject(member)!=null;
    }
}
