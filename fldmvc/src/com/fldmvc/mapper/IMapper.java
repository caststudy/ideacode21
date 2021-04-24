
package com.fldmvc.mapper;

import java.util.List;

public interface IMapper {
    public Object getObject(Object obj);
    public List<Object> getAll(Object obj);
    public  int insert(Object obj);
    public  int delete(int id);
}
