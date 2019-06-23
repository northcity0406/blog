package com.northcity.blog.service;

import com.northcity.blog.dao.AdminRepository;
import com.northcity.blog.entity.Admin;
import com.northcity.blog.service.interfaceDecla.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("AdminService")
public class AdminImpl implements AdminService {

  /** 不能用static修飾 */
  @Autowired private AdminRepository adminRepository;

  @Override
  public List<Admin> findAll() {
    return adminRepository.findByAidNotNull();
  }

  @Override
  public Admin findAdminByAid(int aid) {
    return adminRepository.findAdminByAid(aid);
  }

  @Override
  public Admin findAdminByAidAndUserId(int aid, String uid) {
    return adminRepository.findAdminByAidAndUserId(aid, uid);
  }

  @Override
  public Admin login(String username, String password) {
    return adminRepository.findAdminByUsernameAndPassword(username, password);
  }

  @Override
  public Admin findAdminByUsername(String username) {
    return adminRepository.findAdminByUsername(username);
  }

  @Override
  public Admin save(Admin admin) {
    return adminRepository.saveAndFlush(admin);
  }

}
