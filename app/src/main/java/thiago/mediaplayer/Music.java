package thiago.mediaplayer;

/**
 * Created by thiago on 08/10/16.
 */
public class Music {

    private String name;
    private int raw;

    public Music(String name, int raw) {
        this.name = name;
        this.raw = raw;
    }

    public String getName() {
        return name;
    }
    public int getRaw(){
        return raw;
    }
}
