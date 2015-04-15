package com.color.game.elements.protoEnums;


public enum ProtoColor {
    EMPTY,
    NEUTRAL,
    RED,
    GREEN,
    BLUE;

    public ProtoColor next() {
        if (this.equals(ProtoColor.RED)) {
            return ProtoColor.GREEN;
        } else if (this.equals(ProtoColor.GREEN)) {
            return ProtoColor.BLUE;
        } else if (this.equals(ProtoColor.BLUE)) {
            return ProtoColor.RED;
        } else {
            return ProtoColor.NEUTRAL;
        }
    }
}
