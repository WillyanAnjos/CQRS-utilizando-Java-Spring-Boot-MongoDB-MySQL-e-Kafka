package com.willyan.domain.message;

import com.willyan.domain.enums.Event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Message<T> {

    private Event event;

    private T payload;
}
