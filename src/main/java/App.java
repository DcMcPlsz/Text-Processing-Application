
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
        Pipe<String> file = new PipeImpl<String>();
        file.put("FileToBeRead.txt");
        TextFileReader fileReader = new TextFileReader(file);
        file.closeForWriting();
        WordBoundaryTokenizer splitter = new WordBoundaryTokenizer(fileReader.reading());
        RemoveNonAlphaNumericWord remover = new RemoveNonAlphaNumericWord(splitter.splitting());
        LowercaseTransformer transformer = new LowercaseTransformer(remover.removing());
        StopWordsRemover remover2 = new StopWordsRemover(transformer.transforming());
        RootWordsConverter converter = new RootWordsConverter(remover2.removing());
        WordCounter counter = new WordCounter(converter.converting());
        counter.counting();
    }
}