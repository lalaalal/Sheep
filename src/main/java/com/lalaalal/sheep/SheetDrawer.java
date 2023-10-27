package com.lalaalal.sheep;

import java.io.OutputStream;

public class SheetDrawer {
    private char cornerLetter;
    private char horizontalLetter;
    private char verticalLetter;
    private Sheet sheet;

    public SheetDrawer(char cornerLetter, char horizontalLetter, char verticalLetter, Sheet sheet) {
        this.cornerLetter = cornerLetter;
        this.horizontalLetter = horizontalLetter;
        this.verticalLetter = verticalLetter;
        this.sheet = sheet;
    }

    public void draw(OutputStream outputStream, Range range) {

    }
}
