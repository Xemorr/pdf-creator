package commands;

import com.itextpdf.layout.element.Paragraph;

public class TextCommand implements Command {

    public String text;

    public TextCommand(String text) {
        this.text = text;
    }

    @Override
    public Paragraph getBlockElement(Paragraph paragraph) {
        return paragraph.add(text);
    }

}
