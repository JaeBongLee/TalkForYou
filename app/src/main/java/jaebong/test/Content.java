package jaebong.test;

/**
 * Created by JaeBong on 15. 8. 23..
 */
public class Content {
    private String korean;
    private String japanese;
    private String chinese;
    private String english;
    private String sound;

    public Content(String korean, String japanese, String chinese, String english, String sound) {
        this.korean = korean;
        this.japanese = japanese;
        this.chinese = chinese;
        this.english = english;
        this.sound = sound;
    }

    public String getKorean() {
        return korean;
    }

    public String getJapanese() {
        return japanese;
    }

    public String getChinese() {
        return chinese;
    }

    public String getEnglish() {
        return english;
    }

    public String getSound() { return sound; }
}
