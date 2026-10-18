package com.nikhil.geminiintegration;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatService {
    private final ChatClient chatClient;
    private final List<Message> context= new ArrayList<>();
    public ChatService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public String chat(String message) {

        context.add(new UserMessage(message));
        String response = chatClient.prompt()
                .messages(context)
                .call()
                .content();
        context.add(new AssistantMessage(response));

        return response;
    }
}