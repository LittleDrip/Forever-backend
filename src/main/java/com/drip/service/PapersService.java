package com.drip.service;

import com.drip.domain.dto.SubmitAnswerDTO;
import com.drip.domain.entity.Papers;
import com.baomidou.mybatisplus.extension.service.IService;
import com.drip.util.Result;

import java.util.List;

/**
* @author qq316
* @description 针对表【papers(试卷表)】的数据库操作Service
* @createDate 2024-11-26 08:50:22
*/
public interface PapersService extends IService<Papers> {

    Result getList();

    Result getScoreAndSave(SubmitAnswerDTO submitAnswerDTO);

    Result getPapersByUser();

    List getPapersByUserId(String userId);




    Result getEvaluation(int id);

    String  getEvaluation(String userId,int id);

}
