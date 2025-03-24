package com.drip.service;

import com.drip.domain.dto.DiaryDTO;
import com.drip.domain.entity.Diary;
import com.baomidou.mybatisplus.extension.service.IService;
import com.drip.domain.vo.DiaryVo;
import com.drip.util.Result;

import java.util.List;

/**
* @author qq316
* @description 针对表【diary(心情日记表)】的数据库操作Service
* @createDate 2025-01-08 11:26:37
*/
public interface DiaryService extends IService<Diary> {

    List<DiaryVo> getDiaryList();

    Result createDiary(DiaryDTO diary);

    Result<Void> updateDiary(Long id, DiaryDTO diary);

    Result<Void> deleteDiary(Long id);
}
