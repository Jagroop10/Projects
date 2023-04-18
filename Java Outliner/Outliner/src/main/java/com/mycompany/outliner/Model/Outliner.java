package com.mycompany.outliner.Model;

import com.mycompany.outliner.Model.Section;
import com.mycompany.outliner.Model.Subsection;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Outliner {

    private String title;
    private String description;
    private boolean isComplete;
    private List<Section> sections;
    private List<String> tags;
    private static Outliner instance = null;

    //Getter and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    public List<Section> getSections() {
        return sections;
    }

    //Creates a Constructor for Outliner object
    private Outliner(String title, String description) {
        this.title = title;
        this.description = description;
        this.isComplete = false;
        this.sections = new ArrayList<>();
        this.tags = new ArrayList<>();
    }

    //Creates instance of Outliner Class
    public static Outliner getInstance(String title, String description) {
        if (instance == null) {
            instance = new Outliner(title, description);
        }
        return instance;
    }

    public static Outliner getInstance() {
        return new Outliner("", "");
    }

    //Section
    public void createSection(String sectionName, Date targetDate) {
        Section section = new Section(sectionName);
        if (targetDate != null) {
            section.setTargetDate(targetDate);
        }
        sections.add(section);
    }

    public void editSection(String sectionTitle, String newTitle) {
        Section section = getSectionByTitle(sectionTitle);
        if (section != null) {
            section.setTitle(newTitle);
        }
    }

    public boolean deleteSection(String sectionTitle) {
        for (Section section : sections) {
            if (section.getTitle().equals(sectionTitle)) {
                sections.remove(section);
                return true;
            }
        }
        return false;
    }

    public List<Section> getSectionsByTag(String tag) {
        List<Section> sectionsWithTag = new ArrayList<>();
        for (Section section : sections) {
            if (section.getTag().contains(tag)) {
                sectionsWithTag.add(section);
            }
        }
        return sectionsWithTag;
    }

    public Section getSectionByTitle(String sectionTitle) {
        for (Section section : sections) {
            if (section.getTitle().equals(sectionTitle)) {
                return section;
            }
        }
        return null;
    }

    public void markSectionAsComplete(String sectionTitle) {
        Section section = getSectionByTitle(sectionTitle);
        if (section != null) {
            section.setCompleted(true);
        }
    }

    public void markSectionAsIncomplete(String sectionTitle) {
        Section section = getSectionByTitle(sectionTitle);
        if (section != null) {
            section.setCompleted(false);
        }
    }

    //Subsection
    public void addSubsection(Object parent, Subsection newSubsection) {
        if (parent instanceof Section) {
            ((Section) parent).addSubsection(newSubsection);
        } else if (parent instanceof Subsection) {
            ((Subsection) parent).addSubsection(newSubsection);
        } else {
            throw new IllegalArgumentException("Parent object must be a Section or Subsection.");
        }
    }

    public Subsection getSubsectionByTitle(String title) {
        for (Section section : sections) {
            for (Subsection subsection : section.getSubsections()) {
                if (subsection.getTitle().equals(title)) {
                    return subsection;
                }
            }
        }
        return null;
    }

    //SearchBar
    public List<Section> filterSections(String query) {
        List<Section> filteredSections = new ArrayList<>();

        for (Section section : sections) {
            if (section.getTitle().toLowerCase().contains(query.toLowerCase())) {
                filteredSections.add(section);
            }
        }

        return filteredSections;
    }

}
