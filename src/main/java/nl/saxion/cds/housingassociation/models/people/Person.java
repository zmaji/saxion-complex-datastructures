package nl.saxion.cds.housingassociation.models.people;

public abstract class Person {
    private final Long ID;
    private String name;
    private String initials;

    public Person(Long ID) {
        this.ID = ID;
    }

    public Long getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    @Override
    public String toString() {
        return "ID: " + this.ID + ", name: " + this.name + ", initials: " + this.initials;
    }
}
