package com.drip.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.drip.domain.dto.HistoryBrowseDTO;
import com.drip.domain.entity.HistoryBrowse;
import com.drip.service.HistoryBrowseService;
import com.drip.mapper.HistoryBrowseMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
* @author qq316
* @description 针对表【history_browse(用户历史浏览记录表)】的数据库操作Service实现
* @createDate 2025-03-27 11:02:25
*/
@Service
public class HistoryBrowseServiceImpl extends ServiceImpl<HistoryBrowseMapper, HistoryBrowse>
    implements HistoryBrowseService{

    @Override
    public void saveBrowseHistory(HistoryBrowseDTO browseDTO) {
        HistoryBrowse browse = lambdaQuery().eq(HistoryBrowse::getUserId, browseDTO.getUserId())
                .eq(HistoryBrowse::getArticleId, browseDTO.getArticleId())
                .one();
        if (browse == null) {
            // 如果没有找到记录，则插入新的浏览记录
            HistoryBrowse historyBrowse = new HistoryBrowse();
            // 使用 BeanUtils 将 DTO 的属性复制到 Entity
            BeanUtils.copyProperties(browseDTO, historyBrowse);
            historyBrowse.setViewTime(LocalDateTime.now());
            save(historyBrowse);
        }else {
            browse.setViewTime(LocalDateTime.now());
            updateById(browse);
        }
    }

    @Override
    public List<HistoryBrowse> getBrowseHistory(Long userId) {
        List<HistoryBrowse> list = lambdaQuery().eq(HistoryBrowse::getUserId, userId)
                .orderByDesc(HistoryBrowse::getViewTime)
                .list();
        return list;
    }
}




