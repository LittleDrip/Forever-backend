package com.drip.service;

import com.drip.domain.entity.Questions;
import com.baomidou.mybatisplus.extension.service.IService;
import com.drip.util.Result;

/**
* @author qq316
* @description 针对表【questions(题目表)】的数据库操作Service
* @createDate 2024-11-26 08:50:22
*/
public interface QuestionsService extends IService<Questions> {

    Result getQuestionsByPId(int pid);
}
