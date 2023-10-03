package com.syrtin.beautybooking.model;

import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.HashSet;
import java.util.Set;

@Table("specialist")
public class Specialist {

    @Id
    private final Long id;

    @NonNull
    private final String name;

    @NonNull
    private final String phone;

    @Setter
    @MappedCollection(idColumn = "specialist_id", keyColumn = "procedure_id")
    private Set<ProcedureRef> procedureSet = new HashSet<>();

    @PersistenceCreator
    public Specialist(Long id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public Specialist(String name, String phone) {
        this(null, name, phone);
    }

    public Specialist() {
        this(null, null, null);
    }

    /**
     * The aggregate root should take care of whatever logic is necessary to add a procedure.
     */
    public void addProcedure(Procedure procedure) {
        final ProcedureRef ref = new ProcedureRef(null, AggregateReference.to(procedure.getId()));
        procedureSet.add(ref);
    }

    public void removeProcedure(Procedure procedure) {
        procedureSet.removeIf(ref -> ref.getProcedureId().getId().equals(procedure.getId()));
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public Set<ProcedureRef> getProcedureSet() {
        return procedureSet;
    }

    @Override
    public String toString() {
        return "Specialist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", procedureSet=" + procedureSet +
                '}';
    }
}