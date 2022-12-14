package TelegramBot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class User
{
    private final Long id;
    private final AnswersFactory answersFactory;
    private final Keyboard keyboard;
    private final SendMessage message1,message2;

    protected User(Long idUser)
    {
        id = idUser;
        answersFactory = new AnswersFactory();
        keyboard = new Keyboard();
        message1 = new SendMessage();
        message2 = new SendMessage();
    }

    protected void setCommand(String messageText) { answersFactory.command = messageText; }

    protected void objMessageWorkout(String text, Boolean keyKeyboared, Boolean oneTime, ArrayList<String> availableCommands)
    {
        if (keyKeyboared)
        {
            message2.setChatId(id);
            message2.setText(text);
            keyboard.initKeyboardWorkout(oneTime,availableCommands);
            message2.setReplyMarkup(keyboard.keyboard);
            return;
        }

        message1.setChatId(id);
        message1.setText(text);
    }

    protected ArrayList<SendMessage> objMessageList(String text) throws InterruptedException, IOException
    {
        ArrayList<SendMessage> outputObjList = new ArrayList<>();
        ArrayList<String> outputStrList = new ArrayList<>(answersFactory.getResponse());

        if (Objects.equals(answersFactory.command, "game") || answersFactory.game.getFlagGame()){
            ArrayList<String> arrGame = new ArrayList<>();
            arrGame.add("exit");
            objMessageWorkout(outputStrList.get(0),true,true,arrGame);
            outputObjList.add(message2);
            return outputObjList;
        }
        else if (outputStrList.size() > 1)
        {
            objMessageWorkout(outputStrList.get(0),false,false,answersFactory.getAvailableCommands());
            objMessageWorkout(outputStrList.get(1),true,true,answersFactory.getAvailableCommands());
            outputObjList.add(message1);
            outputObjList.add(message2);
            return outputObjList;
        }

        objMessageWorkout(outputStrList.get(0),true,Objects.equals(text, "with you"),answersFactory.getAvailableCommands());
        outputObjList.add(message2);

        return outputObjList;
    }
}
