package com.testld.service;

import com.testld.model.Fld;

import java.util.List;

public interface FldService {
    boolean insertFld(Fld fld);

    List<Fld> getFld(String fldb);

    boolean checkFlda(int flda);

    boolean deleteFld(int flda);
}
