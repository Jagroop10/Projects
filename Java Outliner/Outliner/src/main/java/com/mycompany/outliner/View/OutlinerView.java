package com.mycompany.outliner.View;

import com.mycompany.outliner.Controller.OutlinerController;
import com.mycompany.outliner.Model.Outliner;
import com.mycompany.outliner.Model.Section;
import com.mycompany.outliner.Model.Subsection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class OutlinerView extends JFrame {

    private Outliner outliner;

    private JTextArea textArea;
    private JMenuItem saveFile;

    private JMenuItem sectionMenuItem;
    private JMenuItem deleteSectionMenuItem;
    private JMenuItem editSectionMenuItem;

    private JMenuItem addTargetDateMenuItem;
    private JMenuItem editTargetDateMenuItem;
    private JMenuItem deleteTargetDateMenuItem;

    private JMenuItem addtagMenuItem;
    private JMenuItem deletetagMenuItem;
    private JMenuItem edittagMenuItem;

    private JMenuItem addpriorityMenuItem;
    private JMenuItem editpriorityMenuItem;
    private JMenuItem deletepriorityMenuItem;

    private JMenuItem addSubsectionMenuItem;
    private JMenuItem editSubsectionMenuItem;
    private JMenuItem deleteSubsectionMenuItem;

    private JMenuItem completeMenuItem;
    private JMenuItem incompleteMenuItem;

    private JTextField searchField;
    private JButton searchButton;

    public OutlinerView() {
        this.outliner = Outliner.getInstance("My Outliner", "A simple outliner");

        this.setSize(800, 800);
        this.setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(800, 700));
        this.add(scrollPane, BorderLayout.CENTER);

        JPanel searchPanel = new JPanel();
        searchField = new JTextField(20);
        searchPanel.add(new JLabel("Search: "));
        searchPanel.add(searchField);

        searchButton = new JButton("Search");
        searchButton.setActionCommand("Search");

        searchPanel.add(searchButton);
        this.add(searchPanel, BorderLayout.SOUTH);

        JMenuBar fileMenuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        fileMenuBar.add(fileMenu);

        JMenuItem quitMenuItem = new JMenuItem("Quit");
        fileMenu.add(quitMenuItem);
        saveFile = new JMenuItem("Save File");
        fileMenu.add(saveFile);
        this.setJMenuBar(fileMenuBar);

        JMenuBar sectionMenuBar = new JMenuBar();
        JMenu sectionMenu = new JMenu("Section");
        sectionMenuBar.add(sectionMenu);
        sectionMenuItem = new JMenuItem("Section");
        sectionMenu.add(sectionMenuItem);
        JMenuItem addSectionMenuItem = new JMenuItem("Add Section");
        sectionMenu.add(addSectionMenuItem);
        deleteSectionMenuItem = new JMenuItem("Delete Section");
        sectionMenu.add(deleteSectionMenuItem);
        editSectionMenuItem = new JMenuItem("Edit Section");
        sectionMenu.add(editSectionMenuItem);

        addTargetDateMenuItem = new JMenuItem("Add Target Date");
        sectionMenu.add(addTargetDateMenuItem);
        editTargetDateMenuItem = new JMenuItem("Edit Target Date");
        sectionMenu.add(editTargetDateMenuItem);
        deleteTargetDateMenuItem = new JMenuItem("Delete Target Date");
        sectionMenu.add(deleteTargetDateMenuItem);

        addtagMenuItem = new JMenuItem("Add Tag");
        sectionMenu.add(addtagMenuItem);
        deletetagMenuItem = new JMenuItem("Delete Tag");
        sectionMenu.add(deletetagMenuItem);
        edittagMenuItem = new JMenuItem("Edit Tag");
        sectionMenu.add(edittagMenuItem);

        addpriorityMenuItem = new JMenuItem("Add Priority");
        sectionMenu.add(addpriorityMenuItem);
        editpriorityMenuItem = new JMenuItem("Edit Priority");
        sectionMenu.add(editpriorityMenuItem);
        deletepriorityMenuItem = new JMenuItem("Delete Priority");
        sectionMenu.add(deletepriorityMenuItem);

        addSubsectionMenuItem = new JMenuItem("Add Subsection");
        sectionMenu.add(addSubsectionMenuItem);
        editSubsectionMenuItem = new JMenuItem("Edit Subsection");
        sectionMenu.add(editSubsectionMenuItem);
        deleteSubsectionMenuItem = new JMenuItem("Delete Subsection");
        sectionMenu.add(deleteSubsectionMenuItem);

        completeMenuItem = new JMenuItem("Mark as Complete");
        sectionMenu.add(completeMenuItem);
        incompleteMenuItem = new JMenuItem("Mark as Incomplete");
        sectionMenu.add(incompleteMenuItem);

        this.add(sectionMenuBar, BorderLayout.NORTH);

        OutlinerController controller = new OutlinerController(this);

        saveFile.addActionListener(controller);
        addSectionMenuItem.addActionListener(controller);
        deleteSectionMenuItem.addActionListener(controller);
        editSectionMenuItem.addActionListener(controller);

        addTargetDateMenuItem.addActionListener(controller);
        editTargetDateMenuItem.addActionListener(controller);
        deleteTargetDateMenuItem.addActionListener(controller);

        addtagMenuItem.addActionListener(controller);
        deletetagMenuItem.addActionListener(controller);
        edittagMenuItem.addActionListener(controller);

        addpriorityMenuItem.addActionListener(controller);
        editpriorityMenuItem.addActionListener(controller);
        deletepriorityMenuItem.addActionListener(controller);

        addSubsectionMenuItem.addActionListener(controller);
        editSubsectionMenuItem.addActionListener(controller);
        deleteSubsectionMenuItem.addActionListener(controller);

        completeMenuItem.addActionListener(controller);
        incompleteMenuItem.addActionListener(controller);

        quitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchTerm = searchField.getText();
                controller.updateSectionTextArea(false, searchTerm);
            }
        });

        saveFile.setActionCommand("Save File");
        addSectionMenuItem.setActionCommand("Add Section");
        deleteSectionMenuItem.setActionCommand("Delete Section");
        editSectionMenuItem.setActionCommand("Edit Section");

        addTargetDateMenuItem.setActionCommand("Add Target Date");
        editTargetDateMenuItem.setActionCommand("Edit Target Date");
        deleteTargetDateMenuItem.setActionCommand("Delete Target Date");

        addtagMenuItem.setActionCommand("Add Tag");
        deletetagMenuItem.setActionCommand("Delete Tag");
        edittagMenuItem.setActionCommand("Edit Tag");

        addpriorityMenuItem.setActionCommand("Add Priority");
        editpriorityMenuItem.setActionCommand("Edit Priority");
        deletepriorityMenuItem.setActionCommand("Delete Priority");

        addSubsectionMenuItem.setActionCommand("Add Subsection");
        editSubsectionMenuItem.setActionCommand("Edit Subsection");
        deleteSubsectionMenuItem.setActionCommand("Delete Subsection");

        completeMenuItem.setActionCommand("Mark as Complete");
        incompleteMenuItem.setActionCommand("Mark as Incomplete");

        quitMenuItem.setActionCommand("Quit");

    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public Outliner getOutliner() {
        return outliner;
    }

    public void appendToTextArea(String text) {
        textArea.append(text + "\n");
    }

    public JMenuItem getSectionMenuItem() {
        return sectionMenuItem;
    }

    public JMenuItem getDeleteSectionMenuItem() {
        return deleteSectionMenuItem;
    }

    public JMenuItem getAddTargetDateMenuItem() {
        return addTargetDateMenuItem;
    }

    public JMenuItem geteditSectionMenuItem() {
        return editSectionMenuItem;
    }
}
