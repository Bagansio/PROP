# IDE:
- IntelliJ

Version:

- JDK version
# GIT & GITLAB:

1. ## LABELS:
- **feature**
- **enhancement**
- **bugfix**
- **critical**
- **documentation**

  ### **feature**:
  A new characteristic of the project.

  **For example:**  a new Class, Interface ...
  ### **enhancement**:
  An improvement of a feature.

  **For example:** add some functions to a class.
  ### **bugfix**:
  When code works wrong
  ### **critical**:
  Something that needs a fast solution.

  **For example:**  the core works bad.
  ### **documentation**:
  When you add some specification to the project or code.

2. ## BRANCHES:

    label-issuenumber-briefdescription

    FEATURE-3-UserClass
    BUG-5-PersistanceNoSaveUsername
    DOCUMENTATION-10-TestingDocu  

   To create: 
        git checkout -b label-issuenumber-briefdescription
    To push:
        git push origin branch_name

    You must change to master branch after pushing it.
        git checkout -b master
        git pull

    To check in which branch you currently are.
        git branch (green indicates the one you are in).

3. ## COMMITS:

    Add only the necessary.
    Brief description.

    git commit

    You MUST be in your issue branch, then if you want to add some files to commit:
        git add file_route
        -git add . (all changes will be uploaded)-

        DANGER: you should use this commands just before doing the commit. 

        git commit

4. ## ISSUES:

    Needs a label.
    Brief description.
    If possible a list of tasks to do.





# CODING:

**All the code except documentation must be in English**

## VARIABLES:
Local variables, instance variables, and class variables are also written in **lowerCamelCase**.

Variable names should be short yet meaningful. The choice of a variable name should be mnemonic — that is, designed to indicate to the casual observer the intent of its use. One-character variable names should be avoided except for temporary "throwaway" variables. Common names for temporary variables are i, j, k, m, and n for integers; c, d, and e for characters.

Example:
- myCar
- mcQueen
- user
- i


## CONSTANTS:
Constants should be written in **uppercase** characters separated by **underscores**. Constant names may also contain digits if appropriate, but not as the first character.

Example:
- MAX_PARTICIPANTS
- PATH

## METHODS:
should be verbs in **lowerCamelCase**.

Example:
- doAlgorithm()
- countWinsOfMcQueen()

## CLASSES:
Class names should be nouns in **UpperCamelCase**, with the first letter of every word capitalised. Use whole words – avoid acronyms and abbreviations (unless the abbreviation is much more widely used than the long form, such as URL or HTML).

Example:
- UserFactory
- Car




