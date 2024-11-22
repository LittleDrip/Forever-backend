package com.drip.service;

import com.drip.domain.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.drip.util.Result;

/**
* @author qq316
* @description 针对表【article(文章信息表)】的数据库操作Service
* @createDate 2024-11-21 15:00:51
*/
public interface ArticleService extends IService<Article> {

    Result getAllArticle();

    Result getArticleById(Integer id);
}
