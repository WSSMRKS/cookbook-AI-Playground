@RestController
public class RecipeGenerator {
    private final ChatClient chatClient;

    public RecipeGenerator(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @PostMapping(value = "/recipe", produces = MediaType.APPLICATION_JSON_VALUE)
    public Recipe generateRecipe(@RequestParam(value = "instructions", defaultValue = "Make it vegan") String instructions) {
        return chatClient.prompt("Generate a cooking recipe. " + instructions)
                         .call()
                         .entity(Recipe.class);
    }
}

record Recipe(String title, String description, List<String> ingredients, List<String> steps) {}
