/*
 * Copyright (C) 2012 Erland Isaksson (erland@isaksson.info)
 * All rights reserved.
 */

package com.ickstream.protocol.service.content;

import com.ickstream.common.jsonrpc.MessageLogger;
import com.ickstream.protocol.common.DeviceStringMessageSender;
import com.ickstream.common.ickdiscovery.MessageSender;

public class DeviceContentService extends ContentService {
    public DeviceContentService(String id, MessageSender messageSender) {
        super(id,new DeviceStringMessageSender(id, messageSender));
    }

    public void setMessageLogger(MessageLogger messageLogger) {
        ((DeviceStringMessageSender)getMessageSender()).setMessageLogger(messageLogger);
    }
}
