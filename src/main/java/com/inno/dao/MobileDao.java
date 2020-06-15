package com.inno.dao;


import com.inno.pojo.Mobile;

import java.util.List;

public interface MobileDao {

    Mobile getMobileById(Integer id);

    void createTable();

    List<Mobile> getAllMobile();
}
