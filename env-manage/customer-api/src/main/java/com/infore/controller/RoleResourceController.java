package com.infore.controller;

import com.infore.model.ResponseDto;
import com.infore.model.RoleResource;
import com.infore.service.RoleResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xuyao on 02/01/2018.
 */
@RestController
@RequestMapping("/role-resource")
@Api(value = "角色资源功能",description = "角色资源功能",produces = MediaType.APPLICATION_JSON)
public class RoleResourceController {
    private static final Logger log = LoggerFactory.getLogger(RoleResourceController.class);

    @Autowired
    private RoleResourceService roleResourceService;

    @ApiOperation(value = "添加用户角色关联")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ResponseDto add(@RequestBody RoleResource resource) {
        roleResourceService.add(resource);
        return new ResponseDto();
    }

    @ApiOperation(value = "删除关系")
    @RequestMapping(value = "/del",method = RequestMethod.POST)
    public ResponseDto del(@RequestParam Integer id) {
        roleResourceService.del(id);
        return new ResponseDto();
    }
}
