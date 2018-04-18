package com.infore.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.infore.auth.annotation.NoNeedAuth;
import com.infore.common.Properties;
import com.infore.common.util.KieUtils;
import com.infore.common.constant.Const;
import com.infore.common.reqVO.TestInfoVO;
import com.infore.model.ResponseDto;
import com.infore.model.TestInfo;
import com.infore.model.dto.TestInfoDto;

import com.infore.model.fact.Address;
import com.infore.model.fact.AddressCheckResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.infore.service.impl.TestInfoServiceImpl;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xuyao on 2017/6/26.
 */
@RestController
@RequestMapping("/info")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "list test",description = "描述测试",produces = "application/json")
public class InfoController {
    private static final Logger logger = LoggerFactory.getLogger(InfoController.class);

    @Autowired
    private TestInfoServiceImpl testInfoService;
    @Autowired
    private Properties properties;

    @RequestMapping(value = "/vo", method = RequestMethod.GET)
    public ResponseDto getInfo(@RequestParam(value = "testInfoVO",required = false) String testInfoVO) {
//        logger.info("------2222-----{}", JSONObject.parseObject(testInfoVO, TestInfoVO.class));
        System.out.println("====11=====" + testInfoVO.toString());
        return new ResponseDto(testInfoVO.toString());
    }

    @NoNeedAuth
    @ApiOperation(value = "获取单条信息",response = TestInfo.class)
    @RequestMapping(value = "/test",method = RequestMethod.POST)
    public ResponseDto getInfo( @RequestBody TestInfoVO testInfoVO) {
        System.out.println("====11=====" +testInfoVO.getUserName()+"_______"+ testInfoVO.toString());
        return new ResponseDto(testInfoVO.toString());
    }
    @NoNeedAuth
    @ApiOperation(value = "新增")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseDto addInfo(@RequestBody TestInfo testInfo) {
        testInfoService.insertInfo(testInfo);
        return new ResponseDto(testInfo.toString());
    }
    @NoNeedAuth
    @ApiOperation(value = "新增DTo")
    @RequestMapping(value = "/addd",method = RequestMethod.POST)
    public ResponseDto addInfoDto(HttpServletRequest request, @RequestBody TestInfoDto testInfoDto
        , @RequestAttribute(Const.PARAM_LOGIN_NAME) String loginName
    ) {
//        testInfoService.insertInfo(testInfo);
        String loginName1 = (String) request.getParameter(Const.PARAM_LOGIN_NAME);
        logger.info("----dd:{}-------loginName:{}--------loginName1:{}",testInfoDto.toString(),loginName,loginName1);
        return new ResponseDto(JSONObject.toJSON(testInfoDto));
    }

    @NoNeedAuth
    @RequestMapping(value = "/lists",method = RequestMethod.GET)
    @ApiOperation(value="获取list",notes="分页查询",response = TestInfo.class,responseContainer = "List")
    @ApiImplicitParams(
        @ApiImplicitParam(name = "name",value = "搜索名称",required = false)
    )
    public ResponseDto getList(HttpServletRequest request, @RequestParam(value = "name",required = false) String name,
                            @RequestParam(value ="page") Integer page, @RequestParam(value ="size") Integer size) {
        page = page == null || page <= 0 ? 1 : page;
        size = size == null || size <= 5 ? 5 : size;
//        logger.info("------分页查询 start,page:{}-----size:{}----properties:{}-----", page,size,properties.CONTEXT_PATH);
//        logger.info("------分页查询 JWT_APP_EXPIRED:{}-----JWT_SECURITYKEY:{}----properties:{}-----", proComponent.JWT_APP_EXPIRED,proComponent.JWT_SECURITY_KEY,size,proComponent.CONTEXT_PATH);
        PageHelper.startPage(page, size);//PageHelper只对紧跟着的第一个SQL语句起作用
        List<TestInfo> ts=testInfoService.queryList();
        PageInfo pageInfo = new PageInfo(ts);
        return new ResponseDto<PageInfo>(pageInfo);
    }

    @ResponseBody
    @RequestMapping("/address")
    public void test(){
        KieSession kieSession = KieUtils.getKieContainer().newKieSession();

        Address address = new Address();
        address.setPostcode("518000");

        AddressCheckResult result = new AddressCheckResult();
        kieSession.insert(address);
        kieSession.insert(result);
        int ruleFiredCount = kieSession.fireAllRules();
        System.out.println("触发了" + ruleFiredCount + "条规则");

        if(result.isPostCodeResult()){
            System.out.println("规则校验通过");
        }

        kieSession.dispose();//释放资源
    }

}
