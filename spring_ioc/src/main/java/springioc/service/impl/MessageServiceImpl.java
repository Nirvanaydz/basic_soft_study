package springioc.service.impl;

import springioc.service.MessageService;

public class MessageServiceImpl implements MessageService {
    @Override
    public String getMessage() {
        return "hello world";
    }
}
