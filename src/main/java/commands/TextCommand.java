package commands;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.hyphenation.HyphenationConfig;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;

public class TextCommand implements Command {
    private final Text text;

    public TextCommand(String text, int indent, boolean bold, boolean italic, boolean large, TextAlignment alignment) {
        this.text = new Text(text);
        Style style = new Style();
        if (bold) style.setBold();
        if (italic) style.setItalic();
        if (large) style.setFontSize(30);
        style.setMarginLeft(indent * 20);
        style.setTextAlignment(alignment);
        this.text.addStyle(style);
    }

    @Override
    public Paragraph getBlockElement(Paragraph paragraph, Document document) {
        if (!paragraph.isEmpty()) {
            String currentString = text.getText();
            switch (currentString.charAt(0)) { //punctuation should not put an additional space where not necessary
                case ',':
                case '.':
                case ' ':
                case ':': return paragraph.add(text);
            }
            text.setText(" " + text.getText());
        }
        return paragraph.add(text);
    }

}
