
package com.ipuc.web.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author wilson-rivera
 */
@Target({ElementType.TYPE, ElementType.METHOD, })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Secured {
    String role() default "";
}
