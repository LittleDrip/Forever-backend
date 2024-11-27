package com.drip.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.drip.domain.entity.Questions;
import com.drip.domain.vo.QuestionsVo;
import com.drip.service.QuestionsService;
import com.drip.mapper.QuestionsMapper;
import com.drip.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author qq316
* @description 针对表【questions(题目表)】的数据库操作Service实现
* @createDate 2024-11-26 08:50:22
*/
@Service
@Slf4j
public class QuestionsServiceImpl extends ServiceImpl<QuestionsMapper, Questions>
    implements QuestionsService{

    @Override
    public Result getQuestionsByPId(int pid) {
        List<Questions> questions = lambdaQuery().eq(Questions::getPid, pid).list();
        if (CollUtil.isEmpty(questions)) {
            return Result.build(null, 500, "此试卷未开放答题");
        }
        // 转换为vo对象
        log.info("查询结果：{}", questions);
        List<QuestionsVo> questionsVos = BeanUtil.copyToList(questions, QuestionsVo.class);
        log.info("转换后的VO对象：{}", questionsVos);
        return Result.ok(questionsVos);
    }
}




