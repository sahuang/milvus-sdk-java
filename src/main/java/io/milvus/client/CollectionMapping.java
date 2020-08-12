/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package io.milvus.client;

import io.milvus.client.Index.Builder;
import java.util.List;
import java.util.Map;
import javax.annotation.Nonnull;

/** Represents a collection mapping */
// Builder Pattern
public class CollectionMapping {
  private final String collectionName;
  private final List<? extends Map<String, Object>> fields;
  private final String paramsInJson;

  private CollectionMapping(@Nonnull Builder builder) {
    collectionName = builder.collectionName;
    fields = builder.fields;
    paramsInJson = builder.paramsInJson;
  }

  public String getCollectionName() {
    return collectionName;
  }

  public List<? extends Map<String, Object>> getFields() {
    return fields;
  }

  public String getParamsInJson() {
    return paramsInJson;
  }

  @Override
  public String toString() {
    return String.format(
        "CollectionMapping = {collectionName = %s, fields = %s, params = %s}",
        collectionName, fields.toString(), paramsInJson);
  }

  /** Builder for <code>CollectionMapping</code> */
  public static class Builder {
    // Required parameters
    private final String collectionName;
    private List<? extends Map<String, Object>> fields;
    private String paramsInJson;

    /**
     * @param collectionName collection name
     */
    public Builder(@Nonnull String collectionName) {
      this.collectionName = collectionName;
    }

    /**
     * Build with fields. Example fields:
     * ` {"fields": [
     *      {"field": "A", "type": DataType.INT64, "index": {"name":"","type":"","params": {..}}},
     *      {"field": "B", "type": DataType.INT64},
     *      {"field": "C", "type": DataType.INT64},
     *      {"field": "Vec", "type": DataType.BINARY_VECTOR,
     *       "params": {"metric_type": MetricType.L2, "dim": 128}}
     *    ]}`
     *
     * @param fields a list of hashmap containing each field. A field must have key "field" and
     *               "type". A vector field must have "dim" in params. "params" should be in json
     *               format.
     * @return <code>Builder</code>
     */
    public Builder withFields(@Nonnull List<? extends Map<String, Object>> fields) {
      this.fields = fields;
      return this;
    }

    /**
     * Build with extra params in json string format.
     *
     * @param paramsInJson can optionally include "segment_row_count", merge will be triggered if
     *                     more than this number of rows inserted.
     * @return <code>Builder</code>
     */
    public Builder withParamsInJson(String paramsInJson) {
      this.paramsInJson = paramsInJson;
      return this;
    }

    public CollectionMapping build() {
      return new CollectionMapping(this);
    }
  }
}
