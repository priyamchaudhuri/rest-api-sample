package org.tat.api.library.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.tat.api.core.filter.Embed;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
@RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json", produces = {MediaType.APPLICATION_JSON_VALUE})
@Embed
public @interface GetJSON {
	@AliasFor(annotation=RequestMapping.class, attribute="path")
	String[] path() default {};
}
