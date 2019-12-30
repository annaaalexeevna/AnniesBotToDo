package ru.spbgasu.annaaalexeevna;

import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

import java.util.ArrayList;
import java.util.List;

public class ClassOfArrayLists extends BotCommand {

    public ClassOfArrayLists(String commandIdentifier, String description, List<Task> arrayOfTasks, List<TaskList> arrayOfTaskLists, List<GroupOfTask> arrayGroupOfTasks) {
        super(commandIdentifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] arguments) {

    }
}
