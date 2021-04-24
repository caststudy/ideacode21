package com.stuinfo.service;


import java.util.List;
import java.util.Map;

public interface AdminService {
    Map login(String name, String pass);

    List select(String name);

    boolean insert(String name, String pass, String privi);

    boolean check(String name);

}
