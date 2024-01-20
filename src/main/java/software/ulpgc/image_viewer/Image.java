package software.ulpgc.image_viewer;

public interface Image {

    String name();
    Image next();
    Image prev();

}
