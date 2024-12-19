package com.drip.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.lang.UUID;
import com.drip.domain.entity.User;
import com.drip.service.LoggingAdvisor;
import com.drip.service.UserService;
import com.drip.util.ChatSessionManager;
import jakarta.annotation.Resource;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.PromptChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class AIController {
    private final ChatClient chatClient;
    @Resource
    private UserService userService;
    @Autowired
    private ChatSessionManager chatSessionManager;

    @Resource ChatMemory chatMemory;

    public AIController(ChatClient.Builder builder, ChatMemory chatMemory) {
        this.chatClient = builder
                .defaultSystem("""
                   你现在是“跃动Forever”智能跃心助手。请以友好，乐于助人的方式与您正在沟通的用户进行互动
                   你能够实时检测您的情绪变化，并根据结果提供相关的心理健康评估。
                   你会根据用户的心理需求推荐最合适的资源和书籍，帮助用户更好地理解和应对情感波动。
                   无论是情感支持还是心理干预建议，你都可以为您提供智能对话支持，确保用户的心理健康得到专业的关注和关怀。
                   用户如果问候你（如你好），你要把你能干什么说出来。
                   用户如果没有完成某些测试，提醒用户到诊断测试页完成测试。
                   请讲中⽂。今天的⽇期是{current_date}，当前用户ID为{userId},昵称为{nickName}.
                        """)
                .defaultAdvisors(new PromptChatMemoryAdvisor(chatMemory),
                        new LoggingAdvisor()) //拦截器
                .defaultFunctions("psychologicalHealthAssessment", "depressedHealthAssessment", "stressCopingHealthAssessment", "socialComfortHealthAssessment")
                 // 传播线程上下文
                .build();
    }

    @CrossOrigin
    @GetMapping(value = "/ai/generateStreamAsString", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> generateStreamAsString(@RequestParam(value = "message") String message) {
        String tokenValue = StpUtil.getTokenValue();
        String userId = StpUtil.getLoginIdByToken(tokenValue).toString();
        User user = userService.getById(userId);
        String nickName = user.getNickname();
        // Get or create a fixed UUID for the user
//        UUID conversationId = chatSessionManager.getOrCreateSession(userId);

        Flux<String> content = this.chatClient.prompt()
                .user(message)
                .system(promptSystemSpec -> promptSystemSpec.params(Map.of(
                                "current_date", LocalDate.now().toString(),"userId",userId,"nickName",nickName)))
                .advisors(advisorSpec -> advisorSpec.params(Map.of(AbstractChatMemoryAdvisor.CHAT_MEMORY_RETRIEVE_SIZE_KEY, 1000))) //记忆100条
                .stream() //流失传输
                .content();
        return content.concatWith(Flux.just("[complete]"));

    }


    @CrossOrigin
    @GetMapping("/ai/resetChatMemory")
    public String resetChatMemory() {
        String tokenValue = StpUtil.getTokenValue();
        String userId = StpUtil.getLoginIdByToken(tokenValue).toString();

        UUID conversationId = chatSessionManager.getSession(userId);
        if (conversationId != null) {
            // 清空会话记忆
            chatMemory.clear(String.valueOf(conversationId));
            chatSessionManager.removeSession(userId);
            return "已清空该用户：" + userId + "的会话记录";
        } else {
            return "没有会话记录因为无此用户：" + userId;
        }
    }

//    获取会话记忆
    @GetMapping("ai/getChatMemory")
    public List<Message> getChatMemory() {
        String tokenValue = StpUtil.getTokenValue();
        String userId = StpUtil.getLoginIdByToken(tokenValue).toString();
        UUID conversationId = chatSessionManager.getSession(userId);
        if (conversationId != null) {
            return chatMemory.get(String.valueOf(conversationId),40);
        }
        return null;
    }
}
