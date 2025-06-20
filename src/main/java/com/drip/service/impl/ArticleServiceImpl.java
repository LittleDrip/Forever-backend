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
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
* @author qq316
* @description 针对表【article(文章信息表)】的数据库操作Service实现
* @createDate 2024-11-21 15:00:51
*/
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
    implements ArticleService {
    @Autowired
    private ChatClient.Builder chatClientBuilder;
    @Autowired
    private ArticleMapper articleMapper;

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


    public Result getArticleConclusionById(Integer id) {
        // 1. 获取文章内容
        Article article = articleMapper.selectById(id);
        if (article == null) {
            return Result.build(null,404,"文章不存在");
        }

        // 2. 创建专用总结客户端
        ChatClient summaryClient = chatClientBuilder
                .defaultSystem("""
                你是一个专业的内容总结助手，请用简洁的中文对用户提供的文章进行总结，
                要求：
                1. 保留核心观点和关键数据
                2. 使用结构化格式（如分点陈述）
                3. 总结长度不超过原文的30%
                """)
                .defaultAdvisors(Collections.emptyList())
                .build();

        // 3. 构造提示词
        String prompt = String.format("""
            请总结以下文章：
            ---
            标题：%s
            内容：%s
            ---
            """, article.getTitle(), article.getContent());

        // 4. 调用AI总结
        try {
            String summary = summaryClient.prompt()
                    .user(prompt)
                    .call()
                    .content();
            return Result.ok(summary);
        } catch (Exception e) {
            return Result.ok("总结生成失败：" + e.getMessage());
        }
    }

    @Override
    public Result getArticleByTag(String tag) {
        List<Article> list = lambdaQuery().eq(Article::getTag, tag).list();
        List<ArticleVo> articleVos = BeanUtil.copyToList(list, ArticleVo.class);
        return Result.ok(articleVos);
    }
//    @Override
//    public Result getArticleConclusionById(Integer id) {
//        Article article = getById(id);
//
//        return null;
//    }
}




