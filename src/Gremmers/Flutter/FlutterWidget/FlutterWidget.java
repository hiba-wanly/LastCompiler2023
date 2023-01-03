package Gremmers.Flutter.FlutterWidget;

import Gremmers.AST;
import Gremmers.Flutter.Column.Column;
import Gremmers.Flutter.Container.Container;
import Gremmers.Flutter.Image.imageClasses;
import Gremmers.Flutter.Padding.paddingClasses;
import Gremmers.Flutter.Row.Row;
import Gremmers.Flutter.SizedBox.SizedBoxClasses;
import Gremmers.Flutter.TextButton.textButton;
import Gremmers.Flutter.TextStatement.TextClasses;

public class FlutterWidget extends AST {

    public Container attr_Container;
    public Column attr_Column;
    public Row attr_Row;
    public TextClasses attr_TextClasses;
    public SizedBoxClasses attr_SizedBoxClasses;
    public textButton attr_textButton;
    public imageClasses attr_imageClasses;

    public paddingClasses attr_paddingClasses;
}
