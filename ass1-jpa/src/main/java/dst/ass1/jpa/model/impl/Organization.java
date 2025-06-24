package dst.ass1.jpa.model.impl;

import dst.ass1.jpa.model.IEmployment;
import dst.ass1.jpa.model.IOrganization;
import dst.ass1.jpa.model.IVehicle;
import dst.ass1.jpa.util.Constants;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Organization entity implementation with bidirectional many-to-many partOf relationship.
 */
@Entity
@Table(name = Constants.T_ORGANIZATION)
public class Organization implements IOrganization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = Constants.M_ORGANIZATION_NAME, nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(
        name = Constants.J_ORGANIZATION_PARTS,
        joinColumns = @JoinColumn(name = Constants.I_ORGANIZATION_PART_OF),
        inverseJoinColumns = @JoinColumn(name = Constants.I_ORGANIZATION_PARTS)
    )
    private Collection<IOrganization> parts = new ArrayList<>();

    @ManyToMany(mappedBy = "parts")
    private Collection<IOrganization> partOf = new ArrayList<>();

    @OneToMany(mappedBy = "id.organization", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<IEmployment> employments = new ArrayList<>();

    @ManyToMany
    @JoinTable(
        name = Constants.J_ORGANIZATION_VEHICLE,
        joinColumns = @JoinColumn(name = Constants.I_ORGANIZATION),
        inverseJoinColumns = @JoinColumn(name = Constants.I_VEHICLES)
    )
    private Collection<IVehicle> vehicles = new ArrayList<>();

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Collection<IOrganization> getParts() {
        return parts;
    }

    @Override
    public void setParts(Collection<IOrganization> parts) {
        this.parts = parts;
    }

    @Override
    public void addPart(IOrganization part) {
        if (part != null) {
            this.parts.add(part);
            part.getPartOf().add(this);
        }
    }

    @Override
    public Collection<IOrganization> getPartOf() {
        return partOf;
    }

    @Override
    public void setPartOf(Collection<IOrganization> partOf) {
        this.partOf = partOf;
    }

    @Override
    public void addPartOf(IOrganization partOf) {
        if (partOf != null) {
            this.partOf.add(partOf);
            partOf.getParts().add(this);
        }
    }

    @Override
    public Collection<IEmployment> getEmployments() {
        return employments;
    }

    @Override
    public void setEmployments(Collection<IEmployment> employments) {
        this.employments = employments;
    }

    @Override
    public void addEmployment(IEmployment employment) {
        if (employment != null) {
            this.employments.add(employment);
            // Set this organization in the employment key if not already set
            if (employment.getId() != null && employment.getId().getOrganization() == null) {
                employment.getId().setOrganization(this);
            }
        }
    }

    @Override
    public Collection<IVehicle> getVehicles() {
        return vehicles;
    }

    @Override
    public void setVehicles(Collection<IVehicle> vehicles) {
        this.vehicles = vehicles;
    }

    @Override
    public void addVehicle(IVehicle vehicle) {
        if (vehicle != null) {
            this.vehicles.add(vehicle);
        }
    }
}
