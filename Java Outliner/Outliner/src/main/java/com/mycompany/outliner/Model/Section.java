package com.mycompany.outliner.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Section {

    private String id;
    private String priority;
    private String title;
    private boolean isCompleted;
    private List<String> tag;
    private List<Subsection> subsections;
    private int indentationLevel;
    private Date targetDate;

    //Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPriority() {
        if (priority != null && !priority.isEmpty()) {
            return priority;
        } else {
            return "No priority set";
        }
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getTag() {
        return tag;
    }

    public void setTag(List<String> tag) {
        this.tag = tag;
    }

    public boolean getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public void addTag(String tag) {
        this.tag.add(tag);
    }

    public void removeTag(String tag) {
        this.tag.remove(tag);
    }

    public void editTag(String oldTag, String newTag) {
        int index = tag.indexOf(oldTag);
        if (index >= 0) {
            tag.set(index, newTag);
        }
    }

    public void clearTags() {
        tag.clear();
    }

    public void markSectionAsCompleted() {
        this.isCompleted = true;
    }

    public List<Subsection> getSubsections() {
        return subsections;
    }

    public void setSubsections(List<Subsection> subsections) {
        this.subsections = subsections;
    }

    public void setTargetDate(Date targetDate) {
        this.targetDate = targetDate;
    }

    public Date getTargetDate() {
        return targetDate;
    }

    public boolean isCompleted() {
        return this.isCompleted;
    }

    public void setCompleted(boolean completed) {
        this.isCompleted = completed;
    }

    //Section Constructor
    public Section(String title) {
        this.title = title;
        this.targetDate = null;
        this.tag = new ArrayList<>();
        this.priority = "";
        this.subsections = new ArrayList<>();
    }

    //Edits the currect Subsection
    public void editSubsection(String subsectionTitle, String newTitle) {
        Subsection subsection = getSubsectionByTitle(subsectionTitle);
        if (subsection != null) {
            subsection.setTitle(newTitle);
        }
    }

    public void removeSubsection(Subsection subsection) {
        subsections.remove(subsection);
    }

    public Subsection getSubsectionByTitle(String title) {
        for (Subsection subsection : subsections) {
            if (subsection.getTitle().equals(title)) {
                return subsection;
            }
        }
        return null;
    }

    /*The first method creates a new Subsection object internally and adds it to the list,
      while the second method takes an existing Subsection object and adds it to the list,
     setting its parent section and indentation level appropriately.
     */
    public void addSubsection(String title) {
        subsections.add(new Subsection(title));
    }

    public void addSubsection(Subsection subsection) {
        if (subsections == null) {
            subsections = new ArrayList<>();
        }
        subsection.setParentSection(this);
        subsection.setIndentationLevel(this.indentationLevel + 1);
        subsections.add(subsection);
    }

}
