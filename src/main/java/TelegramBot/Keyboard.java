package TelegramBot;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class Keyboard {
    //protected Boolean resize;
    //protected Boolean oneTime;
    protected ReplyKeyboardMarkup keyboard;

    Keyboard(){
        keyboard = new ReplyKeyboardMarkup();
    }

    void initKeyboard(Boolean oneTime, ArrayList<String> availableCommands) {
        ArrayList<String> listCommands = new ArrayList<>(availableCommands.stream().toList());
        List<KeyboardRow> keyboardRows = new ArrayList<>();

        while(!listCommands.isEmpty()) {
            KeyboardRow keyboardRow = new KeyboardRow();
            keyboardRows.add(keyboardRow);
            keyboardRow.add(new KeyboardButton(listCommands.get(listCommands.size()-1)));
            keyboard.setKeyboard(keyboardRows);
            listCommands.remove(listCommands.size()-1);
        }

        keyboard.setResizeKeyboard(true);
        keyboard.setOneTimeKeyboard(oneTime);
    }
}
