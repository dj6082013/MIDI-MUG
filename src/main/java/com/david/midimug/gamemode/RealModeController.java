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
package com.david.midimug.gamemode;

import com.david.midimug.handler.MidiInstruments;
import com.david.midimug.handler.Note;
import com.david.midimug.handler.SheetUtils;
import com.david.midimug.render.KeyboardRenderer;
import com.david.midimug.render.SheetRenderer;
import java.util.Arrays;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 *
 * @author david
 */
public class RealModeController extends AbstractModeController {

    private long[] note_status;

    public RealModeController() {
        super();
        note_status = new long[200];
        Arrays.fill(note_status, -1);
    }

    @Override
    public KeyFrame onNoteShow(Duration time, Pane pane, Rectangle bar, Note note) {
        KeyValue barY = new KeyValue(bar.layoutYProperty(), -bar.getHeight());
        return new KeyFrame(time, barY);
    }

    @Override
    public KeyFrame onNoteStart(Duration time, Pane pane, Rectangle bar, Note note) {
        KeyValue barY = new KeyValue(bar.layoutYProperty(), pane.getHeight() - bar.getHeight());
        return new KeyFrame(time, (ActionEvent t) -> {
            note_status[note.getKey()] = Math.round(time.toMillis());
        }, barY);
    }

    @Override
    public KeyFrame onNoteEnd(Duration time, Pane pane, Rectangle bar, Note note) {
        KeyValue barY = new KeyValue(bar.layoutYProperty(), pane.getHeight());
        return new KeyFrame(time, (ActionEvent t) -> {
            note_status[note.getKey()] = -1;
        }, barY);
    }

    @Override
    public void onUserPress(int key) {
        if (note_status[key] != -1) {
            note_status[key] = -1;

            long currentMillis = Math.round(SheetUtils.getCurrentTime().toMillis());
            long score = Math.max(0, 1000 - currentMillis - note_status[key]);
            SheetRenderer.renderCombo(Long.toString(score), Color.CORAL);
        }
        KeyboardRenderer.pressKey(key);
        MidiInstruments.noteOn(key);
    }

    @Override
    public void onUserRelease(int key) {
        KeyboardRenderer.releaseKey(key);
        MidiInstruments.noteOff(key);
    }

    @Override
    public long getScore() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
