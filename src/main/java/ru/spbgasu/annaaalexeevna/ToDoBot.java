package ru.spbgasu.annaaalexeevna;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.ApiContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class ToDoBot extends TelegramLongPollingCommandBot {

    private static final String BOT_USERNAME = "AnnieToDoBot";

    List<Task> arrayOfTasks;
    List<TaskList> arrayOfTaskLists;
    List<GroupOfTask> arrayGroupOfTasks;
    ToDoBot toDoBot;
    ClassOfArrayLists classOfArrayLists;

    public ToDoBot(DefaultBotOptions botOptions) {
        super(botOptions, BOT_USERNAME);
        this.arrayOfTasks = new ArrayList<Task>();
        this.arrayOfTaskLists = new ArrayList<TaskList>();
        this.arrayGroupOfTasks = new ArrayList<GroupOfTask>();
        this.toDoBot = new ToDoBot(botOptions);
        this.classOfArrayLists = new ClassOfArrayLists(arrayOfTasks, arrayOfTaskLists, arrayGroupOfTasks);

        register(new Add("New", "You can create new task/list/group", classOfArrayLists));
        register(new Edit("Edit", "You can edit task/list/group", classOfArrayLists));
        register(new Delete("Delete", "You can delete task/list/group", classOfArrayLists));
        register(new Show("Show", "You can show task/list/group", classOfArrayLists));
        register(new MarkReady("MarkReady", "You can mark ready task", classOfArrayLists));
        register(new Help("Help", "Help"));
    }


    @Override
    public void processNonCommandUpdate(Update update) {
        SendMessage sendMessage = null;
        //String textMessage = null;
        sendMessage = new SendMessage()
                .setText(update.getCallbackQuery().getData())
                .setChatId(update.getCallbackQuery().getMessage().getChatId());

        if (sendMessage != null) {
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public String getBotToken() {
        return System.getenv("BOT_TOKEN");
    }
}
