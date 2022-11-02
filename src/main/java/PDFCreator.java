import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.TextAlignment;
import commands.*;

import java.io.*;
import java.util.List;
import java.util.Optional;

public class PDFCreator {

    public static void main(String[] args) throws IOException {
        File inputFile = getInputFile();
        PdfDocument pdf = new PdfDocument(new PdfWriter("output.pdf"));
        Document document = new Document(pdf);
        document.add(new Paragraph());
        BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile));
        CommandParser commandParser = new CommandParser();
        List<Command> commands = bufferedReader.lines().map(commandParser::createCommand).filter(Optional::isPresent).map(Optional::get).toList();
        Paragraph paragraph = new Paragraph();
        for (Command command : commands) {
            Paragraph newParagraph = command.getBlockElement(paragraph, document);
            newParagraph.setTextAlignment(TextAlignment.JUSTIFIED);
            if (newParagraph != paragraph) {
                if (paragraph.isEmpty()) continue;
                newParagraph.add(" NEW ");
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


}
