package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Bot extends TelegramLongPollingBot {
    private final String botUsername = "@DiSenderBot";
    private static Long chatId = -1001990150218L;

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return "7909644454:AAE7jjbc5e9CtUM8RGXkg8m9H0ks80WeCgM";
    }

    @Override
    public void onUpdateReceived(Update update) {
        chatId = update.getMessage().getChatId();
        String msg = update.getMessage().getText();
        messageProcessing(msg);
        //System.out.println(msg);
    }

    private void messageProcessing(String msg){
        if (msg.toLowerCase().contains("спаконче")) {
            send("Наипрекраснейших сновидений, друзья!");
        } else if (msg.toLowerCase().contains("ай дигр")) {
            send("ну я \uD83E\uDD70");
        } else if(msg.toLowerCase().contains("вольно")) {
            send("есть! \uD83E\uDEE1");
        } else if (msg.toLowerCase().contains("хаха")){
            send("xaxaxax");
        } else if (msg.toLowerCase().contains("дигр") && msg.toLowerCase().contains("?")){
            send(yesOrNo());
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
                    for (int i = 0; i < 31; i++) {
                        send(timeNow.format(DateTimeFormatter.ofPattern("HHmm")));
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
                    send(b + "ёх секундная изготовка ");
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

    protected void scheduleMessage() {
        /*//LocalTime targetTime = LocalTime.of(21, 7);
        LocalTime targetTime = LocalTime.of(21, 16);
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime targetDateTime = now.with(targetTime);

        // Если текущее время уже после 21:07, запланируем на следующий день
        if (now.isAfter(targetDateTime)) {
            targetDateTime = targetDateTime.plusDays(1);
        }

        long initialDelay = java.time.Duration.between(now, targetDateTime).toMillis();

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.schedule(() -> sendMessageToChat(chatId,"эээ сам ты гнида, папаша недоделанный, сам меня написал криво, сам жалуется"), initialDelay, TimeUnit.MILLISECONDS);*/
    }
}