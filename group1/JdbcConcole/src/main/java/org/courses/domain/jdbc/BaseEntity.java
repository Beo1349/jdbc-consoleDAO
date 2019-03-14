package org.courses.domain.jdbc;

import javax.persistence.Entity;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public abstract class BaseEntity {
    protected String tableName = null;
    protected Collection<Column> columns = null;
    public void setTable(String n){tableName = n;}
    public String getName() {
        if (null == tableName) {
            Class _class = this.getClass();
            Entity jpaEntity = (Entity)_class.getAnnotation(Entity.class);
            tableName = jpaEntity.name();
        }
        return tableName;
    }
    public Collection<Column> getColumns() {
        if (null == columns) {
            Class _class = this.getClass();
            columns = new ArrayList<>();
            for (Field field : _class.getDeclaredFields()) {
                javax.persistence.Column jpaColumn = field.getAnnotation(javax.persistence.Column.class);
                if (null == jpaColumn)
                    continue;
                else if(jpaColumn.name().equals("id"))
                    continue;
                Column definition = new Column();
                definition.setName(jpaColumn.name());

                definition.setType(field.getType());

                    columns.add(definition);

            }
            columns = Collections.unmodifiableCollection(columns);
        }
        return columns;
    }

    public Object getColumn(Column definition) throws IllegalAccessException {
        Class _class = this.getClass();
        for (Field field : _class.getDeclaredFields()) {
            javax.persistence.Column jpaColumn = field.getAnnotation(javax.persistence.Column.class);
            if (null == jpaColumn
                    || !jpaColumn.name().equals(definition.getName()))
                continue;

            if (!field.isAccessible())
                field.setAccessible(true);
            return field.get(this);
        }
        return null;
    }

    public void setColumn(Column definition, Object value) {

    }
}
