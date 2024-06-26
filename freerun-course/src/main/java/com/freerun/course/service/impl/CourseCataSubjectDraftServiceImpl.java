package com.freerun.course.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.freerun.common.utils.CollUtils;
import com.freerun.course.constants.CourseConstants;
import com.freerun.course.domain.po.CourseCataSubjectDraft;
import com.freerun.course.mapper.CourseCataSubjectDraftMapper;
import com.freerun.course.service.ICourseCataSubjectDraftService;
import com.freerun.course.service.ICourseCatalogueDraftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 课程-题目关系表草稿 服务实现类
 * </p>
 */
@Service
public class CourseCataSubjectDraftServiceImpl extends ServiceImpl<CourseCataSubjectDraftMapper, CourseCataSubjectDraft> implements ICourseCataSubjectDraftService {

    @Autowired
    private ICourseCatalogueDraftService courseCatalogueDraftService;

    @Override
    @Transactional
    public void deleteNotInCataIdList(Long courseId) {

        //1.获取所有课程的小节和练习
        List<Long> cataIdList = courseCatalogueDraftService.queryCataIdsOfCourse(courseId,
                Arrays.asList(
                        CourseConstants.CataType.SECTION,
                        CourseConstants.CataType.PRATICE
                ));
        //1.删除条件
        LambdaUpdateWrapper<CourseCataSubjectDraft> updateWrapper =
                Wrappers.lambdaUpdate(CourseCataSubjectDraft.class)
                        .eq(CourseCataSubjectDraft::getCourseId, courseId)
                        .notIn(CollUtils.isNotEmpty(cataIdList),
                                CourseCataSubjectDraft::getCataId, cataIdList);
        //2.删除题目
        baseMapper.delete(updateWrapper);
    }
}
