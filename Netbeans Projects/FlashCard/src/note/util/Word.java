package note.util;

public class Word {

    private String word;
    private String mean;
    private String note;
    private String topic;

    Word() {
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Word(String word, String mean, String note, String topic) {
        this.word = word;
        this.mean = mean;
        this.note = note;
        this.topic = topic;
    }

    public Word getIfExisted(String word) {

        //WordDB wd = new WordDB();
        return null;
    }

    public boolean isNewEntry() {
        return false;
    }

    public boolean save() {
        WordUtil.saveWord(this);
        return false;
    }

    @Override
    public String toString() {
        return "Word{" + "word=" + word + ", mean=" + mean + ", note=" + note + ", topic=" + topic + '}';
    }
    
    
}
