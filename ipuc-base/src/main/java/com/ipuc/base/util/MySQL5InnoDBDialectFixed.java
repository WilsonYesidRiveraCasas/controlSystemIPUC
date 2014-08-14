package com.ipuc.base.util;

import java.sql.Types;

import org.hibernate.dialect.MySQL5InnoDBDialect;

/**
 *
 * @author German Escobar
 */
public class MySQL5InnoDBDialectFixed extends MySQL5InnoDBDialect {

    public MySQL5InnoDBDialectFixed() {
        super();
        registerColumnType(Types.BOOLEAN, "bit(1)");
    }
}
