package com.mycompany.outliner.Model;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

public class Subsection {

    private String title;
    private List<Subsection> subsections;
    private Subsection parentSubsection;
    private Section parentSection;

    //Getters and Setters
    /*The method returns a string that combines the title of an object with a number of spaces based on the object's indentation level.
      The number of spaces is determined by a loop that concatenates a space character to a string variable for each iteration,
      with the maximum number of iterations determined by the object's "indentationLevel" attribute.
      The resulting string is then concatenated with the object's title and returned.
     */
    public String getTitle() {
        String spaces = "";
        for (int i = 0; i < this.indentationLevel; i++) {
            spaces += "  ";
        }
        return spaces + this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Subsection> getSubsections() {
        return subsections;
    }

    public Subsection getParentSubsection() {
        return parentSubsection;
    }

    public void setParentSubsection(Subsection parentSubsection) {
        this.parentSubsection = parentSubsection;
    }

    public void setParentSection(Section section) {
        this.parentSection = section;
    }

    public int getIndentationLevel() {
        return indentationLevel;
    }

    public void setIndentationLevel(int indentationLevel) {
        this.indentationLevel = indentationLevel;
    }
    private int indentationLevel;

    //Constructor to create a new subsection
    public Subsection(String title) {
        this.title = title;
        this.subsections = new ArrayList<>();
        this.indentationLevel = indentationLevel;

    }

    public void addSubsection(Subsection subsubsection) {
        if (subsections == null) {
            subsections = new ArrayList<>();
        }
        subsubsection.setIndentationLevel(this.getIndentationLevel() + 1);
        subsubsection.setParentSubsection(this);
        subsections.add(subsubsection);
    }

    public void removeSubsection(Subsection subsection) {
        subsections.remove(subsection);
    }

    public void deleteSubsection(int index) {
        subsections.remove(index);
    }

    public Subsection getSubsectionByTitle(String subsectionTitle) {
        for (Subsection subsection : subsections) {
            if (subsection.getTitle().equals(subsectionTitle)) {
                return subsection;
            } else {
                Subsection result = subsection.getSubsectionByTitle(subsectionTitle);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }

    /*This method returns a list of all subsubsections of a given section.
      It does this by first getting a list of all the immediate subsections of the section, 
      and then iterating over each of these subsections to get their immediate subsections (which are subsubsections of the original section).
      The method uses recursion to ensure that it retrieves all levels of subsubsections.
      Finally, it returns a list containing all the subsubsections it has found.
     */
    public List<Subsection> getSubsubsections() {
        List<Subsection> subsubsections = new ArrayList<>();
        List<Subsection> subsections = this.getSubsections();

        for (Subsection subsection : subsections) {
            List<Subsection> subsubsectionsOfSubsection = subsection.getSubsections();

            for (Subsection subsubsection : subsubsectionsOfSubsection) {
                subsubsections.add(subsubsection);
                subsubsections.addAll(subsubsection.getSubsubsections());
            }
        }
        return subsubsections;
    }

}
