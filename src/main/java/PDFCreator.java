import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import commands.*;

import java.io.*;
import java.util.List;

public class PDFCreator {

    public static void main(String[] args) throws IOException {
        File inputFile = getInputFile();
        PdfDocument pdf = new PdfDocument(new PdfWriter("output.pdf"));
        Document document = new Document(pdf);
        document.add(new Paragraph());
        BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile));
        List<Command> commands = bufferedReader.lines().map(PDFCreator::createCommand).toList();
        Paragraph paragraph = new Paragraph();

        for (Command command : commands) {
            Paragraph newParagraph = command.getBlockElement(paragraph);
            if (newParagraph != paragraph) {
                document.add(paragraph);
                paragraph = newParagraph;
            }
        }

        document.add(paragraph);
        document.flush();
        document.close();
    }

    public static File getInputFile() {
        File file = new File("input.txt");
        if (!file.exists()) { //Sets the input file up if it does not already exist
            String resourceLocation = PDFCreator.class.getResource("input.txt").getFile();
            try (FileReader fileReader = new FileReader(resourceLocation); FileWriter fileWriter = new FileWriter(file)) {
                int c;
                while ((c = fileReader.read()) != -1) {
                    fileWriter.write(c);
                }
            } catch (FileNotFoundException e) {
                System.err.println("The file resource input.txt was not found");
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    public static Command createCommand(String line) {
        if (line.startsWith(".")) {
            String[] command = line.substring(1).split(" ");
            CommandType type = CommandType.valueOf(command[0].toUpperCase());
            return switch (type) {
                case INDENT -> new IndentCommand(Integer.parseInt(command[1]));
                case PARAGRAPH -> new ParagraphCommand();
                case BOLD -> new BoldCommand();
                case FILL -> new FillCommand();
                case ITALICS -> new ItalicCommand();
                case NOFILL -> new NoFillCommand();
                case REGULAR -> new RegularCommand();
                case LARGE -> new LargeCommand();
                case NORMAL -> new NormalCommand();
            };
        } else {
            return new TextCommand(line);
        }
    }
}
