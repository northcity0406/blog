package com.northcity.blog.service;

import com.northcity.blog.dao.AdminRepository;
import com.northcity.blog.entity.Admin;
import com.northcity.blog.service.interfaceDecla.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("AdminService")
public class AdminImpl implements AdminService {

  @Autowired
  private AdminRepository adminRepository;


  @Override
  public List<Admin> findAll() {
    return adminRepository.findAll();
  }

  @Override
  public Admin login(String username, String password) {
    return adminRepository.findAdminByNameAndPassword(username,password);
  }

  @Override
  public Admin findAdminByUsername(String username) {
    return adminRepository.findAdminByName(username);
  }

  @Override
  public Admin save(Admin admin) {
    return adminRepository.saveAndFlush(admin);
  }
}
