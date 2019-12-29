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

public class Main extends TelegramLongPollingCommandBot {
    private static final String BOT_USERNAME = "AnniesToDoBot";
    ArrayList<Task> arrayOfTasks;
    ArrayList<TaskList> arrayOfTaskLists;
    ArrayList<GroupOfTask> arrayGroupOfTasks;
    Main main;

    public Main(DefaultBotOptions botOptions) {
        super(botOptions, BOT_USERNAME);
        register(new Add("New", "You can create new task/list/group", arrayOfTasks, arrayOfTaskLists, arrayGroupOfTasks, main));
        register(new Edit("Edit", "You can edit task/list/group", arrayOfTasks, arrayOfTaskLists, arrayGroupOfTasks, main));
        register(new Delete("Delete", "You can delete task/list/group", arrayOfTasks, arrayOfTaskLists, arrayGroupOfTasks, main));
        register(new Show("Show", "You can show task/list/group", arrayOfTasks, arrayOfTaskLists, arrayGroupOfTasks));
        register(new MarkReady("MarkReady", "You can mark ready task", arrayOfTasks, arrayOfTaskLists, arrayGroupOfTasks, main));
        register(new Help("Help", "Help", main));
    }

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            DefaultBotOptions botOptions = ApiContext.getInstance(DefaultBotOptions.class);
//            System.getProperties().put("proxySet", "true");
//            System.getProperties().put("socksProxyHost", "127.0.0.1");
//            System.getProperties().put("socksProxyPort", "9150");

            botOptions.setProxyHost("127.0.0.1");
            botOptions.setProxyPort(9150);
            botOptions.setProxyType(DefaultBotOptions.ProxyType.SOCKS5);

            telegramBotsApi.registerBot(new Main(botOptions));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
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
