package com.freerun.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.freerun.common.enums.UserType;
import com.freerun.common.utils.StringUtils;
import com.freerun.user.domain.po.UserDetail;
import com.freerun.user.domain.query.UserPageQuery;
import com.freerun.user.mapper.UserDetailMapper;
import com.freerun.user.service.IUserDetailService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 教师详情表 服务实现类
 */
@Service
public class UserDetailServiceImpl extends ServiceImpl<UserDetailMapper, UserDetail> implements IUserDetailService {

    @Override
    public UserDetail queryById(Long userId) {
        return getBaseMapper().queryById(userId);
    }

    @Override
    public List<UserDetail> queryByIds(List<Long> ids) {
        return getBaseMapper().queryByIds(ids);
    }

    @Override
    public Page<UserDetail> queryUserDetailByPage(UserPageQuery query, UserType type) {
        // 1.分页条件
        Page<UserDetail> p = query.toMpPageDefaultSortByCreateTimeDesc();
        // 2.搜索条件
        Integer status = query.getStatus();
        String name = query.getName();
        String phone = query.getPhone();
        QueryWrapper<UserDetail> wrapper = new QueryWrapper<>();
        wrapper
                .eq(type != null , "u.type", type)
                .eq(status != null, "u.status", status)
                .eq(StringUtils.isNotBlank(phone),"u.cell_phone", phone)
                .like(StringUtils.isNotBlank(name), "ud.name", name);
        // 3.查询
        p = getBaseMapper().queryByPage(p, wrapper);
        // 4.返回
        return p;
    }
}
