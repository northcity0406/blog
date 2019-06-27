package com.northcity.blog.dao;

import com.northcity.blog.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
  @Override
  List<Admin> findAll();

  Admin findAdminByNameAndPassword(String name, String password);

  Admin findAdminByName(String username);

  @Override
  <S extends Admin> S saveAndFlush(S s);
}
