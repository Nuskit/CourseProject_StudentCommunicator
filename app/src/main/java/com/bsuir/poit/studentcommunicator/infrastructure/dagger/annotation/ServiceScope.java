package com.bsuir.poit.studentcommunicator.infrastructure.dagger.annotation;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ServiceScope {
}
