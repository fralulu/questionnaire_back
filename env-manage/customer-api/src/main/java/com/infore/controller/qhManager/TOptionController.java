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

import com.infore.auth.annotation.NoNeedAuth;
import com.infore.common.util.ControllerUtil;
import com.infore.controller.BaseIotManagerController;
import com.infore.model.ResponseDto;
import com.infore.model.TOption;
import com.infore.model.vo.TOptionCreateVo;
import com.infore.model.vo.TOptionUpdateVo;
import com.infore.service.qhService.TOptionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @desc   
 * @class  TOptionController
 * @author  create author by deer
 * @date  2018年4月10日下午5:49:22
 */
@RestController
@RequestMapping("/option")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "选项操作",description = "选项操作",produces = "application/json")
public class TOptionController extends BaseIotManagerController{
	@Autowired
	private TOptionService<TOption> tOptionService;
	
	@NoNeedAuth
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ApiOperation(value = "查询某一试卷试题信息的选项", notes = "查询某一试卷试题信息的选项")
	@SuppressWarnings("rawtypes")
	public ResponseDto selectTOptions(HttpServletRequest request,
			@RequestParam(value="testId",required = true) int testId,
			@RequestParam(value="createUser",required = true) int createUser) {
		TOption option = new TOption();
		option.setTestId(testId);
		option.setCreateUser(createUser);
		List<TOption> list = tOptionService.selectListByEntity(option);
		return ControllerUtil.returnDto(true, "成功", list);
	}
	
	
	@NoNeedAuth
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ApiOperation(value = "新增某一试卷试题的选项", notes = "新增某一试卷试题的选项")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "tOption", value = "实体TOptionCreateVo", required = true, dataType = "TOptionCreateVo")
	})
	@SuppressWarnings("rawtypes")
	public ResponseDto insertSelective(HttpServletRequest request,@RequestBody TOptionCreateVo tOption) {
		try {
			TOption option = tOptionService.addOPtions(tOption);
			if(option == null) {
				return ControllerUtil.returnDto(false, "失败", option);
			}else {
				return ControllerUtil.returnDto(true, "成功", option);
			}
		} catch (Exception e) {
			logger.error("tOptionService--addOPtions:系统异常",e);
			return ControllerUtil.returnDto(false, StringUtils.isBlank(e.getMessage())?"添加试题异常":e.getMessage(), null);
		}
	}
	
	@NoNeedAuth
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	@ApiOperation(value = "更新某一试卷的试题选项信息", notes = "更新某一试卷的试题选项信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "tOption", value = "变更实体TOptionUpdateVo", required = true, dataType = "TOptionUpdateVo")
	})
	@SuppressWarnings("rawtypes")
	public ResponseDto updateByPrimaryKeySelective(HttpServletRequest request,
			 @RequestBody TOptionUpdateVo tOption) {
		int count = 0;
		try {
			count = tOptionService.updateOne(tOption.getId(), tOption);
			if(count <= 0) {
				return ControllerUtil.returnDto(false, "失败", count);
			}else {
				return ControllerUtil.returnDto(true, "成功", count);
			}
		} catch (Exception e) {
			logger.error("tOptionService--updateOne:系统异常",e);
			return ControllerUtil.returnDto(false, StringUtils.isBlank(e.getMessage())?"更新试题选项异常":e.getMessage(), count);
		}
	}
	
	@NoNeedAuth
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	@ApiOperation(value = "根据主键删除某一试卷的试题选项信息", notes = "根据主键删除某一试卷的试题选项信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "试题编号", required = true, paramType = "query")
	})
	@SuppressWarnings("rawtypes")
	public ResponseDto deleteByPrimaryKey(HttpServletRequest request,
			@RequestParam(value="id",required = true) int id) {
		int count = 0;
		try {
			count = tOptionService.deleteByPrimaryKey(id);
			if(count <= 0) {
				return ControllerUtil.returnDto(false, "失败", count);
			}else {
				return ControllerUtil.returnDto(true, "成功", count);
			}
		} catch (Exception e) {
			logger.error("tOptionService--deleteByPrimaryKey:系统异常",e);
			return ControllerUtil.returnDto(false, "系统异常", count);
		}
	}
	
	
	
}
