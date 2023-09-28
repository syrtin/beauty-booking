package com.syrtin.beautybooking.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Table;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table("procedure_specialist")
public class ProcedureRef {
    @Id
    private Long id;

    private AggregateReference<Procedure, Long> procedureId;

}
