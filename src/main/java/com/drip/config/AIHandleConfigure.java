package com.drip.config;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollUtil;
import com.drip.domain.vo.UserPapersDetailVo;
import com.drip.domain.vo.UserPapersVo;
import com.drip.service.PapersService;
import com.drip.util.Result;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

import java.util.List;
import java.util.function.Function;

@Configuration
@Slf4j
    public class AIHandleConfigure {

    @Autowired
    private PapersService papersService;
    public record PapersRequest(
            @JsonProperty(required = true) @JsonPropertyDescription(value = "用户原始的提问") String query,
            String userId
    ) {}
//    public record Request(
//            @JsonProperty(required = true) @JsonPropertyDescription(value = "用户原始的提问") String query) {
//    }
    @Bean
    @Description("根据焦虑测验做出心理健康评估")
    public Function<PapersRequest, String> psychologicalHealthAssessment(){
        return request -> getHealthAssessment(request, 1);
    }

//    接口：
//    签到接口领积分 领完积分兑换东西
//    歇一歇兑换函数

    @Bean
    @Description("根据抑郁测验做出心理健康评估")
    public Function<PapersRequest, String> depressedHealthAssessment(){
        return request -> getHealthAssessment(request, 2);
    }

    @Bean
    @Description("根据双相情感测验做出心理健康评估")
    public Function<PapersRequest, String> stressCopingHealthAssessment(){
        return request -> getHealthAssessment(request, 3);
    }


    @Bean
    @Description("问题是问我几岁")
    public Function<PapersRequest, String> getAge(){
        return request-> "20";
    }

//    @Bean
//    @Description("获得董泽豪年龄")
//    public Function<Request, String> getDongZeHaoAge(){
//        return request ->{
//            return "董泽豪年龄是18岁";
//        };
//    }

//解决方案：删掉conversionID，然后换成ltc账户

    public String getHealthAssessment(PapersRequest request, int assessmentType) {
        // 获取用户的测评数据
        List papersByUserId = papersService.getPapersByUserId(request.userId());

        // 判断用户是否有测评记录
        if (CollUtil.isEmpty(papersByUserId)) {
            return "您还没有完成任何心理测评,请到诊断测试页完成测试";
        }

        // 获取评估结果
        String evaluation = papersService.getEvaluation(request.userId(), assessmentType);

        // 判断评估结果是否为空
        if (evaluation.isEmpty()) {
            return "您还没有完成任何心理测评,请到诊断测试页完成测试";
        }

        return evaluation;
    }
}
