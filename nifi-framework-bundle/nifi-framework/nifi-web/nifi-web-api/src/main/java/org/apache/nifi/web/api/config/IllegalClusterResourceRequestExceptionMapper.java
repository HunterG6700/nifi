/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.nifi.web.api.config;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.apache.nifi.util.StringUtils;
import org.apache.nifi.web.IllegalClusterResourceRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Maps illegal cluster resource request exceptions into client responses.
 */
@Provider
public class IllegalClusterResourceRequestExceptionMapper implements ExceptionMapper<IllegalClusterResourceRequestException> {

    private static final Logger logger = LoggerFactory.getLogger(IllegalClusterResourceRequestExceptionMapper.class);

    @Override
    public Response toResponse(IllegalClusterResourceRequestException exception) {
        // log the error
        logger.info("{}. Returning {} response.", exception, Response.Status.NOT_FOUND);

        if (logger.isDebugEnabled()) {
            logger.debug(StringUtils.EMPTY, exception);
        }

        return Response.status(Response.Status.NOT_FOUND).entity(exception.getMessage()).build();
    }

}
