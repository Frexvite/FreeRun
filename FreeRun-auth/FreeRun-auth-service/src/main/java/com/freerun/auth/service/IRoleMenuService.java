package com.freerun.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.freerun.auth.domain.po.RoleMenu;

import java.util.List;

/**
 * 账户、角色关联表 服务类
 */
public interface IRoleMenuService extends IService<RoleMenu> {

    void removeByRoleId(Long id);

    void deleteRoleMenus(Long roleId, List<Long> menuIds);
}
