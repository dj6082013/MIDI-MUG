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
package com.david.midimug;

import com.david.midimug.render.KeyboardRenderer;
import com.david.midimug.render.LoadFileRenderer;
import com.david.midimug.render.MenuRenderer;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author david
 */
public class PrimaryController implements Initializable {

    @FXML
    private Menu instrumentsMenu;
    @FXML
    private Menu devicesMenu;
    @FXML
    private StackPane keyboard;
    @FXML
    private StackPane timeline;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        KeyboardRenderer.renderPianoKeys(keyboard);
        MenuRenderer.renderInstrumentsMenu(instrumentsMenu);
    }

    @FXML
    private void openFile() {
        File source = LoadFileRenderer.renderMidiChooser();
    }
}
