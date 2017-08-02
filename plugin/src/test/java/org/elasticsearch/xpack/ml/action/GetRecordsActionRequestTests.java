/*
 * Copyright Elasticsearch B.V. and/or licensed to Elasticsearch B.V. under one
 * or more contributor license agreements. Licensed under the Elastic License;
 * you may not use this file except in compliance with the Elastic License.
 */
package org.elasticsearch.xpack.ml.action;

import org.elasticsearch.common.xcontent.XContentParser;
import org.elasticsearch.test.AbstractStreamableXContentTestCase;
import org.elasticsearch.xpack.ml.action.GetRecordsAction.Request;
import org.elasticsearch.xpack.ml.action.util.PageParams;

public class GetRecordsActionRequestTests extends AbstractStreamableXContentTestCase<Request> {

    @Override
    protected Request doParseInstance(XContentParser parser) {
        return GetRecordsAction.Request.parseRequest(null, parser);
    }

    @Override
    protected Request createTestInstance() {
        Request request = new Request(randomAlphaOfLengthBetween(1, 20));
        if (randomBoolean()) {
            String start = randomBoolean() ? randomAlphaOfLengthBetween(1, 20) : String.valueOf(randomNonNegativeLong());
            request.setStart(start);
        }
        if (randomBoolean()) {
            String end = randomBoolean() ? randomAlphaOfLengthBetween(1, 20) : String.valueOf(randomNonNegativeLong());
            request.setEnd(end);
        }
        if (randomBoolean()) {
            request.setSort(randomAlphaOfLengthBetween(1, 20));
        }
        if (randomBoolean()) {
            request.setDescending(randomBoolean());
        }
        if (randomBoolean()) {
            request.setRecordScore(randomDouble());
        }
        if (randomBoolean()) {
            request.setExcludeInterim(randomBoolean());
        }
        if (randomBoolean()) {
            int from = randomInt(PageParams.MAX_FROM_SIZE_SUM);
            int maxSize = PageParams.MAX_FROM_SIZE_SUM - from;
            int size = randomInt(maxSize);
            request.setPageParams(new PageParams(from, size));
        }
        return request;
    }

    @Override
    protected boolean supportsUnknownFields() {
        return false;
    }

    @Override
    protected Request createBlankInstance() {
        return new Request();
    }

}
