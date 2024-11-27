package com.drip.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.drip.domain.dto.SubmitAnswerDTO;
import com.drip.domain.entity.Papers;
import com.drip.domain.entity.Questions;
import com.drip.domain.entity.User;
import com.drip.domain.entity.UserPapers;
import com.drip.domain.vo.UserPapersVo;
import com.drip.service.EvaluationService;
import com.drip.service.PapersService;
import com.drip.mapper.PapersMapper;
import com.drip.service.QuestionsService;
import com.drip.service.UserPapersService;
import com.drip.util.Result;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
* @author qq316
* @description 针对表【papers(试卷表)】的数据库操作Service实现
* @createDate 2024-11-26 08:50:22
*/
@Service
public class PapersServiceImpl extends ServiceImpl<PapersMapper, Papers>
    implements PapersService{

    @Resource
    private QuestionsService questionsService;
    @Resource
    private UserPapersService userPapersService;

    @Resource
    private EvaluationService evaluationService;

    @Override
    public Result getList() {
        return Result.ok(list());
    }

    @Override
    public Result getScoreAndSave(SubmitAnswerDTO submitAnswerDTO) {
//        得到试题答案集合(stAnswer)
        Long paperId = submitAnswerDTO.getPaperId();
        List<String> standardAnswers = questionsService.lambdaQuery()
                .eq(Questions::getPid, paperId)
                .select(Questions::getStanswer)
                .list()
                .stream()
                .map(Questions::getStanswer)  // 提取每道题的答案
                .collect(Collectors.toList());
        // 比较标准答案与用户提交的答案，统计匹配的数量
        int correctCount = 0;

        List<String> userAnswers = submitAnswerDTO.getAnswers();
        // 假设 userAnswers 和 standardAnswers 的大小相同
        for (int i = 0; i < userAnswers.size(); i++) {
            String userAnswer = userAnswers.get(i);
            String standardAnswer = i < standardAnswers.size() ? standardAnswers.get(i) : null;
            // 比较答案：如果用户的答案与标准答案相同，认为答对了
            if (userAnswer != null && userAnswer.equals(standardAnswer)) {
                correctCount++;
            }
        }
//        todo:根据正确个数给出评价，存到user_paper表里面
        String evaluation = evaluationService.getEvaluation(correctCount);
//        根据SaToken的会话获取用户
        User user = (User) StpUtil.getSession().get("user");
        Long userId = user.getId();
        // 更新或插入到 user_papers 表中
        UserPapers userPapers = new UserPapers();
        userPapers.setUserId(userId);
        userPapers.setPaperId(paperId.intValue());
        userPapers.setEvaluation(evaluation);
        userPapers.setStatus(1);  // 设置状态为已完成
        UpdateWrapper<UserPapers> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("user_id", userId).eq("paper_id", paperId);
        userPapersService.saveOrUpdate(userPapers, updateWrapper);
            return Result.ok(userPapers);
        }

    @Override
    public Result getPapersByUser() {
        User user = (User) StpUtil.getSession().get("user");
        Long userId = user.getId();
        List<UserPapers> list = userPapersService.lambdaQuery().eq(UserPapers::getUserId, userId).select(UserPapers::getPaperId).list();
        List<UserPapersVo> userPapersVos = BeanUtil.copyToList(list, UserPapersVo.class);
        return Result.ok(userPapersVos);
    }
}




