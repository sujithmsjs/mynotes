package com.suji.pro1.note;

import com.suji.pro1.TextPad;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.Document;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

public class UndoRedo {

    private UndoHandler undoHandler = new UndoHandler();
    private UndoManager undoManager = new UndoManager();
    private RedoAction redoAction = null;
    private UndoAction undoAction = null;
    private JTextArea textArea;
    private Document editorPaneDoc;

    UndoRedo(JTextArea area) {
        textArea = area;

    }

    public void addUnduReduManager() {

        editorPaneDoc = textArea.getDocument();
        editorPaneDoc.addUndoableEditListener(undoHandler);

        KeyStroke undoKeyStroke = KeyStroke.getKeyStroke(
                KeyEvent.VK_Z, InputEvent.CTRL_MASK
        );

        KeyStroke redoKeyStroke = KeyStroke.getKeyStroke(
                KeyEvent.VK_Y, InputEvent.CTRL_MASK
        );

        undoAction = new UndoAction();
        textArea.getInputMap().put(undoKeyStroke, "UndoKeyStroke");
        textArea.getActionMap().put("UndoKeyStroke", undoAction);

        redoAction = new RedoAction();
        textArea.getInputMap().put(redoKeyStroke, "RedoKeyStroke");
        textArea.getActionMap().put("RedoKeyStroke", redoAction);
    }

    // End of variables declaration                   
    class UndoHandler implements UndoableEditListener {

        public void undoableEditHappened(UndoableEditEvent e) {
            undoManager.addEdit(e.getEdit());
            undoAction.update();
            redoAction.update();
        }
    }

    class UndoAction extends AbstractAction {

        public UndoAction() {
            super("Undo");
            setEnabled(false);
        }

        public void actionPerformed(ActionEvent e) {
            try {
                undoManager.undo();
            } catch (CannotUndoException ee) {
                ee.printStackTrace();
            }
            update();
            redoAction.update();
        }

        protected void update() {
            if (undoManager.canUndo()) {
                setEnabled(true);
                putValue(Action.NAME, undoManager.getUndoPresentationName());
            } else {
                setEnabled(false);
                putValue(Action.NAME, "Undo");
            }

        }
    }

    class RedoAction extends AbstractAction {

        public RedoAction() {
            super("Redo");
            setEnabled(false);
        }

        public void actionPerformed(ActionEvent e) {
            try {
                undoManager.redo();
            } catch (CannotRedoException ee) {
                ee.printStackTrace();
            }
            update();
            undoAction.update();
        }

        protected void update() {
            if (undoManager.canRedo()) {
                setEnabled(true);
                putValue(Action.NAME, undoManager.getRedoPresentationName());
            } else {
                setEnabled(false);
                putValue(Action.NAME, "Redo");
            }
        }
    }

}
