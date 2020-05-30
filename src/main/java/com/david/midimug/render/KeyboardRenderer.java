/*
 * Copyright (C) 2020 david
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.david.midimug.render;

import java.util.HashMap;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;

/**
 *
 * @author david
 */
public class KeyboardRenderer {

    private final static int WHITE_KEY_COUNT = 52, BLACK_KEY_COUNT = 36;
    private final static HashMap<Integer, Button> piano_keys = new HashMap<>();

    public static void renderPianoKeys(StackPane target) {
        target.setAlignment(Pos.TOP_LEFT);
        ObservableList nodeList = target.getChildren();
        nodeList.clear();
        piano_keys.clear();

        final double whiteKeyHeight = target.getPrefHeight();
        final double whiteKeyWidth = getPianoWhiteKeyWidth();
        for (int i = 0; i < WHITE_KEY_COUNT; i++) {
            Button key = new Button();
            key.setPrefWidth(whiteKeyWidth);
            key.setPrefHeight(whiteKeyHeight);
            key.setTranslateX(i * whiteKeyWidth);
            nodeList.add(key);
            int key_padding = 2 * i - (i + 5) / 7 - (i + 2) / 7;
            int midi_position = 21 + key_padding;
            piano_keys.put(midi_position, key);
        }

        final double blackKeyHeight = whiteKeyHeight * 0.6;
        final double blackKeyWidth = getPianoBlackKeyWidth();
        for (int i = 0; i < BLACK_KEY_COUNT; i++) {
            Button key = new Button();
            key.setStyle("-fx-background-color: #000000;");
            key.setPrefWidth(blackKeyWidth);
            key.setPrefHeight(blackKeyHeight);
            double x = i * whiteKeyWidth + (whiteKeyWidth - blackKeyWidth / 2);
            int key_padding = (i + 4) / 5 + (i + 2) / 5;
            x += key_padding * whiteKeyWidth;
            key.setTranslateX(x);
            nodeList.add(key);
            int midi_position = 22 + 2 * i + key_padding;
            piano_keys.put(midi_position, key);
        }
    }

    public static double getPianoWhiteKeyWidth() {
        return Screen.getPrimary().getBounds().getWidth() / WHITE_KEY_COUNT;
    }

    public static double getPianoBlackKeyWidth() {
        return getPianoWhiteKeyWidth() * 0.7;
    }
}
