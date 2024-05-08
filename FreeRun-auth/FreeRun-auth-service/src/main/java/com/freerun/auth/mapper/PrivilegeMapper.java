package com.freerun.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.freerun.auth.domain.po.Privilege;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 权限表，包括菜单权限和访问路径权限 Mapper 接口
 */
public interface PrivilegeMapper extends BaseMapper<Privilege> {

    List<Privilege> listRolePrivileges(@Param("roleId") Long roleId);
}