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
import com.infore.model.TPaper;
import com.infore.model.TTest;
import com.infore.model.dto.TPaperDto;
import com.infore.model.vo.TPaperCreateVo;
import com.infore.model.vo.TPaperUpdateVo;
import com.infore.service.qhService.TPaperService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @desc   
 * @class  TPaperController
 * @author  create author by deer
 * @date  2018年4月4日下午4:36:55
 */
@RestController
@RequestMapping("/paper")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "试卷操作",description = "试卷操作",produces = "application/json")
public class TPaperController extends BaseIotManagerController{
	
	@Autowired
	private TPaperService<TPaper> tPaperService;
	
	@NoNeedAuth
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ApiOperation(value = "查询试卷信息", notes = "查询试卷信息")
	@SuppressWarnings("rawtypes")
	public ResponseDto selectPapers(HttpServletRequest request,
			@RequestParam(value="createUser",required = true) int createUser) {
		TPaper paper = new TPaper();
		paper.setCreateUser(createUser);
		List<TPaper> list = tPaperService.selectListByEntity(paper);
		return ControllerUtil.returnDto(true, "成功", list);
	}
	
	
	@NoNeedAuth
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ApiOperation(value = "新增试卷", notes = "新增试卷")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "tPaper", value = "实体TPaperCreateVo", required = true, dataType = "TPaperCreateVo")
	})
	@SuppressWarnings("rawtypes")
	public ResponseDto insertSelective(HttpServletRequest request,@RequestBody TPaperCreateVo tPaper) {
		try {
			TPaper paper = tPaperService.addPaper(tPaper);
			if(paper == null) {
				return ControllerUtil.returnDto(false, "失败", paper);
			}else {
				return ControllerUtil.returnDto(true, "成功", paper);
			}
		} catch (Exception e) {
			logger.error("tPaperService--addPaper:系统异常",e);
			return ControllerUtil.returnDto(false, "添加试卷系统异常", null);
		}
	}
	
	@NoNeedAuth
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	@ApiOperation(value = "更新试卷信息", notes = "更新试卷信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "tPaper", value = "变更实体TPaperUpdateVo", required = true, dataType = "TPaperUpdateVo")
	})
	@SuppressWarnings("rawtypes")
	public ResponseDto updateByPrimaryKeySelective(HttpServletRequest request,
			 @RequestBody TPaperUpdateVo tPaper) {
		int count = 0;
		try {
			count = tPaperService.updateOne(tPaper.getId(), tPaper);
			if(count <= 0) {
				return ControllerUtil.returnDto(false, "失败", count);
			}else {
				return ControllerUtil.returnDto(true, "成功", count);
			}
		} catch (Exception e) {
			logger.error("tPaperService--updateOne:系统异常",e);
			return ControllerUtil.returnDto(false, StringUtils.isBlank(e.getMessage())?"更新试卷异常":e.getMessage(), count);
		}
	}
	
	@NoNeedAuth
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	@ApiOperation(value = "根据主键删除试卷信息", notes = "根据主键删除试卷信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "试卷编号", required = true, paramType = "query"),
		@ApiImplicitParam(name = "createUser", value = "创建者", required = true, paramType = "query"),
	})
	@SuppressWarnings("rawtypes")
	public ResponseDto deleteByPrimaryKey(HttpServletRequest request,
			@RequestParam(value="id",required = true) int id,
			@RequestParam(value="createUser",required = true) int createUser) {
		try {
			 tPaperService.deletePaperById(createUser, id);
			 return ControllerUtil.returnDto(true, "成功", true);
		} catch (Exception e) {
			logger.error("tPaperService--deletePaperById:系统异常",e);
			return ControllerUtil.returnDto(false, "删除试卷异常", false);
		}
	}
	
	@NoNeedAuth
	@RequestMapping(value = "/listByPage", method = RequestMethod.GET)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "createUser", value = "创建者",required = true, paramType = "query"),
		@ApiImplicitParam(name = "queryLike", value = "模糊查询参数",required = false, paramType = "query"),
		@ApiImplicitParam(name = "pageNum", value = "页码",required = true, paramType = "query"),
		@ApiImplicitParam(name = "pageSize", value = "每页的数量",required = true, paramType = "query"),
	})
	@ApiOperation(value = "分页查询某一用户试卷", notes = "分页查询某一用户试卷")
	@SuppressWarnings("rawtypes")
	public ResponseDto selectTPaperByPage(HttpServletRequest request,
			@RequestParam(value = "createUser",required = true) Integer createUser,
			@RequestParam(value = "queryLike",required = false) String queryLike,
			@RequestParam(value = "pageNum",required = true)Integer pageNum,
			@RequestParam(value = "pageSize",required = true)Integer pageSize) {
		try {
			PageInfo<TPaper> list = tPaperService.query(createUser,queryLike,pageNum,pageSize);
			return ControllerUtil.returnDto(true, "成功", list);
		} catch(Exception e){
			logger.error("tPaperService--query:系统异常",e);
			return ControllerUtil.returnDto(false, StringUtils.isBlank(e.getMessage())?"查询异常":e.getMessage(), null);
		}
	
	}
	
	
	//查询试卷详情
	@NoNeedAuth
	@RequestMapping(value = "/queryPaperInfo", method = RequestMethod.GET)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "createUser", value = "创建者",required = true, paramType = "query"),
		@ApiImplicitParam(name = "id", value = "试卷编号",required = false, paramType = "query"),
	})
	@ApiOperation(value = "分页查询某一用户试卷", notes = "分页查询某一用户试卷")
	@SuppressWarnings("rawtypes")
	public ResponseDto selectTPaperByPage(HttpServletRequest request,
			@RequestParam(value = "createUser",required = true) Integer createUser,
			@RequestParam(value = "id",required = false) Integer id) {
		try {
			TPaperDto paper = tPaperService.queryPaperInfo(createUser, id);
			return ControllerUtil.returnDto(true, "成功", paper);
			
		} catch(Exception e){
			logger.error("tPaperService--queryPaperInfo:系统异常",e);
			return ControllerUtil.returnDto(false, StringUtils.isBlank(e.getMessage())?"查询异常":e.getMessage(), null);
		}
	
	}
	
	
	
	
}
