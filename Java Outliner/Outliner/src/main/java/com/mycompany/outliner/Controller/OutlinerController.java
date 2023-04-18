package com.mycompany.outliner.Controller;

import com.mycompany.outliner.View.OutlinerView;
import com.mycompany.outliner.Model.Outliner;
import com.mycompany.outliner.Model.Section;
import com.mycompany.outliner.Model.Subsection;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class OutlinerController implements ActionListener {

    public static void main(String[] args) {
        OutlinerView view = new OutlinerView();
        OutlinerController controller = new OutlinerController(view);
        view.setVisible(true);
    }

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    private final OutlinerView view;
    private final Outliner outliner;

    public OutlinerController(OutlinerView view) {
        this.view = view;
        this.outliner = view.getOutliner();
        this.view.getSectionMenuItem().addActionListener(this);
        this.view.getDeleteSectionMenuItem().addActionListener(this);
        this.view.geteditSectionMenuItem().addActionListener(this);

    }

    List<String> sectionTitles = new ArrayList<>();
    Section section = new Section("");

    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        switch (actionCommand) {

            case "Add Section":
                String sectionTitle = JOptionPane.showInputDialog(view, "Enter section title:", "Add Section", JOptionPane.PLAIN_MESSAGE);
                if (sectionTitle != null && !sectionTitle.trim().isEmpty()) {
                    String targetDateString = JOptionPane.showInputDialog(view, "Enter target date (" + DATE_FORMAT + "):", "Add Section", JOptionPane.PLAIN_MESSAGE);
                    try {
                        Date targetDate = new SimpleDateFormat(DATE_FORMAT).parse(targetDateString);
                        outliner.createSection(sectionTitle, targetDate);

                        updateSectionTextArea(false, null);
                    } catch (ParseException ex) {
                        JOptionPane.showMessageDialog(view, "Invalid date format. Please enter date in " + DATE_FORMAT + " format.", "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(view, "Error while creating section: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;

            case "Edit Section":

                List<String> sectionTitlesToEdit = new ArrayList<>();
                for (Section section : outliner.getSections()) {
                    sectionTitlesToEdit.add(section.getTitle());
                }

                Object sectionToEdit = JOptionPane.showInputDialog(view, "Select section to edit:", "Edit Section", JOptionPane.PLAIN_MESSAGE, null, sectionTitlesToEdit.toArray(), sectionTitlesToEdit.get(0));
                if (sectionToEdit != null) {
                    String newTitle = JOptionPane.showInputDialog(view, "Enter new section title:", "Edit Section", JOptionPane.PLAIN_MESSAGE);

                    outliner.editSection((String) sectionToEdit, newTitle);
                    updateSectionTextArea(false, null);
                }
                break;
            case "Delete Section":

                List<String> sectionTitlesToDelete = new ArrayList<>();
                for (Section section : outliner.getSections()) {
                    sectionTitlesToDelete.add(section.getTitle());
                }

                Object sectionToDelete = JOptionPane.showInputDialog(view, "Select section to delete:", "Delete Section", JOptionPane.PLAIN_MESSAGE, null, sectionTitlesToDelete.toArray(), sectionTitlesToDelete.get(0));
                if (sectionToDelete != null) {
                    outliner.deleteSection((String) sectionToDelete);
                    updateSectionTextArea(false, null);
                }
                break;
            case "Add Target Date":
            
                for (Section section : outliner.getSections()) {
                    sectionTitles.add(section.getTitle());
                }

                Object sectionToDateAdd = JOptionPane.showInputDialog(view, "Select section to edit:", "Edit Target Date", JOptionPane.PLAIN_MESSAGE, null, sectionTitles.toArray(), sectionTitles.get(0));
                if (sectionToDateAdd != null) {

                    Section section = outliner.getSectionByTitle((String) sectionToDateAdd);

                    String newTargetDateString = JOptionPane.showInputDialog(view, "Enter new target date (YYYY-MM-DD):", section.getTargetDate());
                    if (newTargetDateString != null) {

                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        Date newTargetDate = null;
                        try {
                            newTargetDate = dateFormat.parse(newTargetDateString);
                        } catch (ParseException ex) {
                            Logger.getLogger(OutlinerController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        section.setTargetDate(newTargetDate);
                        updateSectionTextArea(false, null);
                    }
                }
                break;

            case "Edit Target Date":

                for (Section section : outliner.getSections()) {
                    sectionTitles.add(section.getTitle());
                }

                Object sectionToDateEdit = JOptionPane.showInputDialog(view, "Select section to edit:", "Edit Target Date", JOptionPane.PLAIN_MESSAGE, null, sectionTitles.toArray(), sectionTitles.get(0));
                if (sectionToDateEdit != null) {

                    Section section = outliner.getSectionByTitle((String) sectionToDateEdit);

                    String newTargetDateString = JOptionPane.showInputDialog(view, "Enter new target date (YYYY-MM-DD):", section.getTargetDate());
                    if (newTargetDateString != null) {

                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        Date newTargetDate = null;
                        try {
                            newTargetDate = dateFormat.parse(newTargetDateString);
                        } catch (ParseException ex) {
                            Logger.getLogger(OutlinerController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        section.setTargetDate(newTargetDate);
                        updateSectionTextArea(false, null);
                    }
                }
                break;
            case "Delete Target Date":

                List<String> sectionTitlesToDeleteDate = new ArrayList<>();
                for (Section section : outliner.getSections()) {
                    if (section.getTargetDate() != null) {
                        sectionTitlesToDeleteDate.add(section.getTitle());
                    }
                }

                Object sectionToDeleteTargetDate = JOptionPane.showInputDialog(view, "Select section to delete target date from:", "Delete Target Date", JOptionPane.PLAIN_MESSAGE, null, sectionTitlesToDeleteDate.toArray(), sectionTitlesToDeleteDate.get(0));
                if (sectionToDeleteTargetDate != null) {

                    Section section = outliner.getSectionByTitle((String) sectionToDeleteTargetDate);

                    section.setTargetDate(null);
                    updateSectionTextArea(false, null);
                }
                break;
            case "Add Tag":
                List<String> sectionTitlesToAddTag = new ArrayList<>();
                for (Section section : outliner.getSections()) {
                    sectionTitlesToAddTag.add(section.getTitle());
                }

                Object sectionToAddTag = JOptionPane.showInputDialog(view, "Select section to add a tag to:", "Add Tag", JOptionPane.PLAIN_MESSAGE, null, sectionTitlesToAddTag.toArray(), sectionTitlesToAddTag.get(0));
                if (sectionToAddTag != null) {

                    Section section = outliner.getSectionByTitle((String) sectionToAddTag);

                    String newTag = JOptionPane.showInputDialog(view, "Enter new tag:", "");
                    if (newTag != null) {

                        section.addTag(newTag);
                        updateSectionTextArea(false, null);
                    }
                }
                break;
            case "Delete Tag":

                List<String> sectionTitlesToDeleteTag = new ArrayList<>();
                for (Section section : outliner.getSections()) {
                    sectionTitlesToDeleteTag.add(section.getTitle());
                }

                Object sectionToDeleteTag = JOptionPane.showInputDialog(view, "Select section to delete a tag from:", "Delete Tag", JOptionPane.PLAIN_MESSAGE, null, sectionTitlesToDeleteTag.toArray(), sectionTitlesToDeleteTag.get(0));
                if (sectionToDeleteTag != null) {

                    Section section = outliner.getSectionByTitle((String) sectionToDeleteTag);
                    List<String> tags = section.getTag();
                    if (!tags.isEmpty()) {

                        Object tagToDelete = JOptionPane.showInputDialog(view, "Select tag to delete:", "Delete Tag", JOptionPane.PLAIN_MESSAGE, null, tags.toArray(), tags.get(0));
                        if (tagToDelete != null) {

                            section.removeTag((String) tagToDelete);
                            updateSectionTextArea(false, null);
                        }
                    } else {
                        JOptionPane.showMessageDialog(view, "No tags to delete for this section.", "Delete Tag", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                break;
            case "Edit Tag":

                List<String> sectionTitlesToEditTag = new ArrayList<>();
                for (Section section : outliner.getSections()) {
                    sectionTitlesToEditTag.add(section.getTitle());
                }

                Object sectionToEditTag = JOptionPane.showInputDialog(view, "Select section to edit a tag from:", "Edit Tag", JOptionPane.PLAIN_MESSAGE, null, sectionTitlesToEditTag.toArray(), sectionTitlesToEditTag.get(0));
                if (sectionToEditTag != null) {

                    Section section = outliner.getSectionByTitle((String) sectionToEditTag);
                    List<String> tags = section.getTag();
                    if (!tags.isEmpty()) {

                        Object tagToEdit = JOptionPane.showInputDialog(view, "Select tag to edit:", "Edit Tag", JOptionPane.PLAIN_MESSAGE, null, tags.toArray(), tags.get(0));
                        if (tagToEdit != null) {

                            String newTag = JOptionPane.showInputDialog(view, "Enter new tag:", "Edit Tag", JOptionPane.PLAIN_MESSAGE);
                            if (newTag != null && !newTag.isEmpty()) {

                                section.editTag((String) tagToEdit, newTag);
                                updateSectionTextArea(false, null);
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(view, "No tags to edit for this section.", "Edit Tag", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                break;
            case "Add Priority":

                List<String> sectionTitlesToAddPriority = new ArrayList<>();
                for (Section section : outliner.getSections()) {
                    sectionTitlesToAddPriority.add(section.getTitle());
                }

                Object sectionToAddPriority = JOptionPane.showInputDialog(view, "Select section to add priority to:", "Add Priority", JOptionPane.PLAIN_MESSAGE, null, sectionTitlesToAddPriority.toArray(), sectionTitlesToAddPriority.get(0));
                if (sectionToAddPriority != null) {

                    Section section = outliner.getSectionByTitle((String) sectionToAddPriority);

                    String priorityStr = JOptionPane.showInputDialog(view, "Enter priority level (1-5):", "Add Priority", JOptionPane.PLAIN_MESSAGE);
                    try {

                        int priority = Integer.parseInt(priorityStr);

                        if (priority < 1 || priority > 5) {
                            JOptionPane.showMessageDialog(view, "Invalid priority level. Please enter a number between 1 and 5.", "Add Priority", JOptionPane.ERROR_MESSAGE);
                        } else {

                            section.setPriority(Integer.toString(priority));
                            updateSectionTextArea(false, null);
                        }
                    } catch (NumberFormatException c) {
                        JOptionPane.showMessageDialog(view, "Invalid input. Please enter a number between 1 and 5.", "Add Priority", JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;
            case "Edit Priority":

                List<String> sectionTitlesToEditPriority = new ArrayList<>();
                for (Section section : outliner.getSections()) {
                    sectionTitlesToEditPriority.add(section.getTitle());
                }

                Object sectionToEditPriority = JOptionPane.showInputDialog(view, "Select section to edit priority:", "Edit Priority", JOptionPane.PLAIN_MESSAGE, null, sectionTitlesToEditPriority.toArray(), sectionTitlesToEditPriority.get(0));
                if (sectionToEditPriority != null) {

                    Section section = outliner.getSectionByTitle((String) sectionToEditPriority);

                    String currentPriority = section.getPriority();
                    String newPriorityString = JOptionPane.showInputDialog(view, "Enter new priority (1-5):", currentPriority);
                    if (newPriorityString != null) {

                        try {
                            int newPriority = Integer.parseInt(newPriorityString);
                            if (newPriority < 1 || newPriority > 5) {
                                JOptionPane.showMessageDialog(view, "Priority must be between 1-5", "Error", JOptionPane.ERROR_MESSAGE);
                            } else {

                                section.setPriority(Integer.toString(newPriority));
                                updateSectionTextArea(false, null);
                            }
                        } catch (NumberFormatException r) {
                            JOptionPane.showMessageDialog(view, "Invalid input. Please enter a number between 1 and 5.", "Edit Priority", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
                break;

            case "Delete Priority":

                List<String> sectionTitlesToDeletePriority = new ArrayList<>();
                for (Section section : outliner.getSections()) {
                    sectionTitlesToDeletePriority.add(section.getTitle());
                }

                Object sectionToDeletePriority = JOptionPane.showInputDialog(view, "Select section to delete priority:", "Delete Priority", JOptionPane.PLAIN_MESSAGE, null, sectionTitlesToDeletePriority.toArray(), sectionTitlesToDeletePriority.get(0));
                if (sectionToDeletePriority != null) {

                    Section section = outliner.getSectionByTitle((String) sectionToDeletePriority);

                    section.setPriority("");
                    updateSectionTextArea(false, null);
                }
                break;
            case "Add Subsection":

                List<String> sectionTitlesToAddSubsection = new ArrayList<>();
                for (Section section : outliner.getSections()) {
                    sectionTitlesToAddSubsection.add(section.getTitle());
                }

                Object sectionToAddSubsection = JOptionPane.showInputDialog(view, "Select section to add a subsection to:", "Add Subsection", JOptionPane.PLAIN_MESSAGE, null, sectionTitlesToAddSubsection.toArray(), sectionTitlesToAddSubsection.get(0));
                if (sectionToAddSubsection != null) {

                    Section section = outliner.getSectionByTitle((String) sectionToAddSubsection);

                    Object[] options = {"New Subsection", "Add Sub-Subsection"};
                    int choice = JOptionPane.showOptionDialog(view, "Do you want to create a new subsection or add a sub-subsection to an existing subsection?", "Add Subsection", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

                    if (choice == JOptionPane.YES_OPTION) {

                        String subsectionTitle = JOptionPane.showInputDialog(view, "Enter subsection title:", "Add Subsection", JOptionPane.PLAIN_MESSAGE);
                        if (subsectionTitle != null && !subsectionTitle.trim().isEmpty()) {
                            section.addSubsection(new Subsection(subsectionTitle));
                            updateSectionTextArea(false, null);
                        }
                    } else if (choice == JOptionPane.NO_OPTION) {

                        List<String> subsectionTitlesToAddSubsection = new ArrayList<>();
                        for (Subsection subsection : section.getSubsections()) {
                            subsectionTitlesToAddSubsection.add(subsection.getTitle());
                        }

                        Object subsectionToAddSubsection = JOptionPane.showInputDialog(view, "Select subsection to add a sub-subsection to:", "Add Sub-Subsection", JOptionPane.PLAIN_MESSAGE, null, subsectionTitlesToAddSubsection.toArray(), subsectionTitlesToAddSubsection.get(0));
                        if (subsectionToAddSubsection != null) {

                            Subsection subsection = section.getSubsectionByTitle((String) subsectionToAddSubsection);

                            String subsubsectionTitle = JOptionPane.showInputDialog(view, "Enter sub-subsection title:", "Add Sub-Subsection", JOptionPane.PLAIN_MESSAGE);
                            if (subsubsectionTitle != null && !subsubsectionTitle.trim().isEmpty()) {
                                subsection.addSubsection(new Subsection(subsubsectionTitle));
                                updateSectionTextArea(false, null);
                            }
                        }
                    }
                }
                break;
            case "Edit Subsection":

                List<String> sectionAndSubsectionTitlesToEdit = new ArrayList<>();
                for (Section section : outliner.getSections()) {
                    sectionAndSubsectionTitlesToEdit.add(section.getTitle());
                    for (Subsection subsection : section.getSubsections()) {
                        sectionAndSubsectionTitlesToEdit.add(section.getTitle() + " - " + subsection.getTitle());
                        for (Subsection subsubsection : subsection.getSubsections()) {
                            sectionAndSubsectionTitlesToEdit.add(section.getTitle() + " - " + subsection.getTitle() + " - " + subsubsection.getTitle());
                        }
                    }
                }

                Object subsectionOrSubsubsectionToEdit = JOptionPane.showInputDialog(view, "Select subsection or sub-subsection to edit:", "Edit Subsection", JOptionPane.PLAIN_MESSAGE, null, sectionAndSubsectionTitlesToEdit.toArray(), sectionAndSubsectionTitlesToEdit.get(0));
                if (subsectionOrSubsubsectionToEdit != null) {
                    String[] titles = ((String) subsectionOrSubsubsectionToEdit).split(" - ");
                    Section section = outliner.getSectionByTitle(titles[0]);

                    switch (titles.length) {
                        case 1 ->
                            JOptionPane.showMessageDialog(view, "Cannot edit a section.", "Error", JOptionPane.ERROR_MESSAGE);
                        case 2 -> {

                            Subsection subsection = section.getSubsectionByTitle(titles[1]);

                            String newTitle = String.valueOf(JOptionPane.showInputDialog(view, "Enter new title:", "Edit Subsection", JOptionPane.PLAIN_MESSAGE, null, null, subsection.getTitle()));
                            if (newTitle != null && !newTitle.trim().isEmpty()) {

                                subsection.setTitle(newTitle);
                                updateSectionTextArea(false, null);
                            }
                        }
                        case 3 -> {

                            Subsection subsection = section.getSubsectionByTitle(titles[1]);
                            Subsection subsubsection = subsection.getSubsectionByTitle(titles[2]);

                            String newTitle = String.valueOf(JOptionPane.showInputDialog(view, "Enter new title:", "Edit Subsection", JOptionPane.PLAIN_MESSAGE, null, null, subsubsection.getTitle()));
                            if (newTitle != null && !newTitle.trim().isEmpty()) {

                                subsubsection.setTitle(newTitle);
                                updateSectionTextArea(false, null);
                            }
                        }
                        default -> {
                        }
                    }
                }

                break;
            case "Delete Subsection":

                List<String> sectionTitlesToDeleteSubsection = new ArrayList<>();
                for (Section section : outliner.getSections()) {
                    sectionTitlesToDeleteSubsection.add(section.getTitle());
                }

                Object sectionToDeleteSubsection = JOptionPane.showInputDialog(view, "Select section to delete a subsection from:", "Delete Subsection", JOptionPane.PLAIN_MESSAGE, null, sectionTitlesToDeleteSubsection.toArray(), sectionTitlesToDeleteSubsection.get(0));
                if (sectionToDeleteSubsection != null) {

                    Section section = outliner.getSectionByTitle((String) sectionToDeleteSubsection);

                    List<String> subsectionTitlesToDeleteSubsection = new ArrayList<>();
                    for (Subsection subsection : section.getSubsections()) {
                        subsectionTitlesToDeleteSubsection.add(subsection.getTitle());
                    }

                    if (subsectionTitlesToDeleteSubsection.isEmpty()) {
                        JOptionPane.showMessageDialog(view, "There are no subsections to delete.", "Delete Subsection", JOptionPane.INFORMATION_MESSAGE);
                    } else {

                        Object[] options = {"Subsection", "Sub-Subsection"};
                        int choice = JOptionPane.showOptionDialog(view, "Do you want to delete a subsection or a sub-subsection?", "Delete Subsection", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

                        if (choice == JOptionPane.YES_OPTION) {

                            Object subsectionToDelete = JOptionPane.showInputDialog(view, "Select subsection to delete:", "Delete Subsection", JOptionPane.PLAIN_MESSAGE, null, subsectionTitlesToDeleteSubsection.toArray(), subsectionTitlesToDeleteSubsection.get(0));
                            if (subsectionToDelete != null) {

                                Subsection subsection = section.getSubsectionByTitle((String) subsectionToDelete);

                                section.removeSubsection(subsection);
                                updateSectionTextArea(false, null);
                            }
                        } else if (choice == JOptionPane.NO_OPTION) {

                            List<String> subsubsectionTitlesToDeleteSubsection = new ArrayList<>();
                            for (Subsection subsection : section.getSubsections()) {
                                for (Subsection subsubsection : subsection.getSubsections()) {
                                    subsubsectionTitlesToDeleteSubsection.add(subsection.getTitle() + " > " + subsubsection.getTitle());
                                }
                            }

                            if (subsubsectionTitlesToDeleteSubsection.isEmpty()) {
                                JOptionPane.showMessageDialog(view, "There are no sub-subsections to delete.", "Delete Subsection", JOptionPane.INFORMATION_MESSAGE);
                            } else {

                                Object subsubsectionToDelete = JOptionPane.showInputDialog(view, "Select sub-subsection to delete:", "Delete Subsection", JOptionPane.PLAIN_MESSAGE, null, subsubsectionTitlesToDeleteSubsection.toArray(), subsubsectionTitlesToDeleteSubsection.get(0));
                                if (subsubsectionToDelete != null) {

                                    String[] parts = ((String) subsubsectionToDelete).split(" > ");
                                    String subsectionTitle = parts[0];
                                    String subsubsectionTitle = parts[1];

                                    Subsection subsection = section.getSubsectionByTitle(subsectionTitle);

                                    Subsection subsubsection = subsection.getSubsectionByTitle(subsubsectionTitle);

                                    subsection.removeSubsection(subsubsection);
                                    updateSectionTextArea(false, null);
                                }
                            }
                        }
                    }
                }
                break;
            case "Mark as Complete":

                List<String> sectionTitlesToMarkasComplete = new ArrayList<>();
                for (Section section : outliner.getSections()) {
                    sectionTitlesToMarkasComplete.add(section.getTitle());
                }
                String[] sectionTitles1 = sectionTitlesToMarkasComplete.toArray(String[]::new);
                String completeTitle = (String) JOptionPane.showInputDialog(
                        null,
                        "Select a section to mark as complete:",
                        "Mark as Complete",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        sectionTitles1,
                        null);

                if (completeTitle != null) {
                    outliner.markSectionAsComplete(completeTitle);
                    JOptionPane.showMessageDialog(null, "Section '" + completeTitle + "' marked as complete.", "Mark as Complete", JOptionPane.INFORMATION_MESSAGE);
                    updateSectionTextArea(false, null);
                }
                break;
            case "Mark as Incomplete":

                List<String> sectionTitlesToMarkasIncomplete = new ArrayList<>();
                for (Section section : outliner.getSections()) {
                    if (section.isCompleted()) {
                        sectionTitlesToMarkasIncomplete.add(section.getTitle());
                    }
                }
                String[] sectionTitles2 = sectionTitlesToMarkasIncomplete.toArray(new String[0]);
                String incompleteTitle = (String) JOptionPane.showInputDialog(
                        null,
                        "Select a section to mark as incomplete:",
                        "Mark as Incomplete",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        sectionTitles2,
                        null);

                if (incompleteTitle != null) {
                    outliner.markSectionAsIncomplete(incompleteTitle);
                    JOptionPane.showMessageDialog(null, "Section '" + incompleteTitle + "' marked as incomplete.", "Mark as Incomplete", JOptionPane.INFORMATION_MESSAGE);
                    updateSectionTextArea(false, null);
                }
                break;
            case "Save File":
                String saveResult = updateSectionTextArea(true, null);
                System.out.println(saveResult);
                break;
        }
    }

    public String updateSectionTextArea(boolean saveToFile, String searchTerm) {
        StringBuilder sb = new StringBuilder();

        for (Section section : outliner.getSections()) {
            String sectionTitle = section.getTitle();
            if (searchTerm != null && !searchTerm.isEmpty() && !sectionTitle.contains(searchTerm)) {
                continue;
            }
            sb.append("Title: ").append(sectionTitle);
            if (section.getTargetDate() != null) {
                sb.append(" complete by ").append(new SimpleDateFormat("dd/MM/yyyy").format(section.getTargetDate()));
            }
            List<String> tags = section.getTag();
            if (!tags.isEmpty()) {
                sb.append(" assigned to ");
                for (String tag : tags) {
                    sb.append(tag).append(", ");
                }
                sb.delete(sb.length() - 2, sb.length());
            }
            if (section.getPriority() != null) {
                sb.append(" priority ").append(section.getPriority());
            }
            if (section.isCompleted()) {
                sb.append(" (Completed)");
            } else {
                sb.append(" (Not Completed)");
            }
            sb.append("\n");

            List<Subsection> subsections = section.getSubsections();
            if (subsections != null && !subsections.isEmpty()) {
                sb.append(">");
                for (Subsection subsection : subsections) {
                    appendSubsectionToStringBuilder(subsection, sb, 1);
                }
            }

            sb.append("\n");
        }
        String output = sb.toString();
        if (saveToFile) {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showSaveDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                if (!fileToSave.getName().endsWith(".txt")) {
                    fileToSave = new File(fileToSave.getAbsolutePath() + ".txt");
                }
                try (PrintWriter writer = new PrintWriter(fileToSave)) {
                    writer.write(output);
                    writer.flush();
                    JOptionPane.showMessageDialog(view, "File saved successfully!");

                } catch (IOException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(view, "Error while saving the file.");
                }
            }
        } else {

            view.getTextArea().setText(output);
        }
        return output;
    }

    private void appendSubsectionToStringBuilder(Subsection subsection, StringBuilder sb, int indentLevel) {
        for (int i = 0; i < indentLevel; i++) {
            sb.append("  ");
        }
        sb.append(">").append(subsection.getTitle());
        sb.append("\n");

        List<Subsection> subSubsections = subsection.getSubsections();
        if (subSubsections != null && !subSubsections.isEmpty()) {
            for (Subsection subSubsection : subSubsections) {
                appendSubsectionToStringBuilder(subSubsection, sb, indentLevel + 1);
            }
        }
    }

}

