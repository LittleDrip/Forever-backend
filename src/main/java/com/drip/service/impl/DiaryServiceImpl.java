package com.drip.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.drip.domain.dto.DiaryDTO;
import com.drip.domain.entity.Diary;
import com.drip.domain.entity.User;
import com.drip.domain.vo.DiaryVo;
import com.drip.service.DiaryService;
import com.drip.mapper.DiaryMapper;
import com.drip.util.Result;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
* @author qq316
* @description 针对表【diary(心情日记表)】的数据库操作Service实现
* @createDate 2025-01-08 11:26:37
*/
@Service
public class DiaryServiceImpl extends ServiceImpl<DiaryMapper, Diary>
    implements DiaryService{

    @Override
    public List<DiaryVo> getDiaryList() {
//        根据用户查询
        User user = (User) StpUtil.getSession().get("user");
        Long userId = user.getId();
        List<Diary> list = lambdaQuery().eq(Diary::getUserId, userId).list();
        // 将 Diary 实体转换为 DiaryVo
        List<DiaryVo> diaryVoList = list.stream()
                .map(diary -> {
                    // 复制属性并设置日期格式
                    DiaryVo diaryVo = BeanUtil.copyProperties(diary, DiaryVo.class);
                    // 使用 Hutool 的 DateUtil 将 Date 转换为 "yyyy-MM-dd" 格式
                    String formattedDate = DateUtil.format(diary.getUpdateTime(), "yyyy-MM-dd");
                    diaryVo.setDate(formattedDate);
                    return diaryVo;
                })
                .collect(Collectors.toList());

        return diaryVoList;
    }

    @Override
    public Result createDiary(DiaryDTO diary) {
        Diary diary1=new Diary();
        Object userID = StpUtil.getLoginId();
        diary1.setContent(diary.getContent());
        diary1.setUserId(Long.valueOf(userID.toString()));
        diary1.setMood(diary.getMood());
        diary1.setContent(diary.getContent());
        diary1.setWeather(diary.getWeather());
        boolean save = this.save(diary1);
        if(save){
            return Result.ok(null);
        }
        return Result.build(null,401,"创建失败");
    }

    @Override
    public Result<Void> updateDiary(Long id, DiaryDTO diary) {
//        根据ID修改diary
        Diary diary1=new Diary();
        diary1.setWeather(diary.getWeather());
        diary1.setContent(diary.getContent());
        diary1.setMood(diary.getMood());
        boolean update = lambdaUpdate().eq(Diary::getId, id).update(diary1);
        if(update){
            return Result.ok(null);
        }else {
            return Result.build(null,401,"修改失败");
        }
    }

    @Override
    public Result<Void> deleteDiary(Long id) {
        boolean remove = this.removeById(id);
        if(remove){
            return Result.ok(null);
        }
        return Result.build(null,401,"删除失败");

    }
}




