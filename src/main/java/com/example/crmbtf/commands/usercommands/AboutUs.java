package com.example.crmbtf.commands.usercommands;


import com.example.crmbtf.commands.Command;
import com.example.crmbtf.model.ReplyButton;
import com.example.crmbtf.model.TelegramUsers;
import com.example.crmbtf.telegram.ExecutionContext;
import com.example.crmbtf.telegram.inline.InlineButton;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AboutUs implements Command {
    @Override
    public void doCommand(ExecutionContext executionContext) {

        List<InlineButton> inlineButtons = List.of(new InlineButton("Instagram", "https://instagram.com"), new InlineButton("Facebook", "https://facebook.com"));
        executionContext.buildInlineKeyboard("Возможно тебя заинтересует одна из наших соц. сетей?", inlineButtons);

        List<ReplyButton> replyButtonList = List.of(
                new ReplyButton("Наш адрес"),
                new ReplyButton("Главное меню"));

        executionContext.buildReplyKeyboard("CLINIC_NAME — это частная клиника в Киеве для всей семьи.", replyButtonList);
        executionContext.replyMessage("""
                Мы позаботимся как о новорожденном ребенке, так и о людях почтенного возраста.
                Мы предоставляем медицинские услуги с выездом на дом, в клинике и онлайн,\s
                чтобы всегда держать под контролем ваше хорошее самочувствие.""");

    }

    @Override
    public boolean shouldRunOnText(String text) {
        return text.equals("О нас");
    }

    @Override
    public TelegramUsers.botstate getGlobalState() {
        return TelegramUsers.botstate.ABOUT;
    }
}