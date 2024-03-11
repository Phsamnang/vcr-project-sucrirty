package com.kosign.vcrprojectsecurity.enums;

public enum AttendanceStatus {
    PRESENT("Present"),
    ABSENT("Absent"),
    LATE("Late"),
    EXCUSED("Excused"),
    WORK_FROM_HOME("Work From Home"),
    VACATION("Vacation"),
    SICK_LEAVE("Sick Leave"),
    OTHER("Other");

    private final String description;

    AttendanceStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
