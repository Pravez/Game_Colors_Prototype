package com.color.game.elements.protoEnums;


public enum ProtoColor {
    EMPTY,
    NEUTRAL,
    RED,
    YELLOW,
    BLUE;

    /**
     * Method to get the next ProtoColor from Red, Green, and Blue
     * @return the next ProtoColor
     */
    public ProtoColor next() {
        if (this.equals(ProtoColor.RED)) {
            return ProtoColor.YELLOW;
        } else if (this.equals(ProtoColor.YELLOW)) {
            return ProtoColor.BLUE;
        } else if (this.equals(ProtoColor.BLUE)) {
            return ProtoColor.RED;
        } else {
            return ProtoColor.NEUTRAL;
        }
    }
}
