package com.northcity.blog.dao;

import com.northcity.blog.entity.Admin;
import com.northcity.blog.entity.AdminPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<Admin, AdminPK> {

  @Override
  List<Admin> findAll();

  List<Admin> findByAidNotNull();

  Admin findAdminByAid(int aid);

  Admin findAdminByAidAndUserId(int aid, String userId);


  Admin findAdminByUsernameAndPassword(String username,String password);

  Admin findAdminByUsername(String username);
}
