public interface BookReader {
    public int getWordsCount();
    public int getCurrentWordIndex();
    public Word nextWord();
    public Word prevWord();
    public Word getWordAt(int n);
}
