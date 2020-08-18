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

import java.util.Map;
import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

/** Contains parameters for <code>insert</code> */
public class InsertParam {
  private final String collectionName;
  private final List<? extends Map<String, Object>> fields;
  private final List<Long> entityIds;
  private final String partitionTag;

  private InsertParam(@Nonnull Builder builder) {
    this.collectionName = builder.collectionName;
    this.fields = builder.fields;
    this.entityIds = builder.entityIds;
    this.partitionTag = builder.partitionTag;
  }

  public String getCollectionName() {
    return collectionName;
  }

  public List<? extends Map<String, Object>> getFields() { return fields; }

  public List<Long> getEntityIds() {
    return entityIds;
  }

  public String getPartitionTag() {
    return partitionTag;
  }

  /** Builder for <code>InsertParam</code> */
  public static class Builder {
    // Required parameter
    private final String collectionName;

    // Optional parameters - initialized to default values
    private List<? extends Map<String, Object>> fields = new ArrayList<>();
    private List<Long> entityIds = new ArrayList<>();
    private String partitionTag = "";

    /** @param collectionName collection to insert entities to */
    public Builder(@Nonnull String collectionName) {
      this.collectionName = collectionName;
    }

    /**
     * The data you wish to insert into collections. Default to an empty <code>ArrayList</code>
     *
     * @param fields a <code>List</code> of <code>Map</code> that contains data to insert for each
     *     field name. "field", "values" and "type" must be present in each map. The size of
     *     map["values"] must match for all maps in the list, which is equivalent to entity count.
     *     Example fields:
     *     <pre>
     * <code>
     *   [
     *         {"field": "A", "values": A_list, "type": DataType.INT32},
     *         {"field": "B", "values": B_list, "type": DataType.INT32},
     *         {"field": "C", "values": C_list, "type": DataType.INT64},
     *         {"field": "Vec", "values": vecs, "type": DataType.FLOAT_VECTOR}
     *   ]
     * </code>
     * </pre>
     *
     * @return <code>Builder</code>
     */
    public Builder withFields(@Nonnull List<? extends Map<String, Object>> fields) {
      this.fields = fields;
      return this;
    }

    /**
     * Optional. Default to an empty <code>ArrayList</code>. Only needed when entity ids are not
     * auto-generated by milvus. This is specified when creating collection.
     *
     * @param entityIds a <code>List</code> of ids associated with the entities to insert.
     * @return <code>Builder</code>
     */
    public Builder withEntityIds(@Nonnull List<Long> entityIds) {
      this.entityIds = entityIds;
      return this;
    }

    /**
     * Optional. Default to an empty <code>String</code>
     *
     * @param partitionTag partition tag
     * @return <code>Builder</code>
     */
    public Builder withPartitionTag(@Nonnull String partitionTag) {
      this.partitionTag = partitionTag;
      return this;
    }

    public InsertParam build() {
      return new InsertParam(this);
    }
  }
}
