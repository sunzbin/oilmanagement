package com.jeefw.service.sys;

import java.util.List;

import com.jeefw.app.bean.UpdateUserPwdRequestBean;
import com.jeefw.model.sys.Department;
import com.jeefw.model.sys.SysUser;

import core.service.Service;
import core.support.PageBaseParameter;

/**
 * 用户的业务逻辑层的接口
 */
public interface SysUserService extends Service<SysUser> {

	// 获取用户信息（将数据库查询出来的信息再处理，增加字段的中文意思）
	List<SysUser> querySysUserCnList(List<SysUser> resultList);

	// 获取个人资料信息（将数据库查询出来的信息再处理，增加头像）
	SysUser getSysUserWithAvatar(SysUser sysuser);

	String updateSysUser(UpdateUserPwdRequestBean brb);
	
	//获取分页部门信息
	public PageBaseParameter<SysUser> querySysUserInfo(PageBaseParameter<SysUser> param);

}
