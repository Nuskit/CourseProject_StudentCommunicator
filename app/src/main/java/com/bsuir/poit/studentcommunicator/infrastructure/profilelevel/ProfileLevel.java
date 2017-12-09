package com.bsuir.poit.studentcommunicator.infrastructure.profilelevel;


public enum ProfileLevel {
    None(-1),
    Student(0),
    Headman(1),
    DeputyHeadman(2),
    Teacher(3),
    Administration(4);

    private ProfileLevel(int id) {
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public boolean compare(int checkedId) {
        return checkedId == id;
    }

    public static ProfileLevel GetValue(int levelId) {
        ProfileLevel[] profileLevels = ProfileLevel.values();
        for (ProfileLevel currentProfileLevel : profileLevels) {
            if (currentProfileLevel.compare(levelId)) {
                return currentProfileLevel;
            }
        }
        return ProfileLevel.None;
    }

    int id;
}
