package org.reflectionx.analyzer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Filter special classes when analyze class dependencies.
 *
 * @author Gabriel Zhang
 * @since 1.0
 */
public class ClassFilter {

    private final Set<String> excludes = new HashSet<>();

    public void setExcludes(String... classNames) {
        Arrays.stream(classNames).forEach(this::setExclude);
    }

    public void setExclude(String className) {
        excludes.add(className);
    }

    public Set<String> getExcludes() {
        return excludes;
    }
}
