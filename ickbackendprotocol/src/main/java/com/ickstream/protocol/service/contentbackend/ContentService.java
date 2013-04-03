/*
 * Copyright (C) 2013 ickStream GmbH
 * All rights reserved
 */

package com.ickstream.protocol.service.contentbackend;

import com.ickstream.common.jsonrpc.JsonRpcError;
import com.ickstream.common.jsonrpc.JsonRpcErrors;
import com.ickstream.common.jsonrpc.JsonRpcParam;
import com.ickstream.common.jsonrpc.JsonRpcParamStructure;
import com.ickstream.protocol.common.data.ContentItem;
import com.ickstream.protocol.service.backend.CloudService;
import com.ickstream.protocol.service.backend.InvalidParameterException;
import com.ickstream.protocol.service.backend.UnauthorizedAccessException;
import com.ickstream.protocol.service.content.ContentResponse;
import com.ickstream.protocol.service.content.GetManagementProtocolDescriptionResponse;
import com.ickstream.protocol.service.content.GetProtocolDescriptionResponse;

import java.util.Map;

public interface ContentService extends CloudService {
    @JsonRpcErrors({
            @JsonRpcError(exception = InvalidParameterException.class, code = -32602, message = "Invalid method parameters"),
            @JsonRpcError(exception = UnauthorizedAccessException.class, code = -32000, message = "Unauthorized access")
    })
    public ContentItem getItem(@JsonRpcParam(name = "contextId", optional = true) String contextId, @JsonRpcParamStructure Map<String, String> parameters);

    @JsonRpcErrors({
            @JsonRpcError(exception = InvalidParameterException.class, code = -32602, message = "Invalid method parameters"),
            @JsonRpcError(exception = UnauthorizedAccessException.class, code = -32000, message = "Unauthorized access")
    })
    public ContentResponse findTopLevelItems(@JsonRpcParam(name = "offset", optional = true) Integer offset, @JsonRpcParam(name = "count", optional = true) Integer count);

    @JsonRpcErrors({
            @JsonRpcError(exception = InvalidParameterException.class, code = -32602, message = "Invalid method parameters"),
            @JsonRpcError(exception = UnauthorizedAccessException.class, code = -32000, message = "Unauthorized access")
    })

    public ContentResponse findItems(@JsonRpcParam(name = "offset", optional = true) Integer offset, @JsonRpcParam(name = "count", optional = true) Integer count, @JsonRpcParam(name = "contextId", optional = true) String contextId, @JsonRpcParamStructure Map<String, String> parameters);

    @JsonRpcErrors({
            @JsonRpcError(exception = InvalidParameterException.class, code = -32602, message = "Invalid method parameters"),
            @JsonRpcError(exception = UnauthorizedAccessException.class, code = -32000, message = "Unauthorized access")
    })
    public Boolean addItem(@JsonRpcParam(name = "contextId") String contextId, @JsonRpcParamStructure Map<String, String> parameters);

    @JsonRpcErrors({
            @JsonRpcError(exception = InvalidParameterException.class, code = -32602, message = "Invalid method parameters"),
            @JsonRpcError(exception = UnauthorizedAccessException.class, code = -32000, message = "Unauthorized access")
    })
    public Boolean removeItem(@JsonRpcParam(name = "contextId") String contextId, @JsonRpcParamStructure Map<String, String> parameters);

    @JsonRpcErrors({
            @JsonRpcError(exception = InvalidParameterException.class, code = -32602, message = "Invalid method parameters"),
            @JsonRpcError(exception = UnauthorizedAccessException.class, code = -32000, message = "Unauthorized access")
    })
    public GetProtocolDescriptionResponse getProtocolDescription(@JsonRpcParam(name = "offset", optional = true) Integer offset, @JsonRpcParam(name = "count", optional = true) Integer count);

    @JsonRpcErrors({
            @JsonRpcError(exception = InvalidParameterException.class, code = -32602, message = "Invalid method parameters"),
            @JsonRpcError(exception = UnauthorizedAccessException.class, code = -32000, message = "Unauthorized access")
    })
    public GetManagementProtocolDescriptionResponse getManagementProtocolDescription(@JsonRpcParam(name = "offset", optional = true) Integer offset, @JsonRpcParam(name = "count", optional = true) Integer count);
}
