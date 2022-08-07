package com.isakov.emcore.job.converters;

public interface GenericConverter<T,S> {
    S convert(T t);
}
