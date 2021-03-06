package com.northcity.blog.controller;

import com.northcity.blog.entity.SysLog;
import com.northcity.blog.response.BaseResponse;
import com.northcity.blog.service.interfaceDecla.SyslogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class SyslogController {
    private Logger logger = LoggerFactory.getLogger(FriendsController.class);

    @Autowired
    private SyslogService syslogService;

    /**
     * 返回所有的系统日志
     * @param page 第几页
     * @param pageSize 页的大小
     * @return
     */

    @RequestMapping(value = "/a/sys/log",method = {RequestMethod.GET,RequestMethod.POST})
    public BaseResponse<Page<SysLog>> findAll(
            @RequestParam("page") int page,
            @RequestParam("pageSize") int pageSize
            ){
        logger.info(page + " " + pageSize);
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(page, pageSize, sort);
        return getResponse(true,"所有日志!",pageable);
    }

    /**
     *
     * @param success 动作是否成功
     * @param msg   返回的消息
     * @param pageable Pageable 参数
     * @return
     */
    private BaseResponse<Page<SysLog>> getResponse(boolean success, String msg, Pageable pageable){
        BaseResponse<Page<SysLog>> response = new BaseResponse<>();
        response.setSuccess(success);
        response.setMsg(msg);
        if(success){
            response.setData(syslogService.findAll(pageable));
            return response;
        }
        response.setData(null);
        return response;
    }


}
