package com.lzx.distributed.lock.mappr;

import com.lzx.distributed.lock.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, String> {

    UserInfo findUserInfoById(String id);

}
