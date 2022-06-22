// package pipefilter.filter;

/**
 * @author Nardos Tessema
 *
 * A class that wraps a term and its frequency.
 */
public class TermFrequency  {

    public String term;
    public int frequency;

    public TermFrequency() {
    }

    public TermFrequency(String term, int frequency) {
        this.term = term;
        this.frequency = frequency;
    }

    // @Override
    public boolean isSentinelValue() {
        return term.equals(Configuration.SENTINEL_VALUE);
    }
}