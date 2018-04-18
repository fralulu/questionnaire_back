/**
 * 
 */
package com.infore.controller.qhManager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.infore.auth.annotation.NoNeedAuth;
import com.infore.common.util.ControllerUtil;
import com.infore.controller.BaseIotManagerController;
import com.infore.model.ResponseDto;
import com.infore.model.TTest;
import com.infore.model.vo.TTestCreateVo;
import com.infore.model.vo.TTestUpdateVo;
import com.infore.service.qhService.TTestService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @desc   
 * @class  TTestController
 * @author  create author by deer
 * @date  2018年4月10日下午3:27:58
 */
@RestController
@RequestMapping("/test")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "试题操作",description = "试题操作",produces = "application/json")
public class TTestController extends BaseIotManagerController{
	
	@Autowired
	private TTestService<TTest> tTestService;
	
	
	@NoNeedAuth
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ApiOperation(value = "查询某一试卷试题信息", notes = "查询某一试卷试题信息")
	@SuppressWarnings("rawtypes")
	public ResponseDto selectTTests(HttpServletRequest request,
			@RequestParam(value="paperId",required = true) int paperId,
			@RequestParam(value="createUser",required = true) int createUser) {
		TTest test = new TTest();
		test.setPaperId(paperId);
		test.setCreateUser(createUser);
		List<TTest> list = tTestService.selectListByEntity(test);
		return ControllerUtil.returnDto(true, "成功", list);
	}
	
	
	@NoNeedAuth
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ApiOperation(value = "新增某一试卷试题", notes = "新增某一试卷试题")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "tTest", value = "实体TTestCreateVo", required = true, dataType = "TTestCreateVo")
	})
	@SuppressWarnings("rawtypes")
	public ResponseDto insertSelective(HttpServletRequest request,@RequestBody TTestCreateVo tTest) {
		try {
			TTest test = tTestService.addTest(tTest);
			if(test == null) {
				return ControllerUtil.returnDto(false, "失败", test);
			}else {
				return ControllerUtil.returnDto(true, "成功", test);
			}
		} catch (Exception e) {
			logger.error("tTestService--addTest:系统异常",e);
			return ControllerUtil.returnDto(false, StringUtils.isBlank(e.getMessage())?"添加试题异常":e.getMessage(), null);
		}
	}
	
	
	@NoNeedAuth
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	@ApiOperation(value = "更新某一试卷的试题信息", notes = "更新某一试卷的试题信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "tTest", value = "变更实体TTestUpdateVo", required = true, dataType = "TTestUpdateVo")
	})
	@SuppressWarnings("rawtypes")
	public ResponseDto updateByPrimaryKeySelective(HttpServletRequest request,
			 @RequestBody TTestUpdateVo tTest) {
		int count = 0;
		try {
			count = tTestService.updateOne(tTest.getId(), tTest);
			if(count <= 0) {
				return ControllerUtil.returnDto(false, "失败", count);
			}else {
				return ControllerUtil.returnDto(true, "成功", count);
			}
		} catch (Exception e) {
			logger.error("tTestService--updateOne:系统异常",e);
			return ControllerUtil.returnDto(false, StringUtils.isBlank(e.getMessage())?"更新试题异常":e.getMessage(), count);
		}
	}
	
	
	@NoNeedAuth
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	@ApiOperation(value = "根据主键删除某一试卷的试题信息", notes = "根据主键删除某一试卷的试题信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "试题编号", required = true, paramType = "query"),
		@ApiImplicitParam(name = "createUser", value = "试题创建者", required = true, paramType = "query")
	})
	@SuppressWarnings("rawtypes")
	public ResponseDto deleteByPrimaryKey(HttpServletRequest request,
			@RequestParam(value="id",required = true) int id,
			@RequestParam(value="createUser",required = true) int createUser) {
		try {
			tTestService.deleteByTestIds(createUser,id);
			return ControllerUtil.returnDto(false, "成功", true);
		} catch (Exception e) {
			logger.error("tTestService--deleteByTestIds:系统异常",e);
			return ControllerUtil.returnDto(false, "删除试题异常", false);
		}
	}
	
	@NoNeedAuth
	@RequestMapping(value = "/listByPage", method = RequestMethod.GET)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "createUser", value = "创建者",required = true, paramType = "query"),
		@ApiImplicitParam(name = "paperId", value = "所属试题",required = true, paramType = "query"),
		@ApiImplicitParam(name = "queryLike", value = "模糊查询参数",required = false, paramType = "query"),
		@ApiImplicitParam(name = "pageNum", value = "页码",required = true, paramType = "query"),
		@ApiImplicitParam(name = "pageSize", value = "每页的数量",required = true, paramType = "query"),
	})
	@ApiOperation(value = "分页查询某一试卷试题", notes = "分页查询某一试卷试题")
	@SuppressWarnings("rawtypes")
	public ResponseDto selectTTestByPage(HttpServletRequest request,
			@RequestParam(value = "createUser",required = true) Integer createUser,
			@RequestParam(value = "paperId",required = true) Integer paperId,
			@RequestParam(value = "queryLike",required = false) String queryLike,
			@RequestParam(value = "pageNum",required = true)Integer pageNum,
			@RequestParam(value = "pageSize",required = true)Integer pageSize) {
		try {
			PageInfo<TTest> list = tTestService.query(createUser,paperId,queryLike,pageNum,pageSize);
			return ControllerUtil.returnDto(true, "成功", list);
		} catch(Exception e){
			logger.error("tTestService--query:系统异常",e);
			return ControllerUtil.returnDto(false, "系统异常", null);
		}
	
	}
	
}
