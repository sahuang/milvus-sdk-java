package io.milvus.client;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;

/**
 * Contains the returned <code>response</code> and either a <code>List</code> of <code>floatVectors
 * </code> or <code>
 * binaryVectors</code> for <code>getEntityByID</code>. If the id does not exist, both float and
 * binary vectors corresponding to the id will be empty.
 */
public class GetEntityByIDResponse {
  private final Response response;
  private List<Long> validIds;
  private List<Map<String, Object>> fieldsMap;

  GetEntityByIDResponse(
      Response response, List<Long> validIds, List<Map<String, Object>> fieldsMap) {
    this.response = response;
    this.validIds = validIds;
    this.fieldsMap = fieldsMap;
  }

  /** @return A <code>List</code> of ids that are valid, i.e. present in your collections. This will
   * be a subset of the ids passed into <code>getEntityByID</code>.
   */
  public List<Long> getValidIds() { return validIds; }

  /**
   * @return A <code>List</code> of map with fields information. The list order corresponds
   * to <code>validIds</code>. The inner <code>Map</code> maps field names to records.
   * The record object can be one of int, long, float, double, List<Float> or ByteBuffer
   *   depending on the field's DataType you specified.
   */
  public List<Map<String, Object>> getFieldsMap() { return fieldsMap; }

  public Response getResponse() {
    return response;
  }

  /** @return <code>true</code> if the response status equals SUCCESS */
  public boolean ok() {
    return response.ok();
  }
}
