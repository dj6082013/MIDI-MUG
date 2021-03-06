/*
 * Copyright (C) 2021 david
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
package tw.davidday.midimug.render;

import java.util.TimerTask;
import javafx.scene.control.ProgressBar;
import tw.davidday.midimug.handler.SheetUtils;

/**
 *
 * @author david
 */
public class ProgressRenderer extends TimerTask {

    private final ProgressBar progressBar;

    public ProgressRenderer(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    @Override
    public void run() {
        double currentTime = SheetUtils.getCurrentTime().toSeconds();
        double totalTime = SheetUtils.getTotalTime().toSeconds();
        double progress = currentTime / totalTime;
        progressBar.setProgress(progress);
    }

}
