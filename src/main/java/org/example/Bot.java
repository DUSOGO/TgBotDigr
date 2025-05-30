package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendDice;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.LocalTime;
import java.util.Random;

import static org.glassfish.grizzly.ProcessorExecutor.execute;

public class Bot extends TelegramLongPollingBot {
    private final String botUsername = "@DiSenderBot";
    private static Long chatId = -1001990150218L;
    private static String token = "7909644454:AAE7jjbc5e9CtUM8RGXkg8m9H0ks80WeCgM";

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        //chatId = update.getMessage().getChatId();
        String msg = update.getMessage().getText();
        messageProcessing(msg);
        //System.out.println(msg);
    }

    private void messageProcessing(String msg) {
        if (msg.toLowerCase().contains("спаконче")) {
            send("Наипрекраснейших сновидений, друзья!");
        } else if (msg.toLowerCase().contains("ай дигр")) {
            send("ну я \uD83E\uDD70");
        } else if (msg.toLowerCase().contains("проверка")) {
            send("Данил Г. из ИСП6 сделал этого бота");
        } else if (msg.toLowerCase().contains("вольно")) {
            send("есть! \uD83E\uDEE1");
        } else if (msg.toLowerCase().contains("хаха")) {
            send("xaxaxax");
        } else if (msg.toLowerCase().contains("дигр") && msg.toLowerCase().contains("?")) {
            send(yesOrNo());
        }else if (msg.toLowerCase().contains("дигр") && msg.toLowerCase().contains("казик")) {
            gamble();
        }
    }

    protected void vazCounter() {
        Thread thread = new Thread(() -> {//System.out.println("Поток " + Thread.currentThread().getName() + " запущен с помощью лямбда.");
            LocalTime timeStart = LocalTime.of(21, 1, 1); //time to start code

            while (true) { // 24/7, this code always works
                LocalTime timeNow = LocalTime.now(); //now
                long a = ChronoUnit.SECONDS.between(timeNow, timeStart); // calculating the time before the start
                int b = (int) a; // same as A, but int type (to multiply it later)

                if (a == 0) { // if now
                    for (int i = 0; i < 39; i++) {
                        String hhmm = timeNow.format(DateTimeFormatter.ofPattern("HHmm"));
                        send(hhmm);
                        if (hhmm.equals("2109")) {
                            send("21099");
                        }
                        //System.out.println(timeNow);
                        try {
                            Thread.sleep(60000);  // sleep minute
                            timeNow = LocalTime.now(); // update actually time
                        } catch (InterruptedException e) {
                            send("поспать не получилось / sleep exception");
                            throw new RuntimeException(e);
                        }
                    }

                    try {
                        send("Товарищи командующие, свою работу завершил! Доложил курсант Дигр! \uD83D\uDCA4");
                        Thread.sleep(84540000);  //sleep 24 hours
                    } catch (InterruptedException e) {
                        System.out.println("in 57 line something didn't work out, give you exception. he couldn't sleep ");
                    }
                }

                try {   // if not now
                    send((b / 60) + 1 + "ёх/ух/и/a минутная изготовка ");
                    send("бананови залёт в чат наху");
                    Thread.sleep(b * 1000);
                } catch (Exception e) {
                    send("Это время прошло кажется");
                    try {
                        send("я спать на близжайшие 12 часов тогда");
                        Thread.sleep(42200000);  //sleep 12 hours
                    } catch (InterruptedException es) {
                        System.out.println("in 65 line, class DraftForBot, method vaz counter. something didn't work out, give you exception. he couldn't sleep ");
                        send("Всё наебнулось! конгратулейшонс бляха");
                    }
                }
            }
        });
        thread.start(); //Запуск потока
    }

    public void send(String what) {
        SendMessage sm = SendMessage.builder()
                .chatId(chatId.toString()) //Who are we sending a message to
                .text(what).build();    //Message content
        try {
            execute(sm);                        //Actually sending the message
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);      //Any error will be printed here
        }
    }

    public String yesOrNo() {
        Random random = new Random();
        // Генерируем случайное число 0 или 1
        int randomNumber = random.nextInt(2); // 0 или 1

        // В зависимости от случайного числа отправляем "да" или "нет"
        if (randomNumber == 0) {
            return "да";
        } else {
            return "нет";
        }
    }

    public void gamble() {
        SendDice dice = new SendDice(String.valueOf(chatId));

        sendAnimoji(dice);

        dice.setEmoji("\uD83C\uDFB0");
        sendAnimoji(dice);

        dice.setEmoji("\uD83C\uDFAF");
        sendAnimoji(dice);
    }

    public void sendAnimoji(SendDice emoji) {
        try {
            execute(emoji);                    //Actually sending the message
        } catch (TelegramApiException e) {
            System.out.println("in method send give you error");
            throw new RuntimeException(e);      //Any error will be printed here
        }
    }
}
