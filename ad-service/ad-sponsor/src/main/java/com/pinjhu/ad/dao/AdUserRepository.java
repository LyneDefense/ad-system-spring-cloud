package com.pinjhu.ad.dao;

import com.pinjhu.ad.entity.AdUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdUserRepository extends JpaRepository<AdUser,Long> {

    /**
     根据用户名查找用户记录
     * */
    AdUser findByUsername(String name);
}
