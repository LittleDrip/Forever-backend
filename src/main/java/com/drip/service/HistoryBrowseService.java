package com.drip.service;

import com.drip.domain.dto.HistoryBrowseDTO;
import com.drip.domain.entity.HistoryBrowse;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author qq316
* @description 针对表【history_browse(用户历史浏览记录表)】的数据库操作Service
* @createDate 2025-03-27 11:02:25
*/
public interface HistoryBrowseService extends IService<HistoryBrowse> {

    void saveBrowseHistory(HistoryBrowseDTO browseDTO);

    List<HistoryBrowse> getBrowseHistory(Long userId);
}
