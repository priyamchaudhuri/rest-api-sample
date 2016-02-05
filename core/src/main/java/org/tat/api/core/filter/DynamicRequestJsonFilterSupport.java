package org.tat.api.core.filter;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.ser.BeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;

@SuppressWarnings("deprecation")
public class DynamicRequestJsonFilterSupport {
	public static final String DYNAMIC_FILTER_ID = "___DYNAMIC_FILTER";

    private ThreadLocal<Set<String>> filterFields;
    private DynamicIntrospector dynamicIntrospector;
    private DynamicFilterProvider dynamicFilterProvider;

    public DynamicRequestJsonFilterSupport() {
        filterFields = new ThreadLocal<Set<String>>();
        dynamicFilterProvider = new DynamicFilterProvider(filterFields);
        dynamicIntrospector = new DynamicIntrospector();
    }

    public FilterProvider getFilterProvider() {
        return dynamicFilterProvider;
    }

    public AnnotationIntrospector getAnnotationIntrospector() {
        return dynamicIntrospector;
    }

    public void setFilterFields(Set<String> fieldsToFilter) {
        filterFields.set(Collections.unmodifiableSet(new HashSet<String>(fieldsToFilter)));
    }

    public void setFilterFields(String... fieldsToFilter) {
        filterFields.set(Collections.unmodifiableSet(new HashSet<String>(Arrays.asList(fieldsToFilter))));
    }

    public void clear() {
        filterFields.remove();
    }

    public static class DynamicIntrospector extends JacksonAnnotationIntrospector {

		private static final long serialVersionUID = 1L;

		@Override
        public Object findFilterId(Annotated annotated) {
            Object result = super.findFilterId(annotated);
            if (result != null) {
                return result;
            } else {
                return DYNAMIC_FILTER_ID;
            }
        }
    }

    public static class DynamicFilterProvider extends FilterProvider {

        private ThreadLocal<Set<String>> filterFields;

        public DynamicFilterProvider(ThreadLocal<Set<String>> filterFields) {
            this.filterFields = filterFields;
        }

        @Override
        public BeanPropertyFilter findFilter(Object filterId) {
            return null;
        }

        @Override
        public PropertyFilter findPropertyFilter(Object filterId, Object valueToFilter) {
            if (filterId.equals(DYNAMIC_FILTER_ID) && filterFields.get() != null) {
                return SimpleBeanPropertyFilter.filterOutAllExcept(filterFields.get());
            }
            return super.findPropertyFilter(filterId, valueToFilter);
        }
    }
}
