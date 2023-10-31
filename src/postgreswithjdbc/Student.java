package postgreswithjdbc;

import java.time.LocalDateTime;

class Student {
    int rollID;
    String name;
    String section;
    LocalDateTime createdDate;

    public Student() {
        rollID = -1;
        name = "";
        section = "";
    }

    public void setRoll(int rollID) {
        this.rollID = rollID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public int getRoll() {
        return this.rollID;
    }

    public String getName() {
        return this.name;
    }

    public String getSection() {
        return this.section;
    }

    public LocalDateTime getCreatedDate() {
        return this.createdDate;
    }
}
