package ru.spbgasu.annaaalexeevna;

import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Help extends BotCommand {

    private final SendMessage sendMessage;

    public Help(String commandIdentifier, String description) {
        super(commandIdentifier, description);
        sendMessage = new SendMessage();
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] arguments) {
        sendMessage.setText("Список доступных команд: \n /add [task/list/group] \n /delete [task/list/group] \n" +
                "/edit [task/list/group] \n \t если task, " +
                "то номер_группы, номер_списка, номер_задания, название_задания, описание, дата_окончания " +
                "в формате dd/MM/yyyy \n \t если list, то номер_группы, номер_списка, название_списка\n \t " +
                "если group, то номер_группы, название_группы \n /show для демонстрации всего ToDo List \n" +
                "/markready - чтобы отметить задание выполненным");
        ToDoBot.trySendMessage(absSender, user, sendMessage);
    }
}
