package com.example.crmbtf.telegram;


import com.example.crmbtf.commands.Command;
import com.example.crmbtf.model.TelegramUser;
import com.example.crmbtf.repository.impl.*;
import com.example.crmbtf.telegram.inline.InlineTelegramBot;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.*;


@Slf4j
@Component
public class MyAppBot extends TelegramLongPollingBot {
    @Autowired
    private TelegramUserServiceImpl dataUserService;
    @Autowired
    private DoctorServiceImpl doctorService;
    @Autowired
    private AppointmentServiceImpl appointmentService;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private InlineTelegramBot inlineTelegramBot;
    @Autowired
    private PatientServiceImpl patientService;
    @Autowired
    private QuestionnaireServiceImpl questionnaireService;
    @Autowired
    private InfoDataServiceImpl infoDataService;
    @Autowired
    public List<Command> commands;

    public HashMap<Long, TelegramUser> user = new HashMap<>();

    @Override
    public String getBotUsername() {
        return "Clinic_bot";
    }

    @Override
    public String getBotToken() {
        return "5220644891:AAEOsFotO-rlhBHyCBf7pZEmnseZP7x8S5U";
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            if (update.hasInlineQuery()) {
                inlineTelegramBot.handleIncomingInlineQuery(update.getInlineQuery(), this);
                System.out.println(" update query= " + update.getInlineQuery().getQuery());
            }
            if ((update.hasMessage()) && (update.getMessage().hasContact())) {
                String phoneNumber = update.getMessage().getContact().getPhoneNumber();
                registerContactNumber(update, phoneNumber);
            } else if (update.hasMessage()) {

                Long chatId = update.getMessage().getChatId();
                String firstName = update.getMessage().getChat().getFirstName();
                String lastName = update.getMessage().getChat().getLastName();
                String inputText = update.getMessage().getText();

                MDC.put("firstName", firstName);
                MDC.put("lastName", lastName);

                if (!CheckLoggin(chatId)) {
                    dataUserService.createUser(chatId, firstName, lastName, "USER");
                }

                user.computeIfAbsent(chatId, a -> new TelegramUser(chatId, firstName, lastName));
                Command command = null;
                for (Command choseCommand : commands) {
                    if (inputText != null) {
                        if (choseCommand.shouldRunOnText(inputText) || (choseCommand.getGlobalState() != null && user.get(chatId).globalState == choseCommand.getGlobalState())) {
                            command = choseCommand;
                            user.get(chatId).globalState = choseCommand.getGlobalState();
                        }
                    }
                }

                ExecutionContext context = new ExecutionContext();
                context.setFirstName(firstName);
                context.setLastName(lastName);
                context.setChatId(chatId);
                context.setInputText(inputText);
                context.setMyAppBot(this);
                context.setUpdate(update);
                context.setTelegramUsersService(dataUserService);
                context.setDoctorService(doctorService);
                context.setAppointmentService(appointmentService);
                context.setUserService(userService);
                context.setPatientService(patientService);
                context.setInfoDataService(infoDataService);
                context.setQuestionnaireService(questionnaireService);

                if (command != null) {
                    log.info(context.printDateAndState() + " start command: " + command.getClass().getSimpleName());
                    command.doCommand(context);
                }
            }
        } catch (Exception e) {
            log.error("error", e);
            e.printStackTrace();

        } finally {
            MDC.clear();
        }
    }

    public boolean CheckLoggin(Long chatId) {
        Optional<TelegramUser> dataUserByChatId = dataUserService.findDataUserByChatId(chatId);
        return dataUserByChatId.isPresent();
    }


    public void registerContactNumber(Update update, String phoneNumber) {
        try {
            System.out.println(phoneNumber);
            TelegramUser user = dataUserService.findDataUserByChatId(update.getMessage().getChatId()).get();
            user.setPhone(phoneNumber);
            dataUserService.save(user);

            SendMessage message = new SendMessage();
            message.setChatId(String.valueOf(update.getMessage().getChatId()));
            message.setText("Регистрация успешна! Спасибо!");
            ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
            List<KeyboardRow> keyboardRowList = new ArrayList<>();
            KeyboardRow row = new KeyboardRow();
            KeyboardButton keyboardButton = new KeyboardButton();
            keyboardButton.setText("Главное меню");
            row.add(keyboardButton);
            keyboardRowList.add(row);
            replyKeyboardMarkup.setSelective(true);
            replyKeyboardMarkup.setResizeKeyboard(true);
            replyKeyboardMarkup.setOneTimeKeyboard(false);
            replyKeyboardMarkup.setKeyboard(keyboardRowList);
            message.setReplyMarkup(replyKeyboardMarkup);
            execute(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @PostConstruct
//    public void register() throws TelegramApiException {
//        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
//        botsApi.registerBot(this);
//    }


}