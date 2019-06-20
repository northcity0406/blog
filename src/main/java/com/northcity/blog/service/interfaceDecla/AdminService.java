package com.northcity.blog.service.interfaceDecla;

import com.northcity.blog.entity.Admin;

import java.util.*;

public interface AdminService {
	List<Admin> findAll();

	Admin findAdminByAid(int aid);

	Admin findAdminByAidAndUserId(int aid,String uid);

	Admin login(String username,String password);


	Admin findAdminByUsername(String username);
}
