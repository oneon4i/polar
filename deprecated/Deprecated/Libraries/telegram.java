package com.slavlend.Libraries;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.*;
import com.pengrad.telegrambot.model.request.InputPollOption;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendPoll;
import com.pengrad.telegrambot.response.SendResponse;
import com.slavlend.Polar.PolarObject;
import com.slavlend.Polar.PolarValue;
import com.slavlend.Polar.Stack.Storage;
import com.slavlend.Polar.StackHistoryWriter;
import com.slavlend.Parser.Statements.FunctionStatement;

import java.util.ArrayList;
import java.util.List;

/*
Библиотека для работы с тг-ботами.
 */
@SuppressWarnings({"unused", "ThrowableNotThrown", "CallToPrintStackTrace", "Convert2Lambda"})
public class telegram {
    private TelegramBot bot;
    public FunctionStatement ON_CALL;
    public PolarObject ON_CALL_OBJ;
    public FunctionStatement POLL_ON_CALL;
    public PolarObject POLL_ON_CALL_OBJ;

    public telegram() {
    }

    public void on_message(PolarObject from, FunctionStatement link) {
        ON_CALL_OBJ = from;
        ON_CALL = link;
    }

    public void on_quiz_answer(PolarObject from, FunctionStatement link) {
        POLL_ON_CALL_OBJ = from;
        POLL_ON_CALL = link;
    }

    public void start(String token) {
        bot = new TelegramBot(token);
        // Register for updates
        bot.setUpdatesListener(new UpdatesListener() {
            @Override
            public int process(List<Update> updates) {
                for (Update update : updates) {
                    Message message = update.message();
                    PollAnswer poll = update.pollAnswer();

                    if (message != null) {
                        ArrayList<PolarValue> params = new ArrayList<>();

                        params.add(new PolarValue(String.valueOf(message.chat().id())));
                        params.add(new PolarValue(message.text()));

                        if (Storage.getInstance().getCallStack().get() == null) {
                            Storage.getInstance().getCallStack().set(new ArrayList<>());
                        }
                        if (StackHistoryWriter.getInstance().getHistory().get() == null) {
                            StackHistoryWriter.getInstance().getHistory().set(new ArrayList<>());
                        }

                        Storage.getInstance().push();
                        ON_CALL.call(ON_CALL_OBJ, params);
                        Storage.getInstance().pop();
                    }
                    else if (poll != null) {
                        ArrayList<PolarValue> params = new ArrayList<>();

                        params.add(new PolarValue(String.valueOf(poll.user().id())));
                        params.add(new PolarValue((float) poll.optionIds()[0]));

                        if (Storage.getInstance().getCallStack().get() == null) {
                            Storage.getInstance().getCallStack().set(new ArrayList<>());
                        }
                        if (StackHistoryWriter.getInstance().getHistory().get() == null) {
                            StackHistoryWriter.getInstance().getHistory().set(new ArrayList<>());
                        }

                        Storage.getInstance().push();
                        POLL_ON_CALL.call(POLL_ON_CALL_OBJ, params);
                        Storage.getInstance().pop();
                    }
                    // ... process updates
                    // return id of last processed update or confirm them all
                }
                return UpdatesListener.CONFIRMED_UPDATES_ALL;
            }
        }, e -> {
            if (e.response() != null) {
                // got bad response from telegram
                e.response().errorCode();
                e.response().description();
            } else {
                // probably network error
                e.printStackTrace();
            }
        });
    }

    public void send_message(String chat_id, String text) {
        SendResponse r = bot.execute(new SendMessage(chat_id, text).parseMode(ParseMode.Markdown));
    }

    public void send_quiz(String chat_id, String question, int right, PolarValue list) {
        ArrayList<InputPollOption> options = new ArrayList<>();

        for (PolarValue v : list.asList()) {
            options.add(new InputPollOption(v.asString()));
        }

        bot.execute(new SendPoll(chat_id, question, options.toArray(new InputPollOption[0])).correctOptionId(right).type("quiz").isAnonymous(false));
    }
}
