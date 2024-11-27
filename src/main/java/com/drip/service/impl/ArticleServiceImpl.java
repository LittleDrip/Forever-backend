package com.drip.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.drip.domain.entity.Article;
import com.drip.domain.vo.ArticleVo;
import com.drip.service.ArticleService;
import com.drip.mapper.ArticleMapper;
import com.drip.util.Result;
import com.drip.util.page.PageParam;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author qq316
* @description 针对表【article(文章信息表)】的数据库操作Service实现
* @createDate 2024-11-21 15:00:51
*/
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
    implements ArticleService {

    @Override
    public Result getAllArticle() {
        List<Article> list = list();
        List<ArticleVo> articleVos = BeanUtil.copyToList(list, ArticleVo.class);
        return Result.ok(articleVos);
    }

    @Override
    public Result getArticleByPage(PageParam pageParam) {
        Page<Article> page=Page.of(pageParam.getCurrent(),pageParam.getSize());
        List<Article> list = lambdaQuery().page(page).getRecords();
        List<ArticleVo> articleVos = BeanUtil.copyToList(list, ArticleVo.class);
        return Result.ok(articleVos);

    }


    @Override
    public Result getArticleById(Integer id) {
        Article article = getById(id);
        return Result.ok(article);
    }

    @Override
    public Result getTotals(PageParam pageParam) {
        Page<Article> page=Page.of(pageParam.getCurrent(),pageParam.getSize());
        long total = lambdaQuery().page(page).getPages();
        return Result.ok(total);
    }
}




