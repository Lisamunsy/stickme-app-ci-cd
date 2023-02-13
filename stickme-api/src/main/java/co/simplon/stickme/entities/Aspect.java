package co.simplon.stickme.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "aspects")
public class Aspect extends AbstractEntity {

    @Column(name = "name", updatable = false)
    private String name;

    @Column(name = "logical_order", updatable = false)
    private int logicalOrder;

    public Aspect() {
	// Required no-arg constructor
    }

    public String getName() {
	return name;
    }

    @SuppressWarnings("unused")
    private void setName(String name) {
	// Not updatable
	this.name = name;
    }

    public int getLogicalOrder() {
	return logicalOrder;
    }

    @SuppressWarnings("unused")
    private void setLogicalOrder(int logicalOrder) {
	// Not updatable
	this.logicalOrder = logicalOrder;
    }

    @Override
    public String toString() {
	return String.format("{id=%s, name=%s, logicalOrder=%s}", getId(), name,
		logicalOrder);
    }
}
