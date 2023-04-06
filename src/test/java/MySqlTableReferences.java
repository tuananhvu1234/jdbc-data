/*MySQL
 
* Note : + {text} : bắt buộc phải có.
              + [text] : không bắt buộc.
              + -- : bỏ đi.
              + text(text) : - chỉ chọn phương án trong ().
                                   - chỉ áp dụng với những yêu cầu có nhiều lựa chọn.
              + || : hoặc.
              + *{ text }* : một khối (nhóm).
              + {text1 || text2 || ...} : bắt buộc phải có 1 đối tượng trong nhóm.

* tableReferences = escapedTableReference [, escapedTableReference] ...

* escapedTableReference = tableReference -- || { OJ tableReference }

* tableReference = tableFactor || joinedTable

* tableFactor = tableName [PARTITION (partitionNames)] [AS aliasName] [indexHintList]

* joinedTable = tableReference(tableFactor) {joinType} tableReference(tableFactor) joinSpecification

* joinType = *{ {innerOrCross} JOIN }* || *{ STRAIGHT_JOIN }*
                  || {leftOrRight} OUTER JOIN
                  || NATURAL { *{ innerOrCross(INNER) }*
                                       || *{ {leftOrRight} OUTER }* } JOIN

* innerOrCross = INNER || CROSS

* leftOrRight = LEFT || RIGHT

* joinSpecification = ON searchCondition -- || USING (joinColumnList)

* joinColumnList = columnName [, columnName] ...

* indexHintList = indexHint [, indexHint] ...

* indexHint = USE {hintElement} || { IGNORE || FORCE } {hintElement}

* hintElement = { INDEX || KEY } [FOR { JOIN || ORDER BY || GROUP BY }] (indexList)

* indexList = indexName [, indexName] ...


data set (data table (data row))
*/
