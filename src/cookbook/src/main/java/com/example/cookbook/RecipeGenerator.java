package com.example.cookbook;

import org.springframework.web.bind.annotation.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ai.ollama.OllamaChatClient;
import org.springframework.ai.chat.ChatClient;

@Configuration
public class ChatClientConfig {

    @Bean
    public ChatClient chatClient() {
        return new OllamaChatClient(); // uses model defined in application.properties
    }
}


@RestController
public class RecipeGenerator {

    private final ChatClient chatClient;

    public RecipeGenerator(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @GetMapping("/recipe")
    public String generateRecipe(@RequestParam(value = "instructions", defaultValue = "Make it delicious") String instructions) {
        return chatClient.prompt("Generate a cooking recipe: " + instructions)
                          .call()
                          .content();
    }
}

