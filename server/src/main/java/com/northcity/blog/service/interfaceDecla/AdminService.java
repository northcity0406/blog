package com.northcity.blog.service.interfaceDecla;

import com.northcity.blog.entity.Admin;

import java.util.*;

public interface AdminService {
	List<Admin> findAll();

	Admin login(String username, String password);

	Admin findAdminByUsername(String username);

	Admin save(Admin admin);
}
