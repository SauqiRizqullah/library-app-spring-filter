package com.example.spring_filter.utils;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class AdminCustomId implements IdentifierGenerator {

    @Override
    public Object generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) {
        String prefix = "A";
        String query = "SELECT COALESCE(MAX(CAST(SUBSTRING(bonus_id, 2) AS INTEGER)), 0) FROM m_admins";
        Integer max = (Integer) sharedSessionContractImplementor.createNativeQuery(query).getSingleResult();
        int nextId = (max == null ? 1 : max + 1);
        return prefix + String.format("%03d", nextId);
    }
}
