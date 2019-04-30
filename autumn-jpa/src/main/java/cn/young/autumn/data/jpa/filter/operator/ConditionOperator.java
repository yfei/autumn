/*
 * Copyright (c) Mr.Yang 2016-2018 All Rights Reserved
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  
 *       http://www.apache.org/licenses/LICENSE-2.0
 *       
 *  Unless required by applicable law or agreed to in writing, software 
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and 
 *  limitations under the License.
 *  
 */

package cn.young.autumn.data.jpa.filter.operator;


public enum ConditionOperator {

    AND("(%0) and (%1)"),
    OR("(%0) or (%1)"),
    EQUAL("%0=%1"),
    NOT_EQUAL("%0<>%1"),
    IN("%0 in (%1)"),
    NOT_IN("%0 not in (1%)"),
    BETWEEN("%0 between %1 and %2"),
    EXISTS("%0 exists (%1)"),
    GT("%0 > (%1)"),
    GT_E("%0 >= (%1)"),
    LT("%0 < (%1)"),
    LT_E("%0 <= (%1)"),
    LIKE("%0 like %1"),;

    private String value;

    ConditionOperator(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static void main(String[] args) {
        System.out.print(ConditionOperator.valueOf(">=").getValue());
    }
}
