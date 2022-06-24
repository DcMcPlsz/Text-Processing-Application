
import filters.LowercaseTransformer;
import filters.RemoveNonAlphaNumericWord;
import filters.RootWordsConverter;
import filters.StopWordsRemover;
import filters.TextFileReader;
import filters.WordBoundaryTokenizer;
import filters.WordCounter;
import pipes.Pipe;
import pipes.PipeImpl;

public class App {

    public static void main(String[] args) throws Throwable {
        // create a pipe for file
        Pipe<String> file = new PipeImpl<String>();

        // put the file name to the pipe
        file.put("FileToBeRead.txt");

        // then link the file pipe with text file reader filter
        TextFileReader fileReader = new TextFileReader(file);

        file.closeForWriting(); // close the pipe after done using

        // then connect the rest of the filter with the previous filter output
        WordBoundaryTokenizer splitter = new WordBoundaryTokenizer(fileReader.reading());
        RemoveNonAlphaNumericWord remover = new RemoveNonAlphaNumericWord(splitter.splitting());
        LowercaseTransformer transformer = new LowercaseTransformer(remover.removing());
        StopWordsRemover remover2 = new StopWordsRemover(transformer.transforming());
        RootWordsConverter converter = new RootWordsConverter(remover2.removing());
        WordCounter counter = new WordCounter(converter.converting());
        // get the final result of word counter here
        counter.counting();
    }
}