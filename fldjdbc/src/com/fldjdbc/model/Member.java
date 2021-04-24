package com.fldjdbc.model;


public class Member {
    long memid;
    String mempass;
    String memalias;

    public long getMemid() {
        return memid;
    }

    public void setMemid(long memid) {
        this.memid = memid;
    }

    public String getMempass() {
        return mempass;
    }

    public void setMempass(String mempass) {
        this.mempass = mempass;
    }

    public String getMemalias() {
        return memalias;
    }

    public void setMemalias(String memalias) {
        this.memalias = memalias;
    }

    public Member(long memid, String mempass) {
        this.memid = memid;
        this.mempass = mempass;
    }
}
